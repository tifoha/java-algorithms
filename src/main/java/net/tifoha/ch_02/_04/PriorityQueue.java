package net.tifoha.ch_02._04;

/**
 * @author Vitalii Sereda
 */
public interface  PriorityQueue<E> {
    void add(E value);

    E poll();

    E peek();

    int size();

    boolean isEmpty();
}
