package cn.fkJava.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCalendar {
    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTime());// Fri Sep 11 17:19:31 CST 2020
        // 获取不同字段
        System.out.println(c.get(Calendar.YEAR));// 2020
        System.out.println(c.get(Calendar.MONTH));// 8 月份从0开始
        System.out.println(c.get(Calendar.DAY_OF_MONTH));// 11
        System.out.println(c.get(Calendar.DAY_OF_WEEK));// 6 星期从星期天开始
        // 对日历字段进行操作
        c.add(Calendar.YEAR, 1);
        System.out.println(c.getTime());//Sat Sep 11 17:19:31 CST 2021 获取的是一个date格式的字符串
        System.out.println(c.getTime().getTime());//1631351971602 相当于date对象获取时间戳
        // 获取一小时以后的时间转换成时间戳方式方便存到数据库
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.HOUR, 1);
        System.out.println(calendar2.getTime());// Fri Sep 11 18:29:34 CST 2020
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println(sdf.format(calendar2.getTime()));// 2020-29-11 06:29:34
        String s = sdf.format(calendar2.getTime());
        Date date = sdf.parse(s);
        System.out.println(new java.sql.Date(date.getTime()));// 2020-01-11
    }
}
