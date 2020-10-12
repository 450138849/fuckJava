package cn.fkJava.test.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Food {
    void produce();
}

class Apple implements Food {
    @Override
    public void produce() {
        System.out.println("苹果装箱!");
    }
}

class ProxyFactory implements InvocationHandler {
    // 目标对象
    private Object targetObject;

    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;

        preProduce();
        ret = method.invoke(targetObject, args);
        afterProduce();

        return ret;
    }

    private void afterProduce() {
        System.out.println("代理后置工作");
    }

    private void preProduce() {
        System.out.println("代理前置工作");
    }
}

public class testDynamicProxy {
    public static void main(String[] args) {
        ProxyFactory proxy = new ProxyFactory();
        Food food = (Food) proxy.newProxyInstance(new Apple());// newProxyInstance传入参数有接口类型，所以能够识别接口类型但是不能识别为具体的实现类类型
        food.produce();
    }
}
