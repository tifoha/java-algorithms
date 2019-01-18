package net.tifoha.ch_02._04;

import net.tifoha.utils.StdRandom;

import java.util.Comparator;

/**
 * @author Vitalii Sereda
 */
public class TopM {
    public static void main(String[] args) {
        int elementsCount = 5;
        PriorityQueue<Integer> pq = new ArrayPriorityQueue<>(Comparator.<Integer>reverseOrder());
        for (int i = 0; i < 10; i++) {
            int value = StdRandom.uniform(100);
            System.out.printf("%s, ", value);
            if (pq.size() == elementsCount) {
                if (pq.peek() < value) {
                    int min = pq.poll();
                    pq.add(value);
                }
            } else {
                pq.add(value);
            }
        }
        System.out.println();
        while (!pq.isEmpty()) {
            System.out.printf("%s, ", pq.poll());
        }
    }
}
