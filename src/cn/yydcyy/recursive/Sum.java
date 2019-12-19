package cn.yydcyy.recursive;

/**
 * Created by YYDCYY on 2019-08-13.
 * 递归思想 : 递归函数就是一个函数, 完成一个功能.
 * 所以哈, 递归调用和普通函数调用没啥区别
 * 递归调用代价 : 函数调用 + 系统栈空间 JMM
 *
 * 近乎所有的链表相关操作, 都可以使用递归的形式完成
 */
public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    public static int sum(int[] arr, int len) {
        if (arr.length == len) { //求解最基本问题
            return 0;
        }
        return arr[len] + sum(arr, len + 1); //原问题转化为更小的问题
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
