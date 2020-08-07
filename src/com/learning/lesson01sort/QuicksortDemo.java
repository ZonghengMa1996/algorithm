/*
 * 快速排序的基本思想
 *
 * （1）根据arr.length找到下标位于区间正中的元素pivot;
 * （2）定义两个指针（l和r）分别从区间的左端和右端相向遍历；
 * （3）将比pivot小的元素置于中间数左侧，大的置于右侧（遍历过程中将l、r所指的元素交换）；
 * （4）左侧递归 (left, mid - 1); 右侧递归 (mid + 1, right)；
 * （5）区间内只剩一个元素（left = right）时，无需再进行排序，退出递归
 */
package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

import java.util.Arrays;

/**
 * 快速排序演示
 *
 * @author Zongheng Ma
 * @date 2020-5-22
 */
public class QuicksortDemo {

    public static void main(String[] args) {
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(-array.length, array.length);
        }
        int[] array2 = Arrays.copyOf(array, array.length);
        long start = System.currentTimeMillis();
        quickSortMedium(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
        quickSortFirst(array2, 0, array2.length - 1);
        long end2 = System.currentTimeMillis();
        System.out.printf("[中间数]排序耗时 %s ms，[挖坑填数]耗时 %s ms\n", end - start, end2 - end);
    }


    /**
     * 快速排序（中间数）
     *
     * @param arr   待排序数组
     * @param left  左端下标
     * @param right 右端下标
     */
    public static void quickSortMedium(int[] arr, int left, int right) {
        // 遍历数组的左右指针
        int l = left, r = right;
        // 取下标位于正中的元素为中间值
        int pivot = arr[(left + right) / 2];
        // 交换用的临时变量
        int temp;
        while (l < r) {
            // l指针寻找比pivot大的元素
            while (arr[l] < pivot) {
                l++;
            }
            // r指针寻找比pivot小的元素
            while (arr[r] > pivot) {
                r--;
            }
            // l>=r，说明pivot左/右边的值已经都小/大于等于pivot
            if (l >= r) {
                break;
            }
            // 交换lr的值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 左等于pivot，右指针前移
            if (arr[l] == pivot) {
                r--;
            }
            // 右等于pivot，左指针后移
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 若l=r则分别自加自减将其错开，否则会栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 中间数左侧不止一个元素，向左递归
        if (left < r) {
            quickSortMedium(arr, left, r);
        }
        // 中间数右侧不止一个元素，向右递归
        if (right > l) {
            quickSortMedium(arr, l, right);
        }
    }


    /**
     * 快速排序（挖坑填数）
     *
     * @param arr   待排序数组
     * @param left  左端
     * @param right 右端
     */
    public static void quickSortFirst(int[] arr, int left, int right) {
        // 当区间内仅有一个元素时，递归终止
        if (left < right) {
            // 将第一个数（下标位于最左端的元素）作为中间值
            int l = left, r = right, pivot = arr[left];
            while (l < r) {
                // 从右向左找第一个小于中间值的元素
                while (l < r && arr[r] >= pivot) {
                    r--;
                }
                if (l < r) {
                    // 若找到，则填至左侧
                    arr[l++] = arr[r];
                }

                // 从左向右找第一个大于等于中间值的元素
                while (l < r && arr[l] < pivot) {
                    l++;
                }
                if (l < r) {
                    // 若找到，则填至右侧
                    arr[r--] = arr[l];
                }
            }
            // l=r时退出循环，确定了中间数的位置
            arr[l] = pivot;
            // 中间数左侧区间，左递归
            quickSortFirst(arr, left, l - 1);
            // 中间数右侧区间，右递归
            quickSortFirst(arr, l + 1, right);
        }
    }
}
