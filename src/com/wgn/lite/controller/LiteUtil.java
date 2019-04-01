package com.wgn.lite.controller;

import java.util.List;

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
     * 打印链表
     *
     * @param listNode listNode
     * @return String
     */
    static String printListNode(ListNode listNode) {
        if (listNode == null) return "NULL";
        StringBuilder merge = new StringBuilder("" + listNode.val);
        while (listNode.next != null) {
            listNode = listNode.next;
            merge.append("->").append(listNode.val);
        }
        return merge + "->NULL";
    }

    /**
     * 打印矩阵
     *
     * @param matrix 矩阵
     * @return String
     */
    static String printMatrix(List<List<Integer>> matrix) {
        if (matrix.size() == 0) return "[]";
        StringBuilder merge = new StringBuilder("[");
        for (List<Integer> row : matrix) {
            merge.append("[");
            for (Integer n : row) {
                merge.append(n).append(", ");
            }
            merge = new StringBuilder(merge.substring(0, merge.length() - 2));
            merge.append("], ");
        }
        merge = new StringBuilder(merge.substring(0, merge.length() - 2));
        return merge + "]";
    }

    /**
     * 辗转相除法 最大公约数
     *
     * @param a int
     * @param b int
     * @return 最大公约数
     */
    static int gcd(int a, int b) {
        while (b > 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    static String printTreeNode(TreeNode root) { // 先序遍历 中序遍历 后序遍历 层序遍历
        return "";
    }

    /**
     * 数组转二叉树
     *
     * @param array Integer[] 层序遍历
     * @param index 0
     * @return TreeNode
     */
    static TreeNode buildTreeNode(Integer[] array, int index) {
        TreeNode tree = null;
        if (index < array.length) {
            Integer value = array[index];
            if (value == null) {
                return null;
            }
            tree = new TreeNode(value);
            tree.left = buildTreeNode(array, 2 * index + 1);
            tree.right = buildTreeNode(array, 2 * index + 2);
            return tree;
        }
        return tree;
    }
}
