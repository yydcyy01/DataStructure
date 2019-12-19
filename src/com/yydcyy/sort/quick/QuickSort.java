package com.yydcyy.sort.quick;

import com.yydcyy.Helper.SortHelper;
import com.yydcyy.sort.insertion.InsertionSort;

import static com.yydcyy.Helper.SortHelper.swap;

/**
 * @author YYDCYY
 * @create 2019-09-01
 *
 *       time  O(n log n)
 *       space O( n )
 */
public class QuickSort {
    private QuickSort(){};
    private static int partition(Comparable[] arr , int L, int R){

        /* v 0.1 按快排定义实现, 1000w 数据正常很快! 但是有个缺点:若遇到有序数组,退化到  O (n^n)  ,效率及极低.
         Caused by: java.lang.StackOverflowError ;因为 等于v 时, 全分到做半部,导致不平衡, 出现最坏情况:*/
       /* Comparable v = arr[L];
        int j = L;
        for (int i = L + 1; i <= R; i ++){
            if (v.compareTo(arr[i]) > 0){ // < 结果小->大
                j ++;
                swap(arr , i , j);// [L,j] 范围是  V 的值,  (j,R] 大于 V 值
            }
        }
        swap(arr , L, j); //选定元素插回中间
        return j; // 返回中值情况, 此时[L,j)集合的数 <= j  [j,R]全 > java*/


           /* v 0.2 双路快排  随机取其中一个为 partition,并 swap 放置第一个 [版本一中取第一个]
           *  1/n! 的概率出现最坏情况
           * */
        int random = (int)(Math.random()*(R-L+1)) + L; //加的是L, 不是1 范围 {L,R}
        swap( arr, L , random );
        Comparable v = arr[L];
        int i = L + 1,j = R;
        while (true){
            while (i <= R && arr[i].compareTo(v) < 0){// 目的:遇到 >=v值,停止. 注意哦,等于也停止交换
                i ++;
            }
            while (j >= L + 1 && arr[j].compareTo(v) > 0){ //找到 <=v值,停止
                j --;
            }

            if (i > j) break; //终止条件
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, L, j); //使当前数组有序.
        return j;


        /**
         * 注意上面两个边界: arr[j].compareTo(v) > 0, arr[i].compareTo(v) < 0 为什么不含等呢,思考下
         *比如数组 1,0,0, ..., 0, 0
         a. 对于arr[i]<v和arr[j]>v的方式，第一次partition得到的分点是数组中间；
         b. 对于arr[i]<=v和arr[j]>=v的方式，第一次partition得到的分点是数组的倒数第二个。
         这是因为对于连续出现相等的情况，a方式会交换i和j的值；而b方式则会将连续出现的这些值归为其中一方，使得两棵子树不平衡(最坏情况变成链表),我们追求的是平衡
         */
    }

    // 递归实现 执行排序arr[l,r]
    private static void sort(Comparable[] arr , int l, int r){
        // if (l >= r) return ; //终止条件
        //优化1 小规模采用插入排序 就用不到上个递归终止条件了
        if(r - l <= 15){
            InsertionSort.sort(arr, l ,r);
            return ;
        }

        int p = partition(arr, l ,r); //划分选取的元素
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    //Sort 算法方法 对外提供的统一接口.  保证形式一致,调用方便
    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr, 0, n - 1);

    }
    public static void main(String[] args){
        int N = 10000000;
        Integer[] arr = SortHelper.generateRandomArray(N , 0, 100000);
        Integer[] arr2 = SortHelper.generateNearlyOrderedArray(N,0);
        // printArray(arr);  //数据量100W了, 打印太恐怖了
        SortHelper.testSort("com.yydcyy.sort.quick.QuickSort" , arr);
        SortHelper.testSort("com.yydcyy.sort.quick.QuickSort" , arr2);
        // QuickSort.sort(arr);
        // System.out.println(isSorted(arr));
        /**
         * 100W cool!
         * QuickSort : 816ms
         */
        return ;
    }
}
