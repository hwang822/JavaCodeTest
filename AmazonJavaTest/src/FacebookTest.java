import java.util.*;

public class FacebookTest {

    //****************************************
    //https://www.educative.io/blog/cracking-top-facebook-coding-interview-questions
    static void top40TestCodes(){
        addTwoNumberUnitTest();

        //1. Given an integer array, move all elements that are equal to 0 to the left while maintaining the order of other elements in the array
        int arr[] = {1, 10, 20, 0, 59, 63, 0, 88, 0};
        MoveZerosLeft(arr);  //{0, 0, 0, 1, 10, 20, 59, 63, 88}

        //2: Merge overlapping intervals
        int[][] timeSample = {{1, 5}, {3, 7}, {4, 6}, {6, 8}};
        int[] merged = merge_overlapping_intervals(timeSample);
        int[][] timeSample1 = {{10, 12}, {12, 15}};
        int[] merged1 = merge_overlapping_intervals(timeSample1);

        //3.
        TreeNode root = new TreeNode(100);            //                      100
        root.left = new TreeNode(50);                 //          50                  200
        root.left.left = new TreeNode(25);            //    25           75       125      350
        root.left.left.right = new TreeNode(30);      //       30     60
        root.left.right = new TreeNode(75);
        root.left.right.left = new TreeNode(60);
        root.right = new TreeNode(200);
        root.right.left = new TreeNode(125);
        root.right.right = new TreeNode(350);

        TreeNode header =  convert_binary_tree_to_doubly_linked(root);
        //25 30 50 60 75 100 125 200 350

        //4. Trees: Level order traversal of binary tree
        root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right= new TreeNode(3);
        root.left.left= new TreeNode(4);
        root.left.right= new TreeNode(5);

        level_order_traversal_of_binary_tree(root);
        //{1, 2, 3, 4, 5}

        //5. Strings: Reverse words in a sentence
        reverse_words_in_a_sentence("Hello World");  //"World Hello"

        //6. Strings: String segmentation
        String s = "hellonow";
        Set <String> direcotry = new HashSet<>();
        direcotry.add("hello");
        direcotry.add("hell");
        direcotry.add("on");
        direcotry.add("now");

        string_segmetatio(s, direcotry);  //{"now", "Hello"}

        //7. Dynamic Programming: Find maximum single sell profit

        int[] prices = {8, 5, 12, 9, 19, 1};
        findBuySellStockPrices(prices);
        int[] prices1 = {21, 12, 11, 9, 6, 3};
        findBuySellStockPrices(prices1);

        //8. Math and Stats: Calculate the power of a number

        calculate_the_power_of_a_number(2, 5);
        calculate_the_power_of_a_number(2, -2);

        //9 Backtracking: Find all possible subsets

        int[] myints = new int[] {8,13,3,22, 17, 39, 87, 45, 36};
        List<Integer> v = new ArrayList<Integer>();
        for (int i : myints) {
            v.add(i);
        }
        List<HashSet<Integer>> subsets = new ArrayList<HashSet<Integer>>();
        get_all_subsets(v, subsets);

        //10 Clone a Directed Graph

        //11. Design: Serialize / deserialize binary tree

        serialize_deserialize_binary_tree(root);

        //12  Sorting and Searching: Find the high and low index
        //    //Given a sorted array of integers, return the low and high index of the given key>. You must return -1 if the indexes are not found.
        //
        int[] arr1 = {1, 2, 3, 3, 3, 3, 5, 5, 5, 5, 20};
        find_the_high_and_low_index(arr1, 5); // [6, 9]
        find_the_high_and_low_index(arr1, 3); // [2, 5]
    }

    //Array
    //1. Given an integer array, move all elements that are equal to 0 to the left while maintaining the order of other elements in the array
    static void MoveZerosLeft(int[] arr){
        System.out.println(arr);
        int readIndex = arr.length-1, wrightIndex = arr.length-1;

        while(readIndex>=0){
            if(arr[readIndex]!=0){
                arr[wrightIndex] = arr[readIndex];
                wrightIndex--;
            }
            readIndex--;
        }

        while(wrightIndex>=0){
            arr[wrightIndex] = 0;
            wrightIndex--;
        }
        System.out.println(arr);
    }

