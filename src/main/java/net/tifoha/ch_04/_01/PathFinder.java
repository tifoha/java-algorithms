package net.tifoha.ch_04._01;

/**
 * @author Vitalii Sereda
 */
public interface PathFinder {
    boolean hasPathTo(int v);

    Iterable<Integer> pathTo(int v);
}
