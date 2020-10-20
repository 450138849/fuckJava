# 1.NIO与传统IO的区别

| 传统IO | NIO        |
| ------ | ---------- |
| 面向流 | 面向缓冲区 |
| 阻塞   | 非阻塞     |
| NULL   | 选择器     |

**NIO核心**

1. 通道(channel)
2. 缓冲区(buffer)
3. 选择器(selector)

## 重要概念

**Channel**

Channel和IO中的Stream(流)是差不多一个等级的。只不过Stream是单向的，譬如：InputStream, OutputStream.而Channel是双向的，既可以用来进行读操作，又可以用来进行写操作。
NIO中的Channel的主要实现有：

- FileChannel
- DatagramChannel
- SocketChannel
- ServerSocketChannel

**Buffer**

NIO中的关键Buffer实现有：ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer, ShortBuffer，分别对应基本数据类型: byte, char, double, float, int, long, short。当然NIO中还有MappedByteBuffer, HeapByteBuffer, DirectByteBuffer等这里先不进行陈述。

**Selector**

Selector运行单线程处理多个Channel，如果你的应用打开了多个通道，但每个连接的流量都很低，使用Selector就会很方便。例如在一个聊天服务器中。要使用Selector, 得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新的连接进来、数据接收等。

# 2.缓冲区

获取缓冲区:

allocate()

缓冲区两个核心方法：

1. put()
2. get()

四个核心属性：

1. capacity
2. limit--写模式中limit=capacity,读模式中limit为文件内容长度
3. position
4. mark



常用方法

flip()

rewind()

clear()--清空只是重置核心属性，数据被"遗忘"但并没有清空

reset()--通过reset可以恢复mark的位置

hasRemaining()--获取缓冲区中剩余可操作性容量

array()--返回缓冲区的字节数组

> buffer创建的时候为写模式，write之前要转化成读模式

**用例**

1. 获取一个缓冲区
2. 向缓冲区写入数据
3. 获取缓冲区的核心属性
4. 从缓冲区读数据
5. 重读
6. 清空缓冲区并查看核心属性和数据

**直接缓冲区与非直接缓冲区**

非直接缓冲区:缓冲区在jvm内存中

直接缓冲区：缓冲区在物理内存中,可以提高效率

**利弊**

利：直接缓冲区不需要物理磁盘->内核缓存->用户缓存->程序,而是使用内存映射文件的方式，读写速度加快

弊：直接缓冲区创建和销毁的消耗大于非直接缓冲区，而且独立于常规的gc之外，文件实际何时落盘不可控

> FileChannel实际上就是使用map方法获取了直接缓冲区，通过直接缓冲区写文件的时候可以不用通过channel进行操作，直接可以对缓冲区进行操作

**用直接缓冲区直接读写文件**

```java
ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
byte[] bytes = new byte[buffer.limit()];
buffer.put(bytes);
buffer.get();
```

**缓冲区创建**

```java
public class TestBuffer {
    /**
     * 非直接缓冲区
     */
    @Test
    public void method1() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
    }

    /**
     * 直接缓冲区
     */
    @Test
    public void method2() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
    }
}
```

# 3.channel原理与获取

> 使用channel直接连接内存与缓冲区

**重要实现类**

1. FileChannel
2. SocketChannel
3. ServerSocketChannel
4. DatagramChannel
5. RandomAccessFile

**channel获取**

1. 针对支持通道的类可以使用getChannel()方法获取
2. JDK1.7针对各个通道提供了**静态方法**open()
3. JDK1.7之后通过Files工具类的newByteChannel()获取channel

# 4.Selector选择器

**Selector的创建：**

`Selector selector = Selector.open();`

>  与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为**FileChannel不能切换到非阻塞模式。而套接字通道都可以。**

**注册**

`ssc.register(selector, SelectionKey.OP_ACCEPT);`

**事件**

其中第二个参数SelectionKey这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：

1. connect
2. read
3. write
4. accept

这四种事件用SelectionKey的四个常量来表示：

1. SelectionKey.OP_CONNECT

2. SelectionKey.OP_ACCEPT

3. SelectionKey.OP_READ

4. SelectionKey.OP_WRITE

**SelectionKey**

当向Selector注册Channel时，register()方法会返回一个SelectionKey对象。这个对象包含了一些你感兴趣的属性：

- interest集合
- ready集合
- Channel
- Selector
- 附加的对象（可选）

**interest集合**：就像向Selector注册通道一节中所描述的，interest集合是你所选择的感兴趣的事件集合。可以通过SelectionKey读写interest集合。

**ready 集合**：ready 集合是通道已经准备就绪的操作的集合。在一次选择(Selection)之后，你会首先访问这个ready set。Selection将在下一小节进行解释。可以这样访问ready集合：

