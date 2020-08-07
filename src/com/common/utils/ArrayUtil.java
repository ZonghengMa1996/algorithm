package com.common.utils;

/**
 * 数组工具类
 *
 * @author Zongheng Ma
 * @date 2020-5-18
 */
public class ArrayUtil {

    /**
     * 打印二维数组
     *
     * @param array 待输出的二维数组
     */
    public static void print2DeArray(int[][] array) {
        for (int[] row : array) {
            for (int i = 0; i < row.length; i++) {
                System.out.println(row[i] + (i == row.length - 1 ? "\n" : " "));
            }
        }
    }
}
