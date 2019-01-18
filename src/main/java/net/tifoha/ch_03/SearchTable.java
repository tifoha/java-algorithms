package net.tifoha.ch_03;

import java.util.Iterator;

/**
 * @author Vitalii Sereda
 */
public interface SearchTable<K extends Comparable<K>, V> extends Iterable<K> {
    V get(K key);

    void put(K key, V val);

    void delete(K key);

    boolean contains(K key);

    int size();

    boolean isEmpty();

    Iterable<K> keys();

    @Deprecated
    default Iterator<K> iterator() {
        return null;
    }
}
