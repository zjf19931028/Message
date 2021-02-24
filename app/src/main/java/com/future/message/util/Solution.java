package com.future.message.util;

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
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return ++index;
    }
}
