package net.tifoha.ch_03;

import net.tifoha.ch_03._01.SequentialSearchST2;
import net.tifoha.ch_03._03.SeparateChainingHashST;
import net.tifoha.utils.StdIn;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.LongStream;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.requireNonNull;

/**
 * @author Vitalii Sereda
 */
public class SearchUtils {
    public int getBucketNumber(Object obj, int size) {
        return (requireNonNull(obj).hashCode() & 0x7FFFFFFF) % size;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        for (int i = 1; i < 50; i++) {
            System.out.printf("Birthsday probability: group=%s, p=%s%n", i, birthdayProbability(i));
        }
//        int hash = -1;
//        System.out.println(hash & 0x7FFFFFFF);
//        System.out.println((hash ^ (hash >>> 16)));
//        System.out.println((hash >>> 16));
//        String s = "aoffckzdaoffckzdaoffckzd";
//        for (int i = 0; i < s.length(); i+=2) {
//            System.out.println(s.substring(i, i+8).hashCode());
//        }
//        System.out.println("BBBBBB".hashCode());
//        System.out.println("AaAaAa".hashCode());
//        System.out.println("BBAaBB".hashCode());
        Object stub = new Object();
        int initialCapacity = 65521;
        Map<String, Object> map = new HashMap<>(initialCapacity);
        SeparateChainingHashST<String, Object> st = new SeparateChainingHashST<>(initialCapacity);
        StdIn.setFile("src\\main\\resources\\mobydick.txt");
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            map.put(key, stub);
            st.put(key, stub);
        }

        System.out.println(map.size());

        Object[] table = getTable(map);
        UnaryOperator<Object> nextNodeExtractor = getNextField(table);
        HashMap<Integer, Integer> bucketSizes = new HashMap<>();
        for (Object node : table) {
            int size = 0;
            while (node != null) {
                node = nextNodeExtractor.apply(node);
                size++;
            }
//            if (size > 0) {
            bucketSizes.compute(size, (k, v) -> v == null ? 1 : v + 1);
//            }
        }
        System.out.println(bucketSizes);
        bucketSizes.clear();
        for (SequentialSearchST2<String, ?> node : st.st) {
            bucketSizes.compute(node.size(), (k, v) -> v == null ? 1 : v + 1);
        }
        System.out.println(bucketSizes);

        Random r = new Random(100);
        Map<Integer, AtomicInteger> hash1 = new HashMap<>();
        Map<Integer, AtomicInteger> hash2 = new HashMap<>();
        for (int i = 0; i < 1024; i++) {
            int value = r.nextInt(1024);
            hash1.computeIfAbsent(hash(value, 32), k -> new AtomicInteger()).incrementAndGet();
            hash2.computeIfAbsent(hash1(value, 31), k -> new AtomicInteger()).incrementAndGet();
        }
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println();
    }

    private static Integer hash1(int value, int n) {
        return value % n;
    }

    private static int hash(int value, int n) {
        return (value ^ (value >>> 16)) % n;
    }

    public static UnaryOperator<Object> getNextField(Object[] table) throws NoSuchFieldException {
        Class<?> nodeClass = table.getClass().getComponentType();
        Field nextField = nodeClass.getDeclaredField("next");
        nextField.setAccessible(true);
        return node -> {
            try {
                return nextField.get(node);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static Object[] getTable(Map<String, ?> map) throws NoSuchFieldException, IllegalAccessException {
        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        return (Object[]) tableField.get(map);
    }

    public static BigDecimal birthdayProbability(int groupCount) {
        BigDecimal allVariants = BigDecimal.valueOf(365).pow(groupCount);
        BigDecimal uniqueCombinations = LongStream
                .range(0, groupCount)
                .map(x -> 365 - x)
                .mapToObj(BigDecimal::valueOf)
                .reduce(ONE, BigDecimal::multiply);
        return ONE.subtract(uniqueCombinations.divide(allVariants, 2, HALF_UP));
    }
}