从SelectionKey访问**Channel**和**Selector**很简单。如下：

```java
Channel  channel  = selectionKey.channel();
Selector selector = selectionKey.selector();
```

可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。例如，可以附加 与通道一起使用的Buffer，或是包含聚集数据的某个对象。使用方法如下：

```java
selectionKey.attach(theObject);
Object attachedObj = selectionKey.attachment();
```

还可以在用register()方法向Selector注册Channel的时候附加对象。如：

```java
SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
```

# 5.通道的数据传输和内存映射文件

## 5.1使用非直接缓冲区复制文件

## 5.2使用直接缓冲区复制文件

## 5.3通道之间的数据传输

transferTo和transferFrom两个方法也是使用的直接缓冲区

**transferFrom**

```java
    public static void method1(){
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try
        {
            fromFile = new RandomAccessFile("src/fromFile.xml","rw");
            FileChannel fromChannel = fromFile.getChannel();
            toFile = new RandomAccessFile("src/toFile.txt","rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            System.out.println(count);
            toChannel.transferFrom(fromChannel, position, count);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(fromFile != null){
                    fromFile.close();
                }
                if(toFile != null){
                    toFile.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
```
**transferTo**

```java
public static void method2()
{
    RandomAccessFile fromFile = null;
    RandomAccessFile toFile = null;
    try
    {
        fromFile = new RandomAccessFile("src/fromFile.txt","rw");
        FileChannel fromChannel = fromFile.getChannel();
        toFile = new RandomAccessFile("src/toFile.txt","rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        System.out.println(count);
        fromChannel.transferTo(position, count,toChannel);
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    finally{
        try{
            if(fromFile != null){
                fromFile.close();
            }
            if(toFile != null){
                toFile.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
```

# 6.分散读取和聚集写入

> 利用通道进行read和write操作的时候，可以直接传入缓冲区数组bytebuffer[]进行分散读取和聚集写入

# 7.字符集

字符串->字节数组

字节数组->字符串

**步骤**

1. 加载字符集Charset.forname()
2. 获取编码器
3. 获取解码器
4. 编码
5. 解码

# 8.阻塞与非阻塞

**阻塞**  传统的 IO 流都是阻塞式的。也就是说，当一个线程调用 read() 或 write()时，该线程被阻塞，直到有一些数据被读取或写入，该线程在此期间不能执行其他任务。因此，在完成网络通信进行 IO 操作时，由于线程会阻塞，所以服务器端必须为每个客户端都提供一个独立的线程进行处理，当服务器端需要处理大量客户端时，性能急剧下降。

**非阻塞**Java NIO 是非阻塞模式的。当线程从某通道进行读写数据时，若没有数据可用时，该线程可以进行其他任务。线程通常将非阻塞 IO 的空闲时间用于在其他通道上执行 IO 操作，所以单独的线程可以管理多个输入和输出通道。因此，NIO 可以让服务器端使用一个或有限几个线程来同时处理连接到服务器端的所有客户端。

**网络通信实例如下**

## 8.1阻塞式NIO

```java
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
```

## 8.2非阻塞式NIO

```java
package cn.fkJava.test.testio.NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 非阻塞NIO--使用selector
 */
public class TestNIONetwork2 {
    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        // 1.获取通道
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8090));
        // 2.设置非阻塞模式
        sc.configureBlocking(false);
        // 3.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4.写入内容
        buffer.put("我是NIO客户端".getBytes());
        buffer.flip();
        sc.write(buffer);
        buffer.clear();
        // 5.关闭通道
        sc.close();
    }

    /**
     * 服务端
     */
    @Test
    public void server() throws IOException {
        // 1.获取通道，设置非阻塞模式
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        // 2.绑定地址
        ssc.bind(new InetSocketAddress(8090));
        // 3.获取选择器
        Selector s = Selector.open();
        // 4.通道注册到选择器上SELECTIONKEYS为事件的枚举类
        ssc.register(s, SelectionKey.OP_ACCEPT);
        // 5.选择器开始轮询
        while (true) {
            Iterator<SelectionKey> it = null;
            if (s.select() > 0) {
                it = s.selectedKeys().iterator();
            }
            // 6.对获取到的key进行轮询
            while (it.hasNext()) {
                SelectionKey key = it.next();
                // 7.对key进行判断
                if (key.isAcceptable()) {
                    // 8.获取客户端连接，切换为非阻塞状态
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socket = channel.accept();
                    socket.configureBlocking(false);
                    // 9.将客户端连接注册到选择器对应的读写事件上
                    socket.register(s, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 10.对读写事件进行反应
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    SocketChannel socket = (SocketChannel) key.channel();
                    int result;
                    while ((result = socket.read(buffer)) > 0) {
                        buffer.flip();// 这里由于下边用下标获取数据，所以不用转换模式没有关系
                        System.out.println(new String(buffer.array(), 0, result));
                        buffer.clear();
                        buffer.compact();
                    }
                }
                // 11.取消选择键
                it.remove();
            }
        }
    }
}
```

