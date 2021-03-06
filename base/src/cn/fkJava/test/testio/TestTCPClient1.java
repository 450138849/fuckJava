package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * 使用Java的Socket实现客户端和服务器端之间的连接，并使客户端向服务端发送一条消息。
 */
public class TestTCPClient1 {
    private Socket socket = null;

    public TestTCPClient1() {
        try {
            socket = new Socket("localhost", 8090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("我是客户端2!");
        } catch (IOException e) {
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

    public static void main(String[] args) {
        TestTCPClient1 t1 = new TestTCPClient1();
        t1.start();
    }
}

