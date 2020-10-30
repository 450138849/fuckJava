>  windows下AIO比NIO效率高，在LINUX下AIO只是NIO的封装，NIO效率更高

CompletionHandler使用了模板方法模式实现，serverSocketChannel.accept中传入的CompletionHandler实现complete方法表示接收成功之后执行的操作，即为继续accept和读取buffer当中的内容，client.read()中传入CompletionHandler表示客户端读取成功的时候会调用completed方法来处理，即为读取文件的具体逻辑。

# 服务端代码--不使用线程池

```java
package cn.fkJava.test.testio.NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试AIO
 */
public class TestAIO {
    public static void main(String[] args) throws IOException {
        new TestAIO().testAIOMethod();
    }

    /**
     * 服务端--单线程
     */
    @Test
    public void testAIOMethod() throws IOException {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8090));
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Void attachment) {
                serverSocketChannel.accept(null, this);

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                client.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer byteBuffer) {
//                        client.read(byteBuffer, byteBuffer, this);// 注意这里如果使用这一句就会嵌套读取
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array()));
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("网络异常");
                exc.printStackTrace();
            }
        });
        // 一直阻塞 不让服务器停止，真实环境是在tomcat下运行，所以不需要这行代码,注意是要让整个方法阻塞，不要写在completed里边
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

# 服务端代码--使用线程池

```java
    /**
     * 服务端--多线程
     */
    @Test
    public void testAIOMethod2() throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(pool, 5);
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(threadGroup).bind(new InetSocketAddress(8090));
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Void attachment) {
                serverSocketChannel.accept(null, this);

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                client.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer byteBuffer) {
//                        client.read(byteBuffer, byteBuffer, this);
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array()));
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("网络异常");
                exc.printStackTrace();
            }
        });
        // 一直阻塞 不让服务器停止，真实环境是在tomcat下运行，所以不需要这行代码
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```



# 客户端代码

```java
  /**
     * 客户端
     */
    @Test
    public void testNIOClient() throws IOException {
        // 获取通道
        AsynchronousSocketChannel sc = AsynchronousSocketChannel.open();
        sc.connect(new InetSocketAddress("127.0.0.1", 8090));
        FileChannel fc = FileChannel.open(Paths.get("test1.txt"), StandardOpenOption.READ);

        // 创建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // 读取文件并发送
        while (fc.read(buf) != -1) {
            buf.flip();// 转换成写模式
            sc.write(buf);
            buf.clear();
        }
        // 关闭通道
        sc.close();
        fc.close();
    }

```

