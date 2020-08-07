package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序（桶排序）演示
 *
 * @author Zongheng Ma
 * @date 2020-5-26
 */
public class RadixSortDemo {

    public static void main(String[] args) {
        // 数组创建及初始化
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(0, array.length);
        }

        long start = System.currentTimeMillis();
        positiveSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("本次排序耗时 %s ms\n", end - start);
    }


    /**
     * 全为自然数的序列排序
     *
     * @param arr 待排序的序列
     */
    public static void positiveSort(int[] arr) {
        // 序列的最大值
        int max = 0;
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        // 传入数组和序列最大值的位数
        radixSort(arr, NumberUtil.getDigit(max));
    }


    /**
     * 序列含有负数的基数排序
     *
     * @param arr 待排序数组
     */
    public static void negativeSort(int[] arr) {
        // 负数最大数
        int nMax = 0;
        // 正数最大数
        int pMax = 0;
        // 暂存负数
        List<Integer> nList = new ArrayList<>();
        // 暂存正数
        List<Integer> pList = new ArrayList<>();
        // 正负数组赋值
        for (int value : arr) {
            if (value < 0) {
                if (Math.abs(value) > nMax) {
                    nMax = Math.abs(value);
                }
                nList.add(value);
            } else {
                if (value > pMax) {
                    pMax = value;
                }
                pList.add(value);
            }
        }

        // 原数组录入元素的索引
        int index = 0;
        // 若原序列内含负数
        if (nList.size() > 0) {
            // 将负数数组倒序插入到原数组中
            int[] nArray = nList.stream().mapToInt(Integer::intValue).toArray();
            radixSort(nArray, NumberUtil.getDigit(nMax));
            for (int i = nArray.length - 1; i >= 0; i--) {
                arr[index++] = nArray[i];
            }
        }
        // 若原数组内含正数
        if (pList.size() > 0) {
            // 将正数数组按顺序插入到原数组
            int[] pArray = pList.stream().mapToInt(Integer::intValue).toArray();
            radixSort(pArray, NumberUtil.getDigit(pMax));
            for (int value : pArray) {
                arr[index++] = value;
            }
        }
    }


    /**
     * 基数排序
     *
     * @param arr 待排序数组
     * @param n   数组元素的最高位数
     */
    public static void radixSort(int[] arr, int n) {
        /*
         * 用二维数组表示10个桶
         * 1.每行的一维数组即一个桶
         * 2.为防止溢出，每个桶大小定为arr.length
         * 3.行下标即对应的数值
         */
        int[][] buckets = new int[10][arr.length];
        // 记录各个桶的每次放入的有效元素个数
        int[] elCount = new int[buckets.length];

        /*
         * 由低位到高位逐位排序
         * 第1轮根据个位
         * 第2轮根据十位
         * 第3轮根据百位，以此类推
         */
        for (int k = 0; k < n; k++) {
            // 记录下标
            int index;
            for (int value : arr) {
                // 当前数位的值即为桶的下标
                index = Math.abs(value) / (int) Math.pow(10, k) % 10;
                // 将元素翻入对应桶
                buckets[index][elCount[index]] = value;
                // 对应桶的元素个数修改
                elCount[index]++;
            }
            // 取出各桶内元素放入原数组
            index = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < elCount[i]; j++) {
                    arr[index++] = buckets[i][j];
                }
                // 桶元素计数清零
                elCount[i] = 0;
            }
        }
    }
}
