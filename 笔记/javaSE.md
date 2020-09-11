# 1.运行过程

- 编译(.java文件编译成.class字节码文件)
- 运行(类加载->字节码校验->解释->解释成不同平台可执行的代码)

# 2.进制转换

- 二进制
- 八进制 0开头
- 十六进制 0x开头

转换方法：除x取余法

# 3.注释

- 单行注释

```java
//
```

- 多行注释

```java
/*

*/
```
- 文本注释

```java
/**
*
*/
```

> 注意注释不会被解析运行

# 4.命名规则

- 数字字母下划线美元符号
- 不能以数字开头

# 5.数据类型

- 基本数据类型

| 类型    | 大小  |
| ------- | ----- |
| boolean | 1位   |
| byte    | 1字节 |
| short   | 2字节 |
| int     | 4字节 |
| long    | 8字节 |
| char    | 2字节 |
| float   | 4字节 |
| double  | 8字节 |

- 引用类型

1. 字符串
2. 数组
3. 类
4. 接口

## 5.1自动类型转换

**整型、实型（常量）、字符型数据可以混合运算。运算中，不同类型的数据先转化为同一类型，然后进行运算。**

转换从低级到高级。

```
低  ------------------------------------>  高

byte,short,char—> int —> long—> float —> double 
```

大转小需要强制类型转换，可能会丢失精度

# 6.运算符

- 算数
- 赋值
- 比较
- 逻辑
- 位运算
- 三目运算

# 7.控制结构

- 选择

```java
if...else...
```

```java
//表达式在jdk5之后可以是枚举类型，7之后可以是字符串
switch(表达式) {
            case 值1:
                语句体1;
                break;
            case 值2:
                语句体2;
                break;
            ...
            default:
                语句体n+1;
                break;
}
```

- 循环

```java
for
    
foreach:(遍历数组arr并打印每个int类型的元素a)
    for(int a: arr)
    {
        System.out.println(a);
    }
    
while
    
do...while
```

- 关键字

> break跳出
>
> continue继续

**关键字和标签组合可以跳转到指定标签，跳出标签和从标签继续**

```java
a:
   for(int i =0;i<10;i++){
   		 for(int j =0;j<10;j++){
   			break a;//直接跳出外层循环，不加上a则跳出内层循环
  		 }
   }
```

# 8.方法

- 声明方法

```java
修饰符 返回值类型 方法名(参数类型 参数名){ 
    ...    方法体    ...    
    return 返回值;
}
```

- 可变参数

```
void test(int ...n){
	 //调用的时候...n可以不传参数为长度为0的数组[]，也可以是数组或多个参数
	 //一个方法只能有一个可变参数,否则不知道参数数量如何分配
	 //位置放在参数列表最后，前边定义剩下的就是可变参数
}
```



- 方法重写和方法重载

  >重写是方法名称和参数都相同
  >
  >重载是方法名相同，参数不同，**与返回值无关**

# 9.数组

**数组是引用类型**

- 定义和初始化

  1. 定义：存放同种类型数据的容器
  2. 初始化

  初始化方式分两种：

  ```java
  // 指定容量
  int[] arr = new int[10];
  arr[0] = 1 // 注意不能整个赋值arr = {1,2,3}
      
  //  指定元素
  int[] arr = new int[]{1,2,3};
  // 这种方法等同于
  int[] arr = {1,2,3};
  ```

- 二维数组

```java
// 数组初始化
  A:数据类型[][] 数组名 = new 数据类型[m][n];
  B:数据类型[][] 数组名 = new 数据类型[m][];
  C:数据类型[][] 数组名 = new 数据类型[][]{{...},{...},{...}};
  D:数据类型[][] 数组名 = {{...},{...},{...}};

// 例子
A:int[][] arr = new int[1][2]//创建之后赋值arr[0]是一个int[2]类型数组
B:int[][] arr = new int[1][]//创建之后arr[0]是一个int[]类型数组，要重新创建一维数组传入
    int[][] arr = new int[10][];
    arr[0] = new int[]{1};
C:int[][] arr = new int[][]{{1,2,3}}//创建的时候赋值
D:int[][] arr =  {{1,2,3}}//C的简化版本
```

