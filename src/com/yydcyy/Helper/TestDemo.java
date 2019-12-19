package com.yydcyy.Helper;

import org.junit.Test;

import java.util.Arrays;

import static com.yydcyy.Helper.SortHelper.*;


/**
 * Created by YYDCYY on 2019-08-15.
 */
public class TestDemo {
    @Test
    public void InsVSSelTest() {
        int N = 100000;
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");

        //随机array测试
        Integer[] arr1 = generateRandomArray(N, 0, N);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);  // 保证公平, 一模一样的无序数组

        System.out.println(" 选择排序用时 :  ");
       // printArray(arr1);
        testSort("com.yydcyy.sort.selection.SelectionSort", arr1);
       // printArray(arr1);

        //printArray(arr2);
        System.out.println(" 插入排序用时 :  ");
        testSort("com.yydcyy.sort.insertion.InsertionSort", arr2);
        //printArray(arr2);

        /**
         * Test for random array, size = 20000 , random range [0, 20000]
         isSorted? true 插入排序用时 :  1533ms
         isSorted? true 选择排序用时 :  614ms
         */
        System.out.println();

        // 测试2 有序性更强的测试
        /*System.out.println("Test for more ordered random array, size = " + N + " , random range [0,3]");

        arr1 = generateRandomArray(N, 0, 3);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        System.out.println(  " 选择排序用时 :  " );
        testSort("com.yydcyy.sort.selection.SelectionSort",arr1);
        System.out.println(  " 插入排序用时 :  " );
        testSort("com.yydcyy.sort.insertion.InsertionSort",arr2);*/

        System.out.println();
        /**
         *  选择排序用时 :
         Is Sorted?trueSelectionSort : 1353ms
         插入排序用时 :
         Is Sorted?falseInsertionSort : 1369ms
         */

        // 测试3 测试近乎有序的数组
        /*int swapTimes = 100;
        System.out.println("Test for nearly ordered array, size = " + N + " , swap time = " + swapTimes);

        arr1 = SortHelper.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);

        System.out.println(  " 选择排序用时 :  " );
        testSort("com.yydcyy.sort.selection.SelectionSort",arr1);
        System.out.println(  " 插入排序用时 :  " );
        testSort("com.yydcyy.sort.insertion.InsertionSort",arr2);*/


        /**
         * Select是1.2 版本
         * 无序
         * Test for random array, size = 20000 , random range [0, 20000]
         选择排序用时 :
         SelectionSortTestHelper : 1144ms
         插入排序用时 :
         InsertionSortTestHelper : 1001ms

         Test for more ordered random array, size = 20000 , random range [0,3]
         选择排序用时 :
         SelectionSortTestHelper : 474ms
         插入排序用时 :
         InsertionSortTestHelper : 460ms

         有序
         Test for nearly ordered array, size = 20000 , swap time = 100
         选择排序用时 :
         SelectionSortTestHelper : 330ms
         插入排序用时 :
         InsertionSortTestHelper : 3ms

         Select是1.3版本
         Test for random array, size = 20000 , random range [0, 20000]
         选择排序用时 :
         SelectionSort : 997ms
         插入排序用时 :
         InsertionSort : 1179ms

         Test for more ordered random array, size = 20000 , random range [0,3]
         选择排序用时 :
         SelectionSort : 922ms
         插入排序用时 :
         InsertionSort : 382ms

         Test for nearly ordered array, size = 20000 , swap time = 100
         选择排序用时 :
         SelectionSort : 275ms
         插入排序用时 :
         InsertionSort : 3ms
         */

    }
}
