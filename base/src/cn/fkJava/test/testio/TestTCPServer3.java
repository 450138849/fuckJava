package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个服务器端可以同时接收多个客户端的信息。
 */
public class TestTCPServer3 {
    ServerSocket ss = null;

    public TestTCPServer3() {
        try {
            ss = new ServerSocket(8090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            Socket server = null;
            try {
                server = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TestClientHandler client = new TestClientHandler(server);
            new Thread(client).start();
        }
    }

    public static void main(String[] args) {
        new TestTCPServer3().start();
    }

    private class TestClientHandler implements Runnable {
        InputStream is = null;
        BufferedReader br = null;
        Socket server = null;

        public TestClientHandler(Socket server) {
            this.server = server;
        }

        @Override
        public void run() {
            try {
                is = server.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String str;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    is.close();
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


