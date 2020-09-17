package cn.fkJava.test.generics.border;

public class TestGenericsMethodBound<T> {

    // 无界
    public static void print(TestGenericsMethodBound<?> t) {
        System.out.println(t);
    }

    // 上界
    public static void printUpperNum(TestGenericsMethodBound<? extends Number> t) {
        System.out.println(t);
    }

    //下界
    public static void printLowerNum(TestGenericsMethodBound<? super Integer> t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        TestGenericsMethodBound<Integer> test1 = new TestGenericsMethodBound<>();
        TestGenericsMethodBound<Double> test2 = new TestGenericsMethodBound<>();
        TestGenericsMethodBound<String> test3 = new TestGenericsMethodBound<>();
        TestGenericsMethodBound<Number> test4 = new TestGenericsMethodBound<>();

        print(test1);
        print(test2);
        print(test3);
        printUpperNum(test1);
        printUpperNum(test2);
//        printUpperNum(test3);// String类型不是Number的子类故编译报错
        printLowerNum(test1);
//        printLowerNum(test2);// Double类型不是Integer或父类，故编译报错
//        printLowerNum(test3);// String类型不是Integer或父类，故编译报错
        printLowerNum(test4);
    }
}
