package cn.fkJava.test.generics.border;

public interface TestGenericsInterface<T> {

}

// 实现的时候继续使用泛型(在实例化的时候指定泛型类型)
class GenericsImpl1<T> implements TestGenericsInterface<T>{

}

// 实现的时候已经制定了泛型类型
class GenericsImpl2 implements TestGenericsInterface<String>{

}


