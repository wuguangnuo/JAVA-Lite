package com.wgn.lite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static com.wgn.lite.controller.LiteUtil.*;

/**
 * 轻量级,简单化
 */
@Controller
public class LiteController {
    /**
     * 唯一入口: Lite
     *
     * @param model Model
     * @return lite.jsp
     */
    @RequestMapping(value = "/")
    public String lite(Model model) {
        //初始化数据
        String result;
        int data = 0;

        final long sTime = System.nanoTime();

        // 执行测试代码
        data = fib(30);

        prefixesDivBy5(new int[]{});

        final long eTime = System.nanoTime();

        // 调整数据
        result = data + "";

        model.addAttribute("Title", "JAVA Debug - Lite");
        model.addAttribute("NanoTime", new DecimalFormat(",###").format(new BigDecimal(eTime - sTime)));
        model.addAttribute("Result", result);
        return "lite";
    }

    /**
     * 休眠 1s
     *
     * @throws InterruptedException sleep
     */
    private static void threadSleep() throws InterruptedException {
        Thread.sleep(1000);
    }

    /**
     * 测试Java矩阵运算(double)
     */
    private String matrix() {
        final double[][] a = {
                {0.12, 0.23, 0.34, 0.45},
                {0.56, 0.67, 0.78, 0.89},
                {0.91, 0.1011, 0.1112, 0.1213},
                {0.1314, 0.1415, 0.1516, 0.1617}
        };
        final double[][] b = {
                {0.12, 0.23, 0.34, 0.45},
                {0.56, 0.67, 0.78, 0.89},
                {0.91, 0.1011, 0.1112, 0.1213},
                {0.1314, 0.1415, 0.1516, 0.1617}
        };
        int i = 0;
        while (i < 1000 * 1000) {
            matrixMultiplication(a, b);
            ++i;
        }

        return a.length + "*" + a[0].length + " 矩阵相乘" + i + "次";
    }

    /**
     * 矩阵相乘
     *
     * @param a 矩阵a
     * @param b 矩阵b
     * @return 返回矩阵
     */
    private static double[][] matrixMultiplication(double[][] a, double[][] b) {
        if (a[0].length != b.length)
            return null;
        int y = a.length, x = b[0].length;
        double[][] c = new double[y][x];
        for (int i = 0; i < y; ++i)
            for (int j = 0; j < x; ++j)
                for (int k = 0; k < b.length; ++k)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    /**
     * 811. 子域名访问计数
     * subdomain-visit-count
     */
    private String subdomainVisits() {
        // 测试数据
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        // 应得结果
        // ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new LinkedList<>();
        for (String cpdomain : cpdomains) {
            String[] str = cpdomain.split(" ");
            int number = Integer.valueOf(str[0]);
            String[] domain = str[1].split("\\.");
            String temp = "";

            for (int i = domain.length - 1; i >= 0; i--) {
                if (i == domain.length - 1)
                    temp = domain[i];
                else
                    temp = domain[i] + "." + temp;
//                map.putIfAbsent(temp, 0);
                map.put(temp, map.getOrDefault(temp, 0) + number);
            }
        }
        for (String s : map.keySet()) {
            list.add(map.get(s) + " " + s);
        }
        return list.toString();
    }

    /**
     * 771. 宝石与石头
     * jewels-and-stones
     */
    private String numJewelsInStones() {
//        输入: J = "aA", S = "aAAbbbb"
//        输出: 3
        String J = "aA", S = "aAAbbbb";

        char[] j = J.toCharArray(), s = S.toCharArray();
        int c = 0;
        for (char m : j)
            for (char n : s)
                if (m == n)
                    ++c;
        return c + "";
    }

    /**
     * 1015. 至少有 1 位重复的数字
     */
    private int numDupDigitsAtMostN(int N) {
//    给定正整数 N，返回小于等于 N 且具有至少 1 位重复数字的正整数。
//    输入：1000
//    输出：262
        n = N;

        return n + 1 - numDupDigitsAtMostNHelp(0, 0);
    }

    private static int n;

    private static int numDupDigitsAtMostNHelp(int mask, int num) {
        if (num > n) return 0;
        int ret = 1;
        for (int nd = num == 0 ? 1 : 0; nd < 10; nd++) {
            if (((mask >> nd) & 1) == 0) {
                ret += numDupDigitsAtMostNHelp(mask | (1 << nd), num * 10 + nd);
            }
        }
        return ret;
    }

    /**
     * 2. 两数相加
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//        输出：7 -> 0 -> 8
//        原因：342 + 465 = 807

        ListNode temp1 = l1, temp2 = l2, listNode = new ListNode(0), temp = listNode;
        int sum = 0;
        while (temp1 != null || temp2 != null) {
            sum /= 10;
            if (temp1 != null) {
                sum += temp1.val;
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                sum += temp2.val;
                temp2 = temp2.next;
            }
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        if (sum > 9) temp.next = new ListNode(1);
        return listNode.next;
    }

    /**
     * 46.全排列
     *
     * @param nums
     * @return
     */
    private List<List<Integer>> permute(int[] nums) {
//        输入: [1,2,3]
//        输出: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

        len = nums.length;
        ans = new LinkedList<>();
        sonSolve(nums, 0);
        return ans;
    }

    private List<List<Integer>> ans = new LinkedList<>();
    private int len = 0;

    private void swap(int[] number, int a, int b) {
        int temp = number[a];
        number[a] = number[b];
        number[b] = temp;
    }

    private void sonSolve(int[] nums, int N) {
        if (N == len) {
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                temp.add(nums[i]);
            }
            ans.add(temp);
            return;
        }
        for (int i = N; i < len; i++) {
            swap(nums, N, i);
            sonSolve(nums, N + 1);
            swap(nums, N, i);
        }
    }

