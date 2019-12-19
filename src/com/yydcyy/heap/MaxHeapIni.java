package com.yydcyy.heap;

/**
 * @author YYDCYY
 * @create 2019-09-26
 */
public class MaxHeapIni <E extends Comparable>{
    private E[] data;
    private  int count;

    /**
     * 构造函数, 构造一个空堆, 可容纳capacity个元素
     * @param capacity
     */
    public MaxHeapIni(int capacity){
        data = (E[]) new Object[capacity + 1];
        this.count = 0;
    }

    // 返回堆中的元素个数
    public int size(){
        return count;
    }

    /**
     * @return 返回一个布尔值, 表示堆中是否为空
     */
    public boolean isEmpty(){
        return count == 0;
    }
    // 测试 MaxHeap
    public static void main(String[] args){
        MaxHeapIni<Integer> maxHeap = new MaxHeapIni<>(100);
        System.out.println(maxHeap.size());
    }
}
