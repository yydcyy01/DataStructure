package cn.yydcyy.queue;

import cn.yydcyy.array.Array;

/**
 * Created by YYDCYY on 2019-08-12.
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue() {
        array = new Array<>();
    }

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(30);
        for (int i = 0; i < queue.getCapacity(); i++) {
            queue.enqueue(i);
            System.out.println(queue);
            //3i-1(i>0)
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(i + ": :" + queue);

            }
        }
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue : front [ ");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != getSize() - 1)
                res.append(", ");

        }
        res.append("] tail");
        return res.toString();
    }
}
