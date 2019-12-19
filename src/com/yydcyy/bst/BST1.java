package com.yydcyy.bst;

/**
 * @author YYDCYY
 * @create 2019-09-17
 * 二分查找法 核心是 find() 方法实现.  //二分查找,  变量意义, 边界 L <= R 等
 *
 */
public class BST1 {
    private BST1(){
    }

    /**
     * find1 非递归实现
     * 传入数组, 目标值,得target 目标角标 或 -1 无;
     */
    public static int find1(Comparable[] arr, Comparable target){
        int l = 0, r = arr.length - 1;
        while( l <= r){
            int mid = l + (r - l)/2;  //无bug情况,  防止极端情况下的整形溢出，使用下面的逻辑求出mid
            if( arr[mid].compareTo(target) == 0)
                return mid;

            if( arr[mid].compareTo(target) > 0)
                r = mid -1;
            else
                l = mid + 1;
        }
        return -1;
    }

    /**
     * find1 递归实现
     *     传入数组, 目标值,得target 目标角标 或 -1 无;
     */

    public static int find2(Comparable[] arr ,Comparable target){
        return Helper(arr, 0, arr.length - 1, target);
    }
    //递归实现帮助函数
    private static int Helper(Comparable[] arr, int l, int r, Comparable target){
        if( l > r) return -1;
        int mid = l + (r - l) / 2;

        if( arr[mid].compareTo(target) == 0 ) //递归终止条件
            return mid;
        else if( arr[mid].compareTo(target) > 0)  //target在mid左
            return Helper(arr, l, mid -1, target);
        else  //target在mid右
            return Helper(arr, mid + 1, r, target);

    }



    public static void main(String[] args) {

        int N = 1000000;
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for(int i = 0 ; i < 2*N ; i ++) {
            //int v = BST1.find2(arr, new Integer(i)); // 测试递归方法实现二分
            int v = BST1.find1(arr, new Integer(i)); // 测试非递归方法实现二分
            if (i < N) {
                if (v != i) throw new RuntimeException("aeesert v == i");
            }
            else //i>=N 情况
                if( v != -1  ) throw new RuntimeException("aeesert v == i-1");
        }
        System.out.println("Over!");
        return;
    }
}
