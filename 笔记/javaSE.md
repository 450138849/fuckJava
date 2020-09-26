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

**构造器格式**(注意不能有返回参数)

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
- **抽象类中的抽象方法只是声明，不包含方法体**，就是不给出方法的具体实现也就是方法的具体功能。
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

![image-20200908133905097](.\javaSE.assets\image-20200908133905097.png)

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

以上三种都是要传入毫秒数(unix毫秒数)来进行构造的

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

**时间格式化**

```java
Date date = new Date();
DateFormat df = DateFormat.getTimeInstance();
DateFormat df1 = DateFormat.getDateTimeInstance();
DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

System.out.println(df.format(date));// 下午12:51:44
System.out.println(df1.format(date));// 2020年9月11日 下午12:52:30
System.out.println(df2.format(date));// 2020年9月11日 下午04时41分35秒
System.out.println(sdf.format(date));// 2020-09-11 04:44:45
```

## 11.6日历类

```java
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
```

# 12正则表达式

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

1. 定义正则表达式Pattern()
2. 输入要匹配的字符串Matcher()
3. 校验matches()

# 13泛型(generics)

> 泛型的本质是参数化类型，可以减少代码冗余，可以在编译期进行类型判断

## 13.1泛型方法

**定义规则**

- 泛型方法必须在返回值前边声明类型参数
- 可以声明多个类型参数(逗号分隔)
- 返回值可以使类型参数
- 类型参数只能是引用类型，不能是基本类型

```java
// 定义泛型方法，输入参数会自动判定泛型类型
public static <T> void printStr(T str){
    System.out.println(str);
}

public static void main(String[] args) {
    Integer a = 1;
    String str = "abc";
    Double b = 0.1;

    printStr(a);
    printStr(b);
    printStr(str);
}
```

## 13.2泛型类

```java
public class TestGenericClass<T> {
    private T t;

    public TestGenericClass(T t) {
        this.t = t;
    }

    private void get() {
        System.out.println(t);
    }

    public static void main(String[] args) {
        TestGenericClass<Integer> t1 = new TestGenericClass(1);
        TestGenericClass<String> t2 =  new TestGenericClass("zhangsan");

        t1.get();
        t2.get();
    }
}
```

## 13.3泛型接口

```java
public interface TestGenericsInterface<T> {

}

// 实现的时候继续使用泛型(在实例化的时候指定泛型类型)
class GenericsImpl1<T> implements TestGenericsInterface<T>{

}

// 实现的时候已经制定了泛型类型
class GenericsImpl2 implements TestGenericsInterface<String>{
    
}
```

## 13.4泛型通配符

- 无界?
- 上界extends
- 下界super

**？和 T的区别**：

1. ?可以设置上界和下界，T只能设置上界
2. ?只能有一个上界，T能有多个上界
3. 多个上界需要用&分隔

**用例**

```java
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
        printUpperNum(test3);// String类型不是Number的子类故编译报错
        printLowerNum(test1);
        printLowerNum(test2);// Double类型不是Integer或父类，故编译报错
        printLowerNum(test3);// String类型不是Integer或父类，故编译报错
        printLowerNum(test4);
    }
}
```

## 13.5泛型擦除

> 泛型在编译的时候会擦除

**擦除原则：**

- 无上下界替换为Object
- 有上下界替换为最左边的类型

## 13.6比较器

Arrays.sort()进行排序的时候，被排序的对象必须实现了comparable接口，可以传入外部比较器进行比较

**实现comparable接口的步骤：**

- 实现comparable<T>
- 重写compareTo方法

```java
package cn.fkJava.test.generics.comparator;

import java.util.Arrays;
import java.util.Comparator;

// 实现comparable<T>接口
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
```

# 14枚举

- 数据量不大
- 数据必须确定

**原理**

创建enum时，会生成一个继承自enum的类

枚举类中的每一项都会继承enum类,enum类当中重写了toString方法，返回枚举名称，所以打印不是object的类名@hash值，而是返回的枚举名称

**声明**

- enum关键字声明枚举类
- 枚举类可以声明变量，方法，构造方法

```
public enum TestEnum {
    TEST_ENUM;
}
```

**方法**

- valueOf--可以将值转成枚举类型
- values--可以获取所有的值
- comprareTo--enum类实现了comparable<T>接口
- getDeclaringClass()--获取枚举类名称
- name()--返回枚举类的名称
- ordinal()--返回枚举类的序数
- describeConstable()
- toString
- clone
- equals
- hashcode

