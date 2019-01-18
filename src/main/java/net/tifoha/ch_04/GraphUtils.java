package net.tifoha.ch_04;

import net.tifoha.ch_04._01.ArrayGraph;
import net.tifoha.ch_04._01.Graph;
import net.tifoha.ch_04._02.ArrayDigraph;
import net.tifoha.ch_04._02.Digraph;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GraphUtils {
    private static final Path RESOURCE_PATH = Paths.get("src/main/resources/");

    public static Digraph tinyDG() {
        return new ArrayDigraph(getScanner("tinyDG2.txt"));
    }

    public static Graph tinyG() {
        return new ArrayGraph(getScanner("tinyG.txt"));
    }

    public static Digraph tinyDAG() {
        return new ArrayDigraph(getScanner("tinyDAG.txt"));
    }

    public static Scanner getScanner(String fileName) {
        try {
            InputStream in = Files.newInputStream(RESOURCE_PATH.resolve(fileName));
            return new Scanner(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
