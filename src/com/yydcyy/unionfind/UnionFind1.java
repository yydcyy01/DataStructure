package com.yydcyy.unionfind;

/**
 * @author YYDCYY
 * @create 2019-09-20
 * 第一版:UnionFind 本质是数组.
 */
public class UnionFind1 implements UF{
    private int[] id;
    public UnionFind1(int size){
        id = new int[size];
        //初始化值
        for (int i = 0; i <size; i++) {
            id[i] = 0;
        }
    }

    //返回根节点
    private int find(int p){
        if(p <0 || p>=id.length){
            throw new IllegalArgumentException("Find is faild,index is out of bound.");
        }
        return id[p];
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnection(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 实现 p q 连联结方法; 实现: 把所有 id[i] == p 的改为 q, 遍历id, id[i] == id[p] 即全改为 id[q]
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        //联通
        if(pID == qID){
            return ;
        }

        for (int i = 0; i < id.length; i++) {
            if(id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
