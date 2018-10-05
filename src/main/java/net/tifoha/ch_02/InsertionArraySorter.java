package net.tifoha.ch_02;

/**
 * @author Vitalii Sereda
 * 1. Xуствителен к сортированым данным (выполняется быстрее)
 * 2. Перестановки: N^2/2 > 0
 * 3. Сравнения: N^2/2 > N
 * 4. Сложность: N^2
 * 5. Память: 1
 */
public class InsertionArraySorter<T extends Comparable<T>> extends AbstractArraySorter<T> {
    protected InsertionArraySorter(T[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int i = 1; i < size(); i++) {
            for (int j = i; j > 0 && isLess(get(j), get(j - 1)); j--) {
                swap(j, j - 1);
            }
        }
    }
}
