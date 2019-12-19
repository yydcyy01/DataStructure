package com.yydcyy.graph;

/**
 * @author YYDCYY
 * @create 2019-09-22
 * 求 无权图连通分量
 */
public class Components {
    Graph G; // 图的引用
    private boolean[] visited;// 记录dfs的过程中节点是否被访问
    private int count; // 记录联通分量个数
    private int[] id ; // 每个节点所对应的联通分量标记  (即 id[]相同, 表明是同一个连通分量 / 组

    public Components(Graph g) {
        G = g;
        int n = g.V();
        visited = new boolean[n];
        id = new int[n];
        count = 0;
        for( int i = 0 ; i < n ; i ++){
            visited[i] = false;
            id[i] = -1; //初始化

        }
        // 求图的联通分量  即重头开始dfs几次,count++
        for( int i = 0 ; i < G.V() ; i ++){
            if( !visited[i] ) {
                dfs(i);
                count ++;
            }
        }
    }

    void dfs(int v){
        visited[v] =true;
        id[v] = count;
        for (int i : G.adj(v)) // 遍历 v 的邻接表
            if (!visited[i])
                dfs(i);
    }

    public int count(){
        return count;
    }

    boolean isConnected(int v, int w){
        assert v >= 0 && v < G.V();
        assert w >= 0 && w < G.V();
        return id[v] == id[w];
    }

}
