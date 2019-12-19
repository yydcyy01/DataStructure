package com.yydcyy.heap;

/**
 * @author YYDCYY
 * @create 2019-09-26
 * 最大索引堆, 顶为最大元素,
 * 参数为int (操作数组的索引下标.)
 * 最大堆, 下移
 */
public class IndexMaxHeap <E extends Comparable>{
    protected E[] data; //记录数据 , 有效范围 [1,N], new n+1 个浪费一个空间, 方便计算
    protected int[] indexes; // 记录索引下标 有效范围 [1,count]
    protected  int count; // 记录有效个数
    protected int capacity; // 记录可存储最大个数

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public IndexMaxHeap(int capacity){
        data = (E[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
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

    public void insert(int i,E item){
        if(count < 0 || count + 1 > capacity)
            throw new RuntimeException("insert item Illigle");
        if(i < 0 || i+1 > capacity)
            throw new RuntimeException("插入位置异常");
        i +=1;//1开始
        count ++;
        data[i] = item;
        indexes[count] = i;

        shiftUp(count);
    }

    /**
     * 从最大堆中取出堆顶元素(最大数)
     * 然后data[count]最后一个数放在顶data[1]
     * data[1] 下移
     * swapIndex shiftDown操作的都是index 记住,data不变
     * @return
     */
    public E extractMax(){
        //assert (count > 0);
        int r = extractMaxIndexHelper();
        return data[r];
    }

    //工具类
    private int extractMaxIndexHelper(){
        if(count <= 0) throw new RuntimeException("extractMaxIndex.count is Illigle");
//        int ret = indexes[1] - 1;   我觉得是这里问题. 我省略了extractMax重复方法, 但是arr从0开始, data从1 开始.试试
        int ret = indexes[1];
        swapIndex( 1 , count );
        count --;  //取出值了,自然总数count-1;
        shiftDown(1); //1为下标
        return ret;
    }
    public E getItem( int i ){
        if(i < 0 || i+1 > capacity)
            throw new RuntimeException("getItem,下标位置异常");
        return data[i+1];
    }
    //改变值,然后
    public void change( int i , E newItem ){
        i +=1;
        data[i] = newItem; //更新数据
        for ( int j = 1 ; j <= count ; j ++){
            if ( indexes[j] == i){
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    private void shiftUp(int k){
        while (k > 1 &&  data[indexes[k/2]].compareTo(data[indexes[k]] ) < 0){
            swapIndex(k, k/2);
            k /= 2;
        }
    }

    /**
     * // 在此轮循环情况最大值
     */
    private void shiftDown(int k){
        while (2*k <= count){
            int j = 2*k; //左下标位置

            if (j+1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0){
                j ++;
            }
            //符合大于子节点条件,终止
            if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) break;
            swapIndex(k, j);
            k = j;
        }
    }

    // 交换索引堆中的索引i和j
    private void swapIndex(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }
}
