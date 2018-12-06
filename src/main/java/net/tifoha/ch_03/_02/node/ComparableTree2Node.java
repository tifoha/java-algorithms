//package net.tifoha.ch_03._02.node;
//
//import java.util.Comparator;
//
//import static java.util.Objects.requireNonNull;
//
///**
// * @author Vitalii Sereda
// */
//public class ComparableTree2Node<K, V> extends Tree2Node<K, V> {
//    protected final Comparator<K> cmp;
//
//    public ComparableTree2Node(K key, V value, Comparator<K> cmp) {
//        super(key, value);
//        this.cmp = requireNonNull(cmp);
//    }
//
//
//    public V put(K key, V value) {
//        V oldValue = null;
//        int c = cmp.compare(this.key, key);
//        if (c > 0) {
//            if (hasLeft()) {
//                oldValue = getLeft().put(key, value);
//            } else {
//                setLeft(key, value);
//            }
//        } else if (c < 0) {
//            if (hasRight()) {
//                oldValue = getRight().put(key, value);
//            } else {
//                setRight(key, value);
//            }
//        } else {
//            oldValue = value;
//            this.value = value;
//        }
//        return oldValue;
//    }
//
//    public V get(K key) {
//        int c = cmp.compare(this.key, key);
//        if (c > 0) {
//            if (hasLeft()) {
//                return getLeft().get(key);
//            }
//            return null;
//        } else if (c < 0) {
//            if (hasRight()) {
//                return getRight().get(key);
//            }
//            return null;
//        }
//        return value;
//    }
//
//    public Comparator<K> getCmp() {
//        return cmp;
//    }
//
//    @Override
//    public ComparableTree2Node<K, V> getLeft() {
//        return (ComparableTree2Node<K, V>) super.getLeft();
//    }
//
//    @Override
//    public ComparableTree2Node<K, V> getRight() {
//        return (ComparableTree2Node<K, V>) super.getRight();
//    }
//
//    @Override
//    public void setLeft(K key, V value) {
//        super.setLeft(new ComparableTree2Node<>(key, value, cmp));
//    }
//
//    @Override
//    public void setRight(K key, V value) {
//        super.setRight(new ComparableTree2Node<>(key, value, cmp));
//    }
//}