    /**
     * 914. 卡牌分组
     */
    private boolean hasGroupsSizeX(int[] deck) {
        int[] f = new int[10001];
        int g = 0;
        for (int d : deck) {
            ++f[d];
        }
        for (int v : f) {
            g = gcd(g, v);
        }
        return g != 1;
    }

    /**
     * 136. 只出现一次的数字
     */
    private int singleNumber(int[] nums) {
//        输入: [4,1,2,1,2]
//        输出: 4
        int res = 0;
        for (int num : nums)
            res ^= num;
        return res;
    }

    /**
     * 206. 反转链表
     */
    private ListNode reverseList(ListNode head) {
//        输入: 1->2->3->4->5->NULL
//        输出: 5->4->3->2->1->NULL
        ListNode pre = null, cur = head, next = null;

        // 迭代
        while (cur != null) {
            // 拿到原来链表head的下一个节点
            next = cur.next;
            // 把当前链表的下一个节点指向上一个节点也就是pre
            cur.next = pre;
            // 重置pre为当前链表节点
            pre = cur;
            // 重置当前节点
            cur = next;
        }
        // 返回反转后的链表 也就是pre 其实就是cur
        return pre;
    }

    /**
     * 104. 二叉树的最大深度
     */
    private int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 868. 二进制间距
     */
    private int binaryGap(int N) {
//        输入：22
//        输出：2
        int max = 0, length = -1;
        while (N != 0) {
            if (N % 2 == 0) {
                if (length != -1)
                    length++;
            } else {
                if (length == -1) {
                    length = 0;
                } else {
                    max = Math.max(max, length + 1);
                    length = 0;
                }
            }
            N = N / 2;
        }
        return max;
    }

    /***
     * 869. 重新排序得到 2 的幂
     */
    private boolean reorderedPowerOf2(int N) {
        String num = N + "";
        int len = num.length(), m, n;
        if (len % 3 == 0) {
            m = (len / 3) * 10 - 3;
            n = 3;
        } else if (len % 3 == 1) {
            m = (len / 3) * 10;
            n = 4;
        } else {
            m = (len / 3) * 10 + 4;
            n = 3;
        }
        String[] pows = new String[n];
        for (int i = 0; i < n; i++) {
            pows[i] = (int) Math.pow(2, m + i) + "";
            char[] powlist = pows[i].toCharArray();
            Arrays.sort(powlist);
            char[] numlist = num.toCharArray();
            Arrays.sort(numlist);
            if (Arrays.equals(powlist, numlist))
                return true;
        }
        return false;
    }

    /***
     * 509. 斐波那契数
     */
    private int fib(int N) {
        if (N < 2) return N;
        int[] m = new int[N + 1];
        m[0] = 0;
        m[1] = 1;
        for (int i = 2; i <= N; i++) {
            m[i] = m[i - 1] + m[i - 2];
        }
        return m[N];
    }

    /**
     * 1029. 可被 5 整除的二进制前缀
     */
    private List<Boolean> prefixesDivBy5(int[] A) {
//        输入：[0,1,1]
//        输出：[true,false,false]

        List<Boolean> answer = new LinkedList<>();
        int tail = 0;
        for (int i : A) {
            if (i == 0) {
                tail *= 2;
            } else {
                tail = tail * 2 + 1;
            }
            tail = tail > 9 ? tail - 10 : tail;
            answer.add(tail == 5 || tail == 10);
        }
        return answer;
    }

    /**
     * 1030. 链表中的下一个更大节点
     */
    private int[] nextLargerNodes(ListNode head) {
//        输入：[1,7,5,1,9,2,5,1]
//        输出：[7,9,9,9,0,5,0,0]

        List<Integer> inp = new ArrayList<>();
        inp.add(head.val);
        while (head.next != null) {
            head = head.next;
            inp.add(head.val);
        }
        int l = inp.size();
        int[] res = new int[l];
        for (int i = 0; i < l; ++i) {
            res[i] = getBigger(i, inp);
        }
        return res;
    }

    private int getBigger(int n, List<Integer> inp) {
        int com = inp.get(n);
        for (int i = n + 1; i < inp.size(); ++i) {
            if (inp.get(i) > com) return inp.get(i);
        }
        return 0;
    }

    /**
     * 700. 二叉搜索树中的搜索
     */
    private TreeNode searchBST(TreeNode root, int val) {
//        输入: [4,2,7,1,3]
//        输出: 2

        if (root == null)
            return root;
        if (root.val == val)
            return root;
        else if (root.val > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }
}
