package com.yydcyy.graph;

/**
 * @author YYDCYY
 * @create 2019-09-22
 */
public interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public boolean hasEdge(int v, int w);
    void show();
    public Iterable<Integer> adj(int v);
}
