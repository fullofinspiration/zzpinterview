package com.zzp.interview;

import com.zzp.interview.Tool.BinaryTree;

import java.util.LinkedList;

/**
 * 层级遍历
 * 1、这个面试我没有写出来，其实是用的linkedlist
 * 2、用linkedList的add和remove方法即可
 * 3、要判断是否为空时要用linkedList.isempty 而不是linkedList == null
 */
public class HierarchyTraverse {
    public void hierachyTraverse(BinaryTree binaryTree) {
        LinkedList<BinaryTree> linkedList = new LinkedList<>();
        if (binaryTree == null)
            return;
        linkedList.add(binaryTree);
        while (!linkedList.isEmpty()) {
            BinaryTree rootBinaryTree = linkedList.remove();
            System.out.println(rootBinaryTree.number);
            if (rootBinaryTree.leftNode != null)
                linkedList.add(rootBinaryTree.leftNode);
            if (rootBinaryTree.rightNode != null)
                linkedList.add(rootBinaryTree.rightNode);
        }
    }

    public static void main(String[] args) {
        HierarchyTraverse hierarchyTraverse = new HierarchyTraverse();
        BinaryTree binaryTree1 = new BinaryTree();
        BinaryTree binaryTree2 = new BinaryTree();
        BinaryTree binaryTree3 = new BinaryTree();
        BinaryTree binaryTree4 = new BinaryTree();
        BinaryTree binaryTree5 = new BinaryTree();
        BinaryTree binaryTree6 = new BinaryTree();
        binaryTree1.leftNode = binaryTree2;
        binaryTree1.rightNode = binaryTree3;
        binaryTree2.leftNode = binaryTree4;
        binaryTree2.rightNode = binaryTree5;
        binaryTree3.leftNode = binaryTree6;
        binaryTree1.number = 1;
        binaryTree2.number = 2;
        binaryTree3.number = 3;
        binaryTree4.number = 4;
        binaryTree5.number = 5;
        binaryTree6.number = 6;
        hierarchyTraverse.hierachyTraverse(binaryTree1);

    }
}