**实现接口**

- 在枚举类当中实现抽象方法
- 如果每个枚举对象有差异，在每个枚举对象后分别实现抽象方法

```java
// 示例
public enum TestEnum {
    ZHANGSAN, LISI, WANGWU, ZHAOLIU;

    public static void main(String[] args) {
        System.out.println(TestEnum.ZHANGSAN);// ZHANGSAN
//        Arrays.asList(TestEnum.values()).stream().forEach(System.out::println);
        System.out.println(TestEnum.ZHANGSAN.getDeclaringClass());// class cn.fkJava.test.testenum.TestEnum
        System.out.println(TestEnum.ZHANGSAN.name());// ZHANGSAN
        System.out.println(TestEnum.LISI.ordinal());// 1
        System.out.println(TestEnum.ZHANGSAN.describeConstable());// Optional[EnumDesc[TestEnum.ZHANGSAN]]如果构造方法有第二个参数，则第二个参数为描述
    }
}
```

# 垃圾回收

主动进行垃圾回收

- System.gc()

# Object

- 常用方法
- 如果要根据指定的规则对对象进行比较，则要使用equals()方法
- 根据hashcode规则，equals比较相同的对象必须有相同的hashCode，equals()比较结果不同的对象也可以有相同的hashCode（hash冲突）
  - 在hash表当中存储的时候，是根据hashcode先进行寻址，hashcode相同的采用链式存储，然后才调用equals比较hashCode相同的对象
- clone方法默认是浅拷贝，如果想要深拷贝，需要重写clone方法，在里边重新创建对象并赋值即可

# 集合

集合是为了解决对象数量不确定的问题 

- 数组必须要调用Arrays.toString()才能打印内容，否则打印的是地址
- 集合可以直接打印就能说出内容，不需要输出地址

## 1.集合框架

![image-20200916220725272](.\javaSE.assets\image-20200916220725272.png)

## 2.集合特点

- Collection--可重复，无序
- List--有序，可重复
  - ArrayList--便于查询，不便于增删
  - LinkedList--便于增删，不便于查询
- Set--无序，不可重复
  - HashSet--增删和查询效率均可，无序
    - LinkedHashSet--按照插入顺序排序，保存hashset查询性能
  - TreeSet--升序排序，查询速度比list快，不如hashSet(底层是二叉树)【存储的对象必须实现comparable接口，基本类型可以直接存取】
- Map--存键值对
  - HashMap--一种快速算法生成的顺序，视为无序
    - LinkedHashMap--按照key插入顺序，保留HashMap查询性能
  - TreeMap--按照比较后升序保存key
  - hashTable--线程安全，性能低
- Queue--只能从一端进一端出
  - priorityQueue--优先级队列，通过传入比较器可以实现排序，否则使用自然排序【遍历的时候是不能保证顺序的，只有用队列操作poll才能保证顺序】
  - Deque--双端队列
  - LinkedList--实现了堆栈和队列的所有操作

**队列操作**

- add
- remove
- get
- offer
- poll
- peek

**栈操作(单侧操作)**

- push
- pop

**map操作**

- entrySet
- keySet
- values
- containsKey
- containsValue

**优缺点：**

ArrayList	--	遍历和查询比较快(扩容1.5倍)

LinkedList	--	添加、删除元素比较快

Vector	--	线程安全的，效率低(扩容为两倍)

**遍历：**

- for
- foreach[两种形式，for(String a:arr)或者list.foreach()]
- 迭代器

## 迭代器

- 数组可以进行foreach操作，但是数组不是iterable类型

foreach方法使用iterable接口移动，故返回iterable接口并重写其中的iterator可以实现foreach的行为，foreach的对象只要是Iterable接口类型或子类即可

**构造反向迭代器**

```java
package cn.fkJava.test.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

class SuperArrayList<T> extends ArrayList<T> {

    SuperArrayList() {
        super();
    }

    SuperArrayList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reverse() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int cur = size() - 1;

                    @Override
                    public boolean hasNext() {
                        return cur > -1;
                    }

                    @Override
                    public T next() {
                        return get(cur--);
                    }
                };
            }
        };
    }
}

public class TestIterator {
    public static void main(String[] args) {
        SuperArrayList<String> arr = new SuperArrayList(Arrays.asList("a", "b", "c"));

        for(String s:arr){
            System.out.println(s);
        }

        for(String s:arr.reverse()){
            System.out.println(s);
        }
    }
}
```

