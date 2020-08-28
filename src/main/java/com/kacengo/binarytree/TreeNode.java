package com.kacengo.binarytree;

/**
 * Created by Kacen on 2020-08-27
 */
public class TreeNode {
    public int key;
    public String data;
    public TreeNode leftNode;
    public TreeNode rightNode;
    public boolean isVisited = false;

    public TreeNode(int key, String data) {
        this.key = key;
        this.data = data;
    }

    public TreeNode(int key, String data, TreeNode leftNode, TreeNode rightNode) {
        this.key = key;
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
