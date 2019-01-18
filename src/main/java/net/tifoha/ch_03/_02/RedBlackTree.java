package net.tifoha.ch_03._02;

import net.tifoha.ch_03.SearchTable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;

import static java.util.Objects.requireNonNull;

/**
 * @author Vitalii Sereda
 */
public class RedBlackTree<K extends Comparable<K>, V> implements SearchTable<K, V> {
    private final Comparator<K> cmp;
    private Node root;

    public RedBlackTree() {
        this.cmp = Comparator.naturalOrder();
    }

    @Override
    public V get(K key) {
        return root != null ? root.get(key) : null;
    }

    @Override
    public void put(K key, V val) {
        requireNonNull(key);
        requireNonNull(val);
        if (root != null) {
            root.put(key, val);
        } else {
            root = new Node(key, val);
        }
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return root.size;
    }

    @Override
    public boolean isEmpty() {
        return size() > 0;
    }

    @Override
    public Iterable<K> keys() {
        Collection<K> keys = Collections.emptySet();
        if (root != null) {
            keys = new LinkedHashSet<>();
            root.deepFirst(keys);
        }
        return keys;
    }

    private class Node {
        private final K key;
        private V value;
        private int size = 0;
        private Node left;
        private Node right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private V put(K key, V value) {
            V oldValue = null;
            int c = cmp.compare(this.key, key);
            if (c > 0) {
                if (left == null) {
                    left = new Node(key, value);
                    size++;
                } else {
                    oldValue = left.put(key, value);
                }
            } else if (c < 0) {
                if (right == null) {
                    right = new Node(key, value);
                    size++;
                } else {
                    oldValue = right.put(key, value);
                }
            } else {
                oldValue = value;
                this.value = value;
            }
            return oldValue;
        }

        private V get(K key) {
            int c = cmp.compare(this.key, key);
            if (c > 0) {
                if (left == null) {
                    return null;
                }
                return left.get(key);
            } else if (c < 0) {
                if (right == null) {
                    return null;
                }
                return right.get(key);
            }
            return value;
        }

        public void deepFirst(Collection<K> keys) {
            if (left != null) {
                left.deepFirst(keys);
            }
            keys.add(key);
            if (right != null) {
                right.deepFirst(keys);
            }
        }
    }
}