# 10.面向对象

特点：

1. 封装
2. 继承
3. 多态

## 访问控制修饰符

1. public-- 所有类
2. protected--子类
3. default--本包
4. private--本类

## 其他修饰符

- static--静态，静态变量不能被修改，静态方法只能包含静态变量，可以使用 类名.[方法名|类名]调用静态[变量|方法]
- final--最终的，最终的类不能被继承扩展，最终的方法不能被重写
- abstract--抽象
- synchronize--同步
- volatile--不稳定的

## 10.1类的写法

写法：

```java
public class Test{
    private int age;
    
    public int getAge(){
        return age;
    }
}
```

## 10.2构造器

**构造器格式**

```java
// 格式
修饰符 方法名(参数列表){
    
}
```

- 无参构造

  ```java
  public Test(){
      
  }
  ```
  
- 有参构造
   ```java
   public Test(参数){
      
   ```

}
   ```
   
- 重载构造器
  
  ```java
  public Test(){
      
  }
  public Test(string name){
      
  }
  public Test(int age){
      
  }
   ```

**注意：当没有指定构造器的时候，系统会默认生成一个空的无参构造器**

> 构造其中可以使用this(参数)其它构造器

**构造器调用规则：**

1. **子类构造会调用父类的构造器**
2. **未指定子类构造器的时候会默认生成无参构造器**
3. **子类构造器未显示调用父类构造的时候会调用父类的无参构造**
4. **如果父类没有无参构造，子类又没有显示调用，则编译报错**

总结：默认调用无参构造，无参构造不存在则报错

**初始化顺序：**

1. 系统初始化(变量初始化等操作)
2. 声明
3. 构造代码块{}
4. 构造器

## 10.3继承

**继承的作用**：提出共同代码，减少代码冗余，提高代码复用

**java不支持多继承：**

```java
// 形如下列的多继承不允许
class C extends A,B {
}
```



**关键字：**

super--父类

this--当前对象

```java
class 父类 {
}
 
class 子类 extends 父类 {
}
```



例如

```java
public class Foo {
    private int age;

    public Foo() {
        System.out.println("this is foo！");
    }
}

public class Child extends Foo {
    public Child() {
        System.out.println("this is child!");
    }
}

```

## 10.4抽象类

**写法**

```java
public abstract class Employee{
    
}

public abstract class TrafficTool {
    public int capacity;
    abstract void move();
}

public class Airplane  extends TrafficTool{
    @Override
    void move() {
        System.out.println("飞行!");
    }
}

public class Car extends TrafficTool {
    @Override
    void move() {
        System.out.println("汽车行驶");
    }
}
```
**抽象类规则**

- **抽象类不能被实例化**(初学者很容易犯的错)，如果被实例化，就会报错，编译无法通过。只有抽象类的非抽象子类可以创建对象。
- **抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类。**
-  **抽象类中的抽象方法只是声明，不包含方法体**，就是不给出方法的具体实现也就是方法的具体功能。
- **构造方法，类方法（用 static 修饰的方法）不能声明为抽象方法。**(构造方法在new的时候调用，抽象类不能创建实例，所以不能有构造方法)
- **抽象类的子类如果不是抽象类，必须实现全部抽象方法**。 

## 10.5接口

**接口写法**

```java
[可见度] interface 接口名称 [extends 其他的接口名] {
        // 声明变量
        // 抽象方法
}
```

**当类实现接口的时候，类要实现接口中所有的方法。否则，类必须声明为抽象的类。**(抽象类中可以包含抽象方法和非抽象方法)

**案例**

```java
public interface Animal {
    void move();
}

