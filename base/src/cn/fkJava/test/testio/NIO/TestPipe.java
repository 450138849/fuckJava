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
