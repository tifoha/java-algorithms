package net.tifoha.ch_02._04.task;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Math.abs;

/**
 * @author Vitalii Sereda
 */
public class DynamicMedian {
    private Double median;
    private Queue<Integer> right = new PriorityQueue<>(Comparator.naturalOrder());
    private Queue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());

    public double getMedian() {
        return median;
    }

    private void refreshMedian() {
        int diff = getDiff();
        if (diff > 0) {
            median = (double) left.peek();
        } else if (diff < 0) {
            median = (double) right.peek();
        } else if (left.size() != 0) {
            median = (double) (left.peek() + right.peek()) / 2.0;
        }
    }

    public void addValue(int v) {
        if (median == null || v < median) {
            left.offer(v);
        } else {
            right.offer(v);
        }
        balance();
        refreshMedian();
    }

    private void balance() {
        int diff = getDiff();
        if (abs(diff) > 1) {
            if (diff > 0) {
                right.offer(left.poll());
            } else {
                left.offer(right.poll());
            }
        }
    }

    private int getDiff() {
        return left.size() - right.size();
    }

    public static void main(String[] args) {
        DynamicMedian dm = new DynamicMedian();
        int[] values = {3, 2, 5, 6, 3, 1, 0, 4, 7, 7, 7, 7,7,7};
        for (int v : values) {
            dm.addValue(v);
            System.out.printf("v = %d, m = %f%n", v, dm.getMedian());
        }
        System.out.println();
    }
}
