package cn.yydcyy.stack;

/**
 * Created by YYDCYY on 2019-08-12.
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    E pop();

    void push(E e);

    E peek();
}
