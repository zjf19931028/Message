package com.future.message.algorithm;

import com.future.message.util.ShowLogUtil;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/15 13:51
 * Description:
 */
public class SolutionString {
    // 翻转字符串
    // 双指针
    // 时间复杂度O(n)
    // 空间复杂度O(1)
    public static char[] reverseString(char[] s) {
        int length = s.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = s[i];
            s[i] = s[length - 1 - i];
            s[length - 1 - i] = temp;
        }
        return s;
    }

    // 整数翻转
    // 时间复杂度O(n)
    // 空间复杂度O(1)
    public static int reverse(int x) {
        boolean isNegative = (x < 0);
        long expandX = x;
        expandX = Math.abs(expandX);
        String s = String.valueOf(expandX);
        char[] chars = s.toCharArray();
        char[] reverseString = reverseString(chars);
        long value = Long.valueOf(String.valueOf(reverseString));
        if (isNegative) value = -1 * value;
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) value;
        }
    }
}
