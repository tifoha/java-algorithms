package net.tifoha.utils;

import net.tifoha.ch_03._02.node.AbstractTreeNode;

import javax.swing.*;

/**
 * @author Vitalii Sereda
 */
public class TreeVisualizer {
    public static void show(AbstractTreeNode<?> root) {
        TreeWindow frame = new TreeWindow(root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 320);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

    }
}
