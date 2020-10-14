package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestUDP {
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
            DatagramPacket receivedPacket = new DatagramPacket(buf, 0, buf.length);
            socket.receive(receivedPacket);
            System.out.println(new String(receivedPacket.getData()));// this(bytes, 0, bytes.length);
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
            server = new DatagramSocket(5060);//Constructs a datagram socket and binds it to the specified port *on the local host machine.
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
            server.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getData().length));// 这里如果不重新构造则打印对象哈希值
            // 这里要根据发送过来的包进行地址解析，启动的时候只指定了server端的端口，IP未指定则为localhost
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            DatagramPacket sendPacket = new DatagramPacket(buf, 0, buf.length, address, port);
            sendPacket.setData("hello client".getBytes());
            server.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}
