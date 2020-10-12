package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestUDPClient {

    /**
     * 通过UDP协议发送数据
     */
    @Test
    public void testUDPClient() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress inet = InetAddress.getLocalHost();
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, inet, 5060);
            packet.setData("hello server".getBytes());
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    /**
     * UDPServer
     */
    @Test
    public void testUDPServer() {
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(5060);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
            server.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getData().length));// 这里如果不重新构造则打印对象哈希值
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}
