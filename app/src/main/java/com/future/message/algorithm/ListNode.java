package com.future.message.algorithm;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/16 15:32
 * Description:
 */
public class ListNode {
    int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
