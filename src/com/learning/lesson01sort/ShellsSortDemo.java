package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

import java.util.Arrays;

/**
 * 希尔排序演示（插入排序-希尔排序）
 *
 * @author Zongheng Ma
 * @date 2020-5-19
 */
public class ShellsSortDemo {

    public static void main(String[] args) {
        // 数组创建及初始化
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(0, array.length);
        }
        int[] array2 = Arrays.copyOf(array, array.length);

        long start = System.currentTimeMillis();
        shellSortExchange(array);
        long end1 = System.currentTimeMillis();
        shellSortShift(array2);
        long end2 = System.currentTimeMillis();
        System.out.printf("交换法耗时为 %s ms，移位法耗时为 %s ms\n", end1 - start, end2 - end1);
    }


    /**
     * 希尔排序[交换法]
     *
     * @param arr 待排序数组
     */
    public static void shellSortExchange(int[] arr) {
        // 交换变量
        int temp;
        // 当增量小于1时停止
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            // 遍历各组
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组的元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        /*
                         * 发现逆序交换元素位置
                         * 交换需要元素一个个比对大小再进行交换
                         * 故[交换法]的性能消耗起始大于直接插入排序
                         */
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }


    /**
     * 希尔排序[移位法]
     *
     * @param arr 待排序数组
     */
    public static void shellSortShift(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            /*
             * 对每个分组分别进行直接插入排序
             * i++时，切到了另外一个分组的插入排序中；
             * 故实际上，各分组的插入排序不是一次性将当前分组执行完，而是按轮次交替执行的
             */
            // 从第gap个元素开始，对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                // 以gap为增量的分组内，进行局部的直接插入排序
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 元素向后移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 找到位置，插入元素
                    arr[j] = temp;
                }
            }
        }
    }
}
