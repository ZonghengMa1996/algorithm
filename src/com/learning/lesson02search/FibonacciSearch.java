package com.learning.lesson02search;

import com.common.utils.NumberUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 斐波那契查找
 *
 * @author Zongheng Ma
 * @date 2020-6-24
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        // 初始化斐波那契数组
        for (int i = 0; i < FBNC.length; i++) {
            FBNC[i] = NumberUtil.fibonacciItem(i + 1);
        }
        // 查找数组的创建与初始化
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 11, 11};
        int val = 11;
        // 获取匹配索引集合
        List<Integer> resList = fibonacciSearch(val, array);
        System.out.println(Arrays.toString(array));
        if (resList.size() > 0) {
            System.out.printf("值为%d的元素索引为%s\n", val, resList.toString());
        } else {
            System.out.printf("未找到值为%d的元素\n", val);
        }
    }


    /**
     * 斐波那契查找
     *
     * @param val 待查找的元素值
     * @param arr 数组
     * @return 包含匹配元素索引的集合
     */
    public static List<Integer> fibonacciSearch(int val, int[] arr) {
        // 包含匹配元素索引的集合
        List<Integer> resList = new ArrayList<>();
        // 左右索引
        int left = 0, right = arr.length - 1;
        // 判断val是否在范围内
        if (val < arr[left] || val > arr[right]) {
            return resList;
        }
        // 斐波那契数量项号
        int k = getFibonacciNum(arr.length);
        // 中间索引
        int mid;
        // 将数组的长度扩充至F[k]-1
        int[] temp = Arrays.copyOf(arr, FBNC[k] - 1);
        for (int i = right + 1; i <= temp.length - 1; i++) {
            temp[i] = arr[right];
        }
        right = temp.length - 1;
        while (left <= right) {
            mid = left + FBNC[k - 1] - 1;
            if (val > temp[mid]) {
                // 向右查找
                left = mid + 1;
                /*
                 * 右边长度为 F[k-2]
                 * 根据斐波那契数列及黄金分割
                 * 将右侧拆分 F[k-2] = F[k-3] + F[k-4]
                 */
                k -= 2;
            } else if (val < temp[mid]) {
                // 向左查找
                right = mid - 1;
                /*
                 * 左边长度为 F[k-1]
                 * 将左侧拆分 F[k-1] = F[k-2] + F[k-3]
                 */
                k -= 1;
            } else {
                /*
                 * 查找时，可能出现在temp多出的部分中匹配的情况
                 * 此时应对mid进行修正，否则之后回到arr查找其他等值元素时会有IndexOutOfBoundsException
                 * 而temp中多出的元素均为arr[n-1]的复制
                 * 故将mid定位至arr.length-1
                 */
                if (mid > arr.length - 1) {
                    mid = arr.length - 1;
                }
                // 为了能正序返回索引，使用栈来暂存mid左边的等值元素的索引
                Stack<Integer> indexStack = new Stack<>();
                // 向左遍历寻找等值元素
                int index = mid - 1;
                while (index >= 0 && arr[index] == val) {
                    indexStack.push(index);
                    index--;
                }
                // 索引出栈
                while (!indexStack.isEmpty()) {
                    resList.add(indexStack.pop());
                }
                resList.add(mid);
                index = mid + 1;
                // 向右遍历寻找等值元素
                while (index <= arr.length - 1 && arr[index] == val) {
                    resList.add(index);
                    index++;
                }
                break;
            }
        }
        return resList;
    }


    /**
     * 斐波那契数组
     */
    private static final int[] FBNC = new int[20];


    /**
     * 获取斐波那契项号
     *
     * @param n 数组长度
     * @return
     */
    private static Integer getFibonacciNum(int n) {
        int k = 0;
        while (FBNC[k] - 1 < n) {
            k++;
        }
        return k;
    }
}
