package com.yydcyy.graph;

import java.util.Stack;
import java.util.Vector;

/**
 * @author YYDCYY
 * @create 2019-09-23
 * 寻路算法 (图中寻找一条两点之间距离
 * 结果 : 类似连通分量, FindAPath(G,s) s 只要属于同一个"连通分量" , 即该 from[i] 都可被赋值为上一个
 * 得到 from[] 路径, 即可得到该路径所有"结果" , 比如打印 / 路径上某点节点是否可达等
 */
public class FindAPath {
    private Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int[] from; //记录路径, from[i]表示查找的路径上i的上一个节点
    private int s; // 起始点

    // 构造函数,初始化寻路算法, 寻找图graph从s点到其他点的路径
    //Graph ui Interface, 传来的图是 DenseGraph / SparseGraph
    public FindAPath(Graph G, int s){
        assert s >= 0 && s < G.V();

        visited = new boolean[G.V()];
        from = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        this.G = G;
        this.s = s;

        //寻路算法
        dfs(s);
    }

    private void dfs(int v) {
        visited[v] = true;
        //Iterator Vector();
        for( int i : G.adj(v))
            if( !visited[i]) {
                from[i] = v;
                dfs(i);
            }
    }

    // 查询从s点到w点是否有路径
    boolean hasPath( int w ){
        assert w >= 0 && w < G.V() ;
        return visited[w];
    }


    Vector<Integer> path(int w ){
        assert hasPath(w);   //单元测试可以, 正式代码assert不可写业务逻辑.
        Stack<Integer> s = new Stack<>();

        int p = w;
        while( p != -1 ){
            s.push(p);
            p = from[p];
        }
        //也可以用 List
        Vector<Integer> res = new Vector<>();
        //从栈中倒一遍出来
        while( !s.empty() ){
            res.add(s.pop());
        }
        return res;
    }

    public void showPath( int w ){
        assert hasPath(w);

        Vector<Integer> vec = path(w);
        for( int i = 0 ; i < vec.size() ; i ++ ){
            System.out.println(vec.elementAt(i));
            //排版处理 好看
            if( i == vec.size() - 1 )
                System.out.print("");
            else
                System.out.print(" -> ");
        }
    }
}
