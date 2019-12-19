package com.yydcyy.bst;

/**
 * @author YYDCYY
 * @create 2019-09-17
 * BST 完整实现.
 *
 * 同目录其他文件说明 :  BST1 实现 二分搜索树中 关键算法 fin()的递归和非递归两种实现方法
 * BSTBasic 实现 二分搜索树的常用方法
 */
public class BST <Key extends Comparable<Key>, Value>{
    // 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }

    private Node root;// 根节点
    private int count;// 树的节点个数

    public BST(){
        root = null;
        count = 0;
    }
    public int size(){return count;    }
    public boolean isEmpty(){return count == 0; }

    /**
     *     二分搜索树的辅助函数
     */
    private Value search(Key key){
        return search( root , key );
    }

    public void insert( Key key, Value value ){
        root = insert( root, key, value );
    }

    /**
     向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法  /  递归本性同链表
     返回插入新节点后的二分搜索树的根
     node 当前节点  key-value 带插入节点值
     若key值同, 则为更新
     return终止条件注意必须写在前面. 只有两处. 1) null return new Node. 2) key = node.key return node;
     */
    private Node insert(Node node, Key key, Value value) {
        if (node == null){
            count ++;
            return new Node(key, value);
        }

        //相等, 执行更新
        if (key.compareTo(node.key) == 0){
            node.value = value;
        }
        else if (key.compareTo(node.key) < 0){
            node.left = insert(node.left, key, value);
        }
        else
            node.right = insert(node.right, key, value);

        // 处理完毕, 返回当前节点 , 终止条件
        return node;
    }

    //递归先考虑终止return条件
    private Value search(Node node, Key key){
        if( node == null) return null;

        if( key.compareTo(node.key) == 0 )  return node.value;
        else if( key.compareTo(node.key) < 0 ) return search( node.left, key );
        else return search( node.right, key);
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
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，学过,复习时改进
        // 以后有机会，我会测 这个打乱和 RandomArr[] 模板区别


        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer,String> bst = new BST<Integer,String>();
        for( int i = 0; i < N; i ++){
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));
        }

        for( int i = 0 ; i < 2*N ; i ++ ) {
            String res = bst.search(i);
            if (i < N) {
                if ( !res.equals(Integer.toString(i)) )   // i< n 时 res == null ?怎么会
                    throw new RuntimeException("aeesert v == i");
            }
            else //i>=N 情况
                if( res != null ) throw new RuntimeException("aeesert v == i-1");
        }
        System.out.println("Over");
    }
}
