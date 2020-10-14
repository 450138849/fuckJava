package cn.fkJava.test.testio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池进行优化
 */
public class TestTCPServer5 {
    List<PrintWriter> pws = new ArrayList<PrintWriter>();
    ServerSocket ss = null;
    ExecutorService pool = null;

    public TestTCPServer5() {
        try {
            ss = new ServerSocket(8090);
            pool = Executors.newFixedThreadPool(40);//引入线程池
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

            TestClientHandler2 client = new TestClientHandler2(server);
            pool.execute(client);
        }
    }

    public static void main(String[] args) {
        new TestTCPServer5().start();
    }

    class TestClientHandler2 implements Runnable {
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        Socket server = null;

        public TestClientHandler2(Socket server) {
            this.server = server;
        }

        @Override
        public void run() {
            try {
                is = server.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String str;
                os = server.getOutputStream();
                pw = new PrintWriter(os);
                addPw(pw);

                while ((str = br.readLine()) != null) {
                    // 循环打印
                    sendMessage(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    is.close();
                    pw.close();
                    os.close();
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    removePw(pw);
                }
            }
        }

        private synchronized void addPw(PrintWriter pw) {
            pws.add(pw);
        }

        private synchronized void removePw(PrintWriter pw) {
            pws.remove(pw);
        }

        private synchronized void sendMessage(String str) {
            for (PrintWriter printWriter : pws) {
                printWriter.println(str);
                printWriter.flush();
            }
        }
    }

}


