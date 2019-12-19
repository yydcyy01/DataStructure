package com.yydcyy.test;

import java.util.Scanner;

/**
 * @author YYDCYY
 * @create 2019-09-01
 */
public class T1 {

    public static void main(String[] args) {
        System.out.println(test());
    }
    public static int test() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        if (n <= 0 || m <= 0){
            return 0;
        }
        Integer[] a = new Integer[n ];
        Integer[] b = new Integer[m ];
        int i = 0;
        while (n -- != 0) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用
            a[i ++] = in.nextInt();
        }
        i = 0;
        while (m -- != 0){
            b[i ++] = in.nextInt();
        }

        // 统计奇偶数
        int t1 = 0; // 记录偶数个数  a.length - t1 即为奇数个数
        int t2 = 0;
        for(Integer j : a){
            if (j % 2 == 0) t1 ++;
        }
        System.out.println();
        for (Integer j : b){
            if (j % 2 == 0) t2 ++;
        }

        int t11 = a.length - t1;
        int t22 = b.length - t2;
        int res1 = 0;
        int res2 = 0;
        res1 = Math.min(t1, t22);
        res2 = Math.min(t11, t2);

        int res = res1 + res2;
        if (res <= Math.min(a.length, b.length)){
            return res;
        }else
            return Math.min(a.length, b.length);
    }
}


class Main {

    public static int main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        if (n <= 0 || m <= 0){
            return 0;
        }
        Integer[] a = new Integer[n ];
        Integer[] b = new Integer[m ];
        int i = 0;
        while (n -- != 0) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用
            a[i ++] = in.nextInt();
        }
        i = 0;
        while (m -- != 0){
            b[i ++] = in.nextInt();
        }

        // 统计奇偶数
        int t1 = 0; // 记录偶数个数  a.length - t1 即为奇数个数
        int t2 = 0;
        for(Integer j : a){
            if (j % 2 == 0) t1 ++;
        }

        for (Integer j : b){
            if (j % 2 == 0) t2 ++;
        }

        int t11 = a.length - t1;
        int t22 = b.length - t2;
        int res1 = 0;
        int res2 = 0;
        res1 = Math.min(t1, t22);
        res2 = Math.min(t11, t2);

        int res = res1 + res2;
        if (res <= Math.min(a.length, b.length)){
            return res;
        }else
            return Math.min(a.length, b.length);
    }
}