public class Rabbit implements  Animal{
    @Override
    public void move() {
        System.out.println("兔子会跳!");
    }
}
```

**接口特性**

- 接口中每一个方法也是隐式抽象的,接口中的方法会被隐式的指定为 **public abstract**（只能是 public abstract，其他修饰符都会报错）。
- 接口中可以含有变量，但是接口中的变量会被隐式的指定为 **public static final** 变量（并且只能是 public，用 private 修饰会报编译错误）。
- 接口中的方法是不能在接口中实现的，只能由实现接口的类来实现接口中的方法。
- 接口中的静态方法是可以带方法体的，由接口名称调用。

**抽象类和接口的区别**

-  抽象类中的方法可以有方法体，就是能实现方法的具体功能，但是接口中只有默认方法和静态方法能有方法体。
-  抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是 **public static final** 类型的。
-  **抽象类只能单继承，接口可以多实现**
-  **接口之间可以多继承**

**接口和抽象类的用途区分**

- 抽象类是一个模板，子类继续扩充模板
- 接口是一个规范，实现同一个接口的类都必须具备同一种规范

> **注**：JDK 1.8 以后，接口里可以有静态方法和方法体了。

**在 JDK1.8，允许我们给接口添加两种非抽象的方法实现：**

1、默认方法，添加 **default** 修饰即可；

2、静态方法，使用 **static** 修饰；示例如下：

```java
// 调用的时候,静态方法通过方法名调用,默认方法通过实现接口的类的对象调用
interface Test{
    //这个是默认方法
    default String get(String aa){
        System.out.println("我是jdk1.8默认实现方法...");
        return "";
    }   
    //这个是静态方法    
    static void staticmethod(){
        System.out.println("我是静态方法");
    }
}
```

**标记接口**

对实现该接口的类进行标记，标机接口不包含任何方法和属性

## 10.6多态

**多态存在的三个必要条件**

- 继承
- 重写
- 父类引用指向子类对象

**会屏蔽子类之间的差异，只能调用父类中定义的公共的内容**

可以指定子类对象为抽象类或接口类型

instanceof可以判断某个对象是否是某个类或接口类型

- 常用场景
  - 父类类型作为参数
  - 父类类型作为返回值

## 10.7内部类

定义在类当中的类:注意单例模式有一种用内部类实现的方式，可以达到和双检锁一样的效果。

分类：

- 成员内部类
  1. 实例成员内部类
  2. 静态成员内部类
- 局部内部类
- 匿名内部类

**实例成员内部类：**

- 写法

```java
public class Outer {
    public void show() {
        System.out.println("show outer!");
    }

    public class Inner {
        private int age;

        public void show() {
            System.out.println("show inner!");
        }
    }
}
```

- 对象创建

```java
Outer.Inner inner = new Outer().new Inner();
```

- 属性访问

> 内部类访问外部类成员可以直接访问，外部类访问内部类需要先创建对象。
>
> 内部类访问外部类同名属性要使用外部类对象访问。

**静态成员内部类：**

- 写法

```java
public class TestStaticOuter {
    public static class Inner {
        public void show() {
            System.out.println("show!");
        }
    }
}
```

- 对象创建

```java
 TestStaticOuter.Inner inner = new TestStaticOuter.Inner();
```

- 属性访问

> 静态成员内部类只能访问静态内容

**局部内部类：**

在代码块或方法中的内部类

- 写法

```java
public class LocalOuter {
    // 内部类定义在方法中，只能在方法内部调用
    public void showInner() {
        int a = 10;
        class Inner {
            public void show() {
                System.out.println("show inner!" + a);
            }
        }
        Inner inner = new Inner();
        inner.show();
    }
}
```

- 对象创建

> 只能在{}内创建，在局部范围内引用

**匿名内部类：**

只在需要的时候才创建类，使代码变得简洁，解决多继承方法同名问题等，视为一个非抽象的实现类，所以不能有抽象方法，同时不能有静态内容和构造方法(只在类创建的时候调用一次，所以不需要构造方法)

- 写法

```java
// 匿名内部类实现一个接口或继承一个类
AnonymousClass anonymous = new AnonymousClass() {
            @Override
            public void show() {
                System.out.println("show anonymousClass!");
            }
        };
        anonymous.show();
