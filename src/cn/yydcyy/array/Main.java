package cn.yydcyy.array;

import cn.yydcyy.array.Array;

/**
 * Created by YYDCYY on 2019-08-12.
 */
public class Main {
    public static void main(String[] args) {
        //Array<Integer> [] arr = new Array[100];
        Array<Integer> arr = new Array<>(10);
        for (int i = 0; i < arr.getCapacity(); i++) {
            // arr[i] = i;
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.addFirst(0);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);
    }
}
