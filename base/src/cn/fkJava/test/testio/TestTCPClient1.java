package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送一条信息，服务端回应
 */
public class TestTCPClient1 {
    @Test
    public void testClient() {
        Socket socket = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            socket = new Socket("localhost", 8900);
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                pw.println("你好！我是客户端");
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                os.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

