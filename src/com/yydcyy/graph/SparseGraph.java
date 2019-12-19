package com.yydcyy.graph;

import java.util.Vector;

/**
 * @author YYDCYY
 * @create 2019-09-22
 *  稀疏图实现 / 邻接表  有向图
 */
public class SparseGraph implements Graph{
    private int m; //边数
    private int n; //节点数
    private boolean directed;// 是否为有向图
    private Vector<Integer>[] g; // 图的具体数据

    public SparseGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        this.directed = directed;
        this.m = 0;

        // g初始化为n个空的vector, 表示每一个g[i]都为空, 即没有任和边
        g =( Vector<Integer>[]) new Vector[n];

        for( int i = 0 ; i < n ; i ++ ){
            g[i] = new Vector<Integer>();
        }
    }

    /**
     * 返回节点个数
     * @return
     */
    @Override
    public int V() {
        return n;
    }

    /**
     * 返回边个数
     * @return
     */
    @Override
    public int E() {
        return m;
    }

    /**
     * 向 v 中添加 w
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;  //v < w这两个是几个意思.不是很懂
        assert w >= 0 && w < n;

        g[v].add(w);
        //无向图 加两遍
        if( v != w && !directed )
            g[w].add(v);

        m ++;
    }

    /**
     * 判断两条边是否联通
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;  //处理合法性

        for( int i = 0 ; i < g[v].size() ; i ++){
            if( g[v].elementAt(i) == w ){
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        for( int i = 0 ; i < n ; i ++){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++)
                //Vector 一直以为被废弃了.
                System.out.print(g[i].elementAt(j) + "\t");

            System.out.println();
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        return g[v];
    }
}
