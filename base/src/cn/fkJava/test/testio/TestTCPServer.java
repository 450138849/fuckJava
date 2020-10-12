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
    @Test
    public void testServer() {
        ServerSocket ss = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            ss = new ServerSocket(8900);
            Socket server = ss.accept();
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
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
