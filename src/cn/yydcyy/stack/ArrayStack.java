package cn.yydcyy.stack;

import cn.yydcyy.array.Array;

/**
 * Created by YYDCYY on 2019-08-12.
 * Array 方法调用了哦, 不是array[i].  万物皆对象
 * toString格式 : stack button[   ] top .  故Add / pop 都是 调用 addLast()
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    //空参构造,Array.capacity默认10
    public ArrayStack() {
        array = new Array<>();
    }

    //指定capacity构造
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 6; i++) {
            stack.push(i * 2);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

    @Override
    public int getSize() {
        return array.getSize();

    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public void push(E e) {
        array.addLast(e);

    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("stack : button[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
