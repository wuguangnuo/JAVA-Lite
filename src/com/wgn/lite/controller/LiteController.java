package com.wgn.lite.controller;

import org.springframework.beans.factory.support.ManagedMap;
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
    public String lite(Model model, String name) {
        //初始化数据
        String result;
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int data;

        final long sTime = System.nanoTime();

        // 执行测试代码
        rotate(nums, k);

        final long eTime = System.nanoTime();
        shortestToChar("loveleetcode", 'v');
        // 调整数据
        result = "";

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
     * 977. 有序数组的平方
     */
    private int[] sortedSquares(int[] A) {
//        输入：[-4,-1,0,3,10]
//        输出：[0,1,9,16,100]
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
            res[i] = A[i] * A[i];
        }
        Arrays.sort(res);
        return res;
    }

    /**
     * 978. 最长湍流子数组
     */
    private int maxTurbulenceSize(int[] A) {
//        输入：[9,4,2,10,7,8,8,1,9]
//        输出：5
        return 0;
    }

    /**
     * 617. 合并二叉树
     */
    private TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//        输入: [1,3,2,5]
//             [2,1,3,null,4,null,7]
//        输出: [3,4,5,5,4,null,7]
        return mergeChild(t1, t2);
    }

    private TreeNode mergeChild(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            t1.val = t1.val + t2.val;
            t1.left = mergeChild(t1.left, t2.left);
            t1.right = mergeChild(t1.right, t2.right);
            return t1;
        } else if (t1 != null) {
            return t1;
        } else if (t2 != null) {
            return t2;
        } else {
            return null;
        }
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

    /**
     * 559. N叉树的最大深度
     */
    private int maxDepth(Node root) {
//        输入：{"id":"1","children":[{"id":"2","children":[{"id":"5","children":[],"val":5},{"id":"6","children":[],"val":6}],"val":3},{"id":"3","children":[],"val":2},{"id":"4","children":[],"val":4}],"val":1}
//        输出：3

        if (root == null) return 0;
        int max = 1;
        for (int i = 0; i < root.children.size(); ++i) {
            max = Math.max(max, 1 + maxDepth(root.children.get(i)));
        }
        return max;
    }

    /**
     * 921. 使括号有效的最少添加
     */
    private int minAddToMakeValid(String S) {
//        输入："()))(("
//        输出：4

        char[] arr = S.toCharArray();
        int r = 0, l = 0;
        for (char a : arr) {
            if (a == '(') {
                ++l;
            } else {
                --l;
                if (l < 0) {
                    l = 0;
                    ++r;
                }
            }
        }
        return r + l;
    }

    /**
     * 717. 1比特与2比特字符
     */
    private boolean isOneBitCharacter(int[] bits) {
//        bits = [1, 1, 1, 0]
//        输出: False

        int i = 0;
        for (; i < bits.length - 1; ++i) {
            if (bits[i] == 1) ++i;
        }
        return i != bits.length;
    }

    /**
     * 21. 合并两个有序链表
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        输入：1->2->4, 1->3->4
//        输出：1->1->2->3->4->4

        ListNode p1 = l1, p2 = l2, pre = new ListNode(0), p = pre;
        while (p1 != null || p2 != null) {
            if (p1 == null) {
                p.next = new ListNode(p2.val);
                p2 = p2.next;
            } else if (p2 == null) {
                p.next = new ListNode(p1.val);
                p1 = p1.next;
            } else if (p1.val <= p2.val) {
                p.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                p.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            p = p.next;
        }
        return pre.next;
    }

    /**
     * 14. 最长公共前缀
     */
    private String longestCommonPrefix(String[] strs) {
//        输入: ["flower","flow","flight"]
//        输出: "fl"

        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String result = "";
        int m = 0;
        while (true) {
            if (strs[0].length() <= m) return result;
            char c = strs[0].charAt(m);
            for (int i = 1; i < strs.length; ++i) {
                if (strs[i].length() <= m || strs[i].charAt(m) != c) return result;
            }
            ++m;
            result = strs[0].substring(0, m);
        }
    }

    /**
     * 846. 一手顺子
     */
    private boolean isNStraightHand(int[] hand, int W) {
//        输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
//        输出：true

        if (hand == null || hand.length == 0 || hand.length % W != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int h : hand) {
            if (map.get(h) > 0) {
                for (int j = 0; j < W; ++j) {
                    if (map.getOrDefault(h + j, 0) > 0) {
                        map.put(h + j, map.get(h + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 28. 实现strStr()
     */
    private int strStr(String haystack, String needle) {
//        输入: haystack = "hello", needle = "ll"
//        输出: 2

        char[] source = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int targetCount = target.length;

        if (targetCount == 0) return 0;

        char first = target[0];
        int max = source.length - targetCount;

        for (int i = 0; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = 1; j < end && source[j] == target[k]; j++, k++) ;
                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 36. 有效的数独
     */
    private boolean isValidSudoku(char[][] board) {
//        输入:
//        [
//          ["5","3",".",".","7",".",".",".","."],
//          ["6",".",".","1","9","5",".",".","."],
//          [".","9","8",".",".",".",".","6","."],
//          ["8",".",".",".","6",".",".",".","3"],
//          ["4",".",".","8",".","3",".",".","1"],
//          ["7",".",".",".","2",".",".",".","6"],
//          [".","6",".",".",".",".","2","8","."],
//          [".",".",".","4","1","9",".",".","5"],
//          [".",".",".",".","8",".",".","7","9"]
//        ]
//        输出: true

        int[] rows = new int[9], cols = new int[9], blks = new int[9];
        for (int ri = 0; ri < 9; ++ri) {
            for (int ci = 0; ci < 9; ++ci) {
                if (board[ri][ci] != '.') {
                    int bi = ri / 3 * 3 + ci / 3;
                    int uvb = 1 << (board[ri][ci] - '0');
                    if ((uvb & (rows[ri] | cols[ci] | blks[bi])) != 0)
                        return false;
                    rows[ri] |= uvb;
                    cols[ci] |= uvb;
                    blks[bi] |= uvb;
                }
            }
        }
        return true;
    }

    /**
     * 53. 最大子序和
     */
    private int maxSubArray(int[] nums) {
//        输入: [-2,1,-3,4,-1,2,1,-5,4],
//        输出: 6

        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 169. 求众数
     */
    public int majorityElement(int[] nums) {
//        输入: [2,2,1,1,1,2,2]
//        输出: 2

        int cnt = 0, ret = 0;
        for (int num : nums) {
            if (cnt == 0) ret = num;
            if (ret != num) --cnt;
            else ++cnt;
        }
        return ret;
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    private int maxProfit(int[] prices) {
        int max = 0, realmax = 0;
        for (int i = 1; i < prices.length; ++i) {
            max += prices[i] - prices[i - 1];
            if (max < 0) max = 0;
            if (max > realmax) realmax = max;
        }
        return realmax;
    }

    /**
     * 70. 爬楼梯
     */
    private int climbStairs(int n) {
//        输入： 3
//        输出： 3

        if (n <= 3) return n;
        int[] m = new int[n + 1];
        m[2] = 2;
        m[3] = 3;
        for (int i = 4; i <= n; i++) {
            m[i] = m[i - 1] + m[i - 2];
        }
        return m[n];
    }

    /**
     * 231. 2的幂
     */
    private boolean isPowerOfTwo(int n) {
//        输入: 16
//        输出: true

        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     */
    private int maxProfit2(int[] prices) {
//        输入: [7,1,5,3,6,4]
//        输出: 7

        if (prices.length <= 1) return 0;
        int count = 0;
        for (int i = 1; i < prices.length; ++i) {
            int dis = prices[i] - prices[i - 1];
            if (dis > 0)
                count += dis;
        }
        return count;
    }

    /**
     * 54. 螺旋矩阵
     */
    private List<Integer> spiralOrder(int[][] matrix) {
//        输入:
//        [
//          [1, 2, 3, 4],
//          [5, 6, 7, 8],
//          [9,10,11,12]
//        ]
//        输出: [1,2,3,4,8,12,11,10,9,5,6,7]

        List<Integer> res = new LinkedList<>();
        int x = matrix.length;
        if (x == 0) {
            res.add(0);
            return res;
        }
        int y = matrix[0].length;
        int m = 0;
        while (m < Math.ceil(new Double(Math.min(x, y)) / 2)) {
            for (int j = 0 + m; j < y - m - 1; ++j) {
                res.add(matrix[m][j]);
            }
            for (int i = 0 + m; i < x - m - 1; ++i) {
                res.add(matrix[i][y - m - 1]);
            }
            for (int j = y - 1 - m; j > 0 + m; --j) {
                res.add(matrix[x - m - 1][j]);
            }
            for (int i = x - m - 1; i > 0 + m; --i) {
                res.add(matrix[i][0 + m]);
            }
            ++m;
        }
        if (res.size() != x * y) {
            res.add(matrix[x / 2][y / 2]);
        }
        return res;
    }

    /**
     * 5016. 删除最外层的括号
     */
    public String removeOuterParentheses(String S) {
//        输入："(()())(())(()(()))"
//        输出："()()()()(())"

        int n = 0;
        List<String> list = new LinkedList<>();
        String pack = "";

        for (char i : S.toCharArray()) {
            pack += i;
            if (i == '(') ++n;
            else --n;
            if (n == 0) {
                list.add(pack);
                pack = "";
            }
        }
        String res = "";
        for (String j : list) {
            res += j.substring(1, j.length() - 1);
        }
        return res;
    }

    /**
     * 5017. 从根到叶的二进制数之和
     */
    public int sumRootToLeaf(TreeNode root) {
//        输入：[1,0,1,0,1,0,1]
//        输出：22

        if (root == null) return 0;
        sumRootToLeafHelper(root, 0);
        return sumRootToLeafRes;
    }

    private int sumRootToLeafRes = 0;

    private void sumRootToLeafHelper(TreeNode r, int v) {
        int cur = v * 2 + r.val;
        cur %= 1000000007;
        if (r.left == null && r.right == null) {
            sumRootToLeafRes += cur;
            sumRootToLeafRes %= 1000000007;
            return;
        }
        if (r.left != null)
            sumRootToLeafHelper(r.left, cur);
        if (r.right != null)
            sumRootToLeafHelper(r.right, cur);
    }

    /**
     * 5018. 驼峰式匹配
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
//        输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
//        输出：[true,false,true,false,false]

//        JavaScript Code
//        const camelMatch = (queries, pattern) => {
//            let res = []
//            for (let q of queries)
//            res.push(helper(q, pattern))
//            return res
//        }, helper = (qs, ps) => {
//            let qa = qs.split(""), pa = ps.split("")
//            for (let q of qa) {
//                if (q <= "Z") {
//                    if (q != pa.shift()) return false
//                } else {
//                    if (q == pa[0]) pa.shift()
//                }
//            }
//            return pa.length == 0
//        }
        return null;
    }

    /**
     * 5019. 视频拼接
     */
    private int videoStitching(int[][] clips, int T) {
//        输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
//        输出：3

        int res = 0, e = 0, tmp;
        while (e < T) {
            tmp = e;
            e = getLongest(e, clips);
            if (tmp == e) return -1;
            ++res;
        }
        return res;
    }

    private int getLongest(int s, int[][] clips) {
        int max = 0;
        for (int[] c : clips)
            if (c[0] <= s && c[1] >= s) max = Math.max(max, c[1]);
        return max;
    }

    /**
     * 26. 删除排序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {
//        输入: [0,0,1,1,1,2,2,3,3,4],
//        输出: [0,1,2,3,4]

        int x = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[x] != nums[i]) {
                nums[++x] = nums[i];
            }
        }
        return x + 1;
    }

    /**
     * 189. 旋转数组
     */
    public void rotate(int[] nums, int k) {
//        输入: [1,2,3,4,5,6,7] 和 k = 3
//        输出: [5,6,7,1,2,3,4]

        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 883. 三维形体投影面积
     */
    public int projectionArea(int[][] grid) {
//        输入：[[1,2],[3,4]]
//        输出：17

        int xy = 0, xz = 0, yz = 0;
        for (int i = 0; i < grid.length; ++i) {
            int max1 = 0, max2 = 0;
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] != 0) ++xy;
                max1 = Math.max(max1, grid[i][j]);
                max2 = Math.max(max2, grid[j][i]);
            }
            xz += max1;
            yz += max2;
        }
        return xy + xz + yz;
    }

    /**
     * 859. 亲密字符串
     */
    public boolean buddyStrings(String A, String B) {
//        输入： A = "aaaaaaabc", B = "aaaaaaacb"
//        输出： true

        if (A.length() != B.length() || A.length() < 2) return false;
        char[] a = A.toCharArray(), b = B.toCharArray();
        if (A.equals(B)) {
            for (int i = 0; i < a.length - 1; ++i) {
                for (int j = i + 1; j < a.length; ++j) {
                    if (a[i] == a[j]) return true;
                }
            }
        }
        int n = 0;
        String tmp = "";
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                if (n > 1) return false;
                if (n == 0) tmp = a[i] + "" + b[i];
                if (n == 1) {
                    if (!tmp.equals(b[i] + "" + a[i])) return false;
                }
                ++n;
            }
        }
        return n == 2;
    }

    /**
     * 965. 单值二叉树
     */
    public boolean isUnivalTree(TreeNode root) {
//        输入：[2,2,2,5,2]
//        输出：false

        return root == null || ((root.left == null || root.left.val == root.val) && (root.right == null || root.right.val == root.val)) && (isUnivalTree(root.left) && isUnivalTree(root.right));
    }

    /**
     * 852. 山脉数组的峰顶索引
     */
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 2; i < A.length; ++i) {
            if (A[i] < A[i - 1]) return i - 1;
        }
        return 0;
    }

    /**
     * 判断奇数
     */
    public boolean isOdd(int n) {
        return (n & 1) == 0;
    }

    /**
     * 171. Excel表列序号
     */
    public int titleToNumber(String s) {
//        输入: "AB"
//        输出: 28

        int res = 0;
        for (int i = 0; i < s.length(); ++i)
            res = res * 26 + s.charAt(i) - 64;
        return res;
    }

    /**
     * 821. 字符的最短距离
     */
    public int[] shortestToChar(String S, char C) {
//        输入: S = "loveleetcode", C = 'e'
//        输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]

        int[] res = new int[S.length()];
        for (int i = 0; i < S.length(); ++i) {
            res[i] = getShort(S, C, i);
        }
        return res;
    }

    private int getShort(String s, char c, int x) {
        if (s.charAt(x) == c) return 0;
        int l = 0, r = 0;
        for (int i = x; i >= 0; --i) {
            if (i == 0) {
                l = 10000;
                break;
            }
            if (i == x) continue;
            ++l;
            if (s.charAt(i) == c) break;

        }
        for (int i = x; i < s.length(); ++i) {
            if (i == s.length() - 1) {
                r = 10000;
                break;
            }
            if (i == x) continue;
            ++r;
            if (s.charAt(i) == c) break;
        }
        return Math.min(l, r);
    }

    /**
     * 912. Sort an Array
     */
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 500. 键盘行
     */
    public String[] findWords(String[] words) {
//        输入: ["Hello", "Alaska", "Dad", "Peace"]
//        输出: ["Alaska", "Dad"]

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('Q', 1);
        map.put('W', 1);
        map.put('E', 1);
        map.put('R', 1);
        map.put('T', 1);
        map.put('Y', 1);
        map.put('U', 1);
        map.put('I', 1);
        map.put('O', 1);
        map.put('P', 1);

        map.put('A', 2);
        map.put('S', 2);
        map.put('D', 2);
        map.put('F', 2);
        map.put('G', 2);
        map.put('H', 2);
        map.put('J', 2);
        map.put('K', 2);
        map.put('L', 2);

        map.put('Z', 3);
        map.put('X', 3);
        map.put('C', 3);
        map.put('V', 3);
        map.put('B', 3);
        map.put('N', 3);
        map.put('M', 3);

        List<String> res = new LinkedList<String>();
        for (String word : words) {
            boolean inline = true;
            int line = 0;
            for (int i = 0; i < word.length(); ++i) {
                Character c = word.charAt(i);
                if (line == 0) {
                    line = map.get(Character.toUpperCase(c));
                } else if (line != map.get(Character.toUpperCase(c))) {
                    inline = false;
                    break;
                }
            }
            if (inline) {
                res.add(word);
            }
        }
        return res.toArray(new String[]{});
    }

    /**
     * 5039. 移动石子直到连续
     */
    public int[] numMovesStones(int a, int b, int c) {
//        输入：a = 1, b = 2, c = 5
//        输出：[1, 2]

        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        int min = 1;
        if (arr[1] - arr[0] == 1 && arr[2] - arr[1] == 1) {
            min = 0;
        } else if (arr[1] - arr[0] > 2 && arr[2] - arr[1] > 2) {
            min = 2;
        }
        return new int[]{min, arr[2] - arr[0] - 2};
    }
}
