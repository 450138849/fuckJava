package cn.fkJava.ooptest;


import java.text.DateFormat;
import java.util.Date;

public class TestDateFormat {
    public static void main(String[] args) {
        Date d = new Date();
        DateFormat df = DateFormat.getDateInstance();
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);

        System.out.println(df.format(d));// 2020年9月11日
        System.out.println(df1.format(d));// 2020/9/11
        System.out.println(df2.format(d));// 2020年9月11日
        System.out.println(df3.format(d));// 2020年9月11日
        System.out.println(df4.format(d));// 2020年9月11日星期五
    }
}
