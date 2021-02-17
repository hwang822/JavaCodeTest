import java.io.*;
import java.util.*;

public class Facebook {

    //from youtube Teh Problem: You have s sorted array of integers.
    //Write a function that returns a sorted array containing the squares of those integers.

    static void sortedArraySquaresTest(){
        int[] arr = {-2, 1, 3, 6};
        arr = sortedArraySquares(arr); // {1, 4, 9, 36}
    }

    static int[] sortedArraySquares(int[] arr){
        int[] result = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;

        for(int i=arr.length-1; i >= 0; i--){
            if(Math.abs(arr[left]) > arr[right]){
                result[i] = arr[left] * arr[left];
                left++;
            }
            else
            {
                result[i] = arr[right] * arr[right];
                right--;
            }
        }
        return result;
    }

    //facebook interview example. Find out avalage value of each leave nodes data in BST.
    //Given a binary tree. get the avarage value at each leave of the tree
    //input
    //              4
    //            /    \
    //           7      9
    //         /   \     \
    //        10   2      4
    //              \
    //               6
    //              /
    //             2
    //
    //output: {4, 8, 6, 6, 2}

    static void findAvargeVlaueOfLeaveOfBSTTest(){
        Amazon.TreeNode root = new Amazon.TreeNode(4);
        root.left = new Amazon.TreeNode(7);
        root.right = new Amazon.TreeNode(9);
        root.left.left = new Amazon.TreeNode(10);
        root.left.right = new Amazon.TreeNode(2);
        root.right.right = new Amazon.TreeNode(4);
        root.left.right.right = new Amazon.TreeNode(6);
        root.left.right.right.left = new Amazon.TreeNode(2);
        findAvargeVlaueOfLeaveOfBST(root);
    }

    static HashMap<Integer, List<Integer>> map = new HashMap<>();
    static List<Integer> findAvargeVlaueOfLeaveOfBST(Amazon.TreeNode root){
        List<Integer> result = new ArrayList<>();
        findAvargeVlaueOfLeaveOfBSTUtl(root, 0);
        for(List vals : map.values()){
            float val = 0;
            for(int i = 0; i < vals.size(); i++)
                val = val + (int)vals.get(i);;
            if(vals.size()>0)
                result.add((int)Math.ceil(val/vals.size()));
        }
        return result;
    }

    static void findAvargeVlaueOfLeaveOfBSTUtl(Amazon.TreeNode node, int leave) {
        if(node==null)
            return;
        //int val = node.key;
        List<Integer> vals; // = new ArrayList<>();
        if(map.containsKey(leave))
            vals = map.get(leave);
        else
            vals = new ArrayList<>();
        vals.add(node.key);
        map.put(leave, vals);
        findAvargeVlaueOfLeaveOfBSTUtl(node.left,leave+1);
        findAvargeVlaueOfLeaveOfBSTUtl(node.right,leave+1);
        return;
    }


//Tree

//#1 InorderTravelTree and GetNextNode
// Facebook remote interviewer by zhouhui at 8/18/2017, 12PM CTS.
// 1. create inorder travel in a given binary tree.
// 2. create function to TreeNode* = getNextNode(TreeNode* root)
    //(a) Inorder (Left, Root, Right) : 4 2 5 1 3
//(b) Preorder (Root, Left, Right) : 1 2 4 5 3
//(c) Postorder (Left, Right, Root) : 4 5 2 3 1
// Breadth First or Level Order Traversal : 1 2 3 4 5
//		     /   \
//         2       3
//        / \
//      4    5
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int key;
        TreeNode(){
            left = null;
            right = null;
        }
        TreeNode(int i){
            left = null;
            right = null;
            key = i;
        }

    }

    static void treeIndorderTravelofBSTTest(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        treeIndorderTravelofBST(root);
        TreeNode nextNode = getNextNode(root.left);
        return;
    }

    static void treeIndorderTravelofBST(TreeNode root){
        if(root==null)
            return;
        if(root.left!=null)
            treeIndorderTravelofBST(root.left);
        System.out.printf("%s ", root.key);
        if(root.right!=null)
            treeIndorderTravelofBST(root.right);
    };

    static TreeNode getNextNode(TreeNode node){
        if(node==null)
            return null;
        if(node.left!=null)
            return node.left;
        if(node.right!=null)
            return node.right;
        return null;
    };

}
