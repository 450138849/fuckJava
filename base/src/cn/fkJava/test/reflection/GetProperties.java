package cn.fkJava.test.reflection;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件测试
 */
public class GetProperties {
    @Test
    public void test1() {
        InputStream testRelectionReadProperties = GetProperties.class.getResourceAsStream("TestRelectionReadProperties.properties");
        //注意要将配置文件放在resource下并指派为resource根目录才能用getResourceAsStream()来直接获取
        Properties properties = new Properties();
        try {
            properties.load(testRelectionReadProperties);
        } catch (IOException e) {
            System.out.println("未找到指定的类");
        }
        System.out.println("properties.get(\"key1\") = " + properties.get("key1"));//properties.get("key1") = zhangsan
    }

    /**
     * 使用File直接读取
     */
    @Test
    public void test2() throws FileNotFoundException {
        //使用绝对路径可以获取
//        FileInputStream fis = new FileInputStream(new File("E:\\github\\fuckJava\\base\\src\\cn\\fkJava\\test\\reflection\\TestRelectionReadProperties2.properties"));
        //项目根目录下可以
        FileInputStream fis = new FileInputStream(new File("base/src/cn/fkJava/test/reflection/TestRelectionReadProperties2.properties"));

        Properties pro = new Properties();
        try {
            pro.load(fis);
        } catch (IOException e) {
            System.out.println("文件找不到");
        }
        System.out.println("pro.get(\"key1\") = " + pro.get("key1"));
    }
}
