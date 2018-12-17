package net.tifoha.ch_04._01;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Vitalii Sereda
 */
public class ArrayGraph implements Graph {
    protected final Collection<Integer>[] vertices;
    protected int edgeCount;

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

    public String toString() {
        StringBuilder s = new StringBuilder(v() + " вершин, " + e() + " ребер\n");
        for (int v = 0; v < v(); v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) {
                s.append(w).append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }
}
