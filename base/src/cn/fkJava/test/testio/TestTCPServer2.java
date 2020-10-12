package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端不断发送，服务端不断接收
 */
public class TestTCPServer2 {
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
