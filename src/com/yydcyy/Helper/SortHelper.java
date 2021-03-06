package com.yydcyy.Helper;

import java.lang.reflect.Method;

/**
 * Created by YYDCYY on 2019-08-15.
 */
public class SortHelper {
    private SortHelper() {
    }

    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        // assert rangeL <=rangeR;
        if (n < 0) {
            throw new RuntimeException("随机数个数 < 0 ?!");
        }

        //使之保持 L <= R

        if (rangeL > rangeR) {
            int t = rangeL;
            rangeL = rangeR;
            rangeR = t;
        }

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }

    /* 生成一个近乎有序的数组
     首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
     swapTimes定义了数组的无序程度:
     swapTimes == 0 时, 数组完全有序
     swapTimes 越大, 数组越趋向于无序*/
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {

        //生成有序数组
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = new Integer(i);

        //交换次数
        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

    /**
     * 判断是否排好序 (小->大)
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0)
                return false;
        }
        return true;
    }

    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println("Over");
    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    public static void testSort(String sortClassName, Comparable[] arr) {
        // 通过Java的反射机制，通过排序的类名，运行排序函数
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort", Comparable[].class);
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            System.out.print(" Is Sorted?" + isSorted(arr));

            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 通过Java的反射机制，通过排序的类名，运行排序函数
    }
}
