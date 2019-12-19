package com.yydcyy.sort.quick;

import com.yydcyy.sort.insertion.InsertionSort;

import static com.yydcyy.Helper.SortHelper.*;


/**
 * @author YYDCYY
 * @create 2019-09-01
 * 递归, 上来就写终止条件鸭兄弟.
 *  time  O(n log n)
 *  *       space O( n )
 */
public class Quick3WaysSort {
    private Quick3WaysSort(){
    }
    private static void Sort(Comparable[] arr, int L, int R){
        if (R - L <= 15){
            InsertionSort.sort(arr, L, R);
            return;
        }
        int random = (int) (Math.random()*(R - L + 1) + L);
        swap(arr, L, random);

        Comparable v = arr[L];

        int lt = L;
       int gt = R + 1;
       int i = L + 1;
       while (i < gt) {
           if (arr[i].compareTo(v) < 0) {
               swap(arr, i++, ++lt);
               /* i ++;
               lt ++;  */
           } else if (arr[i].compareTo(v) > 0) {
               swap(arr, i, --gt);  // i 别 ++, 换回来的数还没处理过呢
               /**  gt --; */
           } else { // arr[i] == v;
               i++;
           }
       }
           swap(arr, L, lt);

           Sort(arr, L, lt - 1); // 注意这几个边界
           Sort(arr, gt, R);

    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        Sort(arr, 0, n - 1);
    }

    // 测试 QuickSort3Ways
    public static void main(String[] args) {

        // 三路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 10000000;
        Integer[] arr = generateRandomArray(N, 0, 100000);
        testSort("com.yydcyy.sort.quick.Quick3WaysSort", arr);
       /*
       1000 万个范围为[0,10万]的数排序, 时间 4462ms
       Is Sorted?trueQuick3WaysSort : 4462ms*/

        return;
    }
}
