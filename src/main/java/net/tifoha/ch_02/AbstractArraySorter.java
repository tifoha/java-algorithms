package net.tifoha.ch_02;

import java.util.Arrays;

/**
 * @author Vitalii Sereda
 */
public abstract class AbstractArraySorter<T extends Comparable<T>> {
    protected final T[] data;
    protected long compareCount = 0;
    protected long swapCount = 0;
    protected long readCount = 0;
    protected long writeCount = 0;

    protected AbstractArraySorter(T[] data) {
        this.data = data;
    }

    public abstract void sort();

    public boolean isLess(T a, T b) {
        compareCount++;
        return a.compareTo(b) < 0;
    }

    public void swap(int i, int j) {
        swapCount++;
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

    public void print() {
        System.out.printf("%s->compare: %d, swap: %d, read: %d, write: %d, total: %d %s%n",
                getClass().getSimpleName(),
                compareCount,
                swapCount,
                readCount,
                writeCount,
                readCount + writeCount,
                Arrays.toString(data));
        compareCount = 0;
        swapCount = 0;
        readCount = 0;
        writeCount = 0;
    }

    public boolean isSorted() {
        for (int i = 1; i < data.length; i++) {
            if (isLess(get(i), get(i - 1))) {
                return false;
            }
        }

        return true;
    }

    public int size() {
        return data.length;
    }

    protected T get(int index) {
        readCount++;
        return data[index];
    }

    protected void set(int index, T value) {
        writeCount++;
        data[index] = value;
    }
}
