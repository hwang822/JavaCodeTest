import javax.swing.*;
import java.io.*;
import java.util.*;

public class Amazon {
    //https://www.geeksforgeeks.org/amazon-interview-preparation/
/*
    //https://www.geeksforgeeks.org/printing-brackets-matrix-chain-multiplication-problem/
    //1-1. (hard)  Printing brackets in Matrix Chain Multiplication Problem
    //思路：增加bracket数组，bracket[start][end]记录从start到end当中的mid，即加括号的地方。若start==end说明只有一个矩阵，直接输出。令name(使用引用)从'A'开始，一旦输出一个，便执行++name
    static int[][] dp = new int[51][51]; //从1开始，不使用0
    static int[][] bracket = new int[51][51]; //记录括号添加的位置

    static int minMatrixMul(int[] arr, int num){
        int start, mid, end;
        int times; //记录当前的最优解，即最少的运算次数
        //dp[i][i]=0 已经在初始化的时候完成
        //dp[i][j] 代表i号到j号矩阵链的运算次数
        for (int length = 2; length < num; ++length) {
            for (start = 1; start <= num-length; ++start) {
                end = start + length - 1;
                dp[start][end] = Integer.MIN_VALUE;
                for (mid = start; mid < end; ++mid) {
                    times = dp[start][mid] + dp[mid+1][end] + arr[start-1]*arr[mid]*arr[end];
                    //如果找到当前长度下更优的解，立刻更新dp数组
                    if (times < dp[start][end]) {
                        dp[start][end] = times;
                        //更新添加括号的位置
                        bracket[start][end] = mid;
                    }
                }
            }
        }
        return dp[1][num-1];
    }

    static void printBrackets(int start, int end, char name){
        String prtString = "";
        if (start == end) {
            prtString =  prtString + Character.toString(name);
            ++name;
            return;
        }
        prtString =  prtString +  "(";
        printBrackets(start, bracket[start][end], name);
        printBrackets(bracket[start][end]+1, end, name);
        prtString =  prtString + ")";

        System.out.println(prtString);
    }

    static void  matrixChainOrderTest() {
        int num;
        char name = 'A';
        int arr[] = {30, 35, 15, 5, 10, 20};
        //memset(dp, 0, sizeof(dp));
        //num = sizeof(arr)/ sizeof(int);
        num = arr.length;
        System.out.printf("Minimum number of multiplications is %s Order: ", minMatrixMul(arr, num) );
        printBrackets(1, num-1, name);
    }

*/

    // Driver code
    static void matrixChainOrderTest()
    {
        int arr[] = {40, 20, 30, 10, 30};   //[40, 20] = 800  [20, 30] = 600 [30, 10] = 300 [10, 30] 300
        int n = 5;

        matrixChainOrder(arr, n);
    }

    static void printParenthesis(int i, int j, int n,
                          int[][] bracket, char name)
    {
        // If only one matrix left in current segment
        if (i == j)
        {
            System.out.printf("%s", name++);
            return;
        }

        System.out.printf("(");

        // Recursively put brackets around subexpression
        // from i to bracket[i][j].
        // Note that "*((bracket+i*n)+j)" is similar to
        // bracket[i][j]
        printParenthesis(i,bracket[i][j], n,
                bracket, name);

        // Recursively put brackets around subexpression
        // from bracket[i][j] + 1 to j.
        printParenthesis(bracket[i][j] + 1, j,
                n, bracket, name);
        System.out.printf(")");
    }

