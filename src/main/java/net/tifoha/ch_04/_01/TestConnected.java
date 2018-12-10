package net.tifoha.ch_04._01;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static net.tifoha.ch_04._01.GraphUtils.getScanner;

/**
 * @author Vitalii Sereda
 */
public class TestConnected {
    public static void main(String[] args) {
        ArrayGraph graph = new ArrayGraph(getScanner("tinyG.txt"));
        ConnectedComponents cc = new ConnectedComponents(graph);
        Map<Integer, Collection<Integer>> components = new HashMap<>();
        for (int v = 0; v < graph.v(); v++) {
            int id = cc.id(v);
            components
                    .computeIfAbsent(id, k -> new LinkedList<>())
                    .add(v);
        }
        System.out.println(components);
        System.out.println(graph);
    }
}
