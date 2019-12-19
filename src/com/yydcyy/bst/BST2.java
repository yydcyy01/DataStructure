package com.yydcyy.bst;

import cn.yydcyy.list.LinkedListQueue;

/**
 * @author YYDCYY
 * @create 2019-09-18
 */
public class BST2<E extends Comparable<E>>{
    private class Node{
        private E e;
        private Node left, right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node root;
    private int size;

    public BST2(){
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = addHelper(root, e);
    }

    private Node addHelper(Node root, E e) {
        if (root == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(root.e) > 0) {
            root.right = addHelper(root.right, e);
        } else if (e.compareTo(root.e) < 0) {
            root.left = addHelper(root.left, e);
        }
        // 等不处理
        return root;
    }

    public boolean contains(E e){
        return containsHelper(root, e);
    }

    private boolean containsHelper(Node root, E e)  {
        if (root == null) {
            return false;
        }
        if (e.compareTo(root.e) == 0)
            return true;
        else if (e.compareTo(root.e) < 0)
            return containsHelper(root.left, e);
        else {  //(e.compareTo(root.e) > 0)
            return containsHelper(root.right, e);
        }
    }

    //先序遍历
    public void preOrder() {
        preOrderHelper(root);
    }

    private void preOrderHelper(Node root) {
        if (root == null) {
            return;
        }
        //先序的关键
        System.out.println(root.e);
        preOrderHelper(root.left);
        preOrderHelper(root.right);
    }

    public void inOrder() {
        inOrderHelper(root);
    }

    private void inOrderHelper(Node root) {
        if (root == null) {
            return;
        }
        inOrderHelper(root.left);
        //中序的关键
        System.out.println(root.e);
        inOrderHelper(root.right);
    }

    public void postOrder() {
        postOrderHelper(root);
    }

    private void postOrderHelper(Node root) {
        if (root == null) {
            return;
        }
        postOrderHelper(root.left);
        postOrderHelper(root.right);
        // 后序的关键
        System.out.println(root.e);
    }

    //层序遍历
    public void levelOrder() {
        LinkedListQueue<Node> q = new LinkedListQueue<Node>();
        q.enqueue(root);
        //不为空,一直遍历
        while (!q.isEmpty()) {
            Node cur = q.dequeue();
            System.out.println(cur.e);

            if (cur.left != null)
                q.enqueue(cur.left);
            if (cur.right != null)
                q.enqueue(cur.right);
        }
    }

    public E minimun() {
        return minimunHlper(root).e;
    }

    //* 二分寻找树性质, max_Left即最小数
    private Node minimunHlper(Node root) {

        if (root == null) {
            throw new IllegalArgumentException("Find minimun failed,BST is empty!");
        }

        if (root.left == null) {
            return root;
        }

        return minimunHlper(root.left);
    }

    public E maximun() {
        return maximunHelper(root).e;
    }


     //* 二分寻找树性质, max_Right即最小数
    private Node maximunHelper(Node root) {
        if (root == null)
            throw new IllegalArgumentException("Find maximun failed,BST is empty");

        if (root.right == null)
            return root;

        return maximunHelper(root.right);
    }

    //删除E
    public void remove(E e){
        root = removeHelper(root,e);
    }

    //root.right = null; 275 269 我觉得这两个赋值反了
    private Node removeHelper(Node root , E e){
        if(root == null){
            return null;
        }

        if(e.compareTo(root.e) <0){
            root.left = removeHelper(root.left,e);
            //终止条件,返回当前节点
            return root;
        }else if(e.compareTo(root.e) >0){
            root.right = removeHelper(root.right,e);
            return root;
        }else {// root.e == e 情况
            if (root.left == null && root.right != null) {
                Node rightNode = root.right;
                root.left = null; //交给垃圾回收机制了
                size --;
                return rightNode; //(直接移上去)占坑
            }
            if (root.right == null && root.left != null) {
                Node leftNode = root.left;
                root.right = null;
                size --;
                return leftNode;
            }

            //左右子树均不为空情况
            Node successor = minimunHlper(root.right);
            // 我此方法实现, 删除 min{root.right}, 也可以实现删除 max{root.left} , 都是符合二分搜索树定义
            successor.right = removeMinHelper(root.right);
            successor.left = root.left;
            return successor;
        }
    }

    public E removeMin(E e){
        E ret = minimun();
        root = removeMinHelper(root);
        return ret;
    }
    private Node removeMinHelper(Node root){
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        //删除6. 一下
        if(root.left == null){
            //为啥不直接return root.right呢 递归不就接上了?! : C++思维, 考虑释放空间吧
            Node rightNode =root.right;
            root.right =null;
            size --;
            return rightNode;
        }/*
        if(root.left == null){
            //为啥不直接return root.right呢 递归不就接上了?!
            return root.right;
        }*/
        //(当前节点)root左不为空,递归找到最左(min)
        root.left = removeMinHelper(root.left);
        return root;
    }

    public static void main(String[] args){
        int N = 1000000;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        // 打乱数组顺序 是打乱, 不重不漏! 优秀!
        for(int i = 0 ; i < N ; i ++){
            int pos = (int) (Math.random() * (i));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
        // 没有这个打乱顺序过程, 则 bst 退化成链表, N 测试 10000 就会栈溢出
        // 打乱后, 100W 没问题

        // 建树过程
        BST2<Integer> bst = new BST2<>();
        for( int i = 0; i < N; i ++)
            bst.add(arr[i]);


        for( int i = 0 ; i < 2*N ; i ++ ) {
            boolean res = bst.contains(i);
            if (i < N) {
                if (!res) // 不包含
                    throw new RuntimeException("aeesert BST.contains is Error");
            }
            else //i>=N  但 bst.contains == true 情况
                if(res) throw new RuntimeException("aeesert ???");
        }
        System.out.println("Over");
    }
}
