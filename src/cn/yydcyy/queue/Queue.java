package cn.yydcyy.queue;

/**
 * Created by YYDCYY on 2019-08-12.
 */
public interface Queue<E> {
    void enqueue(E e);

    E dequeue();

    E getFront();

    int getSize();

    boolean isEmpty();
}
