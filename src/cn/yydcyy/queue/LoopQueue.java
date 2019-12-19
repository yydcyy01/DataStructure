package cn.yydcyy.queue;

/**
 * Created by YYDCYY on 2019-08-13.
 * 注意 :
 * front == tail 队列为空  (tail) % c + 1 == front 表示队列满
 * capacity 浪费一个空间, 形成环
 * LoopQueue 遍历中,for (int i = front; i != tail; i = (i + 1) % data.length) // 详见 toString
 * resize()方法实现中, 终止条件是 i < size, 不是i < capacity
 */
public class LoopQueue<E> implements Queue<E> {
    private int front;
    private int tail;
    private int size;
    private E[] data;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        size = 0;
        tail = 0;
    }

    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>();
        System.out.println(": isEmpty(): " + queue.isEmpty());
        System.out.println(": getCapacity: " + queue.getCapacity());
        //System.out.println(": getFront: "+queue.getFront());  //没问题,成功抛异常.
        System.out.println(": getSize(): " + queue.getSize());
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
        System.out.println(": isEmpty(): " + queue.isEmpty());
        System.out.println(": getCapacity: " + queue.getCapacity());
        System.out.println(": getFront: " + queue.getFront());
        System.out.println(": getSize(): " + queue.getSize());

    }

    @Override
    public void enqueue(E e) {
        //满情况 , 处理
        if ((tail + 1) % data.length == front) {//注 : 这里是capacity,而非size
            resize(2 * getCapacity());
        }
        //非满情况, 处理
        data[tail] = e;
        tail = (tail + 1) % data.length; //判断,循环取模
//        tail++;
        size++;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Connot dequeue from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length; // front ++ ;
        size--;
        //缩容, 防止 1/2 = 0; 扩容时出现非法情况
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < getSize(); i++) { //getSize() 非getCapacity
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E getFront() {
        //先判断不为空
        if (isEmpty()) {
            throw new IllegalArgumentException("Connot getFront from an empty queue.");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        //return size == 0;
        return tail == front;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue : size = %d, capacity = %d \n", size, getCapacity()));
        res.append("front [ ");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
