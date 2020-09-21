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
