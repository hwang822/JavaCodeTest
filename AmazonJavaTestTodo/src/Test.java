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

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import static java.lang.StrictMath.abs;

class Solution { // total 50 solved
    public static void main(String[] args) throws IOException {
        main1(args); //1.Warm-up Challenges
        main2(args); //2.Arrays
        main3(args);   //3. sorting
        main4(args);  //4. Stacks and Queues
    }


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //https://www.hackerrank.com/interview/interview-preparation-kit/stacks-queues/challenges
    //4. Stacks and Queues
    public static void main4(String[] args) throws IOException {
        String[] grid = {".X.", ".X.", "..."};
        int startX = 0; int startY = 0; int goalX = 0; int goalY = 2;

        int result = minimumMoves(grid, startX, startY, goalX, goalY); //HW Castle on the Grid
    }

    static class D {
        int x;
        int y;

        public D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] steps = null;

    static int getSteps(char[][] board, int a, int b, int c, int d) {
        // a,b  --> c,d
        steps = new int[board.length][board.length];
        Queue<D> que = new LinkedList<D>();
        Queue<D> nextlevel;
        que.offer(new D(c, d));
        int s = 0, n = board.length;
        while (!que.isEmpty()) {
            nextlevel = new LinkedList<D>();
            while (!que.isEmpty()) {
                D cur = que.poll();
                int x = cur.x, y = cur.y;
                if (a == x && b == y)
                    return s;
                steps[x][y] = s;
                for (int j = y - 1; j >= 0; j--) { //left
                    if (board[x][j] == 'X')
                        break;
                    nextlevel.offer(new D(x, j));
                    board[x][j] = 'X';
                }
                for (int j = y + 1; j < n; j++) { //right
                    if (board[x][j] == 'X')
                        break;
                    nextlevel.offer(new D(x, j));
                    board[x][j] = 'X';
                }
                for (int i = x - 1; i >= 0; i--) { //up
                    if (board[i][y] == 'X')
                        break;
                    nextlevel.offer(new D(i, y));
                    board[i][y] = 'X';
                }
                for (int i = x + 1; i < n; i++) {
                    if (board[i][y] == 'X')
                        break;
                    nextlevel.offer(new D(i, y));
                    board[i][y] = 'X';
                }
            }
            s += 1;
            que = nextlevel;
        }
        return s;
    }

    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        char[][] board = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            String str = grid[i];
            board[i] = str.toCharArray();
        }
        return getSteps(board, startX, startY, goalX, goalY);