    static void matrixChainOrder(int p[], int n)
    {
    // For simplicity of the program, one extra
    //   row and one extra column are allocated in
    //    m[][]. 0th row and 0th column of m[][]
    //    are not used
        int[][] m = new int[n][n];

        // bracket[i][j] stores optimal break point in
        // subexpression from i to j.
        int[][] bracket = new int[n][n];

    // m[i,j] = Minimum number of scalar multiplications
    //needed to compute the matrix A[i]A[i+1]...A[j] =
    //A[i..j] where dimension of A[i] is p[i-1] x p[i]

        // cost is zero when multiplying one matrix.
        for (int i=1; i<n; i++)
            m[i][i] = 0;

        // L is chain length.
        for (int L=2; L<n; L++)
        {
            for (int i=1; i<n-L+1; i++)
            {
                int j = i+L-1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k=i; k<=j-1; k++)
                {
                    // q = cost/scalar multiplications
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j])
                    {
                        m[i][j] = q;

                        // Each entry bracket[i,j]=k shows
                        // where to split the product arr
                        // i,i+1....j for the minimum cost.
                        bracket[i][j] = k;
                    }
                }
            }
        }

        // The first matrix is printed as 'A', next as 'B',
        // and so on
        char name = 'A';

        System.out.printf("Optimal Parenthesization is : ");
        printParenthesis(1, n-1, n, bracket, name);
        System.out.printf("nOptimal Cost is : %s", m[1][n-1]);
    }

    //https://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
    //1-2. Count total set bits in all numbers from 1 to n

    static void countSetBitsUtilTest(){
        int n = 4;   // bit set: 1, 10, 11, 100  total: 5
        int x = countSetBits(n);
        System.out.printf("Total set bit count is %s", x);
    }

    // Returns count of set bits present
    //  in all numbers from 1 to n

    static int countSetBits( int n) {
        int bitCount = 0;
        for(int i=1; i<=n; i++)
            bitCount += countSetBitsUtil(i);
        return bitCount;
    }

    static int countSetBitsUtil( int x) {
        if(x <= 0)
            return 0;
        return (x % 2 == 0 ? 0 : 1) + countSetBitsUtil(x / 2);
    }

    //https://www.geeksforgeeks.org/number-subsequences-form-ai-bj-ck/
    //1-3. Number of subsequences of the form a^i b^j c^k
    static void countSubsequencesTest(){
        String s = "abbcabc";
        System.out.println(countSubsequences(s));
    }


    // Returns count of subsequences of the form
    // a^i b^j c^k
   //Input abbc, Output : 3 Subsequences are abc, abc and abbc

    static int countSubsequences(String s)
    {
        // caused by different combination of 'a', 'b'
        // and 'c'.

        int aCount = 0;
        int bCount = 0;
        int cCount = 0;

        // Traverse all characters of given string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a')
                aCount = (1 + 2 * aCount);
            else if (s.charAt(i) == 'b')
                bCount = (aCount + 2 * bCount);
            else if (s.charAt(i) == 'c')
                cCount = (bCount + 2 * cCount);
        }
        return cCount;
    }

    //https://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/
    //1-4. Replace every element with the greatest element on right side
    static void nextGreatestTest(){
        int[] arr = {16, 17, 4, 3, 5, 2};
        nextGreatest(arr);  // => {17, 5, 5, 5, 2, -1}
    }

    static void nextGreatest(int arr[]){
        if(arr.length==0)
            return;
        int c = arr[arr.length-1];
        arr[arr.length-1] = -1;
        for(int i=arr.length-2; i>=0; i--){
            int c1 = c;
            if(c<arr[i]){
                c = arr[i];
            }
            arr[i] = c1;
        }
    }

    //https://www.geeksforgeeks.org/highest-power-2-less-equal-given-number/
    //1-5. Highest power of 2 less than or equal to given number
    static void highestPowerof2Test(){
        int n = 19; //=>16
        int ret = highestPowerof2(n);
    }

    static int highestPowerof2(int n) {
        int mod = 1;
        while(n>0){
            n = n-mod;  //19 - 1, 2, 4, 8, 16, 32 => 10-32 < 0 and 32/2=16. Highest power of 2 is 16
            mod *= 2;
        }
        return mod/2;
    }

    //https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
    //1-6. Count all possible paths from top left to bottom right of a mXn matrix
    static void numberOfPathsTest(){
        int m = 2, n = 3;
        int ret = numberOfPaths(m,n);
    }

    static int numberOfPaths(int m, int n){
        if((m==1) || (n ==1))
            return 1;
        return numberOfPaths(m-1, n) + numberOfPaths(m,n-1);
    }

    //https://www.geeksforgeeks.org/return-a-pair-with-maximum-product-in-array-of-integers/
    //1-7. Find a pair with maximum product in array of Integers
    static void maxProductTest(){
        int arr[] = {-1, -3, -4, 2, 0, -5};  //-4, -5
        int n = 6;
        maxProduct(arr, n);
    }
    static void maxProduct(int arr[], int n) {
        if (n < 2)
        {
            System.out.println("No pairs exists");
            return;
        }

        // Initialize max product pair
        int a = arr[0], b = arr[1];

        // Traverse through every possible pair
        // and keep track of max product
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] * arr[j] > a * b){
                    a = arr[i];
                    b = arr[j];
                }

        System.out.println("Max product pair is {" +
                a + ", " + b + "}");
    }//


    //https://www.geeksforgeeks.org/given-binary-string-count-number-substrings-start-end-1/
    //1-8. Given a binary string, count number of substrings that start and end with 1.
    static void countSubStrTest(){
        String string = "00100101";
        char str[] = string.toCharArray();
        int n = str.length;
        int ret = countSubStr(str,n);
        System.out.println(ret); // ret -3
    }
    static int countSubStr(char str[],int n){
        int m = 0; // Count of 1's in input string

        // Traverse input string and count of 1's in it
        for (int i = 0; i < n; i++)
        {
            if (str[i] == '1')
                m++;
        }

        // Return count of possible pairs among m 1's
        return m * (m - 1) / 2;
    }

    //https://www.geeksforgeeks.org/given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
    //1-9. Given only a pointer/reference to a node to be deleted in a singly linked list, how do you delete it?

    static void deleteNodeTest(){
        Node Node_ptr = new Node(1);
        Node_ptr.left =  new Node(12);
        Node_ptr.left.left =  new Node(4);
        deleteNode(Node_ptr);
    }

    static void deleteNode(Node Node_ptr){
        Node temp = Node_ptr.left;
        Node_ptr.left = temp.left;
        Node_ptr.data = temp.data;
        temp = null;
    }

    //https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
    //1-10. Write a Program to Find the Maximum Depth or Height of a Tree
    static void maxDepthTest(){
        Node node = new Node(1);
        node.left =  new Node(2);
        node.right =  new Node(3);
        node.left.left =  new Node(4);
        node.left.right =  new Node(5);

        int ret = maxDepth(node); //ret = 3
        ret = countsTreeNodes(node);  // ret = 5
    }

    static int maxDepth(Node node){

        if(node==null)
            return 0;
        else {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    static int countsTreeNodes(Node node){

        int iCount = 0;
        if((node.left==null)&&(node.right==null))
            return 1;
        else {

            if(node.left!=null)
                iCount += maxDepth(node.left);
            if(node.right!=null)
                iCount += maxDepth(node.right);

            return iCount;
        }
    }


    //https://www.geeksforgeeks.org/print-nodes-at-k-distance-from-root/
    //1-11. Print nodes at k distance from root
    static void printKDistantTest(){
        Node node = new Node(1);
        node.left =  new Node(2);
        node.right =  new Node(3);
        node.left.left =  new Node(4);
        node.left.right =  new Node(5);
        node.right.left =  new Node(8);
        int k = 2;

        printKDistant(node, k);
    }

    static void printKDistant(Node node, int k){
        if(node==null)
            return;
        if (k == 0)
        {
            System.out.print(node.data + " ");
            return;
        }
        else
        {
            printKDistant(node.left, k - 1);
            printKDistant(node.right, k - 1);
        }

    }

    //https://www.geeksforgeeks.org/difference-between-sums-of-odd-and-even-levels/
    //1-12. Difference between sums of odd level and even level nodes of a Binary Tree

    static void getLevelDiffTest(){
        Node node = new Node(5);
        node.left = new Node(2);
        node.right = new Node(6);
        node.left.left = new Node(1);
        node.left.right = new Node(4);
        node.left.right.left = new Node(3);
        node.right.right = new Node(8);
        node.right.right.right = new Node(9);
        node.right.right.left = new Node(7);
        int ret = getLevelDiff(node);
        System.out.println(ret + " is the required difference");
    }
    //getLevelDiff
    static int getLevelDiff(Node node)
    {
        // Base case
        if (node == null)
            return 0;

        // Difference for root is root's data - difference for
        // left subtree - difference for right subtree
        return node.data - getLevelDiff(node.left) -
                getLevelDiff(node.right);
    }

    //https://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
    //1-13 Construct a special tree from given preorder traversal

    static void constructTreeTest(){
        //BinaryTree tree = new BinaryTree();
        int pre[] = new int[]{10, 30, 20, 5, 15};                       //      10
        char preLN[] = new char[]{'N', 'N', 'L', 'L', 'L'};             // 30        15
        int n = pre.length;                                             //20, 5,


        // construct the above tree
        Node mynode = constructTree(pre, preLN, n);

        // Test the constructed tree
        System.out.println("Following is Inorder Traversal of the"
                + "Constructed Binary Tree: ");
        printInorder(mynode);


    }

    // A Binary Tree node
    static public class Node
    {
        public int data;
        public Node left, right;

        public Node(int item)
        {
            data = item;
            left = right = null;
        }
    }

    static public class Index
    {
        public int index = 0;  // need global index value int instance.
    }

    static Index myindex = new Index();

    /* A recursive function to create a Binary Tree from given pre[]
       preLN[] arrays. The function returns root of tree. index_ptr is used
       to update index values in recursive calls. index must be initially
       passed as 0 */
    static Node constructTreeUtil(int pre[], char preLN[], Index index_ptr,
                           int n, Node temp) {
        // store the current value of index in pre[]
        int index = index_ptr.index;

        // Base Case: All nodes are constructed
        if (index == n)
            return null;

        // Allocate memory for this node and increment index for
        // subsequent recursive calls
        temp = new Node(pre[index]);
        (index_ptr.index)++;

        // If this is an internal node, construct left and right subtrees
        // and link the subtrees
        if (preLN[index] == 'N') {
            temp.left = constructTreeUtil(pre, preLN, index_ptr, n, temp.left);
            temp.right = constructTreeUtil(pre, preLN, index_ptr, n, temp.right);
        }
        return temp;
    }

    // A wrapper over constructTreeUtil()
    static Node constructTree(int pre[], char preLN[], int n) {
        // Initialize index as 0. Value of index is used in recursion to
        // maintain the current index in pre[] and preLN[] arrays.
        //int index = 0;

        return constructTreeUtil(pre, preLN, myindex, n, null);
    }

    /* This function is used only for testing */
    static void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    //https://www.geeksforgeeks.org/tree-isomorphism-problem/
    //13. Tree Isomorphism Problem

    static void isIsomorphicTest(){

        // Let us create trees shown in above diagram
        Node node1 = new Node(1);
        node1.left = new Node(2);
        node1.right = new Node(3);
        node1.left.left = new Node(4);
        node1.left.right = new Node(5);
        node1.right.left = new Node(6);
        node1.left.right.left = new Node(7);
        node1.left.right.right = new Node(8);

        Node node2 = new Node(1);
        node2.left = new Node(3);
        node2.right = new Node(2);
        node2.right.left = new Node(4);
        node2.right.right = new Node(5);
        node2.left.right = new Node(6);
        node2.right.right.left = new Node(8);
        node2.right.right.right = new Node(7);

        if (isIsomorphic(node1, node2) == true)
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    static boolean isIsomorphic(Node n1, Node n2){
        if((n1==null)&&(n2==null))
            return true;
        if((n1==null)||(n2==null))
            return false;

        if(n1.data != n2.data)
            return false;

        return (isIsomorphic(n1.left, n2.left) &&
                isIsomorphic(n1.right, n2.right))
                || (isIsomorphic(n1.left, n2.right) &&
                isIsomorphic(n1.right, n2.left));
    }

    //https://www.geeksforgeeks.org/replace-0-5-input-integer/
    //14. Replace all ‘0’ with ‘5’ in an input Integer
    // 0 = 0000 1 = 0001 2 = 0010 3 = 0011 4 = 0100  5 = 0101


    static void replace0with5Test() {
        int number = 1020;
        number += replace0with5(number);

        number =  replace0with5Rec(10120);
    }

    // result = x0x + 050 = x5x
    static int replace0with5(int number) {

        int result = 0;
        int decimalPlace = 1;

        if (number == 0)
            result += (5 * decimalPlace);

        while (number > 0) {
            if (number % 10 == 0) {
                result += (5 * decimalPlace);
            }
            number /= 10;
            decimalPlace *= 10;
        }

        return result;
    }


    // Recursive Approach
    static int replace0with5RecEx(int number) {
        if(number == 0)
            return 0;

        int digit = number % 10;
        if(digit == 0)
            digit = 5;

        return replace0with5RecEx(number/10) * 10 + digit;
    }
    static int replace0with5Rec(int number) {

        if(number==0)
            return 5;
        else
            return replace0with5RecEx(number);
    }


    //Popular Subjective Problems

    //https://practice.geeksforgeeks.org/problems/non-repeating-string-characters/
    //2-1. Non Repeating String Characters
    static void firstNoRepeatChrTest(){
        String str = "BBBBACCCC";
        char ret = firstNoRepeatChrTest(str);
    }

    static char firstNoRepeatChrTest(String str){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                int count = map.get(c);
                map.put(c, ++count);
            }
            else{
                map.put(c, 1);
            }
        }
        for(char c : map.keySet()){
            if(map.get(c)==1){
                return c;
            }
        }
        return 0;
    }

    //https://practice.geeksforgeeks.org/problems/deadlock-in-os/
    //2-2. Deadlock in OS
    //Both processes need resources to continue execution. ... In an operating system, a deadlock occurs when a process or thread enters a waiting state because a requested system resource is held by another waiting process, which in turn is waiting for another resource held by another waiting process.
    //There are three ways to handle deadlock
    //  1) Deadlock prevention or avoidance: The idea is to not let the system into deadlock state.
    //  2) Deadlock detection and recovery: Let deadlock occur, then do preemption to handle it once occurred.
    //  3) Ignore the problem all together: If deadlock is very rare, then let it happen and reboot the system. This is the approach that both Windows and UNIX take.

    //https://practice.geeksforgeeks.org/problems/what-is-50-2/
    //2-3. What is 5.0 % 2
    // System.out.println(5.0%2);

    //https://practice.geeksforgeeks.org/problems/linked-list-intersection/
    //2-4. Linked List Intersection

    static void intersectPointTest(){
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.left.left = new Node(5);

        Node head2 = new Node(7);
        head1.left = new Node(5);
        head1.left.left = new Node(3);

    }

    static int intersectPoint(Node head1, Node head2){
        HashSet<Integer> setList = new HashSet<Integer>();

        while(head1!=null){
            setList.add(head1.data);
            head1 = head1.left;
        }

        while(head2!=null){
            if(setList.contains(head2.data));
                return head2.data;
        }

        return -1;

    }

    //https://practice.geeksforgeeks.org/problems/array-min-element/
    //2-5. Find 2nd min element from given array ?
    static void find2ndMinTeset(){
        int[] arr = {3, 7, 1, 2, 3, 5, 77};
        find2ndMin(arr);
    }
    static int find2ndMin(int[] arr){
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] < min1){
                min1 = arr[i];
            }
            else if(arr[i] < min2){
                min2 = arr[i];
            }

        }
        return min2;
    }

    //https://practice.geeksforgeeks.org/problems/pairs-with-sum-s/
    //2-6. Pairs with Sum = S
    static void pairsWithSumTest(){
        int[] arr = {3, 5, 6, 2, 1};
        int sum = 5;
        pairsWithSumTest(arr, sum);
    }

    static int[] pairsWithSumTest(int[] arr, int sum) {
        int[] ret = {-1, -1};
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0 ; i< arr.length; i ++){
            map.put(arr[i], sum - arr[i]);
            if(map.containsValue(arr[i])){
                ret[0] = sum - arr[i];
                ret[1] = arr[i];
                return ret;
            }

        }
        return ret;
    }


    //https://practice.geeksforgeeks.org/problems/level-order-traversal/
    //2-7. Perform Level Order Tree Traversal
    static void treeTraversalTest(){
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        treeTraversal(head);
    }

    static void treeTraversal(Node head){
        // build Queue using LinkedList. Poll to get head, add to add to tail.
        Queue<Node> queue = new LinkedList<Node>();
        List<Integer> queueList = new ArrayList<Integer>();
        //Stack<Node> stackQueue = new Stack<Node>();
        if(head==null) return;

        queue.add(head);

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(curr.left!=null)
                queue.add(curr.left);
            if(curr.right!=null)
                queue.add(curr.right);
            queueList.add(curr.data);
        }

    }

    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    //Popular Coding Problems

    //https://practice.geeksforgeeks.org/problems/kadanes-algorithm/0
    //3-1. Kadane's Algorithm
    static void KadanesAlgorithmTest(){
        int[] arr = {1, 2, 3, -2, 5};

        //int ret =  KadanesAlgorithm(arr); //ret = 9
        int ret =  findMaxSum(arr); //ret = 9
    }

    static int findMaxSum(int arr[]) {
        int n = arr.length;
        int max = arr[0];
        int current_sum = arr[0];
        for(int i = 1; i < n; i++){
            current_sum = Math.max(arr[i], current_sum+arr[i]);
            max = Math.max(max, current_sum);
        }
        return max;
    }

    static int KadanesAlgorithm(int[] arr){


        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){

        }


        return 0;
    }

    //https://practice.geeksforgeeks.org/problems/missing-number-in-array/0
    //3-2. Missing number in array
    static void misNumberInArrarTest(){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        int ret =  misNumberInArrar(arr);
    }

    static int misNumberInArrar(int[] arr){
        int n = arr.length;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] != (arr[i-1] + 1)){
                return arr[i-1] + 1;
            }
        }
        return -1;
    }

    //https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence/0
    //3-3. Longest Increasing Subsequence
    static void LongIncSubsTest(){
        //int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int[] arr = {5, 8, 3, 7, 9, 1};

        //int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int ret = LongIncSubs(arr);
        System.out.println("Length of lis is " + ret + "\n");
    }

    static int max_ref; // stores the LIS

    /* To make use of recursive calls, this function must return
    two things:
    1) Length of LIS ending with element arr[n-1]. We use
       max_ending_here for this purpose
    2) Overall maximum as the LIS may end with an element
       before arr[n-1] max_ref is used this purpose.
    The value of LIS of full array of size n is stored in
    *max_ref which is our final result */
    static int LongIncSubsUtil(int arr[], int n)
    {
        // base case
        if (n == 1)
            return 1;

        // 'max_ending_here' is length of LIS ending with arr[n-1]
        int res, max_ending_here = 1;

        /* Recursively get all LIS ending with arr[0], arr[1] ...
           arr[n-2]. If   arr[i-1] is smaller than arr[n-1], and
           max ending with arr[n-1] needs to be updated, then
           update it */
        for (int i = 1; i < n; i++)
        {
            res = LongIncSubsUtil(arr, i);
            if (arr[i-1] < arr[n-1] && res + 1 > max_ending_here)
                max_ending_here = res + 1;
        }

        // Compare max_ending_here with the overall max. And
        // update the overall max if needed
        if (max_ref < max_ending_here)
            max_ref = max_ending_here;

        // Return length of LIS ending with arr[n-1]
        return max_ending_here;
    }

    // The wrapper function for _lis()
    static int LongIncSubs(int arr[])
    {
        int n = arr.length;
        // The max variable holds the result
        max_ref = 1;

        // The function _lis() stores its result in max
        LongIncSubsUtil( arr, n);

        // returns max
        return max_ref;
    }



    //https://practice.geeksforgeeks.org/problems/key-pair/0
    //3-4. Key Pair



    //3-5. Subarray with given sum

    static void sumSubarrayTest(){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 15;
        int[] ret = sumSubarray(arr, sum);
    }

    static int[] sumSubarray(int[] arr, int sum){
        int ret[] = {0, 0};
        for(int i = 0; i < arr.length; i++) {
            int locSum = 0;

            for (int j = i; j < arr.length; j++) {
                locSum = locSum + arr[j];
                if (locSum == sum) {
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return ret;
    }


    //3-6. Check for BST


    //3-7. Finding middle element in a linked list
    //https://practice.geeksforgeeks.org/problems/root-to-leaf-path-sum/1

    static void getMiddleTest(){
        //2, 4, 6, 7, 5, 4
        LinkNode head = new LinkNode(2);
        head.next = new LinkNode(4);
        head.next.next = new LinkNode(6);
        head.next.next.next = new LinkNode(7);
        head.next.next.next.next = new LinkNode(5);
        head.next.next.next.next.next = new LinkNode(4);

        getMiddle(head);
    }

    static int getMiddle(LinkNode head)
    {
        // move point fast has double speed to slow to tough to end the slow will be in mid
        if(head == null)
            return -1;

        LinkNode slow = head;
        LinkNode fast = head;

        while((fast!=null)&&(fast.next!=null)){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }


    //https://practice.geeksforgeeks.org/problems/root-to-leaf-path-sum/1
    //3-8. Root to leaf path sum
    static void hasPathSumTest(){
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        int sum = 3;
        boolean ret = hasPathSum(node, sum);
    }

    static boolean hasPathSum(Node node, int sum){
        if((node.left==null)&&(node.right==null))
            return (node.data==sum);

        boolean left = false, right = false;
        if(node.left!=null)
            left = hasPathSum(node.left, sum-node.data);

        if(node.right!=null)
            right = hasPathSum(node.right, sum-node.data);

        return left || right;
    }

    //https://practice.geeksforgeeks.org/problems/sum-tree/1
    //3-9. Sum Tree

    //$$$$$$$$$$$$$$$$$$$$$$$$$
    //Given a Binary Tree. Check whether it is a Sum Tree or not.
    // Should return true if tree is Sum Tree, else false
    public static void isSumTreeTest() {
        Node node = new Node(3);
        node.left = new Node(1);
        node.right = new Node(2);

    }

    static boolean isSumTree(Node node) {
        // Your code here
        if ((node.left == null) || node.right == null)
            return true;
        if (node.data == (node.left.data + node.right.data))
            return true;

        if (node.left == null) {
            return isSumTree(node.right);
        }

        if (node.right == null) {
            return isSumTree(node.left);
        }

        return isSumTree(node.left) && isSumTree(node.right);
    }

    static class LinkNode
    {
        int data;
        LinkNode next;
        LinkNode(int d) {data = d; next = null; }
    }

    //2020-9-14  Amazon coding test
    //4-1 find find top 2 strings using at statements.
    static void findTop2StringsUsedInStatements() {

        int topFeatures = 2;
        List<String> possibleFeatures = new ArrayList<String>();
        possibleFeatures.add("anacell");
        possibleFeatures.add("betacellular");
        possibleFeatures.add("cetracular");
        possibleFeatures.add("deltacellular");
        possibleFeatures.add("eurocell");

        //test case 1
        List<String> featureRequests = new ArrayList<String>();
        featureRequests.add("I love anacell Best services provided by anacell in the town");
        featureRequests.add("betacellular has great services");
        featureRequests.add("deltacellular provides much better services than betacellular");
        featureRequests.add("cetracular is worse than eurocell");
        featureRequests.add("betacellular is better than deltacellular");   //ret {"deltacellular", "betacellular"}
        List<String>  ret = topFeatureRequests(topFeatures, possibleFeatures, featureRequests);

        //test case 2
        featureRequests.add("Best services provided by anacell");
        featureRequests.add("betacellular has great services");
        featureRequests.add("anacell provides much better services than all other");  //ret {"anacell", "services"}
        ret = topFeatureRequests(topFeatures, possibleFeatures, featureRequests);


    }

    public static List<String> topFeatureRequests(int topFeatures, List<String> possibleFeatures, List<String> featureRequests) {
        // Write your code here
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        String maxStr1 = "";
        String maxStr2 = "";

        HashMap<String, Integer> mapRequest =  new HashMap<String, Integer>();

        List<String> ret = new ArrayList<String>();

        for(int j = 0; j < featureRequests.size(); j++ ){
            String str = featureRequests.get(j);
            String[] strList = str.split(" ");
            for(int m = 0; m < strList.length; m++){
                String featurStr =  strList[m];
                if(mapRequest.containsKey(featurStr)){
                    int count = mapRequest.get(featurStr);
                    mapRequest.put(featurStr, ++count);
                }
                else {
                    mapRequest.put(featurStr, 1);
                }

            }

        }

        for(int i = 0; i < possibleFeatures.size(); i++ ){
            String possibleStr = possibleFeatures.get(i);
            if(mapRequest.containsKey(possibleStr)){
                int freq = mapRequest.get(possibleStr);

                if(freq>=max1){
                    max2 = max1;
                    max1 = freq;
                    maxStr2 = maxStr1;
                    maxStr1 = possibleStr;
                }
                else {
                    if(freq>=max2){
                        max2 = freq;
                        maxStr2 = possibleStr;
                    }
                }
            }

        }

        ret.add(maxStr1);
        ret.add(maxStr2);

        return ret;
    }

    //4-2 find two integer in BST of integer array distance.
    static void findTwoNodesDistanceInBST() {
        // build a BST tree for integer list  {4, 3, 5, 2, 1}
        // find tree node1 and node 2 distance.
        List<Integer> valuses = new ArrayList<>();
        valuses.add(4);
        valuses.add(3);
        valuses.add(5);
        valuses.add(2);
        valuses.add(1);
        int ret = foo(valuses, 2, 5);

    }


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

    // build BST from integer array
    static public Node buildTree(int[] a){
        Node parent= new Node(a[0]);
        Node curr= new Node(a[0]);
        Node root=curr;
        int v;
        for(int i=1;i<a.length;i++){
            curr=root;
            v=a[i];
            while(curr!=null){
                if(v>=curr.data) {
                    parent=curr;
                    curr=curr.right;
                }else{
                    parent=curr;
                    curr=curr.left;
                }
            }
            //parent is leaf
            if(v>=parent.data){
                parent.right= new Node(v);
            }else{
                parent.left=new Node(v);
            }
        }
        return root;
    }


    static int distanceFromRoot(Node root, int x)
    {
        if (root.data == x)
            return 0;
        else if (root.data > x)
            return 1 + distanceFromRoot(root.left, x);
        return 1 + distanceFromRoot(root.right, x);
    }
    static int distanceBetween2(Node root, int a, int b)
    {
        if (root == null)
            return 0;

        // Both keys lie in left
        if (root.data > a && root.data > b)
            return distanceBetween2(root.left, a, b);

        // Both keys lie in right
        if (root.data < a && root.data < b) // same path
            return distanceBetween2(root.right, a, b);

        // Lie in opposite directions (Root is
        // LCA of two nodes)
        if (root.data >= a && root.data <= b)
            return distanceFromRoot(root, a) + distanceFromRoot(root, b);

        return -1;
    }

    public static int foo(List<Integer> values, int node1, int node2) {
        int[] array = values.stream().mapToInt(i->i).toArray();
        Node root = buildTree(array); // arraytoBinary(array, 0, values.size()-1);
        int ret = distanceBetween2(root, node1, node2);
        return ret;
    }

    //https://www.youtube.com/watch?v=5co5Gvp_-S0
    //4-3 firstNonRepeatingCharcter
    static void firstNonRepeatingCharcterTest() {
        String str = "aaabcccdeeef";  // ret 'b'
        char ret = firstNonRepeatingCharcter(str);
        ret = firstNonRepeatingCharcter1(str);
    }

    static char firstNonRepeatingCharcter(String str){
        char ret = '_';
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i = 0; i < str.length(); i++){
            char chr = str.charAt(i);
            int count = 1;
            if(map.containsKey(chr)){
                count = map.get(chr);
                count++;
            }
            map.put(chr, count);
        }

        for(int i = 0; i < str.length(); i++){
            char chr = str.charAt(i);
            if(map.containsKey(chr)) {
                int count = map.get(chr);
                if(count==1)
                    return chr;
            }
        }

        return ret;
    }

    static char firstNonRepeatingCharcter1(String str) {
        char ret = '_';
        for (int i = 0; i < str.length(); i++) {

            if(str.indexOf(str.charAt(i))==str.lastIndexOf(str.charAt(i)))
                return str.charAt(i);
        }
        return ret;
    }


    //https://www.youtube.com/watch?v=5o-kdjv7FD0
    //4-4 N steps, take one or two step for how many ways from bottom to top
    static void num_ways_X_bottom_up_Test(){
        int n = 3;
        int[] steps = {1, 3, 5};
        int ret = num_ways_X_bottom_up(n, steps);
    }

    static int num_ways_X_bottom_up(int n, int[] steps) {
        if(n==0) return 1;
        int[] nums = new int[steps.length+1];
        nums[0] = 1;
        for(int i=1; i<=n; i++){
            int total = 0;
            for(int j=1; j<steps.length; j++) {
                if ((i - j) >= 0) {
                    total += nums[i - j];
                }
                nums[i] = total;
            }
        }
        return nums[n];
    }

    //https://www.youtube.com/watch?v=q2v5nik5vwU
    //4-5 Most Common Word
    static void CommonWordTest(){
        String porograph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        String ret = CommonWord(porograph, banned);
    }

    static String CommonWord(String porograph, String[] banned){

        HashSet<String> bannedWords = new HashSet<>();
        for(String word : banned){
            bannedWords.add(word.toLowerCase());
        }

        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(String word : porograph.split(" ")){
            String wordlow = word.toLowerCase();
            wordlow = wordlow.replace(",", "");
            wordlow = wordlow.replace(".","");
            if(!bannedWords.contains(wordlow)){
                counts.put(wordlow, counts.getOrDefault(wordlow, 0) + 1);
            }
        }

        String result = "";
        for(String key : counts.keySet()){
            if (result.equals("") || counts.get(key) > counts.get(result)) {
                result = key;
            }
        }
        return result;
    }

    //https://www.youtube.com/watch?v=il_t1WVLNxk&list=PLqM7alHXFySGqCvcwfqqMrteqWukz9ZoE
    //4-6-a Given a Binary tree, how will you find the vertical Sum of Binary Tree?
    static void findVerticalMaxSumOfBSTTest(){
    //          1
    //     2        3
    //  4      5 7      6
    //  only mid vertical line 1, 5, 7 sum = 1
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(9);
        //4-6-a Given a Binary tree, how will you find the vertical Sum of Binary Tree?
        int ret = findVerticalSumOfBST(root);
        //4-6-b. Given a Binary tree, how will you find the maximum width?
        ret = findMixWidthOfBST(root);
        //4-6-c. N students in a calss paly a game against each other where each student plays
        //       against all other students int eh class. find the total number of matches to be conducted.

        int[] students = {1, 2, 3, 4, 5};  // select any student as root to build a tree. for every student to lost the root one student will put to left, other wise put to right
        playGames(students);

    }

    static void findVerticalSumOfBST(Node root, HashMap<Integer, Integer> map, int key) {
        if(root == null){
            return;
        }
        map.put(key, map.getOrDefault(key, 0) + root.data);
        if(root.left!=null){
            findVerticalSumOfBST(root.left, map, key-1);
        }
        if(root.right!=null){
            findVerticalSumOfBST(root.right, map, key+1);
        }

    }

    static int findVerticalSumOfBST(Node root){
        int sum = Integer.MIN_VALUE;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();  //key is node index and vlaue is sum of same node key
        findVerticalSumOfBST(root, map, 0);
        for(int i : map.values()){
            if(i > sum)
                sum = i;

        }
        return sum;

    }

    static int findMixWidthOfBST(Node root){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();  //key is node index and vlaue is sum of same node key
        findVerticalSumOfBST(root, map, 0);
        for(int i : map.keySet()){
            if(i > max)
                max = i;
            if(i < min)
                min = i;

        }
        return Math.abs(max-min);

    }

    static void playGames(int[] students){
        Node root = buildTree(students);
        treeTraversal(root);
        return;
    }

    //https://www.youtube.com/watch?v=7lbwfkCfNQ4
    //4-7 Given two binary trees, determine whether they have the same inorder traversal.
    static void twoBSTtraversalTest(){
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.right.left = new TreeNode(6);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(6);
        root2.right.left = new TreeNode(5);
        root2.right.right = new TreeNode(7);  //ret true

        boolean ret = twoBSTtraversal(root1, root2);
    }

    static void inorderTravel(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorderTravel(root.left, list);
        list.add(root.key);
        inorderTravel(root.right, list);
        return;
    }

    static boolean twoBSTtraversal(TreeNode root1, TreeNode root2){

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        inorderTravel(root1, list1);
        inorderTravel(root2, list2);

        return list1.equals(list2);

    }

    //https://www.youtube.com/watch?v=4Zq2Fnd6tl0&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
    //4-8 uniques path for left-top to right-bottom in grid (nxm).
    static void uniquesPathOfGrid_nxm_Test(){
        int ret = uniquesPathOfGrid_nxm(4, 6);  //ret 56
        ret = uniquesPathOfGrid_nxm(3, 4);  //ret 10
    }
    static int uniquesPathOfGrid_nxm(int n, int m){
        //agorithm go first row or column, paths number always 1, the others would be paths[i][j] = paths[i-1][j] + paths[i][j-1]
       int[][] paths = new int[n][m];
       for(int i=0; i<n; i++) {
           for (int j = 0; j < m; j++) {
                if((i==0)||(j==0))
                    paths[i][j]=1;
                else
                    paths[i][j] = paths[i-1][j] + paths[i][j-1];
           }
       }
       return paths[n-1][m-1];
    }
    //https://www.youtube.com/watch?v=eaYX0Ee0Kcg
    //4-9 find k Smallest items (points with closer x-y points to [0,0]).
    static void findKSmallestItemsTest(){
        int[][] points = {{-2, 4}, {0, -2}, {-1, 0}, {2, 5}, {-2, -3}, {3, 2}};
        // dist             20        4         1       29      13        13    //ret {1, 2, 4}
        int k = 3;
        int ret[] = findKSmallestItems(points, k);
    }

    static int[] findKSmallestItems(int[][] points, int k){
        // using a heap (smallest heap) to keep tracking of smallest distance from a array
        int smallestDist = 0;
        int[] group = new int[k]; // the [k] always keep smallest dist index
        for(int i = 0; i < points.length; i++){
            int dist = points[i][0]*points[i][0] + points[i][1]*points[i][1];  //20, 4, 1, 29, 13, 13  k=3
            if(i<k){
                if(i==0){                     // 20:[0,0,0] -> 4:[1,0,0] -> 1:[2,1,0]
                    group[i] = i;
                    smallestDist = dist;
                }
                else{
                    if(dist<smallestDist){
                        group[i] = group[i-1];
                        group[i-1] = i;
                    }
                    else {
                        group[i] = i;
                        smallestDist = dist;
                    }
                }
            }
            else {
                if(dist<smallestDist){     // 20:[0,0,0] -> 4:[1,0,0] -> 1:[2,1,0]->29[2,1,0]->13[2,1,0]->13[2,1,0]
                    group[k-1] = i;        // move index
                    smallestDist = dist;
                }
            }
        }
        return group;
    }

    //https://www.youtube.com/watch?v=kHWy5nEfRIQ
    //4-10 Tower Hopper Problem.
    static void towerHopperProblemTest(){
        //jump tower high array
        //jump max k
        //if possible from highest tower to ground after last tower.
        //using DFS, BFS to search pump path

        int k = 4;
        int[] tower = {4, 2, 0, 0, 2, 0};
        boolean ret  = towerHopperProblem(tower, k);
        k = 2;
        int[] tower1 = {1, 3, 6, 3, 1, 0};
        ret  = towerHopperProblem(tower1, k);
    }

    static boolean towerHopperProblem(int[] tower, int k){
        if(tower.length<=1)
            return true;
        for(int index = 1; index < tower.length; index++){
            if((tower[index-1]-tower[index])>k)
                return false;
            continue;
        }
        return true;
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //other interview code test
    //tradeStreamTest
    /*
This problem will require you to write an application that will read an input file ('input.csv') and write out a new file calculated from the inputs. Please create a C++ solution on Linux.

We are looking for an easily extendable solution with a strong emphasis on readability, even though it may not seem required for this application.
We will evaluate your submission based on the following criteria:
- Readability, Design, and Construction
- Tests
- Use of modern C++17/20 Idioms
- Use of C++ TMP/compile-time where appropriate
- Correctness

We are big fans of test driven development (TDD) and SOLID design principles. Please use 2 hours as a guiding constraint to plan your execution time.
Send your source code and output.csv in a zipped git repo back for evaluation when complete. Include the amount of time you spent working on the solution.

Tips:
- Think about the problem as long as you’d like.
- Send us code you would put in production.
- Quality over quantity.  Send us code you would be proud of.
- Expect that your code will be extended in the future.
- Use any test framework you would like
- Include any instructions needed for us to run your solution

-enjoy!
--Quantlab


_______________________________________________________________________________

Input:
The input file represents a very simplified stream of trades on an exchange.
Each row represents a trade.  If you don't know what that means don't worry.
The data can be thought of as a time series of values in columns:

<TimeStamp>,<Symbol>,<Quantity>,<Price>

Although the provided input file is small, the solution should be able to handle
a source dataset well beyond the amount memory and hard disk space on your machine.

Definitions
- TimeStamp is value indicating the microseconds since midnight.
- Symbol is the 3 character unique identifier for a financial
  instrument (Stock, future etc.)
- Quantity is the amount traded
- Price is the price of the trade for that financial instrument.

Safe Assumptions:
- TimeStamp is always for the same day and won't roll over midnight.
- TimeStamp is increasing or same as previous tick (time gap will never be < 0).
- Price - our currency is an integer based currency.  No decimal points.
- Price - Price is always > 0.

Example: here is a row for a trade of 10 shares of aaa stock at a price of 12
1234567,aaa,10,12

Problem:
Find the following on a per symbol basis:
- Maximum time gap
  (time gap = Amount of time that passes between consecutive trades of a symbol)
  if only 1 trade is in the file then the gap is 0.
- Total Volume traded (Sum of the quantity for all trades in a symbol).
- Max Trade Price.
- Weighted Average Price.  Average price per unit traded not per trade.
  Result should be truncated to whole numbers.

  Example: the following trades
	20 shares of aaa @ 18
	5 shares of aaa @ 7
	Weighted Average Price = ((20 * 18) + (5 * 7)) / (20 + 5) = 15

Output:
Your solution should produce a file called 'output.csv'.
file should be a comma separate file with this format:
<symbol>,<MaxTimeGap>,<Volume>,<WeightedAveragePrice>,<MaxPrice>

The output should be sorted by symbol ascending ('aaa' should be first).
Sample Input:
52924702,aaa,13,1136
52924702,aac,20,477
52925641,aab,31,907
52927350,aab,29,724
52927783,aac,21,638
52930489,aaa,18,1222
52931654,aaa,9,1077
52933453,aab,9,756

Sample Output:
aaa,5787,40,1161,1222
aab,6103,69,810,907
aac,3081,41,559,638

Send your source code and output.csv in a zipped git repo back for evaluation when complete.
Include the amount of time you spent working on the solution.
    */

    static class ShareData {
        long time;
        long maxTimeGap;
        long volume;
        long weightedAveragePrice;
        long maxPrice;

        public ShareData(long time, long volume, long price){
            this.maxTimeGap = 0;
            this.time = time;
            this.volume = volume;
            this.weightedAveragePrice = price*volume;
            this.maxPrice = price;
        }

        public void upDate(long time, long volume, long price){
            if((time-this.time)>this.maxTimeGap)
                this.maxTimeGap = time-this.time;
            this.time = time;
            this.volume += volume;
            this.weightedAveragePrice += price*volume;
            if(price>this.maxPrice)
                this.maxPrice = price;
        }

    }

    static void tradeStreamTest() throws IOException {
        HashMap<String, ShareData> tradeMap = new HashMap<>();

        BufferedReader csvReader = new BufferedReader(new FileReader("d:\\workarea\\input1.csv"));
        String row = "";
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String symbol = data[1];
            long time = Long.parseLong(data[0]);
            long volume = Integer.parseInt(data[2]);
            long price = Integer.parseInt(data[3]);


            if(tradeMap.containsKey(symbol)){
                ShareData tradedata  = tradeMap.get(symbol);
                tradedata.upDate(time, volume, price);
                tradeMap.put(symbol,tradedata);
            }
            else{
                ShareData tradedata = new ShareData(time, volume, price);
                tradeMap.put(symbol,tradedata);
            }

        }
        csvReader.close();

        TreeMap<String, ShareData> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(tradeMap);

        PrintWriter cvsWriter = new PrintWriter(new File("d:\\workarea\\output1.csv"));

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, ShareData> entry : sorted.entrySet()){
            String symbol = entry.getKey();
            ShareData tradedata  = entry.getValue();

            sb.append(symbol);
            sb.append(',');
            sb.append(Long.toString(tradedata.maxTimeGap));
            sb.append(',');
            sb.append(Long.toString(tradedata.volume));
            sb.append(',');
            sb.append(Long.toString(tradedata.weightedAveragePrice/tradedata.volume));
            sb.append(',');
            sb.append(Long.toString(tradedata.maxPrice));
            sb.append(',');
            sb.append('\n');

        }
        cvsWriter.write(sb.toString());
        cvsWriter.close();

    }

//Indeed interview code test
/*
    Imagine we have an image. We'll represent this image as a simple 2D array where every pixel is a 1 or a 0. The image you get is known to have a single rectangle of 0s on a background of 1s.
    Write a function that takes in the image and returns one of the following representations of the rectangle of 0's: top-left coordinate and bottom-right coordinate OR top-left coordinate, width, and height.

    image1 = [
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 0, 0, 0, 1],
            [1, 1, 1, 0, 0, 0, 1],
            [1, 1, 1, 1, 1, 1, 1],
            ]

    Sample output variations (only one is necessary):

    findRectangle(image1) =>
    x: 3, y: 2, width: 3, height: 2 == row: 2, column: 3, width: 3, height: 2
            2,3 3,5 -- row,column of the top-left and bottom-right corners

    Other test cases:

    image2 = [
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 0],
            ]

    findRectangle(image2) =>
    x: 6, y: 4, width: 1, height: 1
            4,6 4,6

    image3 = [
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 0, 0],
            [1, 1, 1, 1, 1, 0, 0],
            ]

    findRectangle(image3) =>
    x: 5, y: 3, width: 2, height: 2
            3,5 4,6

    image4 = [
            [0, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            [1, 1, 1, 1, 1, 1, 1],
            ]

    findRectangle(image4) =>
    x: 0, y: 0, width: 1, height: 1
            0,0 0,0

    image5 = [
            [0],
            ]

    findRectangle(image5) =>
    x: 0, y: 0, width: 1, height: 1
            0,0 0,0

    n: number of rows in the input image
    m: number of columns in the input image


*/
//Imagine we have an image. We'll represent this image as a simple 2D array where every pixel is a 1 or a 0. The image you get is known to have a single rectangle of 0s on a background of 1s.
//Write a function that takes in the image and returns one of the following representations of the rectangle of 0's: top-left coordinate and bottom-right coordinate OR top-left coordinate, width, and height.

    static void findRectangleTest(){
        int[][] image1 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        findRectangle(image1);
//        Sample output variations (only one is necessary):
//        findRectangle(image1) =>
//        x: 3, y: 2, width: 3, height: 2 == row: 2, column: 3, width: 3, height: 2
//        2,3 3,5 -- row,column of the top-left and bottom-right corners


        int[][] image2 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0}
        };
        findRectangle(image2);

//        findRectangle(image2) =>
//        x: 6, y: 4, width: 1, height: 1
//        4,6 4,6

        int[][] image3 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0, 0}
        };
        findRectangle(image3);