# 9.内存映射文件

**概述**

SocketChannel的读写是通过一个类叫ByteBuffer来操作的.这个类本身的设计是不错的,比直接操作byte[]方便多了. ByteBuffer有两种模式:直接/间接.间接模式最典型(也只有这么一种)的就是HeapByteBuffer,即操作堆内存 (byte[]).但是内存毕竟有限,如果我要发送一个1G的文件怎么办?不可能真的去分配1G的内存.这时就必须使用"直接"模式,即 MappedByteBuffer,文件映射.

**常用概念**

### 概念

FileChannel提供了map方法来把文件影射为内存映像文件： MappedByteBuffer map(int mode,long position,long size); 可以把文件的从position开始的size大小的区域映射为内存映像文件，mode指出了 可访问该内存映像文件的方式：

- READ_ONLY,（只读）： 试图修改得到的缓冲区将导致抛出 ReadOnlyBufferException.(MapMode.READ_ONLY)
- READ_WRITE（读/写）： 对得到的缓冲区的更改最终将传播到文件；该更改对映射到同一文件的其他程序不一定是可见的。 (MapMode.READ_WRITE)
- PRIVATE（专用）： 对得到的缓冲区的更改不会传播到文件，并且该更改对映射到同一文件的其他程序也不是可见的；相反，会创建缓冲区已修改部分的专用副本。 (MapMode.PRIVATE)

**MappedByteBuffer**

MappedByteBuffer是ByteBuffer的子类，其扩充了三个方法：

- force()：缓冲区是READ_WRITE模式下，此方法对缓冲区内容的修改强行写入文件；
- load()：将缓冲区的内容载入内存，并返回该缓冲区的引用；
- isLoaded()：如果缓冲区的内容在物理内存中，则返回真，否则返回假；

**用例**

这里通过采用ByteBuffer和MappedByteBuffer分别读取大小约为5M的文件"src/1.ppt"来比较两者之间的区别，method3()是采用MappedByteBuffer读取的，method4()对应的是ByteBuffer。

```java
   package cn.fkJava.test.testio.NIO;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestNIOMap {
    @Test
    public static void method4() throws IOException {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("src/1.ppt", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
            buff.clear();
            fc.read(buff);
            //System.out.println((char)buff.get((int)(aFile.length()/2-1)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public static void method3() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("src/1.ppt", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
            // System.out.println((char)mbb.get((int)(aFile.length()/2-1)));
            // System.out.println((char)mbb.get((int)(aFile.length()/2)));
            //System.out.println((char)mbb.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```

# 10.DatagramChannal

Java NIO中的DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。

**核心方法**

- open()
- send(buf)
- receive(buf)

```java
public static void  reveive(){
    DatagramChannel channel = null;
    try{
        channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(8888));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        channel.receive(buf);
        buf.flip();
        while(buf.hasRemaining()){
            System.out.print((char)buf.get());
        }
        System.out.println();
    }catch(IOException e){
        e.printStackTrace();
    }finally{
        try{
            if(channel!=null){
                channel.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
public static void send(){
    DatagramChannel channel = null;
    try{
        channel = DatagramChannel.open();
        String info = "I'm the Sender!";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.put(info.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf, new InetSocketAddress("10.10.195.115",8888));
        System.out.println(bytesSent);
    }catch(IOException e){
        e.printStackTrace();
    }finally{
        try{
            if(channel!=null){
                channel.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
```
# 11.pipe

Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

```java
package cn.fkJava.test.testio.NIO;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 使用管道进行数据传输
 */
public class TestPipe {
    @Test
    public void testPipe() throws IOException {
        // 1.获取管道
        Pipe pipe = Pipe.open();
        // 2.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 3.获取sink通道进行文件写出
        Pipe.SinkChannel sink = pipe.sink();
        buffer.put("张三".getBytes());
        buffer.flip();
        sink.write(buffer);
        buffer.clear();
        // 4.获取source通道进行文件读入
        Pipe.SourceChannel source = pipe.source();
        buffer.flip();
        System.out.println(new String(buffer.array(), 0, source.read(buffer)));
        // 5.关闭流
        sink.close();
        source.close();
    }
}
```

# 参考资料

NIO博客：https://blog.csdn.net/u011381576/article/details/79876754