//        return 0;
    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //https://www.hackerrank.com/interview/interview-preparation-kit/sorting/challenges
    //3. sorting
    public static void main3(String[] args) throws IOException {
        int[] a = {6,4,1}; //Array is sorted in 3 swaps. First Element: 1 Last Element: 6
        countSwaps(a);
        int[] prices = {1, 12, 5, 111, 200, 1000, 10,};
        int k = 50;
        int result = maximumToys(prices, k); //4
        int[] prices1 = {1, 2, 3, 4,};
        k = 7;
        result = maximumToys(prices1, k); //3

        int[] expenditure = {2, 3, 4, 2, 3, 6, 8, 4, 5};
        int d = 5;
        result = activityNotifications(expenditure, d); //2 //HW
        int[] expenditure1 = {1, 2, 3, 4, 4};
        d = 4;
        result = activityNotifications(expenditure1, d); //0 //HW

        int[] arr = {2, 1, 3, 1, 2}; //HW
        long result1 = countInversions(arr); //4
    }

    private static long countInversions(int[] arr) {
        //HW
        int[] aux = arr.clone();
        return countInversions(arr, 0, arr.length - 1, aux);
    }

    private static long countInversions(int[] arr, int lo, int hi, int[] aux) {

        if (lo >= hi) return 0;

        int mid = lo + (hi - lo) / 2;

        long count = 0;
        count += countInversions(aux, lo, mid, arr);
        count += countInversions(aux, mid + 1, hi, arr);
        count += merge(arr, lo, mid, hi, aux);

        return count;
    }

    private static long merge(int[] arr, int lo, int mid, int hi, int[] aux) {
        long count = 0;
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid || j <= hi) {
            if (i > mid) {
                arr[k++] = aux[j++];
            } else if (j > hi) {
                arr[k++] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
                count += mid + 1 - i;
            }
        }

        return count;
    }

    static int activityNotifications(int[] expenditure, int d){
    //HW
        int notificationCount = 0;

        int[] data = new int[201];
        for (int i = 0; i < d; i++) {
            data[expenditure[i]]++;
        }

        for (int i = d; i < expenditure.length; i++) {

            double median = getMedian(d, data);

            if (expenditure[i] >= 2 * median) {
                notificationCount++;

            }

            data[expenditure[i]]++;
            data[expenditure[i - d]]--;

        }

        return notificationCount;

    }

    private static double getMedian(int d, int[] data) {
        double median = 0;
        if (d % 2 == 0) {
            Integer m1 = null;
            Integer m2 = null;
            int count = 0;
            for (int j = 0; j < data.length; j++) {
                count += data[j];
                if (m1 == null && count >= d/2) {
                    m1 = j;
                }
                if (m2 == null && count >= d/2 + 1) {
                    m2 = j;
                    break;
                }
            }
            median = (m1 + m2) / 2.0;
        } else {
            int count = 0;
            for (int j = 0; j < data.length; j++) {
                count += data[j];
                if (count > d/2) {
                    median = j;
                    break;
                }
            }
        }
        return median;
    }

    static class Player{
        void Player(String name, int score){
            this.name= name;
            this.score = score;
        };
        String name;
        int score;
    }

    public int compare(Player a, Player b) {
        if(a.score<b.score)
            return 1;
        else if(a.score>b.score)
            return -1;
        else
            return a.name.compareTo(b.name);
    }

    static int maximumToys(int[] prices, int k) {
        int maxSum = 0;
        Arrays.sort(prices);

        for(int i=0; i<prices.length; i++){
            if(maxSum+prices[i]>k)
                return i;
            maxSum += prices[i];
        }
        return prices.length;
    }

    static void countSwaps(int[] a) {
        int swaps = 0;
        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    swaps++;
                }
            }

        }
        System.out.printf("Array is sorted in %s swaps.\n", swaps);
        System.out.printf("First Element: %s\n", a[0]);
        System.out.printf("Last Element: %s\n", a[a.length-1]);

    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //2.Arrays
    //https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges

    public static void main2(String[] args) throws IOException {
        int[][] arr = {{1, 1, 1, 0, 0, 0},{0, 1, 0, 0, 0, 0},{1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},{0, 0, 0, 2, 0, 0,},{0, 0, 1, 2, 4, 0,}};
        int result = hourglassSum(arr);  //19

        int[] a = {1, 2, 3, 4, 5};
        int d = 4;
        rotLeft(a, d);

        int[] q1 = {2, 1, 5, 3, 4};
        int[] q2 = {2, 5, 1, 3, 4};
        minimumBribes(q1);
        minimumBribes(q2);

        int[] arr1 = {1, 3, 5, 2, 4, 6, 7};
        result = minimumSwaps(arr1);
        int[][] queries = {{1, 2, 100}, {2, 5, 100}, {3, 4, 100}};
        int n = 5;
        long result1 = arrayManipulation(n, queries); //10
    }
    static long arrayManipulation(int n, int[][] queries) {
        long maxSum = Integer.MIN_VALUE;
        long[] array = new long[n+1];
        for(int i=0; i<queries.length; i++){
            int a = queries[i][0];
            int b = queries[i][1];
            long k = queries[i][2];
            array[a - 1] += k;
            array[b] -= k;
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    static int minimumSwaps(int[] arr) {
        int iSwaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int index = arr[i];
            int temp = 0;
            while (index != (i + 1)) {
                temp = arr[index - 1];
                arr[index - 1] = index;
                index = temp;
                iSwaps++;
            }
            arr[index - 1] = index;
        }
        return iSwaps;
    }


    static void minimumBribes(int[] q) { //HW
        int count = 0;
        int expect1 = 1;
        int expect2 = 2;
        int expect3 = 3;

        for(int i = 0; i < q.length; i++){
            if(q[i]==expect1){
                expect1 = expect2;
                expect2 = expect3;
                expect3++;
            }
            else if(q[i]==expect2){
                count++;
                expect2 = expect3;
                expect3++;
            }
            else if(q[i]==expect3){
                count = count + 2;
                expect3++;
            }
            else{
                System.out.printf("Too chaotic\n");
                return;
            }
        }
        System.out.printf("%s\n", count);
    }

    static int[] rotLeft(int[] a, int d) {
        int[] left = new int[d];
        for(int i = 0; i < d; i++){
            left[i] = a[i];
        }
        for(int i = d; i < a.length; i++){
            a[i-d] = a[i];
        }
        for(int i = 0; i < d; i++){
            a[a.length -d + i] = left[i];
        }
        return a;
    }

    static int hourglassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i< arr.length-2; i++){
            for(int j=0; j< arr.length-2; j++) {
                int sum = 0;
                int a = arr[i][j] + arr[i][j+1] + arr[i][j+2];
                sum = sum + a;
                a =  arr[i+1][j+1];
                sum = sum + a;
                a = arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
                sum = sum + a;
                if(sum>maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    //1.Warm-up Challenges
    //https://www.hackerrank.com/interview/interview-preparation-kit/warmup/challenges

    public static void main1(String[] args) throws IOException {

        int[] ar = {10, 20, 20, 10, 10, 30, 50, 10, 20};
        int n = ar.length;
        int result = sockMerchant(n, ar);

        result = countingValleys(8, "UDDDUDUU");
        int[] c = {0, 0, 0, 0, 1, 0};
        result = jumpingOnClouds(c); //3
        int[] c1 = {0, 0, 1, 0, 0, 1, 0};
        result = jumpingOnClouds(c1); //4

        long vresult = repeatedString("abcac", 10); //4
    }

    static long repeatedString(String s, long n) {
        long count = 0;
        char[] a = s.toCharArray();
        long secs = n/a.length;
        long left = n-secs*a.length;
        for(int i=0; i<a.length; i++){
            if(a[i] == 'a'){
                count = count + secs;
                if(i<left)
                    count++;
            }
        }
        return count;
    }


    static int jumpingOnClouds(int[] c) {
        int stps = 0;
        for(int i = 0; i < c.length-1; i++){
            if((c[i]==0)&&(c[i+1]==0)){
                stps++;
                i++;
            }
            else if((c[i]==0)&&(c[i+1]==1)){
                stps++;
                i++;
            }
            else if((c[i]==1)&&(c[i+1]==0)){
                stps++;
            }
            else if((c[i]==1)&&(c[i+1]==1)){
                i++;
            }
        }
        return stps;
    }

    public static int countingValleys(int steps, String path) {
        // Write your code here
        int valCount = 0;
        char[] stp = path.toCharArray();
        int stpCount = 0;
        boolean bVal = false;
        for(int i=0; i< steps; i++){
            if(stp[i]=='D')
                stpCount--;
            else
                stpCount++;
            if(stpCount==0){
                if(bVal==true)
                    valCount++;
                bVal = false;
            }else if(stpCount<0){
                bVal = true;
            }
        }
        return valCount;
    }

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

        int pair = 0;
        HashMap<Integer, Integer> map = new HashMap();
        for(int i=0; i<n;i++){
            int key = ar[i];
            if(map.containsKey(key))
            {
                int count = map.get(key);
                map.put(key, ++count);
            }
            else {
                map.put(key, 1);
            }
        }
        for(int var : map.values() ){
            pair = pair + var/2;
        }
        return pair;
    }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

}

