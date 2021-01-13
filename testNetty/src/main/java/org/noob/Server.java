package org.noob;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Netty实现服务端--连接
 */
class Server {
    public static void main(String[] args) {
        new Server().test();
    }

    public void test() {
        ServerSocket ss;

        try {
            ss = new ServerSocket(8090);
            Socket socket = ss.accept();
            System.out.println("一个client连接上了!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
