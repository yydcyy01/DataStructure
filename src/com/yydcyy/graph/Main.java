package com.yydcyy.graph;

import com.yydcyy.graph.utils.ReadGraph;
import org.junit.Test;

/**
 * @author YYDCYY
 * @create 2019-09-22
 */
public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/yuyang/IdeaProjects/DataStructure/src/com/yydcyy/graph/utils/testG1.txt";
        SparseGraph g1 = new SparseGraph(13 , false);
        ReadGraph readGraph1 = new ReadGraph( g1 , filename );
        System.out.println("test G1 in Sparse Graph: ");
        g1.show();

        System.out.println();

        DenseGraph  g2= new DenseGraph(13 , false);
        ReadGraph readGraph2 = new ReadGraph( g2 , filename );
        System.out.println("test G1 in Dense Graph:");
        g2.show();

        System.out.println();
    }

    @Test
    public void run(){
        String filename1 = "/Users/yuyang/IdeaProjects/DataStructure/src/com/yydcyy/graph/utils/testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        Components component1 = new Components(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        String filename2 = "/Users/yuyang/IdeaProjects/DataStructure/src/com/yydcyy/graph/utils/testG2.txt";
        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        Components component2 = new Components(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
    }
}
