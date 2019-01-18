package net.tifoha.ch_03._02.node;

import java.util.Comparator;

import static net.tifoha.ch_03._02.node.TreeNode.sizeOf;

/**
 * @author Vitalii Sereda
 */
public abstract class AbstractTreeNode<K> implements TreeNode<K> {
    protected AbstractTreeNode<K> parent;
    protected AbstractTreeNode<K> left;
    protected AbstractTreeNode<K> right;
    protected int size = 0;

    public AbstractTreeNode(AbstractTreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public AbstractTreeNode<K> getLeft() {
        return left;
    }

    public void setLeft(AbstractTreeNode<K> node) {
        if (node != null) {
            node.parent = this;
        }
        changeSizeIfNeed(left, node);
        left = node;
    }

    @Override
    public AbstractTreeNode<K> getRight() {
        return right;
    }

    public void setRight(AbstractTreeNode<K> node) {
        if (node != null) {
            node.parent = this;
        }
        changeSizeIfNeed(right, node);
        right = node;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    @Override
    public AbstractTreeNode<K> getParent() {
        return parent;
    }

    @Override
    public int getSize() {
        return size;
    }

    public AbstractTreeNode<K> root() {
        if (hasParent()) {
            return parent.root();
        }
        return this;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public abstract AbstractTreeNode<K> get(K key, Comparator<K> cmp);

    public abstract void swap(AbstractTreeNode<K> from, AbstractTreeNode<K> to);

    protected void changeSizeIfNeed(AbstractTreeNode<?> from, AbstractTreeNode<?> to) {
        int sizeDiff = sizeOf(to) - sizeOf(from);
        if (sizeDiff != 0) {
            changeSize(sizeDiff);
        }
    }

    protected void changeSize(int sizeDiff) {
        AbstractTreeNode<K> node = this;
        while (node != null) {
            node.size += sizeDiff;
            node = node.parent;
        }
    }

    public abstract K put(K key, Comparator<K> cmp);
}