    //Arrays:
    //2: Merge overlapping intervals

    static int[] merge_overlapping_intervals(int[][] timeSample){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < timeSample.length; i++){
            if(min >= timeSample[i][0])
                min = timeSample[i][0];
            if(max <= timeSample[i][0])
                max = timeSample[i][0];
            if(min >= timeSample[i][1])
                min = timeSample[i][1];
            if(max <= timeSample[i][1])
                max = timeSample[i][1];

        }
        int[] mergered = {min, max};
        return mergered;
    }

    //3: Trees: Convert binary tree to doubly linked list
    static List<TreeNode> list = new ArrayList<>();
    static void travelofBST(TreeNode root){
        if(root==null)
            return;
        if(root.left!=null)
            travelofBST(root.left);
        System.out.printf("%s ", root.key);
        list.add(root);
        if(root.right!=null)
            travelofBST(root.right);
    };

    static TreeNode convert_binary_tree_to_doubly_linked(TreeNode bst){
        travelofBST(bst);
        TreeNode header = null;
        TreeNode next = null;
        for(TreeNode node : list){
            if(header==null)
                header = node;
            else {
                next.left = node;
                node.right = next;
            }
            next = node;
        }
        return header;
    }

    //4. Trees: Level order traversal of binary tree

    /* Compute the "height" of a tree -- the number of
    nodes along the longest path from the root node
    down to the farthest leaf node.*/
    static int height(TreeNode root)
    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }

    /* Print nodes at the given level */
    static void printGivenLevel (TreeNode root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }

    /* function to print level order traversal of tree*/

    static void level_order_traversal_of_binary_tree(TreeNode root) {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++)
            printGivenLevel(root, i);
    }

    //5. Strings: Reverse words in a sentence
    static void reverse_words_in_a_sentence(String str){
        String[] strWords = str.split(" ");

        if(strWords.length==2){
            System.out.println();
            System.out.println(strWords[1] + " " + strWords[0]);
        }
    }

    //6. Strings: String segmentation

    static boolean string_segmetatio(String s, Set <String> dictionary){
        for (int i = 1; i <= s.length(); ++i) {
            String first = s.substring(0, i);
            if (dictionary.contains(first)) {
                String second = s.substring(i);

                if (second == null || second.length() == 0 || dictionary.contains(second) || string_segmetatio(second, dictionary)) {
                    return true;
                }

            }
        }
        return false;
    }

    //7. Dynamic Programming: Find maximum single sell profit
    class Tuple<X, Y> {
        public X x;
        public Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int[] findBuySellStockPrices(int[] array) {
        if(array == null || array.length < 2) {
            return null;
        }

        int current_buy = array[0];
        int global_sell = array[1];
        int global_profit = global_sell - current_buy;

        int current_profit = Integer.MIN_VALUE;

        for(int i=1; i<array.length; i++) {
            current_profit = array[i] - current_buy;

            if(current_profit > global_profit) {
                global_profit = current_profit;
                global_sell = array[i];
            }

            if(current_buy > array[i]) {
                current_buy = array[i];
            }
        }

        int[] result = {global_sell - global_profit, global_sell};
        //Tuple<Integer, Integer> result =
        //        new Tuple<Integer, Integer>(global_sell - global_profit, global_sell);

        return result;
    }

    //8. Math and Stats: Calculate the power of a number

    static double calculate_the_power_of_a_number(double x, int n){
        double pow = 1;
        for(int iIndex = 0; iIndex < Math.abs(n); iIndex++){
            pow = x*pow;
        }
        if(n<0)
            return 1/pow;
        else
            return pow;
    }

    //9 Backtracking: Find all possible subsets
    static int get_bit(int num, int bit) {
        int temp = (1 << bit);
        temp = temp & num;
        if (temp == 0) {
            return 0;
        }
        return 1;
    }

    static void get_all_subsets(List<Integer> v, List<HashSet<Integer>> sets) {
        int subsets_count = (int)(Math.pow((double)2, (double)v.size()));
        for (int i = 0; i < subsets_count; ++i) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int j = 0; j < v.size(); ++j) {
                if (get_bit(i, j) == 1) {
                    int x = v.get(j);
                    set.add(x);
                }
            }
            sets.add(set);
        }
    }

    //10 Clone a Directed Graph
    static class Node {
        public int data;
        public List<Node> neighbors = new ArrayList<Node>();
        public Node(int d) {data = d;}
    }

    static class Graph {
        private static Node clone_rec(
                Node root,
                HashMap<Node, Node> nodes_completed) {
            if (root == null) {
                return null;
            }

            Node pNew = new Node(root.data);
            nodes_completed.put(root, pNew);

            for (Node p : root.neighbors) {
                Node x = nodes_completed.get(p);
                if (x == null){
                    pNew.neighbors.add(clone_rec(p, nodes_completed));
                } else {
                    pNew.neighbors.add(x);
                }
            }
            return pNew;
        }

        public static Node clone(Node root) {
            HashMap<Node, Node> nodes_completed =
                    new HashMap<Node, Node>();

            return clone_rec(root, nodes_completed);
        }
    }
    static class clone_graph {
        // if there is an edge from x to y
// that means there must be an edge from y to x
// and there is no edge from a node to itself
// hence there can maximim of (nodes * nodes - nodes) / 2 edgesin this graph
        static ArrayList<Node> create_test_graph_undirected(int nodes_count, int edges_count) {
            ArrayList<Node> vertices = new ArrayList<Node>();
            for (int i = 0; i < nodes_count; ++i) {
                vertices.add(new Node(i));
            }
/*
            List<Pair<Integer, Integer>> all_edges = new ArrayList<Pair<Integer, Integer>>();
            for (int i = 0; i < vertices.size(); ++i) {
                for (int j = i + 1; j < vertices.size(); ++j) {
                    all_edges.add(new Pair<Integer, Integer>(i, j));
                }
            }

            Collections.shuffle(all_edges);

            for (int i = 0; i < edges_count && i < all_edges.size(); ++i) {
                Pair<Integer, Integer> edge = all_edges.get(i);
                vertices.get(edge.first).neighbors.add(vertices.get(edge.second));
                vertices.get(edge.second).neighbors.add(vertices.get(edge.first));
            }
*/
            return vertices;
        }

        static void print_graph(List<Node> vertices) {
            for (Node n : vertices) {
                System.out.print(n.data + ": {");
                for (Node t : n.neighbors) {
                    System.out.print(t.data + " ");
                }
                System.out.println();
            }
        }

        static void print_graph(Node root, HashSet<Node> visited_nodes) {
            if (root == null || visited_nodes.contains(root)) {
                return;
            }

            visited_nodes.add(root);

            System.out.print(root.data + ": {");
            for (Node n : root.neighbors) {
                System.out.print(n.data + " ");
            }
            System.out.println("}");

            for (Node n : root.neighbors) {
                print_graph(n, visited_nodes);
            }
        }

        static void print_graph(Node root) {
            HashSet<Node> visited_nodes = new HashSet<Node>();
            print_graph(root, visited_nodes);
        }

        static boolean are_graphs_equal_rec(Node root1, Node root2, HashSet<Node> visited) {
            if (root1 == null && root2 == null) {
                return true;
            }

            if (root1 == null || root2 == null) {
                return false;
            }

            if (root1.data != root2.data) {
                return false;
            }

            if (root1.neighbors.size() != root2.neighbors.size()) {
                return false;
            }

            for (Node nbr1 : root1.neighbors) {
                boolean found = false;
                for (Node nbr2 : root2.neighbors) {
                    if (nbr1.data == nbr2.data) {
                        if (!visited.contains(nbr1)) {
                            visited.add(nbr1);
                            are_graphs_equal_rec(nbr1, nbr2, visited);
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }

            return true;
        }
    }

    //11. Design: Serialize / deserialize binary tree

    static void serialize_deserialize_binary_tree(TreeNode root){
        List<Integer> serialize_list = new ArrayList<>();
        serialize_binary_tree(root, serialize_list);
        TreeNode node = deserialize_binary_tree(serialize_list, null);
        return;
    }

    static void serialize_binary_tree(TreeNode root, List<Integer> serialize_list){
        if(root==null)
            return;
        serialize_list.add(root.key);
        serialize_binary_tree(root.left, serialize_list);
        serialize_binary_tree(root.right, serialize_list);
        return;
    }

    static TreeNode deserialize_binary_tree(List<Integer> serialize_list, TreeNode root){
        if(serialize_list.size()==0)
            return null;
        int key = serialize_list.remove(0);
        root = new TreeNode(key);
        deserialize_binary_tree(serialize_list, root.left);
        deserialize_binary_tree(serialize_list, root.right);
        return root;
    };

    //12222};

    int[] arr2 = {1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162, 176, 188, 199, 200, 210, 222};

    //search_rotated_array(arr, 0, arr2.length-1, 20);


    ///////////////////////
    static boolean addTwoNumberUnitTest() {
        //unit test function
        int[] No1 = {1,2,3,4,5,6}; // test number
        int[] No2 = {1,2,3,4,5,6};
        String test = "777777";    // correct result
        if(addTwoNumbers(No1, No2).equals(test))
            return true; // test passed
        else
            return false; // test failure
    }

    static String addTwoNumbers(int[] No1, int[] No2){
        String sNo1 = "";
        String sNo2 = "";

        for(int i = 0; i < No1.length; i++){
            sNo1 = sNo1 + No1[i];
        } //set1 number to string

        for(int i = No1.length-1; i >=0; i--){
            sNo2 = sNo2 + No2[i];
        } //set2 number revert to string

        // add two string parsed numbers and return the sum in string
        return Long.toString(Long.parseLong(sNo1)+ Long.parseLong(sNo2));
    }

    //12 Sorting and Searching: Find the high and low index
    //Given a sorted array of integers, return the low and high index of the given key>. You must return -1 if the indexes are not found.

    static int[] find_the_high_and_low_index(int[] arr, int key){
        int[] index = {-1, -1};
        int low = 0;
        int high = arr.length-1;
        int mid = arr.length/2;

        int midIndex = find_the_high_and_low_index_util(arr, key, low, mid, high);
        for(int iIndex = midIndex; iIndex>=0; iIndex--){
            if(key!=arr[iIndex]){
                break;
            }
            else {
                index[0] = iIndex;
            }
        }
        for(int iIndex = midIndex; iIndex<high; iIndex++){

            if(key!=arr[iIndex]){
                break;
            }else{
                index[1] = iIndex;
            }
        }

        return index;
    }

    static int find_the_high_and_low_index_util(int[] arr, int key, int low, int mid, int high){
        if(key==arr[mid])
            return mid;
        if(key<arr[mid]){
            high = mid;
            mid = (high-low)/2 + low;
            return find_the_high_and_low_index_util(arr, key, low, mid, high);
        }
        if(key>arr[mid]){
            low = mid;
            mid = (high-low)/2 + low;
            return find_the_high_and_low_index_util(arr, key, low, mid, high);
        }
        return -1;
    }

    //13 Sorting and Searching: Search rotated array
    //Search for a given number in a sorted array, with unique elements, that has been rotated by some arbitrary number. Return -1 if the number does not exist.
    static int search_rotated_array(int[] arr, int start, int end, int key){

        if(start>end)
            return -1;

        int mid = start + (end-start)/2;
        if(arr[mid]==key)
            return mid;

        if (arr[start] <= arr[mid] && key <= arr[mid] && key >= arr[start]) {
            return search_rotated_array(arr, start, mid - 1, key);
        }

        else if (arr[mid] <= arr[end] && key >= arr[mid] && key <= arr[end]) {
            return search_rotated_array(arr, mid + 1, end, key);
        }

        else if (arr[end] <= arr[mid]) {
            return search_rotated_array(arr, mid + 1, end, key);
        }
        return -1;
    }

    //16 Longest increasing subsequence from array of integers (dynamic programming arrays)

    //17. Unique paths in a grid (dynamic programming matrices)
    static int unique_paths_in_a_grid(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    //****************************************

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

    //youtube foacebook interview question

    static void findLongestSubarrayBySubTest(){
        int[] arr = {1, 2, 3, 7, 5};
        int s = 12;
        arr = findLongestSubarrayBySub(s, arr); //{1,3)
    }

    static int[] findLongestSubarrayBySub(int s, int[] arr){
        int[] result = new int[]{-1};
        int left = 0;
        int right = 0;
        int sum = 0;

        while(right < arr.length){
            sum += arr[right];
            while(left<right && sum >s){
                sum -= arr[left++];
            }
            if(sum==s && (result.length == 1 || result[1] - result[0] < right - left)) {
                result = new int[] {left+1, right+1};
            }
            right++;
        }
        return result;
    }

    //Partition Equal Subset Sum
    //Give a non-empty array contain only positive integers. find if the array can be partitioned into two
    //sub sets is equal.
    //1. Each of the array element will not exceed 100
    //2. The array size will not exceed 200

    //Example 1:
    // Input:[1,5,11,5]
    // Output: true
    // Explanation: The array can be partitioned as [1, 5, 5] and [11].

    //Example 2:
    // Input: [1, 2, 3, 5]
    // Output: false
    // Explanation: the arary cannot be partition two equal subsets.

    static void conPartitionTest(){
        int[] arr = new int[]{1, 5, 11, 5};

        boolean bRe = conPartition(arr);
        arr =new int[]{1, 2, 3, 5};
        bRe = conPartition(arr);
    }

    static boolean conPartition(int[] arr){
        int total = 0;
        for(int i=0; i<arr.length; i++){
            total += arr[i];
        }
        if(total%2!=0)
            return false;
        int sum = total/2;

        return conPartitionUtil(arr, 0, arr.length-1, sum);
    }

    static boolean conPartitionUtil(int[] arr, int left, int right, int sum){
        int total = 0;
        int mid = left + (right-left)/2;
        for(int i=left; i<=mid; i++){
            total += arr[i];
        }
        if(total==sum)
            return true;
        if(total>sum)
            return conPartitionUtil(arr, left, mid, sum);
        else
            return conPartitionUtil(arr, mid+1, right, sum);
    }

    //leevcode
    //1. find two number sum in array equal target value

    static void findTwoNumbersSumInArrayEqualTargetValueTest(){
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] returns = findTwoNumbersSumInArrayEqualTargetValue(nums, target); //[0, 1]
    }
    static int[] findTwoNumbersSumInArrayEqualTargetValue(int[] nums, int target){
        int[] returns = {0, 0};
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]), i};
            }
            else{
                map.put(target-nums[i], i);
            }
        }
        return null;
    }

    //2. Add two link nodes and return the value.
    //input (2->4->3) + (5->6->4)
    // 342+465 = 807
    // ouput (7->0->8)
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    static void addTwoLinksTest(){
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode l3 = addTwoLinks(l1,l2);

    }

    static ListNode addTwoLinks(ListNode l1a, ListNode l2a){
        ListNode l1 = l1a;
        ListNode l2 = l2a;
        //ListNode l3 = new ListNode(0);
        //ListNode current = l3.next;
        ListNode l3 = null;
        ListNode l3a = null;

        //ListNode newL = l3.next;
        int sum = 0;
        int more = 0;

        while((l1!=null)||(l2!=null)||(more>0)){
            sum = sum + (l1!=null? l1.val: 0);
            sum = sum + (l2!=null? l2.val: 0);
            sum = sum + more;
            if(sum>9){
                more = 1;
                sum = sum%10;
            }
            else
                more = 0;

            ListNode current = new ListNode(sum);
            if(l3==null){
                l3=current;
            }
            else {
                l3a.next = current;
            }
            l3a = current;

            if(l1!=null)
                l1 = l1.next;
            if(l2!=null)
                l2 = l2.next;
            sum = 0;

        }
        return l3;
    }

}
