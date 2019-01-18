package net.tifoha.interview_tasks;

import java.util.Arrays;

/**
 * @author Vitalii Sereda
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{2, 3, 4};
        System.out.println(Arrays.toString(mergeArrays(a, b)));
        System.out.println(slowMedian(a, b));
    }

    public static double slowMedian(int[] a, int[] b) {
        int[] resultingArray = mergeArrays(a, b);

        int index = (resultingArray.length - 1) / 2;
        if (resultingArray.length % 2 != 0) {
            return resultingArray[index];
        }

        return (resultingArray[index] + resultingArray[index + 1]) / 2.0;
    }

    public static double fastMedian(int[] a, int[] b) {
        if (a.length > b.length) {
            return fastMedian(b, a);
        }
        return 0;
//
//        if (a[a.length-1]>)
//
//        int index = (resultingArray.length - 1) / 2;
//        if (resultingArray.length % 2 != 0) {
//            return resultingArray[index];
//        }
//
//        return (resultingArray[index] + resultingArray[index + 1]) / 2.0;
    }

    public static int[] mergeArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (aIndex < a.length) {
                if (bIndex < b.length && b[bIndex] < a[aIndex]) {
                    result[i] = b[bIndex++];
                } else {
                    result[i] = a[aIndex++];
                }
            } else {
                result[i] = b[bIndex++];
            }
        }

        return result;
    }
}
