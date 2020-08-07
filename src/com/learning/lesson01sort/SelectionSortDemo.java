package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

/**
 * 选择排序演示（选择排序-简单选择排序）
 *
 * @author Zongheng Ma
 * @date 2020-5-18
 */
public class SelectionSortDemo {

    public static void main(String[] args) {
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(0, array.length);
        }
        long start = System.currentTimeMillis();
        selectionSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("本次排序耗时 %s ms\n", end - start);
    }


    /**
     * 选择排序方法
     *
     * @param array 待排序数组
     */
    public static void selectionSort(int[] array) {
        // 从第一个元素开始逐轮寻找最小值
        for (int i = 0; i < array.length; i++) {
            // 初始化最小值索引
            int minIndex = i;
            // 遍历后面的元素寻找最小值
            for (int j = i + 1; j < array.length; j++) {
                // 若有更小的数，则重新获取最小值索引
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }

            // 找到了最小值，交换
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
