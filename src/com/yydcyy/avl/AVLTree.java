package com.yydcyy.avl;


import cn.yydcyy.map.Map;

/**
 * @author YYDCYY
 * @create 2019-10-03
 * 在计算机科学中，AVL树是最早被发明的自平衡二叉查找树。在AVL树中，任一节点对应的两棵子树的最大高度差为1，因此它也被称为高度平衡树。
 * 查找、插入和删除在平均和最坏情况下的时间复杂度都是{\displaystyle O(\log {n})}
 * 增加和删除元素的操作则可能需要借由一次或多次树旋转，以实现树的重新平衡。
 *
 * 节点的平衡因子是它的左子树的高度减去它的右子树的高度（有时相反）。
 * 带有平衡因子1、0或 -1的节点被认为是平衡的。带有平衡因子 -2或2的节点被认为是不平衡的，并需要重新平衡这个树。
 * 平衡因子可以直接存储在每个节点中，或从可能存储在节点中的子树高度计算出来。
 *
 * 左旋leftRotate 右旋rightRotate 两个例子记着
 */
public class AVLTree <K extends Comparable<K>, V> implements Map<K, V> {
    private class Node{  //树节点存储 <key , value> (left, right) height : 当前节点书高
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height=1; //初始化高度1;
        }
        public Node(){
            this(null,null);
        }
    }

    private Node root; // BST 根节点数
    private int size; //个数

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 获得节点node高度
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获取   左树高度 - 右树高度 差值
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    @Override
    public void add(K key, V value) {
        root = addHelper(root, key, value);
    }

    /**
     * 递归实现add();  相同值执行赋值更新,记住递归步骤!
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node addHelper(Node node, K key, V value){
        // 新元素, add 过程
        if (node == null){
            size ++;
            return new Node(key, value); //返回当前根节点
        }
        //分门别类加入
        if (key.compareTo(node.key) < 0){
            node.left = addHelper(node.left, key, value);
        }
        else if (key.compareTo(node.key) == 0){
            node.value = value;//相同执行更新值
        }
        else {
            node.right = addHelper(node.right, key, value);
        }


        //更新 height
        node.height = Math.max(node.left.height, node.right.height) + 1;

        //计算平衡因子
        int balanceFactory = getBalanceFactor(node);
        /**
         初次实现, 单元测试使用
        if(Math.abs(balanceFactory) > 1){
            System.out.println("unbalanced : " + balanceFactory);
        }*/

        //平衡维护L,R 起名表示高的方向   推导过程不清楚看 20191004号 笔记本推导
        //LL
        if ( balanceFactory > 1 && getBalanceFactor(node.left) > -1){
            return RightRotate(node);
        }
        //LR  ( -> LL 再-> 向右旋转 )
        if (balanceFactory > 1 && getBalanceFactor(node.left) <= -1){
            node.left = LeftRotate(node.left);
            return RightRotate(node);
        }

        //RR
        if (balanceFactory < -1 && getBalanceFactor(node.right) < 1)
            return LeftRotate(node);

        //RL ( -> RR 再 -> 向左旋转)
        if (balanceFactory < -1 && getBalanceFactor(node.right) >= 1) {
            node.right = RightRotate(node.right);
            return LeftRotate(node);
        }

        //平衡, 不维护
        return node;
    }

/**
    对节点y进行向右旋转操作，返回旋转后新的根节点x
            y                              x
         L / \                           /   \
          x   T4     向右旋转 (y)        z     y
        L/ \       - - - - - - - ->    / \   / \
        z   T3                       T1 T2 T3 T4
       / \
     T1   T2

     给Node y , 将 LL 型 进行右旋转
     定义变量 x = y.left , T3 = x.right.
     右旋转 : y.left = T3;
             x.right = y;

     x, y 高度发生变化, 然后更新 height
     */
private Node RightRotate(Node y){
    Node x = y.left;
    Node T3 = x.right;

    //旋转
    y.left = T3;
    x.right = y;

    //维护 height
    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

    return x;// 返回根节点
}

    /**
      对节点y进行向左旋转操作，返回旋转后新的根节点x
        y                             x
      /  \                          /   \
     T1   x      向左旋转 (y)       y     z
         / \   - - - - - - - ->   / \   / \
       T2  z                     T1 T2 T3 T4
          / \
         T3 T4
     给 Node y 将 RR 型 进行左旋转
     定义变量 x , T2, 进行旋转
     x, y 发生变化, 维护树高

     */
    private Node LeftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        //旋转
        x.left = y;
        y.right = T2;

        //维护 height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x; // 返回根节点
    }
    @Override
    public V remove(K key) {
        Node node = removeHelper(root , key);
        return node == null? null : node.value;
    }

    /**
     * 删除节点.  分为 查, 删两个过程 递归实现
     * minimum : 返回的是最小值的 Node
     * removeMin : 返回的是根节点 Node
     * 使用的函数:
     * @param node
     * @param key
     * @return
     */
    private Node removeHelper(Node node, K key){
        if (node == null) return null;

        //AVLTree 特有
        Node retNode; // 左右都为 null 或 都不为 null 情况下没有返回值(需要继续处理层高问题)使用的变量.
        if (key.compareTo(node.key) < 0)
            removeHelper(node.left, key);
        else if (key.compareTo(node.key) > 0)
            removeHelper(node.right,key);
        else{//以下情况全为node.key == key,删除node, 处理 左右子树情况
            /**
             * 分析过程 : 无非四种情况 : 左空右空, 左非空右空, 左空右非空, 左空右空.  然后简化(第一步判断左空, 然后下一个逻辑自然是左非空情况, 如此)
             */
            // 左为空情况 : 右空/不空都一样操作, 合并,走起 (不需要处理层高. 肯定是叶子节点 不然就不平衡被旋转处理了.)
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size -- ;
                return rightNode;
            } else if (node.right == null){
                //后续逻辑都是左不为空情况
                //(左不为空) 右为空

                    Node leftNode = node.left;
                    node.left = null;
                    size --;
                    return leftNode;
            }else { //(左不为空)(右不为空)
                   /**
            一个容易理解的写法
            Node suc=new Node();
            suc.value = minimum(node.right).value;
            suc.left = node.left;
            suc.right = removeMin(node.right);
            */
                Node successor = minimum(node.right);  //successor 记录当前节点"根节点"
                successor.right = removeMin(node.right); //移除这个最小值,并设为当前节点右子树.
                successor.left = node.left; //左子树不变,赋值.


                //交个 GC
                node.left =null;
                node.right = null;
                retNode = successor; // retNdoe 指向当前根节点
            }
        }
        //处理左右节点都不为空 情况 (删除节点了, 树平衡可能被破坏, 需要处理)
        return null; // 没处理呢.  待完成
    }

    /**
     * 返回最小值 的 Node
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if(node.left == null)
            return node;
        else
            return minimum(node.left); //递归,直到找到
    }

    /**
     * 返回删除后当前位置 "根节点" (可能已经不平衡了,需要继续处理)
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            //我能想到只能这么写,而不能直接node = node.right; 因为你要操作node.right=null; 需要一个临时tempNode 指向他.(不然就丢失了.
            Node tempNode = node.right;
            node.right = null;
            size--;
            return tempNode;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K key, V value) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
