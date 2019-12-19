package cn.yydcyy.list;

/**
 * Created by YYDCYY on 2019-08-13.
 * 注意 :
 * 操作都是:  ① Node prev = dummyHead; dummyHead别动
 * ② Node cur = dummyHead.next;
 * for (int i = 0; i < index - 1; i++)  两种写法, 统一用①
 * add,remove 操作实现, 记得维护 size ;
 * <p>
 * removeLast() , 实现 return remove(size - 1); 记得 -1
 * Node{} 需要实现toString()方法
 */
public class LinkedList<E> {
    private Node dummyHead;
    private int size;

    //空参构造
    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.remove(0);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.set(1, 777);
        System.out.println(linkedList);

        System.out.println("Contains:\"1\":" + linkedList.contains(1));

        System.out.println("get(2):  " + linkedList.get(2));

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
       /* if(null == head){
            return true;
        }*/
        return size == 0;
    }

    /**
     * index : 插入第几个角标的前面
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        //边界检查
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, Illegal index.");

        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next); //简洁,帅气不
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 刚看完<Java核心1> 说的这个list中的get(index) 速度老慢了, 可不是Array, 别乱用
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed, Illegal index");

        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed, Illegal index");

        Node cur = dummyHead;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    //从头遍历到尾
    public boolean contains(E e) {
        Node cur = dummyHead;
        cur = cur.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;

            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed, Illegal index");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;// 记录删除的节点, 待返回
        prev.next = retNode.next;
        retNode.next = null; //置空
        size--;
        return retNode.e;
    }

    public void removeElement(E e) {
        Node prev = dummyHead;
        //并不会导致最后一个元素不被判断. 这里难以理解,注: prev.next当一个整体,.(体现多一个dummyHead好处往往两个地址引用地方,现在一个解决.), 既可以比较prev.next.e 与 E e值, 也可以prev.next进行调整.
        while (prev.next != null) {
            prev = prev.next;
            if (prev.e.equals(e))
                break;

        }
        //最后一个元素情况
        if (prev.next != null) {
            //由于dummyHead的存在,prev.next(不是当前的下一个Node)是最后一个节点
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
        /*else{
            //不处理
        }*/
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        //java.lang.NullPointerException   记得size-1
        return remove(size - 1);

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "-> ");
        }
        res.append("NULL");
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
