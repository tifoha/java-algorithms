package net.tifoha.ch_04._02;

import java.util.*;

import static net.tifoha.ch_04._01.GraphUtils.getScanner;

/**
 * @author Vitalii Sereda
 */
public class TopologicalSort {
    private final Deque<Integer> pre = new LinkedList<>();
    private final Queue<Integer> post = new LinkedList<>();
    private final Deque<Integer> reversePost = new LinkedList<>();
    private final BitSet marks;
    private boolean hasCycles = false;

    public TopologicalSort(Digraph graph) {
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
        Integer previous = pre.peekLast();
        pre.offer(v);
        for (Integer w : graph.adj(v)) {
            if (!isMarked(w)) {
                dfs(graph, w);
            } else if (!Objects.equals(w, previous)) {
                hasCycles = true;
                return;
            }
        }
        post.offer(v);
        reversePost.push(v);
    }

    public static void main(String[] args) {
        ArrayDigraph graph = new ArrayDigraph(getScanner("tinyDG.txt"));
        TopologicalSort sort = new TopologicalSort(graph);
        System.out.println(sort.hasCycles());
        sort.pre().forEach(System.out::println);
        sort.post().forEach(System.out::println);
        sort.reversePost().forEach(System.out::println);
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public Iterable<Integer> post() {
        return post;
    }


    private Iterable<Integer> pre() {
        return pre;
    }

    private void mark(int v) {
        marks.set(v);
    }

    public boolean hasCycles() {
        return hasCycles;
    }
}
