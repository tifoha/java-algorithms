package net.tifoha.ch_03._02.node;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static net.tifoha.ch_02.SortUtils.*;

/**
 * @author Vitalii Sereda
 */
public class Balanced23Tree<K> extends AbstractCollection<K> {
    protected final Comparator<K> cmp;
    public AbstractTreeNode<K> root;

    public Balanced23Tree(Comparator<K> cmp) {
        this.cmp = cmp;
    }

    public Balanced23Tree() {
        this((Comparator<K>) Comparator.naturalOrder());
    }

    @Override
    public Iterator<K> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.getSize();
    }

    @Override
    public boolean add(K key) {
        Objects.requireNonNull(key);
        if (root == null) {
            root = new Tree2Node(key, null);
            return true;
        }
        return addKey(root, key);
    }

    private boolean addKey(AbstractTreeNode<K> root, K key) {
        AbstractTreeNode<K> node = root.get(key, cmp);
        return node.put(key, cmp) == null;
    }

    private void swap(AbstractTreeNode<K> parent, AbstractTreeNode<K> from, AbstractTreeNode<K> to) {
        if (parent != null) {
            parent.swap(from, to);
        } else {
            root = to;
        }
    }

    /**
     * @author Vitalii Sereda
     */
    public class Tree2Node extends AbstractTreeNode<K> {

        private final K key;

        public Tree2Node(K key, AbstractTreeNode<K> parent) {
            super(parent);
            this.key = requireNonNull(key);
            this.size = 1;
        }

        public Tree2Node(K key) {
            this(key, null);
        }


        //    public Iterator<Tree2Node<K>> deepFirst(Tree2Node<K, V> node) {
        //        return new InorderDeepFirstIterator<>(node);
        //    }
        //
        //    public Iterator<Tree2Node<K, V>> breathFirst(Tree2Node<K, V> node) {
        //        return new BreathFirstIterator<>(node);
        //    }
        public K getKey() {
            return key;
        }

        @Override
        public AbstractTreeNode<K> get(K key, Comparator<K> cmp) {
            int c = cmp.compare(this.key, key);
            if (c > 0) {
                return left == null ? this : left.get(key, cmp);
            } else if (c < 0) {
                return right == null ? this : right.get(key, cmp);
            }
            return this;
        }

        //Tree2Node
        @Override
        public void swap(AbstractTreeNode<K> from, AbstractTreeNode<K> to) {
            if (to instanceof Balanced23Tree.Tree2Node) {
                Tree3Node node;
                Tree2Node child = (Tree2Node) to;
                if (right == from) {
                    node = new Tree3Node(key, child.key);
                    node.setLeft(left);
                    node.setMiddle(child.left);
                    node.setRight(child.right);
                    Balanced23Tree.this.swap(parent, this, node);
                } else if (left == from) {
                    node = new Tree3Node(child.key, key);
                    node.setLeft(child.left);
                    node.setMiddle(child.right);
                    node.setRight(right);
                    Balanced23Tree.this.swap(parent, this, node);
                }
                return;
            }
//            to.parent = this;
            if (left == from) {
                setLeft(to);
//                changeSizeIfNeed(from,to);
            } else if (right == from) {
                setRight(to);
//                changeSizeIfNeed(from,to);
            }
        }

        @Override
        public K put(K key, Comparator<K> cmp) {
            AbstractTreeNode<K> node;
            if (eq(key, this.key, cmp)) {
                node = toTree2Node(key);
                Balanced23Tree.this.swap(parent, this, node);
                return this.key;
            }
            node = toTree3Node(key, cmp);
            Balanced23Tree.this.swap(parent, this, node);
            return null;
        }

        private Tree2Node toTree2Node(K key) {
            Tree2Node node = new Tree2Node(key);
            node.setLeft(left);
            node.setRight(right);
            return node;
        }

        private Tree3Node toTree3Node(K key, Comparator<K> cmp) {
            Tree3Node node;
            if (less(key, this.key, cmp)) {
                node = new Tree3Node(key, this.key);
            } else {
                node = new Tree3Node(this.key, key);
            }
            node.setLeft(left);
            node.setRight(right);

            return node;
        }

        @Override
        public String toString() {
            return "{" + key + '}';
        }
    }

    /**
     * @author Vitalii Sereda
     */
    public class Tree3Node extends AbstractTreeNode<K> {
        protected final K leftKey;
        protected final K rightKey;
        protected AbstractTreeNode<K> middle;

        public Tree3Node(K leftKey, K rightKey, AbstractTreeNode<K> parent) {
            super(parent);
            this.leftKey = requireNonNull(leftKey);
            this.rightKey = requireNonNull(rightKey);
            this.size = 2;
        }

        public Tree3Node(K leftKey, K rightKey) {
            this(leftKey, rightKey, null);
        }

        public K getLeftKey() {
            return leftKey;
        }

        public K getRightKey() {
            return rightKey;
        }

        public AbstractTreeNode<K> getMiddle() {
            return middle;
        }

        public void setMiddle(AbstractTreeNode<K> node) {
            if (node != null) {
                node.parent = this;
            }
            changeSizeIfNeed(middle, node);
            middle = node;
        }

        public boolean hasMiddle() {
            return middle != null;
        }

        @Override
        public AbstractTreeNode<K> get(K key, Comparator<K> cmp) {
            int cLeft = cmp.compare(this.leftKey, key);
            int cRight = cmp.compare(this.rightKey, key);
            if (cLeft > 0) {
                return left == null ? this : left.get(key, cmp);
            } else if (cRight < 0) {
                return right == null ? this : right.get(key, cmp);
            } else if (cLeft < 0 && cRight > 0) {
                return middle == null ? this : middle.get(key, cmp);
            }

            return this;
        }

        //Tree3Node
        @Override
        public void swap(AbstractTreeNode<K> from, AbstractTreeNode<K> to) {
            if (to instanceof Balanced23Tree.Tree2Node) {
                AbstractTreeNode<K> node;
                if (left == from) {
                    node = new Tree2Node(leftKey);
                    node.setLeft(to);
                    node.setRight(new Tree2Node(rightKey));
                    node.right.setLeft(middle);
                    node.right.setRight(right);
                    Balanced23Tree.this.swap(parent, this, node);
                } else if (right == from) {
                    node = new Tree2Node(rightKey);
                    node.setLeft(new Tree2Node(leftKey));
                    node.left.setLeft(left);
                    node.left.setRight(middle);
                    node.setRight(to);
                    Balanced23Tree.this.swap(parent, this, node);
                } else if (middle == from) {
                    node = to;
                    Tree2Node l = new Tree2Node(leftKey);
                    l.setLeft(left);
                    l.setRight(node.left);
                    Tree2Node r = new Tree2Node(rightKey);
                    r.setLeft(node.right);
                    r.setRight(right);
                    node.setLeft(l);
                    node.setRight(r);
                    Balanced23Tree.this.swap(parent, this, node);
                }
            } else {
                to.parent = this;
                if (left == from) {
                    setLeft(to);
//                    changeSizeIfNeed(from,to);
                } else if (right == from) {
                    setRight(to);
//                    changeSizeIfNeed(from,to);
                } else if (middle == from) {
                    setMiddle(to);
//                    changeSizeIfNeed(from,to);
                }
            }
        }

        @Override
        public K put(K key, Comparator<K> cmp) {
            AbstractTreeNode<K> node;
            if (eq(key, this.leftKey, cmp)) {
                node = toTree3Node(key, rightKey);
                Balanced23Tree.this.swap(parent, this, node);
                return this.leftKey;
            } else if (eq(key, this.rightKey, cmp)) {
                node = toTree3Node(leftKey, key);
                Balanced23Tree.this.swap(parent, this, node);
                return this.rightKey;
            }
            node = toTree2Node(key, cmp);
            Balanced23Tree.this.swap(parent, this, node);
            return null;
        }

        private AbstractTreeNode<K> toTree3Node(K leftKey, K rightKey) {
            Tree3Node node = new Tree3Node(leftKey, rightKey);
            node.setLeft(left);
            node.setMiddle(middle);
            node.setRight(right);

            return node;
        }

        public Tree2Node toTree2Node(K key, Comparator<K> cmp) {
            Tree2Node node;
            if (less(key, leftKey, cmp)) {
                node = new Tree2Node(leftKey);
                node.setLeft(new Tree2Node(key));
                node.setRight(new Tree2Node(rightKey));
            } else if (more(key, rightKey, cmp)) {
                node = new Tree2Node(rightKey);
                node.setLeft(new Tree2Node(leftKey));
                node.setRight(new Tree2Node(key));
            } else {
                node = new Tree2Node(key);
                node.setLeft(new Tree2Node(leftKey));
                node.setRight(new Tree2Node(rightKey));

            }
            node.left.setLeft(left);
            node.left.setRight(middle);
            node.right.setRight(right);
            return node;
        }

        @Override
        public String toString() {
            return "{" + leftKey + ":" + rightKey + "}";
        }

        @Override
        public K getKey() {
            return leftKey;
        }
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(map.put(1, 1));
//        Balanced23Tree<Integer> tree = new Balanced23Tree<>();
//        tree.addAll(Arrays.asList(6, 5, 4, 3, 2, 1, 7, 8, 9));
//        Balanced23Tree<String> tree = new Balanced23Tree<>();
//        tree.addAll(asList("SEARCHXMPL".split("")));

        Balanced23Tree<Character> tree = new Balanced23Tree<>();
//        Balanced23Tree<Character> tree = new Balanced23Tree<>((a, b) -> a > b ? 1 : -1);
//        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            tree.add(c);
            if (i > 27 && tree.size() != 27) {
                System.out.println(c);
            }
        }
        System.out.println(tree.size());
    }
}
