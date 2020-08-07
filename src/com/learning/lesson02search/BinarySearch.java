package com.learning.lesson02search;

import com.common.utils.NumberUtil;
import com.learning.lesson01sort.InsertionSortDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找
 *
 * @author Zongheng Ma
 * @date 2020-6-22
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtil.randomGenerate(0, 5);
        }
        InsertionSortDemo.insertionSort(array);
        searchAll(3, array);
    }

    /**
     * 查找（单个）
     *
     * @param val 待查找元素
     * @param arr 数组
     */
    public static void search(int val, int[] arr) {
        System.out.println(Arrays.toString(arr));
        int index = binarySearchCirculation(val, 0, arr.length - 1, arr);
        if (index >= 0) {
            System.out.printf("值为 %d 的元素索引为 %d\n", val, index);
        } else {
            System.out.printf("未查找到值为 %d 的元素\n", val);
        }
    }


    /**
     * 递归二分查找
     *
     * @param val   待查找的值
     * @param left  左索引
     * @param right 右索引
     * @param arr   数组
     * @return 待查找元素的索引
     */
    public static int binarySearchRecursion(int val, int left, int right, int[] arr) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (val == arr[mid]) {
                // 找到
                return mid;
            } else if (val > arr[mid]) {
                // 向右递归
                return binarySearchRecursion(val, mid + 1, right, arr);
            } else if (val < arr[mid]) {
                // 向左递归
                return binarySearchRecursion(val, left, mid - 1, arr);
            }
        }
        return -1;
    }


    /**
     * 循环二分查找
     *
     * @param val   待查找的值
     * @param start  左索引
     * @param end 右索引
     * @param arr   数组
     * @return 待查找元素的索引
     */
    public static int binarySearchCirculation(int val, int start, int end, int[] arr) {
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (val == arr[mid]) {
                return mid;
            } else if (val > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 查找所有索引
     *
     * @param val 待查找的值
     * @param arr 数组
     */
    public static void searchAll(int val, int[] arr) {
        System.out.println(Arrays.toString(arr));
        List<Integer> indexes = binarySearchAllCir(val, 0, arr.length - 1, arr);
        if (indexes.size() > 0) {
            System.out.printf("值为 %d 的元素索引为：%s\n", val, indexes.toString());
        } else {
            System.out.printf("未查找到值为 %d 的元素\n", val);
        }
    }


    /**
     * 二分查找所有索引
     *
     * @param val   待查找的值
     * @param left  左索引
     * @param right 右索引
     * @param arr   数组
     * @return 匹配到的索引集合
     */
    public static List<Integer> binarySearchAll(int val, int left, int right, int[] arr) {
        // 存储匹配到的元素索引
        List<Integer> indexes = new ArrayList<>();
        if (left <= right) {
            int mid = (left + right) / 2;
            if (val > arr[mid]) {
                // 向右递归
                return binarySearchAll(val, mid + 1, right, arr);
            } else if (val < arr[mid]) {
                // 向左递归
                return binarySearchAll(val, left, mid - 1, arr);
            } else {
                // 找到匹配元素，先向mid左侧查找
                int temp = mid - 1;
                while (temp >= 0 && arr[temp] == val) {
                    indexes.add(temp);
                    temp--;
                }
                // mid存入集合
                indexes.add(mid);
                // 再向mid右侧查找
                temp = mid + 1;
                while (temp <= arr.length + 1 && arr[temp] == val) {
                    indexes.add(temp);
                    temp++;
                }
                return indexes;
            }
        }
        return indexes;
    }


    /**
     * 二分查找（循环）
     *
     * @param val
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> binarySearchAllCir(int val, int start, int end, int[] arr) {
        List<Integer> resList = new ArrayList<>();
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (val < arr[mid]) {
                end = mid - 1;
            } else if (val > arr[mid]) {
                start = mid + 1;
            } else {
                int temp = mid - 1;
                while (temp >=0 && arr[temp] == val) {
                    resList.add(temp);
                    temp--;
                }
                resList.add(mid);
                temp = mid + 1;
                while (temp <= arr.length-1 && arr[temp] == val) {
                    resList.add(temp);
                    temp++;
                }
                break;
            }
        }
        return resList;
    }
}
