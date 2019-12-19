package com.yydcyy.heap;

import static com.yydcyy.Helper.SortHelper.swap;

/**
 * @author YYDCYY
 * @create 2019-09-25
 * 最大堆, 顶为最大元素, new [capacity + 1], 有效值 [1,n]?
 *  自底向上实现
 */
public class MaxHeap <E extends Comparable>{
    //类型为 protected , 因为 PrintableMaxHeap.java, 为简化代码, extends 此类, 若属性为 private ,那个类就不可用了.
    protected E[] data;
    protected  int count; // 记录数量 (已存多少)
    protected int capacity;  //记录容量 (可存多少)

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public MaxHeap(int capacity){
        data = (E[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    // 返回堆中的元素个数
    public int size(){
        return count;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return count == 0;
    }

    public void insert(E item){
        if(count < 0 || count + 1 > capacity)
            throw new RuntimeException("insert item Illigle");
        data[count + 1] = item;
        count ++ ;
        shiftup(count);
    }

    private void shiftup(int k){
        while (k > 1 &&  data[k/2].compareTo(data[k] ) < 0){
            swap(data, k, k/2);
            k /= 2;
        }
    }

}
