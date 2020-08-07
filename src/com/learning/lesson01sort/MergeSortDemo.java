/*
 * 归并排序的基本思想（分治）
 *
 * （1）[分]将待排序序列递归拆分成子序列（直至区间内元素个数为1）
 * （2）[治]两个子序列合并为一个有序序列，并回写到原序列的对应位置
 */
package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

/**
 * 归并排序演示
 *
 * @author Zongheng Ma
 * @date 2020-5-25
 */
public class MergeSortDemo {

    public static void main(String[] args) {
        // 数组创建及初始化
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(-array.length, array.length);
        }

        int[] temp = new int[array.length];
        long start = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.printf("本次排序耗时 %s ms\n", end - start);
    }


    /**
     * 归并排序
     *
     * @param arr   待排序数组
     * @param left  索引左端索引
     * @param right 索引右端索引
     * @param temp  暂存数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        // 拆分数组，直至区间内仅有一个元素
        if (left < right) {
            // 取中点
            int mid = (left + right) / 2;
            // 向左递归
            mergeSort(arr, left, mid, temp);
            // 向右递归
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, right, mid, temp);
        }
    }


    /**
     * 合并的方法
     *
     * @param arr   待排序数组
     * @param left  左边有序子序列的初始索引
     * @param right 右边索引
     * @param mid   中间索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        // 左边有序数列的初始索引
        int l = left;
        // 右边有序数列的初始索引
        int r = mid + 1;
        // temp插入元素的索引
        int t = 0;

        // 左右两个（有序）序列的数据规则填充到temp
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }

        // 剩余元素赋给temp
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }

        // 将temp拷贝给arr
        if (t >= 0) {
            System.arraycopy(temp, 0, arr, left, t);
        }
    }
}
