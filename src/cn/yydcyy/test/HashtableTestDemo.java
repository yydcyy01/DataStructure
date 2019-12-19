package cn.yydcyy.test;

import cn.yydcyy.hashtable.HashTable1;
import cn.yydcyy.utils.FileOperation;

import java.util.ArrayList;

/**
 * @author YYDCYY
 * @create 2019-09-05
 */
public class HashtableTestDemo {
    public static void main(String[] args) {

        System.out.println("丰乳肥臀");

        ArrayList<String> words = new ArrayList<>();
        //if(FileOperation.readFile("D:\\IntelijProjectPackage\\Array\\src\\com\\yydcyy\\test\\File\\pride-and-prejudice.txt", words)) {
        //if(FileOperation.readFile("/Users/yuyang/Downloads/windows/IntelijProjectPackage/Array/src/com/yydcyy/test/File/pride-and-prejudice.txt", words)) {
        //if(FileOperation.readFile("/Users/yuyang/IdeaProjects/DataStructure/src/com/yydcyy/test/resource/FRFT.txt", words)) {
        if(FileOperation.readFile("src/com/yydcyy/test/resource/FRFT.txt", words)) {
            //
            System.out.println("Total words: " + words.size());

            long startTime;
            long endTime ;
            startTime = System.nanoTime();

             //HashTable3<String, Integer> ht = new HashTable3<>();
             //HashTable2<String, Integer> ht = new HashTable2<>();
             HashTable1<String, Integer> ht = new HashTable1<>();
            //HashTable<String, Integer> ht = new HashTable<>(131071);\

            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }
            for(String word: words)
                ht.contains(word);

            endTime = System.nanoTime();

            double  time2 = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time2 + " s");
        }

        System.out.println();
        /** 结果说明 :
         * 没实现 resize() 前,   默认 M = 97 : prejudice 耗时 0.063s     M= 131071 (最接近 12w 单词量的一个素数) 耗时 0.031 s
         * 实现 resize() 后,  M = 97 :  0.74s  M = 131071 0.047s   但是空间更灵活了.
         *
         *
         * Mac 上测试中文小说试试
         * HashTable3
         * 丰乳肥臀
         * Total words: 50456
         * HashTable: 0.056154855 s
         *
         * HashTable2
         * 丰乳肥臀
         * Total words: 50456
         * HashTable: 0.05454065 s
         *
         *  HashTable1
         * 丰乳肥臀
         * Total words: 50456
         * HashTable: 0.052567832 s
         *
         * 差不多. 可能数据少了.
         */
    }
}