```

- 对象创建

在创建类的时候创建内部类

## 10.8UML统一建模语言

- 继承
- 实现
- 关联
- 依赖
- 组合
- 聚合

![image-20200908133905097](C:\Users\lvbowen450138849\AppData\Roaming\Typora\typora-user-images\image-20200908133905097.png)

## 单例模式

**构成**

- 一个成员变量
- 一个构造方法
- 一个getInstance()方法

### 饱汉式

**线程安全**

```java
// 添加了synchronized关键字后对性能影响很大，但是getInstance()方法很少调用，所以对应用的效率影响不大
public class Singleton {  
    private static Singleton instance;  
    private Singleton (){}  
    public static synchronized Singleton getInstance() {  
    if (instance == null) {  
        instance = new Singleton();  
    }  
    return instance;  
    }  
}
```

**线程不安全**

```java
public class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
```

### 饿汉式

```java
public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }
}
```

### 双检锁 

```java
public class Singleton {  
    private volatile static Singleton singleton;  
    private Singleton (){}  
    public static Singleton getSingleton() {  
    if (singleton == null) {  
        synchronized (Singleton.class) {  
        if (singleton == null) {  // 确保对象未创建且未在创建中
            singleton = new Singleton();  
        }  
        }  
    }  
    return singleton;  
    }  
}
```

## javabean标准

1. public标识
2. 属性私有
3. 无参构造
4. get/set方法

## UML图例

https://blog.csdn.net/Lemon_husky/article/details/80181856

# 11API

## 11.1字符串

### **String**

> 在创建的时候，如果静态方法区存在该字符串，则直接引用，如果不存在，则创建，如果是new String(“”)方式创建，则在堆中创建引用对象指向静态方法区中的字符串常量

**常用方法**

- charAt(index)
- contentEquals()
- concat()
- startsWith()
- endsWith()
- toLowerCase()
- toUpperCase()
- substring()
- split()

### **StringBuffer**

线程安全

**常用方法**

- append()
- reverse()
- insert()
- replace(start,end,string)
- delete(start,end)
- deleteCharAt(index)
- capacity()
- charAt()
- setCharAt(index,char)--替换指定位置的字符
- toString()

### **StringBuilder**

线程不安全，效率更高

**常用方法**

> 方法同StringBuffer但是线程不安全

## 11.2包装类

除了char->Character ,int->Integer,其他的都是首字母大写

**装箱和拆箱**

装箱：valueOf()

拆箱：intValue()

指定类型直接赋值的时候会自动调用上述方法进行装箱和拆箱操作

**常量池**

除了flout和Double类型之外的类型都实现了常量池技术

-128~127会缓存，超出之后会重新创建对象

```java
public static void main(String[] args) {
        Integer a1 = 1;
        Integer a2 = 1;
        System.out.println(a1 == a2);// true，因为1存在了常量池里边
        Integer a3 = 128;
        Integer a4 = 128;
        System.out.println(a3 == a4);// false，因为128超出-128~127，会调用new Integer(128)重新创建对象，两个对象不相同
    }
```

**字符串和包装类的类型转换**

```java
// 字符串转化成Integer类型,三种写法都可以，源码实现其实都是调用了parseInt方法
int i = new Integer("123");
int i2 = Integer.valueOf("123");
int i3 = Integer.parseInt("123");
// 包装类转String
String s1 = 123 + "";// 常用
String s2 = new Integer(123).toString();
String s3 = String.valueOf(123);
```

**进制转换**

```java
        System.out.println(Integer.toBinaryString(2));// 10 -> 2
        System.out.println(Integer.toOctalString(8));// 10 -> 8
        System.out.println(Integer.toHexString(16));// 10 -> 16
        System.out.println(Integer.valueOf("10",16));// 16 -> 10 parseInt只能讲不同进制转换成十进制
        System.out.println(Integer.valueOf("10",8));// 8->10
        System.out.println(Integer.valueOf("10",2));// 2 -> 10
