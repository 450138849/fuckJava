package cn.fkJava.test.testio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端可以将用户的信息转发给所有客户端，并在每个客户端控制台上显示。
 */
public class TestTCPServer4 {
    List<PrintWriter> pws = new ArrayList<PrintWriter>();
    ServerSocket ss = null;

    public TestTCPServer4() {
        try {
            ss = new ServerSocket(8090);
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
            new Thread(client).start();
        }
    }

    public static void main(String[] args) {
        new TestTCPServer4().start();
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
                pws.add(pw);

                while ((str = br.readLine()) != null) {
                    System.out.println(pws.size());
                    // 循环打印
                    for (PrintWriter printWriter : pws) {
                        printWriter.println(str);
                        printWriter.flush();
                    }
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
                    pws.remove(pw);
                }
            }
        }
    }

}


