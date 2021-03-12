package com.future.message.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/22 18:51
 * Description:
 */
public class Solution {
    // 删除排序数组中的重复项
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

    // 旋转数组
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

    //  存在重复元素
    //  int[] a = new int[]{0, 1, 2, 3, 4, 5, 6};
    //  boolean containsDuplicate = Solution.containsDuplicate(a);
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

//    // 两个数组的交集
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public static int[] intersect(int[] nums1, int[] nums2) {
//        if (nums1.length > nums2.length) {
//            return intersect(nums2, nums1);
//        }
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        // 遍历第一个数组放入到哈希表
//        for (int num : nums1) {
//            int count = map.getOrDefault(num, 0) + 1;
//            map.put(num, count);
//        }
//        for (int i = 0; i < intersection.length; i++) {
//            ShowLogUtil.info("[" + i + "]=" + intersection[i]);
//        }
//
//        // 创建一个等比例数组
//        int[] intersection = new int[nums1.length];
//        int index = 0;
//        for (int num : nums2) {
//            int count = map.getOrDefault(num, 0);
//            if (count > 0) {
//                intersection[index++] = num;
//                count--;
//                if (count > 0) {
//                    map.put(num, count);
//                } else {
//                    map.remove(num);
//                }
//            }
//        }
//        for (int i = 0; i < intersection.length; i++) {
//            ShowLogUtil.info("[" + i + "]=" + intersection[i]);
//        }
//        ShowLogUtil.info("=============");
//        return Arrays.copyOfRange(intersection, 0, index);
//    }

    // 时间复杂度O(m+n)
    // 空间复杂度O(min(m,n))
    // 两个数组的交集
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums1) {
            int count = hashMap.getOrDefault(num, 0) + 1;
            hashMap.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = hashMap.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    hashMap.put(num, count);
                } else {
                    hashMap.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);

//        // 排序+双指针
//        // 时间复杂度
//        // 空间复杂度min(m,n)
//        Arrays.sort(nums1);
//        Arrays.sort(nums2);
//        int length1 = nums1.length;
//        int length2 = nums2.length;
//        int[] intersection = new int[Math.min(length1, length2)];
//        int index1 = 0;
//        int index2 = 0;
//        int index = 0;
//        while (index1 < length1 && index2 < length2) {
//            if (nums1[index1] < nums2[index2]) {
//                index1++;
//            }
//            if (nums1[index1] > nums2[index2]) {
//                index2++;
//            }
//            if (nums1[index1] == nums2[index2]) {
//                intersection[index] = nums1[index1];
//                ShowLogUtil.info("intersection[" + index + "]=" + intersection[index]);
//                index1++;
//                index2++;
//                index++;
//            }
//        }
//        return Arrays.copyOfRange(intersection, 0, index);
    }

    // 数学算法
    // 时间复杂度O(n)
    // 空间复杂度O(1)
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            ShowLogUtil.info("[" + i + "]=" + digits[i]);
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static int[] moveZeroes(int[] nums) {
        int offset = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                offset++;
            } else {
                nums[index++] = nums[i];
            }
            if (i == offset++) {
                nums[i] = 0;
            }
        }
        return nums;
    }
}
