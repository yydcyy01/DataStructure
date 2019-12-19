package com.yydcyy.sort.selection;


import static com.yydcyy.Helper.SortHelper.*;

/**
 * Created by YYDCYY on 2019-08-15.
 * for ( i∈ [0 - n) ) 每次选择最最小的一个移至i
 * <p>
 * T写在方法上
 */
public class SelectionSort {
    public SelectionSort() {
    }

    //1.1 版本
    /*public static void sort1(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i ++){
            //寻找[i,n)区间最小值 外层0,n-1] 内层(i,n]
            int minIndex = i;
            for (int j = i + 1; j < n; j ++)
                if(arr[minIndex] > arr[j])
                    minIndex = j;//注:和j比较
            swap(arr,i,minIndex);
        }
      }
         public static void swap(int[] arr,int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }*/

    // 1.2 版本.  换成 T 统配
   /* public static <T extends Comparable<T>> void sort2(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) //寻找最小值
                    minIndex = j;
            }
            swap(arr, i, minIndex); //T[] arr  -> Object[]
        }
    }*/

    // 1.3 版本对选择排序进行优化   Time O(n ^ 2) , 但相比于1.2版本, 内部少循环一半
    // 在每一轮中, 可以同时找到当前未处理元素的最大值和最小值
    
    public static void sort(Comparable[] arr) {
        int left = 0, right = arr.length - 1;  //left,right:为角标

        while (left < right) {
            int minIndex = left;
            int maxIndex = right;

            //保证 left存最小值, right存最大值
            if (arr[left].compareTo(arr[right]) > 0) {
                swap(arr, left, right);
            }

            //内层for循环一次找到min / max两个数
            for (int i = left + 1; i < right; i++) {
                if (arr[i].compareTo(arr[minIndex]) < 0)
                    minIndex = i;
                else if (arr[i].compareTo(arr[maxIndex]) > 0)
                    maxIndex = i;
            }

            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);
            // printArray(arr); //打印测试,费时
            left ++;
            right --;
        }
    }

    /*public static void main(String[] args) {

        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = generateRandomArray(N, 99, 100000);

        //测试Integer类型
        Integer[] arr1 = {10, 8, 9, -1, 7, 6, 5, 4, 3, 2, 1};
        SelectionSort.sort(arr1);
        printArray(arr1);

        //测试Double类型
        Double[] arr2 = {4.4, 3.3, 2.2, 1.1, -1.0, -1.1};
        SelectionSort.sort(arr2);
        printArray(arr2);

        //测试String类型
        String[] arr3 = {"D", "C", "B", "A", "a", "c", " "};
        SelectionSort.sort(arr3);
        printArray(arr3);


        //测试自定义类型(Student)
        Student[] d = new Student[4];
        d[0] = new Student("D", 90);
        d[1] = new Student("C", 100);
        d[2] = new Student("B", 95);
        d[3] = new Student("A", 95);
        SelectionSort.sort(d); //java.lang.ClassCastException: [Lcn.yydcyy.array.Student; cannot be cast to [Ljava.lang.Comparable;  Student需要实现Comparable
        printArray(d);
        return;
    }*/
}
