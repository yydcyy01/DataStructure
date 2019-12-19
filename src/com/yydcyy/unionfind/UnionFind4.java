package com.yydcyy.unionfind;

/**
 * 基于rank(树的层数)优化,
 * 主要优化在于unionElements方法,合并不同,test 时间也不同.
 * @author YYDCYY
 * @create 2019-09-21
 */
public class UnionFind4 implements UF{
    private int[] parent; //parent[i] 表示第一个元素所指向的父节点
    private int[] rank; //rank[i]表示以i为根的集合表示的树的层数.

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        // 初始化,每个parent[i]指向自己. 表示自成一个集合; rank[i] i层数 都是 1
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int p){
        if(p <0 || p>=parent.length)
                throw new IllegalArgumentException("Find is faild, index is out of bound.");


        //不断查询自己父节点, 直到到达根节点
        //根节点特点parent[p] ==p
        while(p !=parent[p]){
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
         return parent.length;
    }

    /**
         查看元素p和元素q,是否所属一个集合
         O(h) 复杂度, h为树的高度     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnection(int p, int q) {
            return find(p) == find(q);
    }

    /**
     合并元素p和元素q
     O(h) 复杂度, h为树的高度
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
        //由两元素所在的树的rank不同判断合并方向
        //将层数低的集合合并到层数低高的集合上, 保证合并过程层数不会增加
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] =qRoot;
        }else if(rank[pRoot] >rank[qRoot] ){
            parent[qRoot] = pRoot;
        }
        else{//两层相同, 谁指向谁,同
            parent[qRoot] = pRoot;
            rank[qRoot] +=1;   //维护rank值
        }
        //parent[pRoot] =qRoot;  //改进这个版本.
    }
}
