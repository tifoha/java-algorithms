package net.tifoha.ch_02._01;

import net.tifoha.ch_02.AbstractArraySorter;

import java.util.Arrays;

/**
 * @author Vitalii Sereda
 * 1. Не чуствителен к сортированым данным
 * 2. Перестановки: N
 * 3. Сравнения: N^2/2
 * 4. Сложность: N^2
 * 5. Память: 1
 */
public class SelectionArraySorter<T extends Comparable<T>> extends AbstractArraySorter<T> {
    public SelectionArraySorter(T[] data) {
        super(Arrays.copyOf(data, data.length));
    }

    @Override
    public void sort() {
        for (int i = 0; i < size(); i++) {
            int nextIndex = getNextIndex(i);
            swap(i, nextIndex);
        }
    }

    private int getNextIndex(int startIndex) {
        int result = startIndex;
        T value = get(startIndex);
        for (int i = startIndex; i < size(); i++) {
            T currentValue = get(i);
            if (isLess(currentValue, value)) {
                value = currentValue;
                result = i;
            }
        }
        return result;
    }
}
