package cn.fkJava.test.testio.NIO;

import org.junit.Test;

import java.nio.ByteBuffer;

public class TestBuffer {
    /**
     * 非直接缓冲区
     */
    @Test
    public void method1() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
    }

    /**
     * 直接缓冲区--可以直接使用缓冲区对文件进行操作
     */
    @Test
    public void method2() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        byte[] bytes = new byte[buffer.limit()];
        buffer.put(bytes);
        buffer.get();
    }
}
