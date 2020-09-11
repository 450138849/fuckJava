package cn.fkJava.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TestNumberFormat {
    public static void main(String[] args) {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormat format2 = NumberFormat.getCurrencyInstance();
        DecimalFormat format3 = new DecimalFormat("000.000");

        System.out.println(format.format(123.456));// 123.456
        System.out.println(format2.format(123.456));// ¥123.46 以默认环境的货币方式输出
        System.out.println(format3.format(12.34));// 按照指定格式输出012.340
    }
}
