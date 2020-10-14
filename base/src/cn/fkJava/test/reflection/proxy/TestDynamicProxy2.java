package cn.fkJava.test.reflection.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGLIB不需要实现接口
 */
class Water {
    public void move() {
        System.out.println("水在流动！");
    }
}

class Factory implements MethodInterceptor {
    public Object getInstance(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        //告诉cglib，生成的子类需要继承哪个类
        enhancer.setSuperclass(clazz);
        //设置回调
        enhancer.setCallback(this);
        //生成源代码
        //编译成class文件
        //加载到JVM中，并返回被代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        methodProxy.invokeSuper(o, args);
        after();
        return null;
    }

    private void before() {
        System.out.println("代理前！");
    }

    private void after() {
        System.out.println("代理后!");
    }
}

public class TestDynamicProxy2 {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Water water = (Water) factory.getInstance(Water.class);
        water.move();
    }
}
