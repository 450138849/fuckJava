package cn.fkJava.test.testio;

import java.io.*;
import java.net.Socket;

/**
 * 客户端重复发送数据到服务器端的功能。即，用户可以在控制台不断输入内容，并将内容逐一发送给服务端。
 */
public class TestTCPClient2 {
    private Socket socket = null;

    public TestTCPClient2() {
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
                pw.flush();//这里要用flush将数据发送出去
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
        TestTCPClient2 t1 = new TestTCPClient2();
        t1.start();
    }
}

