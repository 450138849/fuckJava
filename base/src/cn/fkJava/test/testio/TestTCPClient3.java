package cn.fkJava.test.testio;

import java.io.*;
import java.net.Socket;

/**
 * 使用线程来实现一个服务器端可以同时接收多个客户端的信息。
 */
public class TestTCPClient3 {
    private Socket socket = null;

    public TestTCPClient3() {
        try {
            socket = new Socket("localhost", 8090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        OutputStream os = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            br = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while (!"exit".equals(str = br.readLine())) {//注意这里比较字符串的方式，不要用==来进行比较
                pw.println(str);
                pw.flush();
            }
//            socket.shutdownOutput();//可以用这种方式结束输出,但是此处就会正常往后执行完程序退出所以不必要
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                br.close();
                os.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestTCPClient3 t1 = new TestTCPClient3();
        t1.start();
    }
}

