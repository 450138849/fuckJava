package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.*;

public class TestFileReader {
    @Test
    public void test() {
        FileReader fr = null;
        try {
            //生成文件
            File file = new File("test1.txt");
            //创建字符流
            fr = new FileReader(file);
            //对文件进行读取
            int data = fr.read();
            while (data != -1) {
                System.out.print((char) data);
                data = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
