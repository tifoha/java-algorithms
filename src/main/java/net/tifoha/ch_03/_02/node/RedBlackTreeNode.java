//package net.tifoha.ch_03._02.node;
//
//import java.util.Comparator;
//import java.util.Deque;
//import java.util.Iterator;
//import java.util.LinkedList;
//
//import static java.util.Objects.requireNonNull;
//import static net.tifoha.ch_02.SortUtils.less;
//import static net.tifoha.ch_03._02.node.TreeNode.sizeOf;
//
///**
// * @author Vitalii Sereda
// */
//public class RedBlackTreeNode<K> extends AbstractTreeNode<K> {
//    protected final K key;
//    protected final Comparator<K> cmp;
//    protected boolean red = true;
//
//
//    public RedBlackTreeNode(K key, Comparator<K> cmp, AbstractTreeNode<K> parent) {
//        super(parent);
//        this.key = requireNonNull(key);
//        this.cmp = cmp;
//        this.size = 1;
//    }
//
////    public RedBlackTreeNode<K> red(K key) {
////        return new RedBlackTreeNode<>(key, cmp, this);
////    }
////    public RedBlackTreeNode(K key) {
////        this(key, null, cmp);
////    }
//
//    public Iterator<RedBlackTreeNode<K>> deepFirst(RedBlackTreeNode<K> root) {
//        return new InorderDeepFirstIterator<>(root);
//    }
//
//    public Iterator<RedBlackTreeNode<K>> breathFirst(RedBlackTreeNode<K> root) {
//        return new BreathFirstIterator<>(root);
//    }
//
//    public K getKey() {
//        return key;
//    }
//
//    public RedBlackTreeNode<K> get(K key, Comparator<K> cmp) {
//        int c = cmp.compare(this.key, key);
//        if (c > 0) {
//            RedBlackTreeNode<K> left = getLeft();
//            return left == null ? this : left.get(key, cmp);
//        } else if (c < 0) {
//            RedBlackTreeNode<K> right = getLeft();
//            return right == null ? this : right.get(key, cmp);
//        }
//        return this;
//    }
//
//    @Override
//    public void swap(AbstractTreeNode<K> from, AbstractTreeNode<K> to) {
//        throw new UnsupportedOperationException();
//    }
//
//    public boolean put(K key) {
//        RedBlackTreeNode<K> node;
//        if (less(key, this.key, cmp)) {
//            node = getLeft();
//            boolean added = hasLeft() ? node.put(key) : setLeft(key);
//            if (added) {
//                size++;
//            }
//            return added;
//        } else {
//            node = getRight();
//            boolean added = hasRight() ? node.put(key) : setRight(key);
//            if (added) {
//                size++;
//            }
//            return added;
//        }
//    }
//
//    private boolean setLeft(K key) {
//        boolean added = true;
//        if (hasLeft()) {
//            added = false;
//        }
//        left = new RedBlackTreeNode<>(key, cmp, this);
//
//        return added;
//    }
//
//    private boolean setRight(K key) {
//        boolean added = true;
//        if (hasRight()) {
//            added = false;
//        }
//        right = new RedBlackTreeNode<>(key, cmp, this);
//
//        return added;
//    }
//
//    public boolean isRed() {
//        return red;
//    }
//
//    public void red() {
//        red = true;
//    }
//
//    public void black() {
//        red = false;
//    }
//
//    @Override
//    public RedBlackTreeNode<K> getLeft() {
//        return (RedBlackTreeNode<K>) super.getLeft();
//    }
//
//    @Override
//    public RedBlackTreeNode<K> getRight() {
//        return (RedBlackTreeNode<K>) super.getRight();
//    }
//
//    @Override
//    public RedBlackTreeNode<K> getParent() {
//        return (RedBlackTreeNode<K>) super.getParent();
//    }
//
//    public static <K> void flipColors(RedBlackTreeNode<K> node) {
//        red(node);
//        black(node.getLeft());
//        black(node.getRight());
//    }
//
//    private static <K> void red(RedBlackTreeNode<K> node) {
//        if (node != null) {
//            node.red = true;
//        }
//    }
//
//    private static <K> void black(RedBlackTreeNode<K> node) {
//        if (node != null) {
//            node.red = false;
//        }
//    }
//
//    public static <K> RedBlackTreeNode<K> rotateRight(RedBlackTreeNode<K> node) {
//        RedBlackTreeNode<K> root = node.getLeft();
//        node.left = root.right;
//        root.right = node;
//        root.red = node.red;
//        red(node);
//        root.size = node.size;
//        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
//
//        return root;
//    }
//
//    public static <K> RedBlackTreeNode<K> rotateLeft(RedBlackTreeNode<K> node) {
//        RedBlackTreeNode<K> root = node.getRight();
//        node.right = root.left;
//        root.left = node;
//        root.red = node.red;
//        red(node);
//        root.size = node.size;
//        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
//
//        return root;
//    }
//
////    public void setLeft(RedBlackTreeNode<K> node) {
////        if (node != null) {
////            node.parent = this;
////        }
////        left = node;
////    }
////
////    public void setRight(RedBlackTreeNode<K> node) {
////        if (node != null) {
////            node.parent = this;
////        }
////        right = node;
////    }
//
//    public static class BreathFirstIterator<K> implements Iterator<RedBlackTreeNode<K>> {
//        protected final Deque<RedBlackTreeNode<K>> deque = new LinkedList<>();
//
//        public BreathFirstIterator(RedBlackTreeNode<K> node) {
//            addNodeIfNotNull(node);
//        }
//
//        private void addNodeIfNotNull(RedBlackTreeNode<K> node) {
//            if (node != null) {
//                deque.addLast(node);
//            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            return !deque.isEmpty();
//        }
//
//        @Override
//        public RedBlackTreeNode<K> next() {
//            RedBlackTreeNode<K> node = deque.remove();
//            addNodeIfNotNull(node.getLeft());
//            addNodeIfNotNull(node.getRight());
//            return node;
//        }
//    }
//
//    public static class InorderDeepFirstIterator<K> implements Iterator<RedBlackTreeNode<K>> {
//        protected final Deque<RedBlackTreeNode<K>> deque = new LinkedList<>();
//
//        public InorderDeepFirstIterator(RedBlackTreeNode<K> node) {
//            addAllLeftNodes(node);
//        }
//
//        private void addAllLeftNodes(RedBlackTreeNode<K> node) {
//            if (node != null) {
//                deque.addFirst(node);
//                addAllLeftNodes(node.getLeft());
//            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            return !deque.isEmpty();
//        }
//
//        @Override
//        public RedBlackTreeNode<K> next() {
//            RedBlackTreeNode<K> node = deque.remove();
//            addAllLeftNodes(node.getRight());
//            return node;
//        }
//    }
//}