//        findRectangle(image3) =>
//        x: 5, y: 3, width: 2, height: 2
//        3,5 4,6

        int[][] image4 = {
                {0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        findRectangle(image4);

//        findRectangle(image4) =>
//        x: 0, y: 0, width: 1, height: 1
//        0,0 0,0

        int[][] image5 = {
                {0}
        };
        findRectangle(image5);

//        findRectangle(image5) =>
//        x: 0, y: 0, width: 1, height: 1
//        0,0 0,0

//        n: number of rows in the input image
//        m: number of columns in the input image

    }

    static void findRectangle(int[][] image){
        int top = image.length - 1;
        int bottom = 0;
        int left = image[0].length - 1;
        int right = 0;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == 0) {
                    if (i < top)
                        top = i;
                    if (i > bottom)
                        bottom = i;

                    if (j < left)
                        left = j;
                    if (j > right)
                        right = j;
                }
            }
        }
        int[] range = {0, 0, 0, 0};
        range[0] = left;
        range[1] = top;
        range[2] = right;
        range[3] = bottom;
        System.out.printf("%s %s %s %s\n", top, left, bottom, right);
        return;
    };

//https://www.youtube.com/watch?v=7lbwfkCfNQ4
//compare two tree have same internal travel

    static void two_bst_have_same_internal_travelsal_test(){
        TreeNode tree1 = new TreeNode(5);
        tree1.left = new TreeNode(3);
        tree1.left.left = new TreeNode(1);    // 1->3->5->6->7  (left->node->right)
        tree1.right = new TreeNode(7);
        tree1.right.left = new TreeNode(6);

        TreeNode tree2 = new TreeNode(3);
        tree2.left = new TreeNode(1);
        tree2.right = new TreeNode(6);
        tree2.right.left = new TreeNode(5);
        tree2.right.right = new TreeNode(7);  // 1->3->5->6->7  (left->node->right)

        boolean bSame = two_bst_have_same_internal_travelsal(tree1, tree2);
        System.out.println(bSame);
    }

    // time complexity o(n+m)
    // space complexity o(n+m)
    static boolean two_bst_have_same_internal_travelsal(TreeNode tree1, TreeNode tree2){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        bstInternalTravelsal(tree1, list1);
        bstInternalTravelsal(tree2, list2);

        return list1.equals(list2); // compare two list same
    }

    static void bstInternalTravelsal(TreeNode node, List<Integer> list){
        if(node==null)
            return;
        bstInternalTravelsal(node.left,list);
        list.add(node.key);
        bstInternalTravelsal(node.right,list);
    }

    //https://www.youtube.com/watch?v=thkuu_FWFD8
    //Print Left View of Binary tree.
    static void print_Left_View_of_BST_test(){    //only see 1st left nodes of BST
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.right = new TreeNode(6);     //1,2,6
        List<TreeNode> list = new ArrayList<>();
        list.add(node);
        List<TreeNode> leftlist = new ArrayList<>();
        print_Left_View_of_BST(list, leftlist);
        System.out.println(list.toArray());
    };
    static void print_Left_View_of_BST(List<TreeNode> chlist, List<TreeNode> leftlist){
        boolean left = true;
        List<TreeNode> list = new ArrayList<>();
        for(TreeNode chnode : chlist){
            if(left==true){
                leftlist.add(chnode);
                left = false;
            }
            if(chnode.left!=null){
                list.add(chnode.left);
            }
            if(chnode.right!=null){
                list.add(chnode.right);
            }
        }
        if(list.size()>0){
            print_Left_View_of_BST(list, leftlist);
        }
        return;
    }


    //https://www.youtube.com/watch?v=mjZpZ_wcYFg
    // String encode
    static void encode_test(){
        String s = "aaabbcb";  //3a2b1c1b
        String res = encode(s);
        System.out.println(res);
    }

    static String encode(String input) {
        String output = "";
        int count = 0;
        char cInput[] = input.toCharArray();
        char last = 0;
        for(char chr : cInput){
            if(count==0){
                last = chr;
                count = 1;
            } else {
                if(chr==last){
                    count = count + 1;
                }
                else {
                    output = output + count + last;
                    last = chr;
                    count = 1;
                }
            }
        }
        if(count>0){
            output = output + count + last;
        }


        return output;
    }

        static String encode1(String input) {
        String encode = "";
        String sCh = "";
        int iCount = 0;
        for(int i = 1; i <= input.length(); i++){
            String nextCh = input.substring(i-1, i);
            if(sCh==""){
                sCh = nextCh;
                iCount = 1;
            }else if(sCh.equals(nextCh)){
                iCount++;
            }else {
                encode = encode + iCount + sCh;
                sCh = nextCh;
                iCount = 1;
            }
        }
        if(iCount>0)
            encode = encode + iCount + sCh;
        return encode;
    }

    //amazon interivew test 5/24/2021
    // 1. get leave of BST children list

    static void get_leave_of_BST_children_list_test(){
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.right = new TreeNode(6);
        List<TreeNode> nodeList = new ArrayList<>();
        get_leave_of_BST_children_list(node, nodeList);  // travel tree leave node => 2, 6
        System.out.println(nodeList);

    };
    static void get_leave_of_BST_children_list(TreeNode node, List<TreeNode> nodelist) {
        if(node==null)
            return;
        if((node.left==null)&&(node.right==null)){
            nodelist.add(node);
            return;
        }
        if(node.left!=null)
            get_leave_of_BST_children_list(node.left, nodelist);
        if(node.right!=null)
            get_leave_of_BST_children_list(node.right, nodelist);
        return;
    }

    // 2. log data |**|*

