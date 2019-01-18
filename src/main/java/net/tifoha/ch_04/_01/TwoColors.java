package net.tifoha.ch_04._01;

import static net.tifoha.ch_04.GraphUtils.getScanner;

/**
 * @author Vitalii Sereda
 */
public class TwoColors {
    private final Boolean[] colors;
    private boolean twoColor = true;

    public TwoColors(Graph graph) {
        colors = new Boolean[graph.v()];
        for (int v = 0; v < graph.v(); v++) {
            if (colors[v] == null) {
                colors[v] = false;
                dfs(graph, v);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        for (int w : graph.adj(v)) {
            if (colors[w] == null) {
                colors[w] = !colors[v];
                dfs(graph, w);
            } else if (colors[w] == colors[v]) {
                twoColor = false;
                return;
            }
        }
    }

    public boolean isTwoColor() {
        return twoColor;
    }

    public static void main(String[] args) {
        ArrayGraph graph1 = new ArrayGraph(getScanner("tinyG.txt"));
        TwoColors colors1 = new TwoColors(graph1);
        System.out.println(colors1.isTwoColor());

        ArrayGraph graph2 = new ArrayGraph(6);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 5);
        graph2.addEdge(0, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(2, 4);
        TwoColors colors2 = new TwoColors(graph2);
        System.out.println(colors2.isTwoColor());

        ArrayGraph graph3 = new ArrayGraph(6);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 0);
        graph3.addEdge(3, 4);
//        graph3.addEdge(4, 2);
        TwoColors colors3 = new TwoColors(graph3);
        System.out.println(colors3.isTwoColor());
    }
}
