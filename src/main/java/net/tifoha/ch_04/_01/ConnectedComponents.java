package net.tifoha.ch_04._01;

/**
 * @author Vitalii Sereda
 */
public class ConnectedComponents {
    private final int[] ids;
    private final boolean[] marked;
    private int count;

    public ConnectedComponents(Graph graph) {
        marked = new boolean[graph.v()];
        ids = new int[graph.v()];
        for (int v = 0; v < graph.v(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        ids[v] = count;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return ids[v] == ids[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return ids[v];
    }
}
