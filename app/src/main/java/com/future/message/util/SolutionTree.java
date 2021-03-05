package com.future.message.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/5 13:01
 * Description:
 */
public class SolutionTree {
    // 二叉树最大深度
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }


    // 验证二叉搜索树
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        // 左树的值小于根节点，右树的值大于根节点
        if (isValidBST(root.left) && isValidBST(root.right)
                && (root.left.val < root.val) && (root.right.val > root.val)) return true;
        return false;
    }

    // 二叉树的层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;
        List<List<Integer>> parent = new ArrayList<>();
        List<Integer> child1 = new ArrayList<>();
        if (root != null) {
            child1.add(root.val);
        }
        parent.add(child1);

        List<Integer> child2 = new ArrayList<>();
        if (root.left != null) {
            child2.add(root.left.val);
        }
        if (root.right != null) {
            child2.add(root.right.val);
        }
        parent.add(child2);

        List<Integer> child3 = new ArrayList<>();
        if (root.right.left != null) {
            child3.add(root.right.left.val);
        }
        if (root.right.right != null) {
            child3.add(root.right.right.val);
        }
        parent.add(child3);
        return parent;
    }
}
