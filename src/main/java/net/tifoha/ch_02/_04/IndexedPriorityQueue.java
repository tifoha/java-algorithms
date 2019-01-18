package net.tifoha.ch_02._04;

/**
 * @author Vitalii Sereda
 */
public interface IndexedPriorityQueue<E> extends PriorityQueue<E> {
//    void add(int index, E value);

    E set(int index, E value);

//    boolean contains(int index);

    E delete(int index);


    E get(int index);
}
