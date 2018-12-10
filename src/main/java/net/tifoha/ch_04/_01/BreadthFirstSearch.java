package net.tifoha.ch_04._01;

import java.util.*;

/**
 * @author Vitalii Sereda
 */
public class BreadthFirstSearch implements Search, PathFinder {
    private final boolean[] marked;
    private final int s;
    private int count;
    private final int[] edgeTo;
    private final int[] distances;
    private int maxDistance;
    private final Set<Collection<Integer>> cycles = new HashSet<>();

    public BreadthFirstSearch(Graph graph, int s) {
        int v = graph.v();
        this.s = s;
        this.marked = new boolean[v];
        this.edgeTo = new int[v];
        this.distances = new int[v];
        for (int i = 0; i < v; i++) {
            edgeTo[i] = -1;
            distances[i] = -1;
        }
        bfs(graph);
    }

    private void bfs(Graph graph) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        distances[s] = 0;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            maxDistance = Math.max(maxDistance, distances[v]);
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distances[w] = distances[v] + 1;
                    queue.offer(w);
                } else if (w != edgeTo[v]) {
                    cycle(v, w);
                }
            }
            count++;
        }
    }

//    private void cycle(Graph g, Map<Integer, Integer> path, int v, int s) {
//        if (v == s) {
//            return;
//        }
//        for (int w : g.adj(v)) {
//            if (!path.containsKey(w)) {
//                path.put(w, v);
//            }
//        }
//    }

    private void cycle(int v, int w) {
        Set<Integer> cycle = new HashSet<>();
        while (cycle.add(v) && cycle.add(w)) {
            v = edgeTo[v] >= 0 ? edgeTo[v] : v;
            w = edgeTo[w] >= 0 ? edgeTo[w] : w;
        }
        cycles.add(cycle);
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

    /**
     * @return Кратчайшее расстояние до самой дальней вершины
     */
    public int eccentricity() {
        return maxDistance;
    }

    /**
     * Расстояние от вершины v до основания графа заданного
     * в конструкторе
     *
     * @param v целевая вершина
     * @return
     */
    public int distanceTo(int v) {
        return distances[v];
    }

    /**
     * Если графж не содержит циклы тогда вернет -1
     *
     * @return Кратчайший цикл
     */
    public int girth() {
        return cycles.stream().mapToInt(Collection::size).min().orElse(-1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BreadthFirstSearch{");
        sb.append("s=").append(s);
        sb.append(", count=").append(count);
        sb.append(", maxDistance=").append(maxDistance);
        sb.append('}');
        return sb.toString();
    }
}
