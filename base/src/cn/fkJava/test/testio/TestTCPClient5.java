package cn.fkJava.test.testio;

import java.io.*;
import java.net.Socket;

/**
 * 使用线程池进行优化，Client端没有修改
 */
public class TestTCPClient5 {
    private Socket socket = null;

    public TestTCPClient5() {
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
        new Thread(new ReceiveClient()).start();

        try {
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            br = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while (!"exit".equals(str = br.readLine())) {//注意这里比较字符串的方式，不要用==来进行比较
                pw.println(str);
                pw.flush();
            }

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

    /**
     * 用来接收消息的客户端
     */
    private class ReceiveClient implements Runnable {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        @Override
        public void run() {
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String str = null;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    isr.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TestTCPClient5 t1 = new TestTCPClient5();
        t1.start();
    }
}