/*  //Amazon interview 2nd round  6/15/2021
    Given a modified Unix /etc/passwd file (colon separated) with the following format: <login>:<password>:<id>:<name>:<last_change_date>

    Provide a list of all logins and names that are older than a provided date.

    Example:
    alice:S3cretP4sswd:12345:Henry Wang:20210201
    bob:An0therPassd:67852:Steve O'Dor:20210314
    carlos:Someth1nElse:23443:Tyler:20210430
*/

    static class logInfo{
        String lonin;
        String name;
        logInfo(String login, String name){
            this.lonin = login;
            this.name = name;
        }
    }

    static void getListofLoginTest(){
        String[] logins = {"alice:S3cretP4sswd:12345:Henry Wang:20210201",
        "bob:An0therPassd:67852:Steve O'Dor:20210314",
        "carlos:Someth1nElse:23443:Tyler:20210430"};
        String providedDate = "20210317";
        List<logInfo> list = getListofLogin(logins, providedDate);
        System.out.println(list);  //carlos, Tyler
    }

    static List<logInfo> getListofLogin(String[] logs, String sDate) {
        List<logInfo> list = new ArrayList<>();
        for(String log : logs){
            String info[] = log.split(":");
            if(info.length==5){
                if(Integer.parseInt(info[4])>=Integer.parseInt(sDate)){
                    logInfo item = new logInfo(info[0], info[3]);
                    list.add(item);
                }
            }
        }
        return list;
    }

}