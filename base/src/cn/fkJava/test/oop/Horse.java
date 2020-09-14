package cn.fkJava.test.oop;

public class Horse implements Traffics,Animal{
    public void move() {
        Traffics.move();
        Animal.move();
    }
}
