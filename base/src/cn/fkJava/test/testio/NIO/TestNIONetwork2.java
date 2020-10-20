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
                        buffer.flip();
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
