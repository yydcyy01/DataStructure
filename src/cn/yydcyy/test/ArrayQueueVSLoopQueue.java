package cn.yydcyy.test;

import cn.yydcyy.list.LinkedListQueue;
import cn.yydcyy.queue.LoopQueue;
import cn.yydcyy.queue.Queue;

import java.util.Random;

/**
 * Created by YYDCYY on 2019-08-13.
 */
public class ArrayQueueVSLoopQueue {
    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));//取值范围0-max
        }

        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0; // x.0,不取整
    }

    public static void main(String[] args) {
        int opCount = 10000000;

        //100000时可取消注释, 否则太慢了
        /*ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue , time : " + time1 + " s");*/

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue , time : " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue , time : " + time3 + " s");
        /**
         * opCount = 100k时
         ArrayQueue , time : 5.681222663 s
         LoopQueue , time : 0.021450336 s   差100W倍
         */

        /**
         ArrayQueue , time : 5.696848137 s
         LoopQueue , time : 0.022360522 s
         LinkedListQueue , time : 0.012405954 s
         */

        /**
         * opCount = 1kk时
         LoopQueue , time : 0.144194475 s
         LinkedListQueue , time : 0.351609309 s

         opCount = 10kk时
         LoopQueue , time : 4.979667997 s
         LinkedListQueue , time : 6.832070486 s
         */
    }
}