## 工具类

- collections
- arrays
  - 参数可以使用数组或可变参数
  - 使用arrays.asList()返回的是数组，不可以改变容量
  - 类型会按照给出的元素给出最精确的类型，显示指定为Arrays.<T>asList()

**collections常用方法**

1. sort(collection)
2. sort(collection,comparator)
3. reverse
4. max
5. min
6. fill
7. binarySearch(collection,element)

## stream

通过collection.stream()可以获取对应的Stream

**stream常用方法**

- max
- min
- sum
- avarage
- count
- allMatch
- anyMatch
- filter

**筛选条件用法**

```java
List<Dog> dogs = new ArrayList<>();

dogs.stream().allMatch(new Predicate<Dog>() {
    @Override
    public boolean test(Dog dog) {
        return false;
    }
})
```

# IO

## 1.文件对象操作

### 1.1构造方法

```java
File file1 = new File("test1.txt");
File file2 = new File("parent","child");
File file3 = new File(file2,"child2");
```

### 1.2常用方法

**基础方法**

```java
System.out.println(file1.getAbsoluteFile());// E:\github\fuckJava\test1.txt
System.out.println(file1.getPath());// test1.txt
System.out.println(file1.getParent());//null
System.out.println(file1.getName());//test1.txt
System.out.println(file1.length());//3
System.out.println(new Date(file1.lastModified()));//Tue Sep 22 21:39:15 CST 2020
System.out.println(Arrays.asList(file4.list()));// [.idea, base.iml, output, src]
System.out.println(Arrays.asList(file4.listFiles()));// [base\.idea, base\base.iml, base\output, base\src]
```

- renameTo

**判断方法**

```java
file1.isDirectory();
file1.isFile();
file1.exists();
file1.canRead();
file1.canWrite();
file1.isHidden();
```

**创建方法**

```java
// 不写盘符默认在项目路径下
file1.createNewFile();
file1.mkdir();
file1.mkdirs();
```

**删除功能**

```java
// java中删除不走回收站
file1.delete();
```

**分隔符**

```java
File.separator;// 在linux下是/，在windows下是\\
```

## 2.文件内容操作

> 文件内容操作就要用到IO

### 2.1流的分类

- 输入流
- 输出流
- 字节流
- 字符流
- 节点流--直接作用于文件对象
- 处理流--将已有的流进行包装

| 抽象基类     | 节点流           | 缓冲流               |
| ------------ | ---------------- | -------------------- |
| InputStream  | FileInputStream  | BufferedInputStream  |
| OutputStream | FileOutputStream | BufferedOutputStream |
| Reader       | FileReader       | BufferedReader       |
| Writer       | FileWriter       | BufferedWriter       |

**命名方式**

- InputStream/OutputStream  --  字节流
- Reader/Writer  --  字符流

![image-20200925235338990](E:\github\fuckJava\笔记\javaSE.assets\image-20200925235338990.png)

### 2.2流的使用

>  主要作用就是进行文件复制和读取

read(byte[],offset,length)中的byte[]是从硬盘读取的时候每次读取多少的限定，二缓冲流每次会先缓存8192个字节到内存中

#### **文件读取**

1. 创建文件
2. 创建字符流
3. 进行读取
4. 关闭流

```java
package cn.fkJava.test.testio;

import org.junit.Test;

import java.io.*;

public class TestFileReader {
    @Test
    public void test() {
        FileReader fr = null;
        try {
            //生成文件
            File file = new File("test1.txt");
            //创建字符流
            fr = new FileReader(file);
            //对文件进行读取
            int data = fr.read();// 文件末尾返回-1，否则返回一个字符int格式
            while (data != -1) {
                System.out.print((char) data);
                data = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### **文件写入(写入操作允许文件不存在,不存在就创建)**

```java
 @Test
    public void test() throws Exception {
        File file = new File("test1.txt");

        FileWriter fw = new FileWriter(file, true);
        fw.write("zhansgan\n");
        fw.write("lisi");

        fw.close();
    }
```

#### 文本文件复制

字节流处理可能会出现乱码，如果每个字符都是一个字节能存下的则可以还原，否则会乱码

```java
@Test
public void copyFile() throws Exception {
    File src = new File("test1.txt");
    File dest = new File("test2.txt");

    FileReader fr = new FileReader(src);
    FileWriter fw = new FileWriter(dest);

    int date;
    while ((date = fr.read()) != -1) {
        fw.write(date);
    }

    fr.close();
    fw.close();
}
```

#### **字节文件复制**

字符流不能传输非文本文件

```java
/**
 * 非文本文件的复制
 */
