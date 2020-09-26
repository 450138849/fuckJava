package cn.fkJava.test.testio;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.io.*;
import java.util.Scanner;

public class TestFileWriter {
    @Test
    public void test() throws Exception {
        File file = new File("test1.txt");

        FileWriter fw = new FileWriter(file, true);
        fw.write("zhangsan\n");
        fw.write("lisi");

        fw.close();
    }

    /**
     * 文本文件的复制
     *
     * @throws Exception
     */
    @Test
    public void copyFile() throws Exception {
        File src = new File("test1.txt");
        File dest = new File("test2.txt");

        FileReader fr = new FileReader(src);
        FileWriter fw = new FileWriter(dest);

        int date;
        while ((date = fr.read()) != -1) {
            fw.write(date);
        }

        fr.close();
        fw.close();
    }

    /**
     * 非文本文件的复制
     */
    @Test
    public void copyByteFile() {
        File src = new File("testmov.avi");
        File dest = new File("testmov.avi.bak");
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用缓冲流复制非字符文件
     */
    @Test
    public void copyFileByBuffer() {
        File src = new File("testmov.avi");
        File dest = new File("testmov2.avi.bak");

        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 用缓冲流复制文本文件
     */
    @Test
    public void copyTextFileByBuffer() {
        File src = new File("test1.txt");
        File dest = new File("test1bak.txt");

        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            fr = new FileReader(src);
            fw = new FileWriter(dest);

            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);

            int length;
            char[] chars = new char[1024];
            while ((length = br.read(chars)) != -1) {
                bw.write(chars, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                br.close();
                fw.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 转换流
     */
    @Test
    public void transStream() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream("test1.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String str;
            char[] chars = new char[1024];
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打印流+系统流
     */
    @Test
    public void testPrintStream() {
        PrintStream pw = null;
        try {
            File file = new File("test3.txt");
            FileOutputStream fos = new FileOutputStream(file);
            pw = new PrintStream(fos, true);
            System.setOut(pw);
            System.out.println("zhangsan\nlisi\nwangwu");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * 系统流
     */
    @Test
    public void testSystemStream() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            String str;
            while (true) {
                str = br.readLine();
                if ((str.equals("e")) || (str.equals("exit"))) {
                    break;
                }
                System.out.println(str);
            }
            System.out.println("结束");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据流
     */
    @Test
    public void testDataStream() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            File file = new File("test1.txt");
            dos = new DataOutputStream(new FileOutputStream(file));
            dis = new DataInputStream(new FileInputStream(file));
            dos.writeUTF("zhangsan");
            dos.writeBoolean(true);
            String str = dis.readUTF();
            boolean flag = dis.readBoolean();
            System.out.println(str + '\n' + flag);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对象流序列化和反序列化
     */
    @Test
    public void testObjectStream() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            File file = new File("test.dat");
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(new String("i love Beijing!"));
            oos.flush();
            ois = new ObjectInputStream(new FileInputStream(file));
            System.out.println(ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试缓冲流的性能
     */
    @Test
    public void speedTest() {
        long time1 = System.currentTimeMillis();
        copyByteFile();
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        long time3 = System.currentTimeMillis();
        copyFileByBuffer();
        long time4 = System.currentTimeMillis();
        System.out.println(time4 - time3);
    }

    public static void main(String[] args) {
        new TestFileWriter().testSystemStream();
    }


}
