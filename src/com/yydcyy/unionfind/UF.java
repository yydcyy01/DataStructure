package com.yydcyy.unionfind;

/**
 * @author YYDCYY
 * @create 2019-09-19
 */
public interface UF {
        int getSize();
        boolean isConnection(int p,int q);
        void unionElements(int p,int q);
}
