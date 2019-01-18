package net.tifoha.ch_04._02;

import net.tifoha.ch_04.DepthFirstWalker;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StrongConnectedComponents extends DepthFirstWalker {
    protected final int[] ids;
    protected int count;
    private Map<Integer, Collection<Integer>> components = new HashMap<>();

    public StrongConnectedComponents(int verticesCount) {
        super(verticesCount);
        ids = new int[verticesCount];
    }

    @Override
    protected void preProcess(int v) {
        ids[v] = count;
        components
                .computeIfAbsent(count, k -> new HashSet<>())
                .add(v);
    }

    public boolean stronglyConnected(int v, int w) {
        return ids[v] == ids[w];
    }

    public int count() {
        return count;
    }

    public int compnent(int v) {
        return ids[v];
    }

    public Map<Integer, Collection<Integer>> components() {
        return components;
    }
}
