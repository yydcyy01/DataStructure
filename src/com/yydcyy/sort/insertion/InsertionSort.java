package com.yydcyy.sort.insertion;

/**
 * Created by YYDCYY on 2019-08-15.
 */
public class InsertionSort {

    private InsertionSort() {
    }

    //按照插入排序定义实现  V 0.1
    /*public static <T extends Comparable<T>> void sort(T[] arr) {
        //写法1
         for (int i = 0; i < arr.length; i ++){
             for (int j = i; j > 0 ; j--){ //if 判断可写进for中间条件&&上.  好理解写下面
                 if ( arr[j].compareTo(arr[j-1]) < 0)
                  swap(arr, j,j - 1);
             }
         }
    }*/

    /**
     * 优化 V 0.2
     * 插入排序默认前提 : 每次前i个已经是有序数组.  插入需要全部后移.  基于这一点优化 :
     * 相比方法1,每次swap移动3次,   变成 :  交换一次, 找到合适位置后赋值即可
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            T e = arr[i];
            int j = i;
            /*for (; j > 0; j--) {
                if (arr[j - 1].compareTo(e) > 0) {
                    arr[j] = arr[j - 1];
                }else
                    break;  //break不可少 否则逻辑不对
            }*/
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j --){
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * 对arr[l...r]的区间使用InsertionSort排序
     * 按照各排序特点, 组合使用
     * 对外提供接口  应用类型 , 故给 arr 及 l,r 范围即可
     */
    public static void sort(Comparable[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > L && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

//    public static void main(String[] args) {
//        // 测试排序算法辅助函数
//        int N = 20;
//        Integer[] arr = generateRandomArray(N, 0, 120);
//        printArray(arr);
//        //testSort(arr);
//        SortHelper.testSort("com.yydcyy.sort.insertion.InsertionSort", arr);
//        printArray(arr);
//        return;
//        /**
//         * 方法2 排序用时 :  1041ms  1043ms 1001ms  优化了
//         * 方法1 排序用时 :  1525ms  1365ms   1202ms 1629ms
//         */
//
//    }
}
