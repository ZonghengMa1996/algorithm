package com.learning.lesson02search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插值查找
 *
 * @author Zongheng Ma
 * @date 2020-6-23
 */
public class InterpolationSearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 5};
        int val = 5;
        List<Integer> res = interpolationSearch(val, arr);
        System.out.println(Arrays.toString(arr));
        if (res.size() > 0) {
            System.out.printf("值为 %d 的元素索引为：%s\n", val, res.toString());
        } else {
            System.out.printf("未查找到值为 %d 的元素\n", val);
        }
    }


    /**
     * 插值查找
     *
     * @param val 待查找的元素值
     * @param arr 数组
     * @return 包含匹配元素索引的集合
     */
    public static List<Integer> interpolationSearch(int val, int[] arr) {
        List<Integer> resList = new ArrayList<>();
        // 左右索引
        int left = 0, right = arr.length - 1;
        if (val >= arr[left] && val <= arr[right]) {
            // 中间索引
            int mid;
            while (left <= right) {
                // 自适应确定中间索引
                mid = left != right ? (left + (right - left) * (val - arr[left]) / (arr[right] - arr[left])) : left;
                if (val > arr[mid]) {
                    // 向右查找
                    left = mid + 1;
                } else if (val < arr[mid]) {
                    // 向左查找
                    right = mid - 1;
                } else {
                    int temp = mid - 1;
                    // 向左遍历匹配元素
                    while (temp >= 0 && arr[temp] == val) {
                        resList.add(temp);
                        temp--;
                    }
                    resList.add(mid);
                    temp = mid + 1;
                    // 向右遍历匹配元素
                    while (temp <= arr.length - 1 && arr[temp] == val) {
                        resList.add(temp);
                        temp++;
                    }
                    break;
                }
            }
        }
        return resList;
    }
}
