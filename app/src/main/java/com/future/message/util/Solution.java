package com.future.message.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/22 18:51
 * Description:
 */
public class Solution {
    public static int removeDuplicates(int[] nums) {
        // 定义双指针，第一个指针代表不重复的下标，即index，第二个指针代表每次循环向后推进的下标，即i
        // 比较两个指针的值，如果相等，什么操作都不用做，继续向后循环。
        // 如果不相等，需要把第一个指针向后对进一个，然后赋值为第二个指针的值
        // 直到循环结束，第一个指针的数值增加1，这跟下标是从0开始有关。
        // 时间复杂的O(N)
        // 空间复杂的O(1)
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return ++index;
    }

//    public static void rotate(int[] nums, int k) {

//    }

    public static void rotate(int[] nums, int k) {
//        // 使用额外的数组
        // 时间复杂的O(N)
        // 空间复杂的O(N)
//        k = k % nums.length;
//        int n = nums.length;
//        int[] newArr = new int[n];
//        for (int i = 0; i < n; ++i) {
//            newArr[(i + k) % n] = nums[i];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            ShowLogUtil.info("newArr[" + i + "]=" + newArr[i]);
//        }

//        //数组翻转
        // 时间复杂的O(2N)
        // 空间复杂的O(1)
//        k = k % nums.length;
//        reverse(nums, 0, nums.length - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, nums.length - 1);
//        for (int i = 0; i < nums.length; i++) {
//            ShowLogUtil.info("nums[" + i + "]=" + nums[i]);
//        }

        // 时间复杂的O(N平方)
        // 空间复杂的O(1)
        //        k = k % nums.length;
//        int temp = 0;
//        for (int j = 0; j < k; j++) {
//            temp = nums[nums.length - 1];
//            for (int i = (nums.length - 1); i > 0; i--) {
//                nums[i] = nums[i - 1];
//            }
//            nums[0] = temp;
//        }
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static boolean containsDuplicate(int[] nums) {
        // 排序
        // 时间复杂的O(NlogN)
        // 空间复杂的O(logN)
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;

//        // 哈希表
//        // 时间复杂的O(N)
//        // 空间复杂的O(1)
//        Set<Integer> hashSet = new HashSet<>();
//        for (int n : nums) {
//            if (!hashSet.add(n))
//                return true;
//        }
//        return false;
    }

}
