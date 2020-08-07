package com.learning.lesson01sort;

import com.common.utils.NumberUtil;

/**
 * 插入排序演示（插入排序-直接插入排序）
 *
 * @author Zongheng Ma
 * @date 2020-5-18
 */
public class InsertionSortDemo {

    public static void main(String[] args) {
        // 数组创建及初始化
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(0, array.length);
        }
        long start = System.currentTimeMillis();
        insertionSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("本次排序耗时 %s ms\n", end - start);
    }


    /**
     * 插入排序方法
     *
     * @param arr 待排序数组
     */
    public static void insertionSort(int[] arr) {
        // 遍历索引
        int insertIndex;
        // 插入值
        int insertVal;
        for (int i = 1; i < arr.length; i++) {
            // 从前一个元素开始向前查找
            insertIndex = i - 1;
            // 获取插入值
            insertVal = arr[i];
            // 寻找插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 寻找时移动元素位置
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            /*
             * 关于insertIndex + 1:
             * (1) 若i之前的元素均小于insertVal，while循环将一直持续到i = 0然后退出，退出之前由于有过--
             *     此时insertIndex = -1，这种情况下insertVal的插入位置应在下标0处，故+ 1；
             * (2) 当while循环因为insertVal > arr[insertIndex]退出时
             *     insertIndex此时指向insertVal应插入位置的前驱（指向的是比insertVal小的那个元素），
             *     若不+1会将这个元素覆盖掉，则gg
             */
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
