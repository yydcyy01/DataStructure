package com.yydcyy.heap;

import static com.yydcyy.Helper.SortHelper.swap;

/**
 * @author YYDCYY
 * @create 2019-09-25
 * 最大堆,  堆化过程自顶向下实现
 * 在堆的有关操作中，需要比较堆中元素的大小，所以Item需要extends Comparable
 * 而一般长度为 n 的数据(我起名 arr)存储有效范围 [0,n]
 * 堆的data 存储数组有效范围采用[1,n], 浪费一个data[0]空间, 但是 shiftDown / shiftUp过程求 parent / child 很方便, 直接 * or /2 即可
 *
 * 自顶向下 / 自底向上 两方法使用 :  最大堆中insert()方法中调用shiftUp, 取调用 shiftDown
 *
 * */
public class MaxHeap_ShiftDown <E extends Comparable>{
    private  E[] data;
    private  int count;
    private int capacity;

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public MaxHeap_ShiftDown(int capacity){

        data = (E[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;

    }

    public MaxHeap_ShiftDown(E arr[]){

        int n = arr.length;
        data = (E[])new Comparable[n + 1];
        this.capacity = n;

        for (int i = 0; i < n; i++) {
            //数组有效下标范围[0, n-1], data/堆存储下标范围[1,n]
            data[i+1] = arr[i];
        }
        count = n;
        for (int i = count / 2; i >= 1; i++) {
            //推导公式看笔记本
            shiftDown(i);
        }
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
        //边界处理
        if(count < 0 || count +1 > capacity) //合理范围 [0,n]
            throw new RuntimeException("insert item Illigle");

        data[count + 1] = item;
        count ++ ;
        shiftUp(count);
    }

    /**
     * 从最大堆中取出堆顶元素(此为最大值)
     * 然后最后一个数(data[count]) 放在顶 (data[1])
     *    并执行 data[1] shiftDown()操作
     * @return
     */
    public E extractMax(){
        assert (count > 0);
        //if(count <= 0) throw new RuntimeException("extractMax.count is Illigle");

        E ret = data[1];
        swap(data,1,count);
        count --;
        shiftDown(1);
        return ret;
    }

    private void shiftUp(int k){
        /**
         * eg:最大堆时 : 自底向上时, k>1 且 parent[] < data[k] 进行交换, k = 父节点 (data 有效范围[1,n]时, parent坐标推导公式是 i  = k/2)
         */
        while (k > 1 &&  data[k/2].compareTo(data[k] ) < 0){
            swap(data, k, k/2);
            k /= 2;
        }
    }

    /**
     * // 在此轮循环中,data[k]和data[j]交换位置
     * j 是左右两边合法情况最大值
     */
    private void shiftDown(int k){
        while (2*k <= count){
            int j = 2*k; //左下标位置

            if (j+1 <= count && data[j + 1].compareTo(data[j]) > 0){
                j ++;
            }
            //符合大于子节点条件,终止
            if (data[k].compareTo(data[j]) >= 0) break;
            swap(data, k, j);
            k = j;
        }
    }

    public static void main(String[] args){
        int N = 1000000; // 堆中元素个数
        int M = 1000000; // 堆中元素取值范围[0, M)

        MaxHeap_ShiftDown<Integer> maxHeap = new MaxHeap_ShiftDown<Integer>(N);
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.insert( new Integer((int)(Math.random() * M)) );

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.extractMax();
            //System.out.print(arr[i] + " ");
        }
        System.out.println("排序 Over");

        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            if (arr[i - 1] < arr[i]) {
                throw new RuntimeException("assert arr[i-1] >= arr[i];");
            }

        System.out.println("测试 ok");
    }
}
