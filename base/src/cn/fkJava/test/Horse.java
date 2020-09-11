package cn.fkJava.test;

public class Horse implements Traffics,Animal{
    public void move() {
        Traffics.move();
        Animal.move();
    }
}
