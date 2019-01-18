package net.tifoha.ch_04._02;

import net.tifoha.ch_04.DepthFirstWalker;
import net.tifoha.ch_04._01.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static net.tifoha.ch_04.GraphUtils.tinyDG;

public class DepthFirstOrder extends DepthFirstWalker {
    private final Deque<Integer> pre = new LinkedList<>();
    private final Queue<Integer> post = new LinkedList<>();
    private final Deque<Integer> reversePost = new LinkedList<>();

    public DepthFirstOrder(Graph graph) {
        super(graph.v());
        walk(graph);
    }

    public Deque<Integer> pre() {
        return pre;
    }

    public Queue<Integer> post() {
        return post;
    }

    public Deque<Integer> reversePost() {
        return reversePost;
    }

    @Override
    protected void preProcess(int v) {
        pre.offer(v);
    }

    @Override
    protected void postProcess(int v) {
        post.offer(v);
        reversePost.push(v);
    }

    public static void main(String[] args) {
        Digraph graph = tinyDG();
        DepthFirstOrder dfo = new DepthFirstOrder(graph);
        System.out.println(graph);
        System.out.println(dfo.pre());
        System.out.println(dfo.post());
        System.out.println(dfo.reversePost());
    }
}
