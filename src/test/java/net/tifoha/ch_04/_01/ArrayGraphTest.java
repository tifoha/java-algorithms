package net.tifoha.ch_04._01;

import net.tifoha.ch_04.GraphUtils;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author Vitalii Sereda
 */
public class ArrayGraphTest {

    @Test
    public void adj() {
        try (Scanner scanner = GraphUtils.getScanner("tinyG.txt")) {
            Graph g = new ArrayGraph(scanner);
            System.out.println(g);
        }
    }
}