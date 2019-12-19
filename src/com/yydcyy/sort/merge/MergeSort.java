package com.yydcyy.sort.merge;

import com.yydcyy.Helper.SortHelper;
import com.yydcyy.sort.insertion.InsertionSort;

import java.util.Arrays;

/**
 * Created by YYDCYY on 2019-08-15.
 * time  O(nlog n)
 * space O(n) //生成了一个中间数组aux
 */
public class MergeSort {

    public MergeSort() {
    }

    //Merge V 0.1    思想 : 自顶向下
    private static void merge(Comparable[] arr, int L, int mid, int R) {
        Comparable[] aux = Arrays.copyOfRange(arr, L, R + 1);  //aux比arr长度多1         //理解: aux[] 当做整体
        // 初始化，i指向左半部分的起始索引位置 l ；j指向右半部分起始索引位置 mid + 1
        //代码中全是 L/左, 别用小写l, 不好区分数字1 不过他们颜色不同的

        int i = L, j = mid + 1;
        for (int k = L; k <= R; k++) { // arr[L,R] 逐个从 aux中选出最小值
            //排完了, 右边直接复制上
            if (i > mid) {
                arr[k] = aux[j - L];
                j++;
            } else if (j > R) {
                arr[k] = aux[i - L];
                i++;
            } else if (aux[i - L].compareTo(aux[j - L]) < 0) {
                arr[k] = aux[i - L];
                i++;
            } else {//aux[i-l].compareTo(aux[j - l]) >= 0)
                arr[k] = aux[j - L];
                j++;
            }
        }
    }

    // https://www.bilibili.com/video/av18980253/ 动画理解  跑一把就知道了, 层次理解

    /**
     * Merge V 0.2
     * // 优化2: 对于小规模数组, 使用插入排序 十几毫秒的优化(这也是几个算法学习,比较,工具嘛,应用体现.)
     */
    private static void sort(Comparable[] arr, int L, int R) {

        if (R - L <= 15) {
            InsertionSort.sort(arr, L, R);
            return;
        }
        if (L >= R) return; //递归实现,先写终止条件
        int mid = L + (R - L) / 2;
        sort(arr, L, mid); //换分左
        sort(arr, mid + 1, R);
        // merge(arr , l ,mid ,r);
        // 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
        // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, L, mid, R);
    }

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1); //下标
    }

    public static void main(String[] args) {
         /*Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
         可以在1秒之内轻松处理100万数量级的数据
         注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
         否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异:*/
        int N = 10000000; //1000K
        Integer[] arr = SortHelper.generateRandomArray(N, 0, 100); //100K
        //SortHelper.testSort("com.yydcyy.sort.merge.MergeSort" , arr);
        //printArray(arr);
        SortHelper.testSort("com.yydcyy.sort.merge.MergeSort", arr);
       // printArray(arr);

        /* n = 1000W  :  Is Sorted? true MergeSort : 2719ms      */

       /* int [] arr = {1,2,3,4,5};
        int [] aux = Arrays.copyOfRange(arr, 1, arr.length+1);
        for (int i:aux ) {
            System.out.println(" ["+ i+"] ");
        }*/
    }
}
