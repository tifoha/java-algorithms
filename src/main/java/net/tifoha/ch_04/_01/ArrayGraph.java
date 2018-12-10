package net.tifoha.ch_04._01;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Vitalii Sereda
 */
public class ArrayGraph extends Graph {
    private Collection<Integer>[] vertices;
    private int edgeCount;

    @SuppressWarnings("unchecked")
    public ArrayGraph(int v) {
        vertices = (Collection<Integer>[]) new Collection[v];
        for (int i = 0; i < v; i++) {
            vertices[i] = new LinkedList<>();
        }
    }

    public ArrayGraph(Scanner scanner) {
        this(scanner.nextInt());
        int e = scanner.nextInt();
        for (int i = 0; i < e; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            addEdge(v, w);
        }
    }

    @Override
    public int v() {
        return vertices.length;
    }

    @Override
    public int e() {
        return edgeCount;
    }

    @Override
    public void addEdge(int v, int w) {
        if (vertices[v].add(w) && vertices[w].add(v)) {
            edgeCount++;
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return vertices[v];
    }
}
