import javax.imageio.plugins.tiff.TIFFImageReadParam;
import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class Test {

    static class refrigerator{

        static private class item {
            String name;
            int size;
            void item(String itemName, int itemSize){
                name = itemName;
                size = itemSize;
            }
        }


        static private int[] shelves; //size of spaces
        static private List<item>[] itemsList;
        static void refrigerator(int[] sizes){
            shelves = sizes;   // refrigerator contractor by size shelves
            Arrays.sort(shelves);  // sort size form small to big.
            itemsList = new ArrayList[sizes.length];
        }

        static public item get(item theItem){
            for(int i=0; i<shelves.length; i++){
                for(item it : itemsList[i]){
                    if(it.name == theItem.name){
                        itemsList[i].remove(it);
                        return it;
                    }
                }

            }
            return null;
        }

        static public String put(item theItem){
            for(int i=0; i<shelves.length; i++){
                if(shelves[i]>theItem.size){
                    shelves[i] = shelves[i] - theItem.size; // reduces size available
                    itemsList[i].add(theItem);
                    return "Store at size " + shelves[i] + "shelves";
                }
            }
            return "Error: no size available.";
        }

    }


/*
    public static void main(String[] args) {
        int[] arr = {15, 10, 20, 8, 12, 18, 25};
        Node root = buildBST(arr);
        inorder(root);
        int[] arr1 = {4, 3, 5, 2, 1};
        Node root1 = buildBST(arr1);
        inorder(root1);

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
        Node root2 = SortedArrayToBalancedBST.sortedArrayToBST(values);
        SortedArrayToBalancedBST.traversInorder(root2);
    }

    static class Node {
        Node left = null;
        Node right = null;
        int key;
        Node(int key){
            this.key = key;
        }
    }

    // build BST from sorted array to high balance tree
    static Node buildBST(int[] arr){
        Node root = null;

        for(int i=0; i<arr.length; i++){
            if(root==null){
                root = new Node(arr[i]);
            }
            else{
                if(arr[i]<root.key)
                    root.left = insert(root.left, arr[i]);
                if(arr[i]>root.key)
                    root.right = insert(root.right, arr[i]);
            }
        }
        return root;
    }

    static void inorder(Node node){
        if(node==null){
            return;
        }
        inorder(node.left);
        System.out.printf("s", node.key);
        inorder(node.right);
    }

    static Node insert(Node root, int key){
        if(root==null){
            root = new Node(key);
            return root;
        }
        if(key<root.key){  //sort integer no need check left or right is null
            root.left = new Node(key);
        }
        if(key>root.key){
            root.right = new Node(key);
        }
        return root;
    }

    static class SortedArrayToBalancedBST {
        static void traversInorder(Node root){
            if(root == null){
                return;
            }

            traversInorder(root.left);
            System.out.printf("%s", root.key);
            traversInorder(root.right);
        }

        static Node insertBalace(List<Integer> values, int start, int end){
            if(start>end)
                return null;

            int mid = (start + end)/2;
            Node root = new Node(values.get(mid));
            root.left = insertBalace(values, start, mid-1);
            root.right = insertBalace(values, mid+1, end);
            return root;
        }

        static Node sortedArrayToBST(List<Integer> values){
            if(values.isEmpty()){
                return null;
            }
            return insertBalace(values, 0, values.size()-1);
        }



    }

*/
}

