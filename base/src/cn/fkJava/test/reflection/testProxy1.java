package cn.fkJava.test.reflection;

interface Cloth {
    void produce();
}

class ClothFactory implements Cloth {

    @Override
    public void produce() {
        System.out.println("工厂生产一件衣服");
    }
}

class ClothProxy implements Cloth {
    Cloth cloth = new ClothFactory();

    @Override
    public void produce() {
        preProduce();
        cloth.produce();
        afterProduce();
    }

    private void afterProduce() {
        System.out.println("代理后续工作");
    }

    private void preProduce() {
        System.out.println("代理前置工作");
    }
}

public class testProxy1 {
    public static void main(String[] args) {
        Cloth cloth =  new ClothProxy();
        cloth.produce();//代理前置工作 工厂生产一件衣服 代理后续工作
    }
}
