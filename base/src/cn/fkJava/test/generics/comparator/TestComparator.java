package cn.fkJava.test.generics.comparator;

import java.nio.file.attribute.AclEntryFlag;
import java.util.Arrays;
import java.util.Comparator;

class Test implements Comparable<Test> {
    public int age;

    public Test() {
    }

    public Test(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Test o) {
        return this.age - o.age;
    }
}

public class TestComparator {
    public static void main(String[] args) {
        Test t1 = new Test(33);
        Test t2 = new Test(22);
        Test[] arr = {t1, t2};

        Arrays.sort(arr);// 通过实现comparable接口进行排序
        // 通过传入comparator进行排序
        Arrays.sort(arr, new Comparator<Test>() {
            @Override
            public int compare(Test o1, Test o2) {
                return o1.age - o2.age;
            }
        });
        // 通过lambda表达式实现
        Arrays.sort(arr, (((o1, o2) -> {
            return o1.age - o2.age;
        })));
    }
}



