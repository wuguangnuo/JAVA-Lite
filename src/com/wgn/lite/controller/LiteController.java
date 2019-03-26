package com.wgn.lite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public String lite(Model model) throws InterruptedException {
        final long sTime = System.nanoTime();

        // 执行测试代码
        String result = threadSleep();

        final long eTime = System.nanoTime();
        model.addAttribute("Title", "JAVA Debug - Lite");
        model.addAttribute("NanoTime", new DecimalFormat(",###").format(new BigDecimal(eTime - sTime)));
        model.addAttribute("Result", result);
        return "lite";
    }

    /**
     * 休眠 1s
     * @throws InterruptedException sleep
     */
    private static String threadSleep() throws InterruptedException {
        Thread.sleep(1000);
        return null;
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
}