```

## 11.3Character类

char的包装类，表示一个字符

- isLetter()是否字符
- isDigit()是否数字
- isWhitespace()是否空白字符(包括空格，tab，换行符'\n','\t')
- isUpperCase()
- isLowerCase()
- toUpperCase()
- toLowerCase()
- toString()

## 11.4日期操作类

**初始化日期**

1. new Date()//获取当前时间
2. new Date(long xxxL)//根据指定时间获取Date对象

```java
Date date = new Date();
System.out.println(date);// Fri Sep 11 11:45:35 CST 2020
System.out.println(date.getTime()); // 1599795935890
Date date1 = new Date(1599795935890L);// 可以输入new类型的数值来初始化
System.out.println(date1);// Fri Sep 11 11:45:35 CST 2020
```

**日期的表现形式**

1. date日期
2. time时间
3. timeStamp时间戳

```java
java.sql.Date date2 = new java.sql.Date(1599795935890L);// 是用sql.date可以直接获得年月日
System.out.println(date2);// 2020-09-11   会调用date2的toString方法
System.out.println(java.sql.Date.valueOf("2020-09-11").getTime());//1599753600000  string->date
Time time = new Time(1599795935890L);
System.out.println(time);// 11:45:35
Timestamp ts = new Timestamp(1599795935890L);
System.out.println(ts);// 2020-09-11 11:45:35.89
System.out.println(Timestamp.valueOf("2020-09-11 11:45:35.89"));// 字符串转时间戳
```

**格式转换**

- valueOf将字符串转化成对应格式
- toString将当前格式转化为字符串

## 11.5格式化

**数字格式化**

1. NumberFormat
2. DecimalFormat

```java
        NumberFormat format = NumberFormat.getInstance();
        NumberFormat format2 = NumberFormat.getCurrencyInstance();
        DecimalFormat format3 = new DecimalFormat("000.000");

        System.out.println(format.format(123.456));// 123.456
        System.out.println(format2.format(123.456));// ¥123.46 以默认环境的货币方式输出
        System.out.println(format3.format(12.34));// 按照指定格式输出012.340
```

**日期格式化**

```java
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
```



# 正则表达式

\前要加上转义字符\

|  \D   |      非数字      |
| :---: | :--------------: |
|  \d   |       数字       |
|  \W   |    非单词字符    |
|  \w   |     单词字符     |
|   .   | 换行符之外的字符 |
|  {n}  |       n次        |
| {n,m} |      n-m次       |
|   *   |       >=0        |
|   +   |        >0        |
|   ?   |       0或1       |

**java验证正则表达式三步骤：**

1. 定义正则表达式Pattern
2. 输入要匹配的字符串Matcher
3. 校验matches()

# 泛型

# 集合

# 网络编程

# 多线程

# 异常处理

# 反射

# java8特性

## 函数式接口

> 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
>
> 函数式接口可以被隐式转换为 lambda 表达式。

```java
@FunctionalInterface
interface GreetingService 
{
    void sayMessage(String message);
}

// 这种实现方式相当于用匿名内部类重写接口中的唯一一个抽象方法
GreetingService greetService1 = message -> System.out.println("Hello " + message);
```

## Lambda表达式

- 写法

```java
()->{}
```

- lambda表达式和函数接口的差别
  - lambda表达式只能调用函数接口，匿名内部类可以实现接口，类
  - lambda表达式不能调用默认方法

## 方法引用

方法引用的四种方法：

- 构造器引用

```java
Class::new
```

- 静态方法
```java
Class::static_method
```

- 特定对象

```java
instance::method
```
- 任意对象

```java
Class::method
```

