package net.tifoha.ch_03._02.node;

/**
 * @author Vitalii Sereda
 */
public interface TreeNode<K> {
    static int sizeOf(TreeNode<?> from) {
        return from != null ? from.getSize() : 0;
    }

    K getKey();

    TreeNode<K> getLeft();

    TreeNode<K> getRight();

    TreeNode<K> getParent();

    int getSize();
}
