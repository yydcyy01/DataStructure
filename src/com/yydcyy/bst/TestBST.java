package com.yydcyy.bst;

/**
 * @author YYDCYY
 * @create 2019-09-17
 * 测试 二分搜索 find()方法 递归和非递归实现运行时间差别
 */
public class TestBST {
    private TestBST(){}
    public static void main(String[] args) {

        int N = 10000000;
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);


        // 测试非递归二分查找法
        long startTime = System.currentTimeMillis();

        for(int i = 0 ; i < 2*N ; i ++) {
            int v = BST1.find1(arr, new Integer(i));
            if (i < N) {
                if (v != i) throw new RuntimeException("aeesert v == i");
            }
            else //i>=N 情况
                if( v != -1  ) throw new RuntimeException("aeesert v == i-1");
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search (Without Recursion): " + (endTime - startTime) + "ms");


        // 测试递归的二分查找法
        startTime = System.currentTimeMillis();

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for(int i = 0 ; i < 2*N ; i ++) {
            int v = BST1.find2(arr, new Integer(i));
            if (i < N) {
                if (v != i) throw new RuntimeException("aeesert v == i");
            }
            else //i>=N 情况
                if( v != -1  ) throw new RuntimeException("aeesert v == i-1");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Binary Search (With Recursion): " + (endTime - startTime) + "ms");

        /**
         *100w数据
         * Binary Search (Without Recursion): 277ms
         Binary Search (With Recursion): 208ms

         Binary Search (Without Recursion): 113ms
         Binary Search (With Recursion): 152ms


         1000W数据
         Binary Search (Without Recursion): 2042ms
         Binary Search (With Recursion): 2007ms

         Binary Search (Without Recursion): 1149ms
         Binary Search (With Recursion): 1937ms
         结论 递归比非递归慢 具体慢多少, 看电脑及 idea 配置吧. 两次数据一次是老电脑 hp i5; 一次是新电脑 mbp 9代i7
         */
    }
}
