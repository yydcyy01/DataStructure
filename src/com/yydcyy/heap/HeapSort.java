package com.yydcyy.heap;

import static com.yydcyy.Helper.SortHelper.swap;

/**
 * @author YYDCYY
 * @create 2019-09-25
 * 堆排序 : 传来 Arr[] 数组, 原地处理至 arr[] 数组为最小到最大.    (传来的数组应该是 被 Heapy 过的堆.)
 */
public class HeapSort <YYDCYY extends Comparable> {
    // 我们的算法类不允许产生任何实例
    private HeapSort(){}
    public static void sort(Comparable[] arr){ //始于 0, 有效范围 : [0, length-1] 即 [0,n]
        int n = arr.length -1; //n 为有效值
        for (int i = ( n-1 ) / 2; i >= 0; i --){  //两种情况, 始于 0 / 始于 1 , left/right child 推 parent(i) 公式不同, 见笔记本上推导
            shiftDown(arr,n,i);
        }

        //逐步取出最大值,放置末尾, 执行 n-1次. 最终排序
        for (int i = n -1 ; i > 0; i -- ){
            swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }
    // 原始的shiftDown过程
    private static void shiftDown(Comparable[] arr, int n, int k){
        while( 2*k+1 < n){ //左子树不超 n 范围
            int j = 2*k+1;
            //选两子节点中较大值
            if (j+1 < n && arr[j+1].compareTo(arr[j]) > 0){
                j +=1;
            }
            //若当前节点大于子节点较大值,停止
            if (arr[k].compareTo(arr[j]) >=0) break;
            swap(arr, k ,j); //否则,执行交换.//过程 shiftDown
        }
    }



}
