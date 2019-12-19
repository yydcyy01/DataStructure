package cn.yydcyy.list;

import cn.yydcyy.queue.Queue;

/**
 * Created by YYDCYY on 2019-08-13.
 * 使用头尾节点 LinkedList 实现队列.
 * 优点: 队头插入,队尾删除. 时间复杂度O(1);
 * <p>
 * tail指向最后一个Node
 * dequeue()方法实现时, 既要维护head, 也要维护tail
 */
public class LinkedListQueue<E> implements Queue<E> {
    private Node head, tail;
    private int size;

    //空参构造
    public LinkedListQueue() {
        tail = null;
        head = null;
        size = 0;
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    @Override
    public void enqueue(E e) {
        //空队列情况
        if (tail == null) {
            tail = new Node(e);//tail.next=null;
            head = tail;
        } else {
            /*tail.next = new Node(e,tail.next);
            tail=tail.next;*/
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Deququ is failed,Queue is empty.");
        } else {
            Node retNode = head;
            head = head.next;
            retNode.next = null;

            //同时维护 tail
            if (head == null) {
                tail = null;
            }
            size--;
            return retNode.e;
        }
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("GetFront is failed,Queue is empty.");
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue : front");

        for (Node cur = head; cur != null; cur = cur.next) {
            res.append(cur.e + "-> ");
        }
        res.append("null tail");
        return res.toString();
    }

    private class Node {
        public E e;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
