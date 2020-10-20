package cn.fkJava.test.testio.NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞式NIO
 */
public class TestNIONetwork1 {
    /**
     * 客户端
     */
    @Test
    public void testNIOClient() throws IOException {
        // 获取通道
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8090));
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

    /**
     * 服务端
     */
    @Test
    public void testNIOServer() throws IOException {
        // 获取通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 使用写模式和创建模式打开
        FileChannel fc = FileChannel.open(Paths.get("testNIO1.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 创建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 绑定地址
        ssc.bind(new InetSocketAddress(8090));
        // 获取通道
        SocketChannel sc = ssc.accept();

        // 获取文件并写入磁盘
        while (sc.read(buf) != -1) {
            buf.flip();
            fc.write(buf);
            buf.clear();
        }
        sc.close();
        fc.close();
        ssc.close();
    }

}
