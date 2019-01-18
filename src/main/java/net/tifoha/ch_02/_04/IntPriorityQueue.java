package net.tifoha.ch_02._04;

import static net.tifoha.ch_02.SortUtils.*;

/**
 * @author Vitalii Sereda
 */
public class IntPriorityQueue {
    private final int[] queue;
    private int size;

    public IntPriorityQueue(int initialCapacity) {
        this.queue = new int[initialCapacity + 1];
        this.size = 0;
    }

    public IntPriorityQueue(int[] queue) {
        this(queue.length);
        for (int aData : queue) {
            add(aData);
        }
    }

    public void add(int value) {
        queue[++size] = value;
        swim(queue, size);
    }

    public int poll() {
        int max = queue[1];
        exch(queue, 1, size--);
        sink(queue, 1, size);
        return max;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        IntPriorityQueue queue = new IntPriorityQueue(6);
        for (int i = 0; i < 9; i++) {
            queue.add(i);
        }

        while (queue.size() > 0) {
            System.out.printf("%s, ", queue.poll());
        }
    }

    public int peek() {
        return queue[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
