package cn.yydcyy.tree.binary_search_tree;

/**
 * Created by YYDCYY on 2019-08-13.
 * 注意 :
 * 二叉树: null节点 / 一个节点 也叫二叉树.  性质 : 无重复节点
 *
 * 二叉树 != 二分搜索树, 注意性质区分, 和面试官确定理解,也是体现沟通能力一部分
 * e.compareTo(node.e)>0 节点需要比较, 故 E extends Comparable
 * <p>
 * 调用方法实现的功能方法, 不需要维护 size++ / --; 但是一定要"底层"维护
 */
public class BST<E extends Comparable> {
    private Node root;
    private int size;


    //空参构造
    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
            //调用方法,不用维护size
        }
    }

    private void add(Node node, E e) {
        //终止情况 : 相等或左 / 右为 null
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            //插入左子树情况
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            //插入右子树情况
            node.right = new Node(e);
            size++;
            return;
        }

        //其他情况 : 不等且左 / 右不为空
        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }
}
