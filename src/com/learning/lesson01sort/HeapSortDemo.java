/*
 * 堆排序的基本思想
 *
 * (1) 将序列构造成一个大顶堆（若降序排序则用小顶堆）
 * (2) 此时，堆的根节点即序列的最大值，将其与序列尾部元素交换
 * (3) 对剩下的n-1个元素重复(1)、(2)两步，直至堆只剩一个元素，整个序列有序
 */
package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

/**
 * 堆排序演示（选择排序 - 堆排序）
 *
 * @author Zongheng Ma
 * @date 2020-7-6
 */
public class HeapSortDemo {

    public static void main(String[] args) {
        int[] array = new int[800000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(-array.length, array.length);
        }
        long start = System.currentTimeMillis();
        heapSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("本次排序耗时 %s ms\n", end - start);
    }


    /**
     * 堆排序方法
     *
     * @param arr 待排序的序列
     */
    public static void heapSort(int[] arr) {
        // 将无序序列调整为大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换堆顶与堆尾
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            /*
             * 最大元素被交换至堆尾（堆顶与堆尾交换）
             * 此时新的堆顶为下层交换上来的元素，已经不再满足大顶堆的要求
             * 需要从新堆顶开始，将序列重新调整为大顶堆，故此处的i为0
             */
            adjustHeap(arr, 0, j);
        }
    }


    /**
     * 将顺序存储的二叉树调整为大顶堆
     *
     * @param arr    顺序存储的二叉树
     * @param i      非叶子节点在数组的下标
     * @param length 待调整数组的长度
     * @description: 将以i为根节点的子树调整为大顶堆
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素的值
        int temp = arr[i];
        /*
         * k = i * 2 + 1; k是i的左子节点
         * 比较子节点与根节点的大小，决定是否调整位置
         */
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // i的左子节点小于右子节点，则k指向右子节点
                k++;
            }
            if (arr[k] > temp) {
                // 子节点的值大于根，将较大值给到根
                arr[i] = arr[k];
                // i指向k，继续循环
                i = k;
            } else {
                // i已经是最后一个非叶子节点
                break;
            }
        }
        /*
         * 循环结束后，以i为父节点的树的最大值放至顶部
         * 原先的根temp放到调整后的位置上
         */
        arr[i] = temp;
    }
}
