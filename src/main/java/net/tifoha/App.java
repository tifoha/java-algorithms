package net.tifoha;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        print("sdfasdg");
////        print(() -> "sadfas");
//        print(1 + 1, 1, 3, 5);
//        print(1.0);
        LocalDateTime beforeShift = LocalDateTime.of(2018, 11, 4, 1, 0);
        LocalDateTime afterShift = beforeShift.withHour(2);
        ZonedDateTime pst = ZonedDateTime.of(beforeShift, ZoneId.of("America/New_York"));
        ZonedDateTime current = pst.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println(pst.toLocalTime());
        System.out.println(current.toLocalTime());
        System.out.println();
        pst = ZonedDateTime.of(afterShift, ZoneId.of("America/New_York"));
        current = pst.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println(pst.toLocalTime());
        System.out.println(current.toLocalTime());

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
