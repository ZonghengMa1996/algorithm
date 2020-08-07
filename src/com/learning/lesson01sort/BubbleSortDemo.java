package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

/**
 * 冒泡排序演示实例（交换排序-冒泡排序）
 *
 * @author Zongheng Ma
 * @date 2020-5-15
 */
public class BubbleSortDemo {

    public static void main(String[] args) {
        // 初始化数组
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = NumberUtil.randomGenerate(0, arr.length);
        }

        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行排序
        bubbleSort(arr);
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        System.out.printf("共%d次冒泡，运行时间为%sms\n", bubbleCount, endTime - startTime);
    }


    /**
     * 冒泡次数统计量
     */
    private static int bubbleCount = 0;


    /**
     * 冒泡排序
     *
     * @param array 输入数组
     */
    public static void bubbleSort(int[] array) {
        for (int j = array.length - 1; j >= 1; j--) {
            // 是否发生交换的flag
            boolean exchange = false;
            // 记录冒泡次数
            bubbleCount++;
            for (int i = 0; i <= j - 1; i++) {
                // 若前>后，则交换这两个元素的位置
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    // 发生了交换，flag置true
                    exchange = true;
                }
            }
            // 没有一次交换，序列已经有序，结束冒泡
            if (!exchange) {
                break;
            }
        }
    }
}
