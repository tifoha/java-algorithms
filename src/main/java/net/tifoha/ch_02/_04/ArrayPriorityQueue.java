package net.tifoha.ch_02._04;

import java.util.Arrays;
import java.util.Comparator;

import static net.tifoha.ch_02.SortUtils.exch;
import static net.tifoha.ch_02.SortUtils.less;

/**
 * @author Vitalii Sereda
 */
public class ArrayPriorityQueue<E> implements IndexedPriorityQueue<E> {
    private static final int DEFAULT_INITIAL_SIZE = 4;
    private final Comparator<E> comparator;
    private int[] pq;
    private int[] invertedPq; //inverse of pq:  invertedPq[pq[i]] = pq[invertedPq[i]] = i
    private E[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        this.values = (E[]) new Object[DEFAULT_INITIAL_SIZE + 1];
        Arrays.fill(this.values, 0, this.values.length, null);
        this.pq = new int[DEFAULT_INITIAL_SIZE + 1];
        Arrays.fill(this.pq, 0, this.pq.length, -1);
        this.invertedPq = new int[DEFAULT_INITIAL_SIZE + 1];
        Arrays.fill(this.invertedPq, 0, this.invertedPq.length, -1);
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue() {
        this((Comparator<E>) Comparator.naturalOrder());
    }

    @Override
    public void add(E value) {
        resize();
        values[size] = value;
        int index = size + 1;
        invertedPq[size] = index;
        pq[index] = size++;
//        System.out.printf("i = %d, %d, %d%n", index, invertedPq[pq[index]], pq[invertedPq[index]]);
        swim(index);
    }

    @Override
    public E poll() {
        int index = pq[1];
        E max = values[index];
        values[index] = null;
        move(size--, 1);
        sink(1);
        return max;
    }

    @Override
    public E peek() {
        return values[pq[1]];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E set(int index, E newValue) {
        E oldValue = get(index);
        values[index] = newValue;
        if (less(newValue, oldValue, comparator)) {
            sink(invertedPq[index]);
        } else {
            swim(invertedPq[index]);
        }

        return oldValue;
    }

    @Override
    public E delete(int index) {
        E oldValue = get(index);
        values[index] = null;
        E newValue = get(pq[size]);
        int pqIndex = invertedPq[index];
        move(size--, pqIndex);
        if (less(newValue, oldValue, comparator)) {
            sink(pqIndex);
        } else {
            swim(pqIndex);
        }

        return oldValue;
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (values.length == size + 1) {
            E[] oldValues = values;
            int newlength = Math.min(Integer.MAX_VALUE, size * 2);

            E[] newArray = (E[]) new Object[newlength];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;

            newlength++;
            int[] newIntArray = new int[newlength];
            System.arraycopy(pq, 1, newIntArray, 1, size);
            Arrays.fill(newIntArray, size + 1, newlength, -1);
            pq = newIntArray;

            newIntArray = new int[newlength];
            System.arraycopy(invertedPq, 1, newIntArray, 1, size);
            Arrays.fill(newIntArray, size + 1, newlength, -1);
            invertedPq = newIntArray;
        }
    }

    public void swim(int index) {
        while (index > 1 && less(values[pq[index / 2]], values[pq[index]], comparator)) {
            exch(invertedPq, pq[index / 2], pq[index]);
            exch(pq, index / 2, index);
            index = index / 2;
        }
    }

    public void sink(int index) {
        while (index * 2 <= size) {
            int j = index * 2;
            //check end of heap and choose biggest node
            if (j < size && less(values[pq[j]], values[pq[j + 1]], comparator)) j++;
            if (!less(values[pq[index]], values[pq[j]], comparator)) break;
            exch(invertedPq, pq[index], pq[j]);
            exch(pq, index, j);
            index = j;
        }
    }

    public void move(int from, int to) {
        invertedPq[pq[to]] = from;
        invertedPq[pq[from]] = -1;
        pq[to] = pq[from];
//        pq[from] = -1;
    }


    public static void main(String[] args) {
        ArrayPriorityQueue<Integer> pq1 = new ArrayPriorityQueue<>();
        ArrayPriorityQueue<Integer> pq2 = new ArrayPriorityQueue<>();
        int[] values = new int[]{2, 1, 5, 8, 1, 0, 5, 1, 4, 6};
//        int size = 10;
        int size = values.length;
        for (int i = 0; i < size; i++) {
//            int v = StdRandom.uniform(10);
            int v = values[i];
            pq1.add(v);
            pq2.add(v);
            System.out.printf("%s, ", v);
        }
        System.out.println();
        int index = 2;
        System.out.printf("values[%d] = %d%n", index, pq2.get(index));
        pq2.set(index, 9);
        System.out.printf("values[%d] = %d%n", index, pq2.get(index));
        pq2.delete(9);
        while (!pq2.isEmpty()) {
            System.out.printf("%s, ", pq2.poll());
        }
        System.out.println();
        while (!pq1.isEmpty()) {
            System.out.printf("%s, ", pq1.poll());
        }
    }
}
