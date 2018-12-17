package net.tifoha.ch_04._02;

import java.util.*;

import static net.tifoha.ch_04._01.GraphUtils.getScanner;

/**
 * @author Vitalii Sereda
 */
public class DirectedCycles {
    private Collection<Collection<Integer>> cycles = new ArrayList<>();
    private Deque<Integer> stack = new LinkedList<>();
    private final BitSet marks;

    public DirectedCycles(Digraph graph) {
        marks = new BitSet(graph.v());
        for (int v = 0; v < graph.v(); v++) {
            if (!isMarked(v)) {
                dfs(graph, v);
            }
        }
    }

    private boolean isMarked(int v) {
        return marks.get(v);
    }

    private void dfs(Digraph graph, int v) {
        mark(v);
        Integer previous = stack.peek();
        stack.push(v);
        for (Integer w : graph.adj(v)) {
            if (!isMarked(w)) {
                dfs(graph, w);
            } else if (!Objects.equals(w, previous)) {
                LinkedList<Integer> cycle = new LinkedList<>();
                cycles.add(cycle);
                // TODO: 14.12.2018 не находить подциклы
                for (int p : stack) {
                    cycle.add(p);
                    if (p == w) {
                        break;
                    }
                }
            }
        }
        stack.poll();
    }

    private void mark(int v) {
        marks.set(v);
    }

    public boolean hasCycles() {
        return !cycles.isEmpty();
    }

    public Collection<Collection<Integer>> cycles() {
        return cycles;
    }

    public static void main(String[] args) {
        ArrayDigraph graph1 = new ArrayDigraph(getScanner("tinyDG.txt"));
        DirectedCycles cycles1 = new DirectedCycles(graph1);
        System.out.println(cycles1.hasCycles());
        cycles1.cycles().forEach(System.out::println);

//        ArrayGraph graph2 = new ArrayGraph(6);
//        graph2.addEdge(0,1);
//        graph2.addEdge(1,5);
//        graph2.addEdge(0,2);
//        graph2.addEdge(2,3);
//        graph2.addEdge(2,4);
//        Cycles cycles2 = new Cycles(graph2);
//        System.out.println(cycles2.hasCycles());
    }
}
