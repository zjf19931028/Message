package com.future.message.algorithm;

import com.future.message.util.ShowLogUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/16 15:31
 * Description:
 */
public class SolutionLinkedList {
    // 删除链表中的节点
    public static ListNode deleteNode(ListNode node, int deleteValue) {
//        while (node != null) {
//            ShowLogUtil.info("node.val=" + node.val);
//            node = node.next;
//        }
//        ShowLogUtil.info("===============");
//        while (node != null) {
//            ShowLogUtil.info("node.val=" + node.val);
//            if (node.val == deleteValue) {
//                node.val = node.next.val;
//                node.next = node.next.next;
//                break;
//            }
//        }
//        while (node != null) {
//            ShowLogUtil.info("node.val=" + node.val);
//            node = node.next;
//        }
        return node;
    }

    // 删除链表的倒数第N个节点
    public static void removeNthFromEnd(ListNode head, int n) {
//        ListNode dummy = new ListNode(0, head);
//        int length = getLength(head);
//        ListNode cur = dummy;
//        for (int i = 1; i < length - n + 1; ++i) {
//            cur = cur.next;
//            ShowLogUtil.info("cur.val=" + cur.val);
//        }
//        cur.next = cur.next.next;
//        ListNode ans = dummy.next;
//        ergodic(ans);

        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        ergodic(ans);
    }

    public static int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static int ergodic(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            ShowLogUtil.info("node.val=" + node.val);
            node = node.next;
        }
        return length;
    }

}
