package net.tifoha.ch_04._01;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Vitalii Sereda
 */
public class DepthFirstSearch implements Search, PathFinder {
    private final boolean[] marked;
    private final int s;
    private int count;
    private int[] edgeTo;

    public DepthFirstSearch(Graph graph, int s) {
        int v = graph.v();
        this.marked = new boolean[v];
        this.edgeTo = new int[v];
        for (int i = 0; i < v; i++) {
            edgeTo[i] = -1;
        }
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public boolean hasPathTo(int v) {
        return marked(v);
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return Collections.emptySet();
        }

        Deque<Integer> path = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);

        return path;
    }
}
