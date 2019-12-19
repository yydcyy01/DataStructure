package cn.yydcyy.recursive;

/**
 * Created by YYDCYY on 2019-08-13.
 */
public class ShowRecursive {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new ShowRecursive()).removeElements(head, 6, 0);
        System.out.println(res);
    }

    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthSting = generateDepthString(depth);

        System.out.print(depthSting);
        System.out.println("Call : remove" + val + "in" + head);

        //设置终止条件
        if (head == null) {
            System.out.print(depthSting);
            System.out.println("Result: " + head);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthSting);
        System.out.println("After remove  " + val + " : " + res);

        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthSting);
        System.out.println("Result: " + ret);

        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    //链表节点的构造函数
    //使用arr为参数, 创建一个链表, 当前的ListNode为链表的节点.
    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 0; i < arr.length - 1; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    //当前节点为头结点的链表的信息字符串
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("null");
        return res.toString();
    }
}