package net.tifoha;

import java.util.Arrays;
import java.util.function.Supplier;

import static java.lang.String.join;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        print("sdfasdg");
//        print(() -> "sadfas");
        print(1 + 1, 1, 3, 5);
        print(1.0);
    }


    public static void print(int i, int... ii) {
        System.out.println("This is int " + i);
    }

    public static void print(Supplier<Integer> intSupplier) {
        System.out.println("This is int supplier " + intSupplier.get());
    }

    public static void print(String s) {
        System.out.println("This is string " + s);
    }

    public static <T> void print(T s) {
        System.out.println("This is generic " + s);
    }
}
