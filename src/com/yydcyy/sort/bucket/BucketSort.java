package com.yydcyy.sort.bucket;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author YYDCYY
 * @create 2020-03-10
 */
public class BucketSort {
    public static void bucketSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE; // 初始化

        // 找最大/小值
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        //创建桶
        int buckerNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(buckerNum);
        for (int i = 0; i < buckerNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //元素放入桶中.
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / arr.length;
            bucketArr.get(num).add(arr[i]);
        }

        //对每个桶进行排序, 使用系统排序, 也可以使用自己的排序. 无论是快排 / 堆 / 插入排序 等等.
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
    }
}
