package cn.fkJava.test.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInet {
    @Test
    public void testInet() throws Exception {
        InetAddress inet = InetAddress.getByName("localhost");
        InetAddress inet2 = InetAddress.getByName("192.168.1.1");
        InetAddress inet3 = InetAddress.getLocalHost();


        System.out.println(inet);// localhost/127.0.0.1 通过域名
        System.out.println(inet2);// /192.168.1.1 通过ip
        System.out.println(inet3);// DESKTOP-NAQ7MAF/192.168.2.225
        System.out.println(inet.getHostName());// localhost
        System.out.println(inet.getHostAddress());// 127.0.0.1
    }
}
