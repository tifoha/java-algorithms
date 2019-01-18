package net.tifoha.ch_04._01;

import static net.tifoha.ch_04.GraphUtils.tinyDG;

/**
 * @author Vitalii Sereda
 */
public class Cycles {
    private boolean hasCycles;
    private final boolean[] marked;

    public Cycles(Graph graph) {
        marked = new boolean[graph.v()];
        for (int v = 0; v < graph.v(); v++) {
            if (!marked[v]) {
                dfs(graph, v, v);
            }
        }
    }

    private void dfs(Graph graph, int v, int s) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w, v);
            } else if (w != s) {
                hasCycles = true;
            }
        }
    }

    public boolean hasCycles() {
        return hasCycles;
    }

    public static void main(String[] args) {
        Graph graph1 = tinyDG();
        Cycles cycles1 = new Cycles(graph1);
        System.out.println(cycles1.hasCycles());

        ArrayGraph graph2 = new ArrayGraph(6);
        graph2.addEdge(0,1);
        graph2.addEdge(1,5);
        graph2.addEdge(0,2);
        graph2.addEdge(2,3);
        graph2.addEdge(2,4);
        Cycles cycles2 = new Cycles(graph2);
        System.out.println(cycles2.hasCycles());
    }
}
