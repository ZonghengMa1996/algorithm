package com.common.utils;

import java.util.List;

/**
 * number工具类
 *
 * @author Zongheng Ma
 * @date 2020-5-15
 */
public class NumberUtil {

    /**
     * 在min和max之间随机生成一个整数
     *
     * @param min 下界
     * @param max 上界
     * @return 随机数
     * @throws RuntimeException
     */
    public static int randomGenerate(int min, int max) {
        if (min >= max) {
            throw new RuntimeException("随机数的下界必须小于上界");
        } else {
            return min + (int) (Math.random() * (max - min + 1));
        }
    }


    /**
     * 在指定的数字内随机生成
     *
     * @param designated 指定的数字集合
     * @return
     */
    public static int randomDesignated(List<Integer> designated) {
        return (designated != null && designated.size() > 0) ? designated.get(randomGenerate(0, designated.size() - 1)) : 0;
    }


    /**
     * 根据项号获取斐波那契数列项
     *
     * @param k 项号
     * @return
     */
    public static Integer fibonacciItem(int k) {
        if (k == 1 || k == 2) {
            return 1;
        }
        return fibonacciItem(k - 1) + fibonacciItem(k - 2);
    }


    /**
     * 判断整数的位数
     *
     * @param num 输入的整数
     * @return 输入整数的位数
     */
    public static int getDigit(int num) {
        return String.valueOf(Math.abs(num)).length();
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("斐波那契数列的第%d项为%d\n", i, fibonacciItem(i));
        }
    }
}
