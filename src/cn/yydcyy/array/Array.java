package cn.yydcyy.array;

import java.util.Arrays;

/**
 * Created by YYDCYY on 2019-08-12.
 * 说明 :
 * get() 返回 size ;  getLast 返回 data[size-1]
 * set起始位0 ,合理范围[0,size)
 * 实现<E>泛型的Array, find/ contains() 方法实现用data[i].equals(e)
 */
public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        //data=new int[capacity];
        data = (E[]) new Object[capacity];  //巧妙转换.
        size = 0; //初始0长度
    }

    public Array(E[] arr) {
        //强转
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        size = arr.length; //赋值size
    }

    //默认 capacity 构造size = 0, capacity = 10;
    public Array() {
        this(10);
    }

    // 返回长度
    public int getSize() {
        return size;
    }

    // 返回容量
    public int getCapacity() {
        return data.length;
    }

    // 判断Array是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //插入index位置插入e index为下标,0始
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed,Require index >=0 and index<=size.");
        }
        //超出情况
        if (size == data.length) {
            //自动扩容, 2倍合理
            resize(2 * data.length);
        }
        //合理情况, [index,size]元素后移
        for (int j = size - 1; j >= index; j--) {
            data[j + 1] = data[j];
        }
        data[index] = e;

        size++;//add操作,维护size+1;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    //获取操作
    public E get(int index) {
        //边界检查
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,Index is illegal.");
        }

        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    //set起始位0 ,合理范围[0,size)
    public void set(int index, E e) {
        //边界
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed,Index is illegal.");
        }

        data[index] = e;
    }

    //查找元素,存在返回下标,不存在返回-1
    public int find(E e) {
        for (int i = 0; i < getSize(); i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    //有效下标范围 : [0,capacity)
    public E remove(int index) {
        //边界检查
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Remove failed,Index is illegal.");
        }

        E ret = data[index]; // 记录, 等待返回

        //i初始化就+1吧,+1好理解,这里容易错
        for (int i = index + 1; i < getSize(); i++) {
            data[i - 1] = data[i]; // 元素前移动
        }
        /*for (int i = index ; i < getSize() - 1 ; i ++) {
            data[i] = data[i + 1]; // 元素前移动
        }*/

        size--;//remove()操作, 维护size
        data[size] = null;//loitering objects != memory leak

        //自动缩容. 不用判断<, 由> 到 < 经过=,会被执行;   采用lazy策略,防止复杂度震荡(扩缩扩缩...) ; 并防止1/4=0情况,不可能生成长度0数组
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(getSize() - 1);
    }

    //按元素删除
    public void removeElement(E e) {
        /*if(contains(e)) {
            for (int i = 0; i < getSize(); i++) {
                if(data[i]==e){
                    remove(i);
                    return true;
                }
            }
        }else{
            return false;
        }*/
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    //扩容
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];

        //复制Array
        for (int i = 0; i < getSize(); i++)
            newData[i] = data[i];

        data = newData;  //改变索引
    }

    //实现数组两下标元素互换
    public void swap(int i, int j) throws IllegalAccessException {
        //数组始于0 ,下标范围: [0,size)为合法
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalAccessException("Index is illegal");

        //合理情况, 交换
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    //覆写toString, 返回容量, 内容
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("cn.yydcyy.array.Array : size = %d, capacity = %d \n", size, data.length));

        res.append('[');
        //for (E i : data) { // 需要用下标i, 不可简写
        for (int i = 0; i < size; i++) { //范围 [0, size)
            res.append(data[i]);

            if (i != size - 1)
                res.append(", "); // 格式处理
        }
        res.append("] ");
        return res.toString();
    }


}
