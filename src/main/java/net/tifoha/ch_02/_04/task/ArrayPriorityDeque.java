package net.tifoha.ch_02._04.task;

import java.util.Comparator;
import java.util.Objects;

import static java.lang.Math.log;
import static net.tifoha.ch_02.SortUtils.*;

/**
 * @author Vitalii Sereda
 */
public class ArrayPriorityDeque<E> implements PriorityDeque<E> {
    private int size;
    private final Comparator<E> cmp;
    private E[] heap;

    public ArrayPriorityDeque(E[] elements, Comparator<E> cmp) {
        this.cmp = cmp;
        this.size = elements.length;
        this.heap = constructBidirectionalHeap(elements, cmp);
    }

    public <T extends Comparable<T>> ArrayPriorityDeque(T[] elements) {
        this((E[]) elements, (Comparator<E>) Comparator.naturalOrder());
    }

    private E[] constructBidirectionalHeap(E[] array, Comparator<E> cmp) {
        int size = array.length;
        heap = (E[]) new Object[size + 1];
        System.arraycopy(array, 0, heap, 1, size);

        for (int i = getParent(size); i > 0; i--) {
            sink(i);
        }

        return heap;
    }

    private void sink(int i) {
        if (isMinLevel(i)) {
            sinkMin(i);
        } else {
            sinkMax(i);
        }
    }

    private void sinkMin(int index) {
        if (!hasChildren(index)) {
            return;
        }
        int minIndex = minChildOrGrandchildIndex(index);
        if (index != minIndex && less(heap[minIndex], heap[index], cmp)) {
            exch(heap, index, minIndex);
            if (isGrandChild(index, minIndex)) {
                int parent = getParent(minIndex);
                if (less(heap[parent], heap[minIndex], cmp)) {
                    exch(heap, parent, minIndex);
                }
                sinkMin(minIndex);
            }
        }
    }

    private void sinkMax(int index) {
        if (!hasChildren(index)) {
            return;
        }
        int maxIndex = maxChildOrGrandchildIndex(index);
        if (index != maxIndex && less(heap[index], heap[maxIndex], cmp)) {
            exch(heap, index, maxIndex);
            if (isGrandChild(index, maxIndex)) {
                int parent = getParent(maxIndex);
                if (less(heap[maxIndex], heap[parent], cmp)) {
                    exch(heap, parent, maxIndex);
                }
                sinkMax(maxIndex);
            }
        }
    }

    private int minChildIndex(int index) {
        int child = index * 2;
        if (child < size) {
            return minIndexByValue(child, child + 1);
        }
        return child;
    }

    private int maxChildIndex(int index) {
        int child = index * 2;
        if (child < size) {
            return maxIndexByValue(child, child + 1);
        }
        return child;
    }

    private int minChildOrGrandchildIndex(int index) {
        int child = index * 2;
        int minIndex = child;
        if (hasChildren(child)) {
            minIndex = minIndexByValue(minIndex, minChildIndex(child));
        }
        if (child < size) {
            child++;
            minIndex = minIndexByValue(minIndex, child);
            if (hasChildren(child)) {
                minIndex = minIndexByValue(minIndex, minChildIndex(child));
            }
        }
        return minIndex;
    }

    private int maxChildOrGrandchildIndex(int index) {
        int child = index * 2;
        int maxIndex = child;
        if (hasChildren(child)) {
            maxIndex = maxIndexByValue(maxIndex, maxChildIndex(child));
        }
        if (child < size) {
            child++;
            maxIndex = maxIndexByValue(maxIndex, child);
            if (hasChildren(child)) {
                maxIndex = maxIndexByValue(maxIndex, maxChildIndex(child));
            }
        }
        return maxIndex;
    }

    private boolean isGrandChild(int parent, int scion) {
        return scion > (parent * 2 + 1);
    }

    private int minIndexByValue(int i, int j) {
        return less(heap[i], heap[j], cmp) ? i : j;
    }

    private int maxIndexByValue(int i, int j) {
        return less(heap[i], heap[j], cmp) ? j : i;
    }

