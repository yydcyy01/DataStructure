package cn.yydcyy.hashtable;

/**
 * @author YYDCYY
 * @create 2019-09-05
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Java 包装类型和 String 可以调用 hashCode 方法
         */
        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double d = 3.1415926;
        System.out.println(((Double)d).hashCode());

        //String s = "YYDCYY";    
        String s = "yydcyy";
        System.out.println(s.hashCode());
    }
}
