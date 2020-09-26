package cn.fkJava.test.testio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class TestFile {
    public static void main(String[] args) throws IOException {
        File file1 = new File("test1.txt");
        File file2 = new File("parent", "child");
        File file3 = new File(file2, "child2");
        File file4 = new File("base");

        System.out.println(file1.getAbsoluteFile());// E:\github\fuckJava\test1.txt
        System.out.println(file2.getAbsoluteFile());// E:\github\fuckJava\parent\child
        System.out.println(file3.getAbsoluteFile());// E:\github\fuckJava\parent\child\child2
        System.out.println(file1.getPath());// test1.txt
        System.out.println(file1.getParent());//null
        System.out.println(file1.getName());//test1.txt
        System.out.println(file1.length());//3
        System.out.println(new Date(file1.lastModified()));//Tue Sep 22 21:39:15 CST 2020
        System.out.println(Arrays.asList(file4.list()));// [.idea, base.iml, output, src]
        System.out.println(Arrays.asList(file4.listFiles()));// [base\.idea, base\base.iml, base\output, base\src]
        file1.isDirectory();
        file1.isFile();
        file1.exists();
        file1.canRead();
        file1.canWrite();
        file1.isHidden();
        file1.createNewFile();
        file1.mkdir();
        file1.mkdirs();
        file1.delete();
    }
}
