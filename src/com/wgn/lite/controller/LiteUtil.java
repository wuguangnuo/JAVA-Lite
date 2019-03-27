package com.wgn.lite.controller;

/**
 * Util 合集
 */
class LiteUtil {

    /**
     * 创建链表
     *
     * @param array 数组
     * @return 链表
     */
    static ListNode buildListNode(int[] array) {
        ListNode first = null, last = null, newNode;
        if (array.length > 0) {
            for (int i : array) {
                newNode = new ListNode(i);
                newNode.next = null;
                if (first == null) {
                    first = newNode;
                    last = newNode;
                } else {
                    last.next = newNode;
                    last = newNode;
                }
            }
        }
        return first;
    }

    /**
     * 删除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode p = head, q = head.next;
        while (q != null) {
            if (q.val == val) {
                p.next = q.next;
                q = q.next;
            } else {
                p = p.next;
                q = q.next;
            }
        }
        if (head.val == val) {
            return head.next;
        }
        return head;
    }

    /**
     * 链表 转 字符串
     *
     * @param listNode listNode
     * @return String
     */
    static String listNodeToString(ListNode listNode) {
        if (listNode == null) return "[]";
        StringBuilder merge = new StringBuilder("[" + listNode.val);
        while (listNode.next != null) {
            listNode = listNode.next;
            merge.append(", ").append(listNode.val);
        }
        return merge + "]";
    }
}
