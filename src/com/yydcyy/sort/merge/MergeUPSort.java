package com.yydcyy.sort.merge;

import com.yydcyy.Helper.SortHelper;
import com.yydcyy.sort.insertion.InsertionSort;

import java.util.Arrays;

/**
 * @author YYDCYY
 * @create 2019-08-31
 * 自顶向下和自底向上 复杂度差不多,  速度讲, 自顶向下可能快那么一点点.
 * 但是 自底向上思想可以使用在链表排序上, 向上归并过程就是合并链表过程
 */
public class MergeUPSort {

        public MergeUPSort() {
        }

        //Merge V 0.1    思想 : 自顶向下 / 字底向上 归并过程都是一样的
        private static void merge(Comparable[] arr, int L, int mid, int R) {
            Comparable[] aux = Arrays.copyOfRange(arr, L, R + 1);

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

        public static void sort(Comparable[] arr) {
           int n = arr.length;


           // v 0.1 按照自下而上定义实现, 啥优化都没有版本
            /* for (int sz = 1; sz < n; sz *= 2) {  // 自底向上, 扩大范围 i=1开始哦 i: 此时一组个数 eg 16 个一组;   j: 当前
                for (int i = 0; i < n - sz; i += 2*sz) {
                    int mid = i + sz - 1;
                    int r = Math.min(i + sz*2 -1, n - 1); // 处理边界,防越界
                    merge(arr, i, mid, r); // merge 范围注意
                }
            }*/

            // 小优化, 对于分组大小小于 16 情况,采用插入排序优化
            for (int i = 0; i < n; i += 16) {
                InsertionSort.sort(arr, i, Math.min(i + 15, n - 1));
            }

            for (int sz = 16; sz < n; sz += sz) {
                for (int i = 0; i < n - sz; i += sz*2) {
                    int mid = i + sz - 1;
                    int r = Math.min(mid + sz, n-1);
                    if (arr[mid].compareTo(arr[mid + 1]) > 0){ // 小优化, 左分组 <= 右分组情况(已有序),  不执行 merge
                        merge(arr, i, mid, r);
                    }
                }
            }
        }

        public static void main(String[] args) {
            int N = 10000000; //1000K
            Integer[] arr = SortHelper.generateRandomArray(N, 0, 10000); //100K
          // printArray(arr);
            //System.out.println();
            SortHelper.testSort("com.yydcyy.sort.merge.MergeUPSort", arr);
           // printArray(arr);

        }
}
