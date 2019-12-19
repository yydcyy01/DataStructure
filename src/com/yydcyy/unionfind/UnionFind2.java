package com.yydcyy.unionfind;

/**
 *
 * @author YYDCYY
 * @create 2019-09-20
 */
public class UnionFind2 implements UF{
    private int[] parent;

    //初始化, 初始每个元素指向自己,自成一个集合
  public UnionFind2(int size){
        parent = new int[size];

        //初始化,每个parent[]指向自己. 表示自成一个集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * 返回 parent[p] 根节点
     * @param p
     * @return
     */
    private int find(int p){
        if(p <0 || p>=parent.length)
                throw new IllegalArgumentException("Find is faild,index is out of bound.");

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
     * 查看元素p和元素q,是否所属一个集合, O(h) 复杂度, h为树的高度
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnection(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 联合 p q 两元素
     * 把 p 的父节点设为 q 根节点的坐标
     * O(h) 复杂度, h为树的高度
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
        parent[pRoot] =qRoot;
    }
}
