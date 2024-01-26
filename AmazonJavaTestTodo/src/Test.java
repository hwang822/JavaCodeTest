import javax.xml.transform.Templates;
import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.io.Reader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Solution { // total 50 solved

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int key;

        TreeNode() {
            left = null;
            right = null;
        }

        TreeNode(int i) {
            left = null;
            right = null;
            key = i;
        }

    }

    public static void main(String[] args) throws IOException {
        //constractTreefromGivenPreorderTraversalTest(); //1. Construct a special tree from given preorder traversal (to know wihch one would be node and leaf, put leaf to prv node)
        //getMinPostiveNumberNoInArrayTest(); //2. get first missed number at 1, 2 ... n (sorted array first)
        //getMostUsedWordTest(); //3 get Most Used Word, Most Used Word Times from words"
        //getTreeHighTest(); //4 Write a Program to Find the Maximum Depth or Height of a Tree
        //playerComparatorTest(); //5, sort list object by field.
        //pickingNumbersTest(); //6,
        //findKSmallestItemsTest(); //7
        getSongsTimeinLimitTest();

    }

    //1 ***********************
    static void constractTreefromGivenPreorderTraversalTest() {
        int[] pre = {10, 30, 20, 5, 15};      // N:10 -> (N:30 -> LL: 20 ->RL: 5) -> RL: 10
        char[] preLN = {'N', 'N', 'L', 'L', 'L'};
        TreeNode root = null;
        root = constractTreefromGivenPreorderTraversal(pre, preLN, null);

        List<Integer> list = new ArrayList<>();
        preTravelar(root, list);
    }

    static int preIndex = 0;

    static TreeNode constractTreefromGivenPreorderTraversal(int[] pre, char[] preLN, TreeNode nood) {
        int n = pre.length;
        if (preIndex == n)
            return nood;

        TreeNode temp = new TreeNode(pre[preIndex]);
        if (preLN[preIndex++] == 'N') {
            temp.left = constractTreefromGivenPreorderTraversal(pre, preLN, temp.left);
            temp.right = constractTreefromGivenPreorderTraversal(pre, preLN, temp.right);
        }
        return temp;
    }

    //2 ***********************

    static void getMinPostiveNumberNoInArrayTest() {
        int[] A = {4, 2, 2, 4, 2};
        int res = getMinPostiveNumberNoInArray(A);  // return 1
        int[] A1 = {1, 2, 3, 2};
        int res1 = getMinPostiveNumberNoInArray(A1);  // return 4
        int[] A2 = {0, 5, 4, 4, 5, 12};
        int res2 = getMinPostiveNumberNoInArray(A2);  // return 1;
        int[] A3 = {4, 4};
        int res3 = getMinPostiveNumberNoInArray(A3); // return 1
        int[] A4 = {-1, -3};
        int res4 = getMinPostiveNumberNoInArray(A4); // return 1
        int[] A5 = {1, 3, 6, 4, 1, 2};
        int res5 = getMinPostiveNumberNoInArray(A5); // return 5
        int[] A6 = {1, 2, 3};
        int res6 = getMinPostiveNumberNoInArray(A6); // return 4
        int[] A7 = {-1, -3};
        int res7 = getMinPostiveNumberNoInArray(A7); // return 1

    }

    ;

    static int getMinPostiveNumberNoInArray(int[] A) {
        int minA = 1;
        Arrays.sort(A);

        for (int a : A) {
            if (a < minA) {
                continue;
            } else if (a == minA) {
                minA++;
                continue;
            } else
                return minA;
        }
        return minA;

    }

    //3 *********************************
    static void getMostUsedWordTest() { // get Most Used Word, Most Used Word Times from words"
        String[] words = {"Two", "households", "both", "alike", "in", "dignity", "Two", "Two", "both"};
        getMostUsedWord(words);  //"Two", 3
    }

    ;

    // get Most Used Word, Most Used Word Times from words"
    static void getMostUsedWord(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        String mostUsedWord = "";
        int mostUsedTimes = 0;
        for (String word : words) {
            int iTimes = 1;
            map.keySet();
            if (map.containsKey(word)) {
                iTimes = map.get(word) + 1;
            }
            map.put(word, iTimes);
            if (iTimes > mostUsedTimes) {
                mostUsedTimes = iTimes;
                mostUsedWord = word;
            }

        }

        System.out.printf("Most Used Word: %s, Most Used Word Times: %s",
                mostUsedWord, mostUsedTimes);
        return;
    }

    //4 ******************************
    static void getTreeHighTest() { // Write a Program to Find the Maximum Depth or Height of a Tree
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        int ret = getTreeHigh(node);
        System.out.println(ret);
    }

    static int getTreeHigh(TreeNode root) {
        //1-10. Write a Program to Find the Maximum Depth or Height of a Tree
        if (root == null)
            return 0;
        return Math.max(getTreeHigh(root.left) + 1, getTreeHigh(root.right) + 1);
    }

    static void preTravelar(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.key);
        preTravelar(root.left, list);
        preTravelar(root.right, list);
    }

    /* /////////////////////////////////////////
    This is a demo task.

    Write a function:

    class Solution { public int solution(int[] A); }

    that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

    For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

    Given A = [1, 2, 3], the function should return 4.

    Given A = [−1, −3], the function should return 1.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [1..100,000];
    each element of array A is an integer within the range [−1,000,000..1,000,000].
    */


    //5 ***********************
    static class Player {
        String name;
        Integer score;

        Player(String name, Integer score) {
            this.name = name;
            this.score = score;
        }
    }

    static void playerComparatorTest() {   // Comparator<T> java.unit.

        Player[] playerList = new Player[]
                {
                        new Player("amy", 100),
                        new Player("aakansha", 75),
                        new Player("david", 100),
                        new Player("heraldo", 50),
                        new Player("aleksa", 150)
                };

        playerComparator(playerList);
    }

    static void playerComparator(Player[] playList) {   // Comparator<T> java.unit.
        Arrays.sort(playList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if(o1.score==o2.score)
                    return o1.name.compareTo(o2.name);
                else
                    return o1.score.compareTo(o2.score);

            }
        });
    }

    static void playerComparator1(Player[] playList) {   // Comparator<T> java.unit.
        Arrays.sort(playList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                int scoreCom = p2.score.compareTo(p1.score);
                if (scoreCom == 0) { //p1, p2 score is same
                    int nameCom = p1.name.compareTo(p2.name);  // p1, p2 difference of character name
                    return nameCom;
                }
                return scoreCom;
            }
        });
    }

    //6. ******************************8
    static void pickingNumbersTest() {
        //??? Given an array of integers, find the longest sub array where the absolute difference between any two elements is less than or equal to 1.
        //4 6 5 3 3 1   //4, 6 diff 2; 6, 5 diff 1; 5,3 diff 2; 3, 3 diff 0; 3, 1 diff 2; longest 1.
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(6);
        list.add(5);
        list.add(3);
        list.add(3);
        int res = pickingNumbers(list);
        System.out.println(res);
    }

    static int pickingNumbers(List<Integer> a) {
        // Write your code here
        int max = 0;
        int count = 0;
        int index = 1;
        while (index < a.size()) {
            int a1 = a.get(index - 1);
            int a2 = a.get(index);
            if (Math.abs(a1 - a2) <= 1) {
                count++;
            } else {
                if (count > max)
                    max = count;
                count = 0;
            }
            index++;
        }
        if (count > max)
            max = count;
        return max;
    }

    //7 ***************************************
    //
    static void findKSmallestItemsTest() {
        int arr[] = {20, 4, 1, 29, 13, 13};
        int k=3;
        int[] kArr = findKSmallestItems(arr, k);
        System.out.println(kArr);
    }

    static int[] findKSmallestItems(int[] arr, int k) {
        // using a heap (smallest heap) to keep tracking of smallest distance from a array
        int smallestDist = 0;
        int[] heap = new int[k]; // the [k] always keep smallest dist index
        for (int i = 0; i < arr.length; i++) {
            //arr[];  //20, 4, 1, 29, 13, 13  k=3
            if (i < k) {
                if (i == 0) {                     // 20:[0,0,0] -> 4:[1,0,0] -> 1:[2,1,0]
                    heap[i] = i;
                    smallestDist = arr[i];
                } else {
                    if (arr[i] < smallestDist) {
                        heap[i] = heap[i - 1];
                        heap[i - 1] = i;
                    } else {
                        heap[i] = i;
                        smallestDist = arr[i];
                    }
                }
            } else {
                if (arr[i] < smallestDist) {     // 20:[0,0,0] -> 4:[1,0,0] -> 1:[2,1,0]->29[2,1,0]->13[2,1,0]->13[2,1,0]
                    heap[k - 1] = i;        // move index
                    smallestDist = arr[i];
                }
            }
        }
        return heap;
    }

    public static void getSongsTimeinLimitTest() {
        // SLB interview from songtimes list find two songs time sum = targetTime
        int targetTime = 420;
        String[][] songTimes = {
                {"Song1", "3:20"},
                {"Song2", "4:30"},
                {"Song3", "3:40"},
                {"Song4", "1:12"},
        };

        getSongsTimeinLimit(songTimes, targetTime);

        //[Song1, 3:20]
        //[Song3, 3:40]
    }

    public static void getSongsTimeinLimit(String[][] songTimes, int targetTime){

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int index = 0; index < songTimes.length; index++){
            String[] songTime =  songTimes[index];
            String[] time = songTimes[index][1].split(":");
            int iTime = Integer.valueOf(time[0])*60 + Integer.valueOf(time[1]);
            map.put(iTime, index);
        }
        for(int index = 0; index < songTimes.length; index++){
            String[] songTime =  songTimes[index];
            String[] time = songTime[1].split(":");
            int iTime = Integer.valueOf(time[0])*60 + Integer.valueOf(time[1]);
            if(map.containsKey(targetTime-iTime)){
                System.out.println(Arrays.toString(songTimes[index]));
                System.out.println(Arrays.toString(songTimes[map.get(targetTime-iTime)]));
                return;
            }
        }
    }
}