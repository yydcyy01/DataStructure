package com.yydcyy.bst;

/**
 * @author YYDCYY
 * @create 2019-09-17
 * 二分搜索树基本定义
 */
public class BSTBasic<K extends Comparable<K>, V>{
    // 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
    private class Node{
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private Node root;// 根节点
    private int count;// 树的节点个数

    public BSTBasic(){
        root = null;
        count = 0;
    }
    public int size(){return count;    }
    public boolean isEmpty(){return count == 0; }

}
