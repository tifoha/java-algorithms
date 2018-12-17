package net.tifoha.ch_04._02;

import net.tifoha.ch_04._01.ArrayGraph;

import java.util.Scanner;

/**
 * @author Vitalii Sereda
 */
public class ArrayDigraph extends ArrayGraph implements Digraph {
    public ArrayDigraph(int v) {
        super(v);
    }

    public ArrayDigraph(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void addEdge(int v, int w) {
        if (vertices[v].add(w)) {
            edgeCount++;
        }
    }

    @Override
    public Digraph reverse() {
        ArrayDigraph result = new ArrayDigraph(v());
        for (int v = 0; v < v(); v++) {
            for (Integer w : adj(v)) {
                result.addEdge(w, v);
            }
        }
        return result;
    }
}
