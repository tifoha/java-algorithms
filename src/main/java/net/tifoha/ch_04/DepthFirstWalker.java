package net.tifoha.ch_04;

import net.tifoha.ch_04._01.Graph;

public abstract class DepthFirstWalker extends GraphMarker {
    public DepthFirstWalker(int verticesCount) {
        super(verticesCount);
    }

    protected void walk(Graph graph) {
        for (int v = 0; v < graph.v(); v++) {
            if (notMarked(v)) {
                dfs(graph, v);
            }
        }
    }

    public void dfs(Graph graph, int v) {
        mark(v);
        preProcess(v);
        for (Integer w : graph.adj(v)) {
            if (notMarked(w)) {
                dfs(graph, w);
            }
        }
        postProcess(v);
    }

    protected void preProcess(int v) {

    }

    protected void postProcess(int v) {

    }
}
