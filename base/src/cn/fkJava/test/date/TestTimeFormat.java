package cn.fkJava.test.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTimeFormat {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat df = DateFormat.getTimeInstance();
        DateFormat df1 = DateFormat.getDateTimeInstance();
        DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println(df.format(date));// 下午12:51:44
        System.out.println(df1.format(date));// 2020年9月11日 下午12:52:30
        System.out.println(df2.format(date));// 2020年9月11日 下午04时41分35秒
        System.out.println(sdf.format(date));// 2020-09-11 04:44:45
    }
}
