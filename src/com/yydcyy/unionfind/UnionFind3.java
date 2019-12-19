package com.yydcyy.unionfind;

/**
 * 并查集类似树 (不是二叉树, 是 n 叉树)
 * 基于size(节点数)优化
 * @author YYDCYY
 * @create 2019-09-21
 */
public class UnionFind3 implements UF{
    private int[] parent; //parent[i] 表示第一个元素所指向的父节点
    private int[] sz; //sz[i]表示以i为根的集合中,元素个数.

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        //初始化,每个parent[i]指向自己. 表示自成一个集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    private int find(int p){
        if(p <0 || p>=parent.length)
                throw new IllegalArgumentException("Find is faild,index is out of bound.");

        //不断查询自己父节点, 直到到达根节点
        //根节点特点parent[p] ==p
        while(p != parent[p])
            p = parent[p];

        return p;
    }

    /**
     * 查看元素p和元素q,是否所属一个集合
     *  O(h) 复杂度, h为树的高度
     * @return
     */
    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnection(int p, int q) {
        return find(p) == find(q);

    }

    /**
     * 合并元素p和元素q
     * O(h) 复杂度, h为树的高度
     * 基于 size 优化 : min{size} 向 max{size} 联合, 而不是规定死 p 移向 q
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot ==qRoot){
            return;
        }
        //由两元素所在的树的元素个数不同判断合并方向
        //将元素个数少的集合合并到元素个数多的集合上.
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] =qRoot;
            sz[qRoot] +=sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot] +=sz[qRoot];
        }
        //parent[pRoot] =qRoot;  //改进这个版本.
    }
}