    private boolean hasChildren(int index) {
        return index * 2 <= size;
    }

    private int getParent(int index) {
        return index / 2;
    }

    @Deprecated
    private static boolean isMinLevel2(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("Index can't be less than 1(" + index + ")");
        }
        int depth = 0;
        for (int i = index; i / 2 > 0; i /= 2) {
            depth++;
        }
        return depth % 2 == 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == heap.length - 1) {
            resize();
        }

        heap[++size] = e;
        swim(size);
        return true;
    }

    private void swim(int index) {
        int parent = getParent(index);
        if (parent == 0) {
            return;
        }
        if (isMinLevel(index)) {
            if (less(heap[parent], heap[index], cmp)) {
                exch(heap, parent, index);
                swimMax(parent);
            } else {
                swimMin(index);
            }
        } else {
            if (less(heap[index], heap[parent], cmp)) {
                exch(heap, parent, index);
                swimMin(parent);
            } else {
                swimMax(index);
            }
        }
    }

    private void swimMax(int index) {
        int grandParent = index / 4;
        if (grandParent <= 0) {
            return;
        }
        if (less(heap[grandParent], heap[index], cmp)) {
            exch(heap, index, grandParent);
        }
        swimMax(grandParent);
    }

    private void swimMin(int index) {
        int grandParent = index / 4;
        if (grandParent <= 0) {
            return;
        }
        if (less(heap[index], heap[grandParent], cmp)) {
            exch(heap, index, grandParent);
        }
        swimMin(grandParent);
    }

    private boolean hasParent(int index) {
        return getParent(index) != 0;
    }

    private void resize() {
        E[] newHeap = (E[]) new Object[heap.length * 2];
        System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    @Override
    public E pollFirst() {
        E min = heap[1];
        move(heap, size--, 1);
        sink(1);
        return min;
    }

    @Override
    public E pollLast() {
        int maxChildIndex = size > 1 ? maxChildIndex(1) : 1;
        E max = heap[maxChildIndex];
        move(heap, size--, maxChildIndex);
        sink(maxChildIndex);
//        System.out.println(this);
        return max;
    }

    @Override
    public E peekFirst() {
        return heap[1];
    }

    @Override
    public E peekLast() {
        return heap[size > 1 ? maxChildIndex(1) : 1];
    }

    private static boolean isMinLevel(int i) {
        return depth(i) % 2 == 0;
    }

    private static int depth(int index) {
        return (int) (log(index) / log(2));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int depth = depth(size);
        int currentLevel = 1;
        for (int i = 1; i < heap.length; i++) {
            if (depth(i) + 1 > currentLevel) {
                builder.append('\n');
                currentLevel++;
            }
            int spaceCount = (2 << depth - currentLevel) - 1;
            addSpaces(builder, spaceCount);
            builder.append(Objects.toString(heap[i] == null ? "x" : heap[i]));
            addSpaces(builder, spaceCount);
            builder.append(' ');
        }
        return builder.toString();
    }

    private void addSpaces(StringBuilder builder, int count) {
        for (int i = 0; i < count; i++) {
            builder.append(' ');
        }
    }

    public static void main(String[] args) {
//        System.out.println(log(4));
//        Integer[] heap = new Integer[32];
//        Comparator<Integer> cmp = Integer::compareTo;
        ArrayPriorityDeque<Integer> deque1 = new ArrayPriorityDeque<>(new Integer[]{3, 8, 7, 2, 2, 5, 2, 3, 4, 7, 5, 2, 3, 5, 6});
        ArrayPriorityDeque<Integer> deque2 = new ArrayPriorityDeque<>(new Integer[]{3, 8, 7, 2, 2, 5, 2, 3, 4, 7, 5, 2, 3, 5, 6});
        System.out.println(deque1);
//        deque.offer(9);
//        deque.offer(1);
//        System.out.println(deque);
        while (deque1.size > 0) {
            System.out.printf("%s, ", deque1.pollFirst());
        }
        System.out.println();
        while (deque2.size > 0) {
//            deque2.pollLast();
            System.out.printf("%s, ", deque2.pollLast());
        }
    }
}