@Test
public void copyByteFile() {
    File src = new File("jdk api 1.8_google.CHM");
    File dest = new File("jdk api 1.8_google.CHM.bak");
    FileInputStream fis = null;
    FileOutputStream fos = null;

    try {
        fis = new FileInputStream(src);
        fos = new FileOutputStream(dest);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, length);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### 缓冲流进行文本文件复制

> 缓冲流读取的时候是从缓冲区通过设置好大小的缓存数组来进行读取，不用缓冲流就是每次用byte[]直接从硬盘读取

可以使用readLine方法,该方法读取到的数据不包含换行符

也可以使用newLine()换行

```java
/**
 * 用缓冲流复制文本文件
 */
@Test
public void copyTextFileByBuffer() {
    File src = new File("test1.txt");
    File dest = new File("test1bak.txt");

    FileReader fr = null;
    FileWriter fw = null;
    BufferedReader br = null;
    BufferedWriter bw = null;
    try {
        fr = new FileReader(src);
        fw = new FileWriter(dest);

        br = new BufferedReader(fr);
        bw = new BufferedWriter(fw);

        int length;
        char[] chars = new char[1];
        while ((length = br.read(chars)) != -1) {
            bw.write(chars, 0, length);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            bw.close();
            br.close();
            fw.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### 缓冲流进行非文本文件复制

```java
/**
 * 用缓冲流复制非字符文件
 */
@Test
public void copyFileByBuffer() {
    File src = new File("testmov.avi");
    File dest = new File("testmov2.avi.bak");

    FileInputStream fis = null;
    FileOutputStream fos = null;
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
        fis = new FileInputStream(src);
        fos = new FileOutputStream(dest);

        bis = new BufferedInputStream(fis);
        bos = new BufferedOutputStream(fos);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, length);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            bis.close();
            bos.close();
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### **转换流**

> 提供字节流和字符流的转换功能,可以在构建转换流的时候填入编码，实现文件的编码和解码

```java
/**
 * 转换流
 */
@Test
public void transStream() {
    FileInputStream fis = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    try {
        fis = new FileInputStream("test1.txt");
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);

        String str;
        char[] chars = new char[1024];
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### 标准输入/输出流

- system.in
- system.out
- setIn--重定向流，不设置默认是键盘输入
- setOut--重定向流，默认从控制台输出

**将输入输出到控制台，输入e或exit退出**

```java
/**
 * 系统流
 */
@Test
public void testSystemStream() {
    InputStreamReader isr = null;
    BufferedReader br = null;
    try {
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
        String str;
        while (true) {
            str = br.readLine();
            if ((str.equals("e")) || (str.equals("exit"))) {
                break;
            }
            System.out.println(str);
        }
        System.out.println("结束");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**将输入转化成需要的类型的值，比如scanner的nextInt()方法**

#### 打印流

- PrintStream
- PrintWriter

> 一般通过配合system.setOut()方法将输出保存下来而不输出到控制台

```java
/**
 * 打印流+系统流
 */
@Test
public void testPrintStream() {
    PrintStream pw = null;
    try {
        File file = new File("test3.txt");
        FileOutputStream fos = new FileOutputStream(file);
        pw = new PrintStream(fos, true);
        System.setOut(pw);
        System.out.println("zhangsan\nlisi\nwangwu");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        pw.close();
    }
}
```

#### 数据流

> 用于操作基本数据类型和String,可以直接返回或者写入对应数据格式的数据

数据流读的时候要和写的时候顺序以及对应的数据类型一致，否则报错

```java
/**
 * 数据流
 */
@Test
public void testDataStream() {
    DataInputStream dis = null;
    DataOutputStream dos = null;
    try {
        File file = new File("test1.txt");
        dos = new DataOutputStream(new FileOutputStream(file));
        dis = new DataInputStream(new FileInputStream(file));
        dos.writeUTF("zhangsan");
        dos.writeBoolean(true);
        String str = dis.readUTF();
        boolean flag = dis.readBoolean();
        System.out.println(str + '\n' + flag);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### 对象流

> 用于序列化和反序列化

可以将任何实现了serializable转换成二进制字节流，序列化是RMI(远程方法调用)和javaEE的基础

**对象序列化机制概念**

对象序列化机制是指对象可以转换成二进制流，存在磁盘上或者通过网络传输出去，其他程序获取到该二进制流后可以还原出原本的java对象

#### 对数据进行序列化和反序列化

```java
/**
 * 对象流序列化和反序列化
 */
@Test
public void testObjectStream() {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try {
        File file = new File("test.dat");
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(new String("i love Beijing!"));
        oos.flush();
        ois = new ObjectInputStream(new FileInputStream(file));
        System.out.println(ois.readObject());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 3.流的常用方法

**Reader**

- read
- read(char[])

**InputStream**

- read
- read(byte[])

# 网络编程

# 多线程和高并发

# 注解

**注解的重要性:**

框架=注解+反射+设计模式

## 1.文档注解

```java
// 共有7种文档注释
/**
 * @author lvbowen450138849
 * @version
 * @see
 * @since
 */
public class TestAnnotation {
    /**
     * @param
     * @return
     * @exception
     */
    public String test(String test) {
        return "";
    }
}
```

## 2.基本注解

```java
public class TestAnnotation2 {
    @Override
    @Deprecated
    @SuppressWarnings()
}
```

## 3.元注解

```java
// 用于修饰注解的注解
public class TestMetaAnnotation {
    @Target(ElementType)// 参数为枚举
    @Retention(RetentionPolicy)// 参数为枚举
    @Documented
    @Inherited// 修饰的注解都可以被子类继承
}
```

**ElementType表示注解修饰的类型**

1. @Target(ElementType.TYPE)  //接口、类、枚举、注解
2. @Target(ElementType.FIELD) //字段、枚举的常量

3. @Target(ElementType.METHOD) //方法

4. @Target(ElementType.PARAMETER) //方法参数

5. @Target(ElementType.CONSTRUCTOR) //构造函数

6. @Target(ElementType.LOCAL_VARIABLE)//局部变量

7. @Target(ElementType.ANNOTATION_TYPE)//注解

8. @Target(ElementType.PACKAGE) ///包  


**RetentionPolicy**

1. SOURCE--保存在源码
2. CLASS--保存在字节码文件中
3. RUNTIME--运行的时候加载到JVM中，保存在内存里【只有这种才能通过反射获取】

## 4.自定义注解

**如果没有值的注解被称为标记注解**

```java
package cn.fkJava.test.testAnnotation;

public @interface TestAnnotation3 {
}
```

```java
package cn.fkJava.test.testAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCustomAnnotation {
    String value() default "a";// 如果这里没有给出defaultValue那么注解后边一定要带上对应的值，否则编译报错

    String nonDefaultValue();
}

class Test {
    // 这里由于没有带上nonDefaultValue的值所以报错
    @TestCustomAnnotation()
    void Test() {

    }

    // value有默认值，所以不填value的值也不报错
    @TestCustomAnnotation(nonDefaultValue = "test")
    void Test2() {

    }
    
    // 标记注解不用赋值，括号可带可不带
    @TestAnnotation3
    void Test3() {

    }
}
```

## 5.可重复注解

> 声明Repeatable(需要重复的注解类名)注解

在注解中标明在哪个注解类中重复使用，rentention和target要一致，然后就可以对同一个元素使用多个相同的注解了

rentention和target不一致则编译报错，如果两个注解一个是@inherited标注的另外一个不是则运行报错

```java
// MyAnnotation
package cn.fkJava.test.testAnnotation;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
public @interface MyAnnotation {
    String value();
}
```

```java
package cn.fkJava.test.testAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
public @interface MyAnnotations {
    MyAnnotation[] value();
}

class TestXX{
    @MyAnnotation(value="a")
    @MyAnnotation(value="b")
    public static void main(String[] args) {

    }
}
```

## 6.类型注解

- TYPE_PARAMETER用于类型声明处
- TYPE_USE用于类型使用处

```java
// 这里是TYPE_PARAMETER
class TestXX<@MyAnnotation(value="c") T>{
    @MyAnnotation(value="a")
    @MyAnnotation(value="b")
    public static void main(String[] args) {

    }

    // 这里是PARAMETER
    void test(@MyAnnotation(value="a") int a){
        double aaa = (@MyAnnotation(value="c") double) a;// 这里要用TYPE_USE
    }
}
```

## 注解处理器

# 异常处理

> 如果没有异常类，处理异常的手段是用if...else..等臃肿的代码自行判断可能出现的问题并进行处理，代码臃肿且不易维护。

## 1.异常类型

- **检查性异常：**最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。
- **运行时异常：** 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。
- **错误：** 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。

## 2.异常类

![image-20200913103909138](.\javaSE.assets\image-20200913103909138.png)

**运行异常(RuntimeException)**

| **异常**                        | **描述**                                                     |
| :------------------------------ | :----------------------------------------------------------- |
| ArithmeticException             | 当出现异常的运算条件时，抛出此异常。例如，一个整数"除以零"时，抛出此类的一个实例。 |
| ArrayIndexOutOfBoundsException  | 用非法索引访问数组时抛出的异常。如果索引为负或大于等于数组大小，则该索引为非法索引。 |
| ArrayStoreException             | 试图将错误类型的对象存储到一个对象数组时抛出的异常。         |
| ClassCastException              | 当试图将对象强制转换为不是实例的子类时，抛出该异常。         |
| IllegalArgumentException        | 抛出的异常表明向方法传递了一个不合法或不正确的参数。         |
| IllegalMonitorStateException    | 抛出的异常表明某一线程已经试图等待对象的监视器，或者试图通知其他正在等待对象的监视器而本身没有指定监视器的线程。 |
| IllegalStateException           | 在非法或不适当的时间调用方法时产生的信号。换句话说，即 Java 环境或 Java 应用程序没有处于请求操作所要求的适当状态下。 |
| IllegalThreadStateException     | 线程没有处于请求操作所要求的适当状态时抛出的异常。           |
| IndexOutOfBoundsException       | 指示某排序索引（例如对数组、字符串或向量的排序）超出范围时抛出。 |
| NegativeArraySizeException      | 如果应用程序试图创建大小为负的数组，则抛出该异常。           |
| NullPointerException            | 当应用程序试图在需要对象的地方使用 `null` 时，抛出该异常     |
| NumberFormatException           | 当应用程序试图将字符串转换成一种数值类型，但该字符串不能转换为适当格式时，抛出该异常。 |
| SecurityException               | 由安全管理器抛出的异常，指示存在安全侵犯。                   |
| StringIndexOutOfBoundsException | 此异常由 `String` 方法抛出，指示索引或者为负，或者超出字符串的大小。 |
| UnsupportedOperationException   | 当不支持请求的操作时，抛出该异常。                           |

**检查性异常(ReflectiveOperationException)**

| **异常**                   | **描述**                                                     |
| :------------------------- | :----------------------------------------------------------- |
| ClassNotFoundException     | 应用程序试图加载类时，找不到相应的类，抛出该异常。           |
| CloneNotSupportedException | 当调用 `Object` 类中的 `clone` 方法克隆对象，但该对象的类无法实现 `Cloneable` 接口时，抛出该异常。 |
| IllegalAccessException     | 拒绝访问一个类的时候，抛出该异常。                           |
| InstantiationException     | 当试图使用 `Class` 类中的 `newInstance` 方法创建一个类的实例，而指定的类对象因为是一个接口或是一个抽象类而无法实例化时，抛出该异常。 |
| InterruptedException       | 一个线程被另一个线程中断，抛出该异常。                       |
| NoSuchFieldException       | 请求的变量不存在                                             |
| NoSuchMethodException      | 请求的方法不存在                                             |

## 3.常用方法

- getMessage()--异常信息
- getCause()--异常原因
- toString()--返回类的串级名字

## 4.异常处理

- try...catch
- throw
- throws

## 5.自定义异常

- 写一个类继承自Exception
- 重写对应报错的方法
- 捕获并处理自定义异常

# 反射

# java8特性

## 函数式接口

> 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法(Object中的方法不计入其中)，但是可以有多个非抽象方法的接口。
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

## stream

集合和Array可以转化为stream方便链式调用

- **stream()** − 为集合创建串行流。
- **parallelStream()** − 为集合创建并行流。

通过流可以调用一系列方便的API例如

- foreach
- count

参考`public interface Stream<T>`

# 补充：编码

## 1.UTF-8编码的二进制存储方式

![image-20200924171348664](.\javaSE.assets\image-20200924171348664.png)

首先查询unicode编码a，看unicode编码a对应的16进制，再按照上图对应找二进制格式，将a转成二进制数b，将b按照位数依次填入上述的x处即为最终存储的二进制数

## 2.字符集

ANSI是对应操作系统的编码，英文操作系统是ISO-8859-1,中文操作系统是GBK

![image-20200924171639786](.\javaSE.assets\image-20200924171639786.png)