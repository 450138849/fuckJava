package cn.fkJava.test.collection;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 1.用list存储多条狗狗信息（美美 1，亮亮 2，欢欢 3）
 * 2.逐条打印各狗狗信息
 * 3.用comparable实现年龄自然升序排序
 * 4.删除美美的信息
 * 5.输出集合中还剩下几只狗狗
 */
class Dog implements Comparable<Dog> {
    private String name;
    private int age;
    private String type;

    public Dog() {
    }

    public Dog(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Dog o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return name + "\t" + type + "\t" + age;
    }
}

public class TestList {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.addAll(Arrays.asList(
                new Dog("欢欢", 3, "松狮"),
                new Dog("美美", 1, "拉布拉多"),
                new Dog("亮亮", 2, "金毛")));

        System.out.println(dogs);// [欢欢	松狮	3, 美美	拉布拉多	1, 亮亮	金毛	2]
//        Collections.sort(dogs);
//        System.out.println(dogs);// [美美	拉布拉多	1, 亮亮	金毛	2, 欢欢	松狮	3]
        Collections.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(dogs);// [美美	拉布拉多	1, 亮亮	金毛	2, 欢欢	松狮	3]
        dogs.removeIf(x -> x.getName() == "美美");
        System.out.println(dogs);// [亮亮	金毛	2, 欢欢	松狮	3]
        System.out.println(dogs.size());// 2
    }
}

