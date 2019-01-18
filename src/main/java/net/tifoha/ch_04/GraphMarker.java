package net.tifoha.ch_04;

import java.util.BitSet;

public abstract class GraphMarker {
    protected final BitSet marks;

    public GraphMarker(int verticesCount) {
        marks = new BitSet(verticesCount);
    }

    protected boolean marked(int v) {
        return marks.get(v);

    }

    protected boolean notMarked(int v) {
        return !marked(v);
    }

    protected void mark(int v) {
        marks.set(v);
    }
}
