//package net.tifoha.ch_03._02.node;
//
///**
// * @author Vitalii Sereda
// */
//public class Test<V, T extends Test<V, T>> {
//    private T parent;
//
//    public T getParent() {
//        return parent;
//    }
//
//    public static void main(String[] args) {
//        Test2<Integer, Test2<Integer, Test2>> t = new Test2<>();
//        Test2<Integer> p = t.getParent();
//    }
//}
//
//class Test2<V, T extends Test2<V, T>> extends Test<V, T> {
//    private V value;
//
//    public V getValue() {
//        return value;
//    }
//}
//
//
