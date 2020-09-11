package cn.fkJava.ooptest;

import java.text.DateFormat;
import java.util.Date;

public class TestTimeFormat {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat df = DateFormat.getTimeInstance();
        DateFormat df1 = DateFormat.getDateTimeInstance();

        System.out.println(df.format(date));// 下午12:51:44
        System.out.println(df1.format(date));// 2020年9月11日 下午12:52:30
    }
}
