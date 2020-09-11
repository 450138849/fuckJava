package cn.fkJava.test.date;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);// Fri Sep 11 11:45:35 CST 2020
        System.out.println(date.getTime()); // 1599795935890
        Date date1 = new Date(1599795935890L);// 可以输入new类型的数值来初始化
        System.out.println(date1);// Fri Sep 11 11:45:35 CST 2020
        java.sql.Date date2 = new java.sql.Date(1599795935890L);// 是用sql.date可以直接获得年月日
        System.out.println(date2);// 2020-09-11   会调用date2的toString方法
        System.out.println(java.sql.Date.valueOf("2020-09-11").getTime());//1599753600000  string->date
        Time time = new Time(1599795935890L);
        System.out.println(time);// 11:45:35
        Timestamp ts = new Timestamp(1599795935890L);
        System.out.println(ts);// 2020-09-11 11:45:35.89
        System.out.println(Timestamp.valueOf("2020-09-11 11:45:35.89"));// 2020-09-11 11:45:35.89
    }
}
