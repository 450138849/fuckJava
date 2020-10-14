package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送一条信息，服务端接收
 */
public class TestTCPServer {
    private ServerSocket ss = null;

    public TestTCPServer() {
        try {
            this.ss = new ServerSocket(8090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Socket socket = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            socket = ss.accept();
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String str = null;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                is.close();
                socket.close();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestTCPServer server1 = new TestTCPServer();
        server1.start();
    }
}
