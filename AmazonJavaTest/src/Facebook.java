import java.util.*;

public class Facebook {

    //****************************************
    //Cracking the top 40 Facebook coding interview questions
    //https://www.educative.io/blog/cracking-top-facebook-coding-interview-questions

    //Array
    //1. Given an integer array, move all elements that are equal to 0 to the left while maintaining the order of other elements in the array
    static void MoveZerosLeft_test() {
        int arr[] = {1, 10, 20, 0, 59, 63, 0, 88, 0};
        System.out.println(Arrays.toString(arr));
        int[] arr1 = new int[arr.length];
        int index1 = arr.length - 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            if (arr[index] != 0) {
                arr1[index1] = arr[index];
                index1--;
            }
        }
        System.out.println(Arrays.toString(arr1));
        //{0, 0, 0, 1, 10, 20, 59, 63, 88}

    }

    //2: Merge overlapping intervals
    static void merge_overlapping_intervals_test() {
        int[][] arrs = new int[][]{{1, 5}, {3, 7}, {4, 6}, {6, 8}, {10, 12}, {11, 15}};
        for(int[] arr : arrs){
            System.out.print(Arrays.toString(arr) + ", ");
        }
        System.out.println(" ");
        // => list = {{1, 8},{10, 15}}
        List<int[]> list = new ArrayList<int[]>();
        int[] range = arrs[0];
        for (int index = 1; index < arrs.length; index++) {
            if (range[1] < arrs[index][0]) {
                list.add(range);
                System.out.println(Arrays.toString(range));
                range = arrs[index];

            } else if (range[1] >= arrs[index][0] && range[1] <= arrs[index][1]) {
                range[1] = arrs[index][1];
                if (index == arrs.length - 1)
                    System.out.println(Arrays.toString(range));
            }
        }
    }


    //linked lists
    //3. Add two integers
    static void add_two_integers_test() {
        // 1->0->9->9 + 7->3->2 => 8->3->1->0->1   1099 + 732 = 1831
        int[] int1 = {1, 0, 9, 9};
        int[] int2 = {7, 3, 2};
        int length = Math.max(int1.length, int2.length);
        int[] int3 = new int[length + 1];
        int index1 = int1.length - 1;
        int index2 = int2.length - 1;
        int index3 = int3.length - 1;

        int carry = 0;
        while ((index1 >= 0) || (index2 >= 0)) {
            int a1 = 0;
            int a2 = 0;
            if (index1 >= 0) {
                a1 = int1[index1];
                index1--;
            }
            if (index2 >= 0) {
                a2 = int2[index2];
                index2--;
            }
            int a3 = a1 + a2 + carry;
            carry = 0;
            if (a3 >= 10) {
                carry = 1;
                a3 = a3 - 10;
            }
            int3[index3] = a3;
            index3--;

        }
        System.out.println("carry =" + carry);
        int3[index3] = carry;
        // 1->0->9->9 + 7->3->2 => 1, 8, 3,1   1099 + 732 = 1831
        System.out.println(Arrays.toString(int3));
    }

    //4. Merge two sorted linked lists
    static void merge_two_sorted_linked_lists_test(){

        LinkedList<Integer> list1 = new LinkedList<Integer>();
        list1.add(4);
        list1.add(8);
        list1.add(15);
        list1.add(19);

        System.out.println("4->8->15->19 meger 7->9->10->16");

        LinkedList<Integer> list2 = new LinkedList<Integer>();
        list2.add(7);
        list2.add(9);
        list2.add(10);
        list2.add(16);
        // 4,7,8,9,10,15,16,19

        LinkedList<Integer> list3 = new LinkedList<Integer>();
        for(int i : list1)
            list3.add(i);
        for(int i : list2)
            list3.add(i);
        Collections.sort(list3);
        for(int i : list3)
            System.out.println(i);
    }

    //5. Convert binary tree to doubly linked
    //  Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
    static void Convert_binary_tree_to_doubly_linked_test(){ //best search tree B
        TreeNode root = new TreeNode(4);             //              4
        root.left = new TreeNode(2);                 //           /     \
        root.left.left = new TreeNode(1);            //        2          5
        root.left.right = new TreeNode(3);           //     /     \
        root.right = new TreeNode(5);

        LinkedList<TreeNode> list = new LinkedList<TreeNode>();                //  1          3
        Convert_binary_tree_to_doubly_link(root, list);
        TreeNode head = null;
        TreeNode lastNode = null;
        TreeNode newNode = null;
        for(int index = 0; index < list.size(); index++){
            newNode = list.get(index);
            if(index==0){
                newNode.left = null;
                head = newNode;
            }
            else {
                newNode.left = lastNode;
                lastNode.right = newNode;
                if(index==list.size()-1){
                    lastNode.right = head;
                    head.left = lastNode;
                    break;
                }
            }
            lastNode = newNode;
            System.out.print(newNode.key + ",");
        }
        System.out.println(" ");
    }
    public static void Convert_binary_tree_to_doubly_link(TreeNode root, LinkedList<TreeNode> list)
    {
        if(root == null)
            return;
        if(root.left!=null)
            Convert_binary_tree_to_doubly_link(root.left, list);
        list.add(root);
        if(root.right!=null)
            Convert_binary_tree_to_doubly_link(root.right, list);
        return;
    }


    //6. Trees: Level order traversal of binary tree
    static void level_order_traversal_of_binary_tree_test() {
        TreeNode root = new TreeNode(1);         //              1
        root.left = new TreeNode(2);              //       2            3
        root.right = new TreeNode(3);             //   4       5
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        //LinkedList<TreeNode> list = new LinkedList<>();
        //list.add(root);
        travel_level_order_of_BST(root);
        //{1, 2, 3, 4, 5}
        System.out.println(" ");

    }

    static void travel_level_order_of_BST(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.key + ", ");
        if (node.left != null)
            travel_level_order_of_BST(node.left);
        if (node.right != null)
            travel_level_order_of_BST(node.right);

    }

    //7. Strings: Reverse words in a sentence
    static void reverse_words_in_a_sentence_test() {
        String str = "Hello World !";  //"! World Hello"
        String[] strArr = str.split(" ");
        for (int index = 0; index < strArr.length; index++) {
            StringBuffer tem = new StringBuffer(strArr[index]);
            tem = tem.reverse();
            if (index == 0)
                str = tem.toString();
            else
                str = str + " " + tem.toString();
        }
        System.out.println(str);    // splite  " " then reverse
    }

    //8. Strings: String segmentation
    static void string_segmetatio_test(){
        String s = "hellonow";          //?? words char by char, seach new string in set, Recursion seach next paration
        System.out.println(s);

        Set <String> direcotry = new HashSet<>();
        direcotry.add("hello");
        direcotry.add("hell");
        direcotry.add("on");
        direcotry.add("now");

        for(String str : direcotry){
            String newStr = s.replace(str, "");
            if(direcotry.contains(newStr)){
                System.out.println(str + " " + newStr);
                break;
            }
        }
    }

    //9. Dynamic Programming: Find maximum single sell profit
    static void findBuySellStockPrices_test(){
        int[] prices = {8, 5, 12, 9, 19, 1};
        System.out.println(Arrays.toString(prices));

        int buyPt = prices[0];
        int sellPt = prices[0];
        int buyDiff = 0;
        int sellDiff = 0;
        for(int index = 1; index < prices.length; index++){
            if(buyPt>prices[index]){
                buyPt=prices[index];
                if((sellDiff < sellPt - buyPt)) {
                    sellDiff = sellPt - buyPt;
                }
            }
            if(sellPt<prices[index]){
                sellPt=prices[index];
                if(buyDiff < sellPt - buyPt){
                    buyDiff = sellPt - buyPt;
                }

            }
        }
        System.out.println("Buy Pt: " + buyPt + " Buy Diff: " + buyDiff );
        System.out.println("Sell Pt: " + sellPt + " Sell Diff: " + sellDiff );

    }

    //10. Math and Stats: Calculate the power of a number
    static void calculate_the_power_of_a_number_test() {
        double ret = 0;
        ret = calculate_the_power_of_a_number(2, 5);
        System.out.println("calculate_the_power_of_a_number(2, 5) = " + ret);
        ret = calculate_the_power_of_a_number(3, 4);
        System.out.println("calculate_the_power_of_a_number(3, 4) = " + ret);
        ret = calculate_the_power_of_a_number(1.5, 3);
        System.out.println("calculate_the_power_of_a_number(1.5, 3) = " + ret);
        ret = calculate_the_power_of_a_number(2, -2);
        System.out.println("calculate_the_power_of_a_number(2, -2) = " + ret);
/*
power (2, 5) = 32
power (3, 4) = 81
power (1.5, 3) = 3.375
power (2, -2) = 0.25
 */

    }

    static double calculate_the_power_of_a_number(double x, int n) {
        double pow = 1;
        for (int iIndex = 0; iIndex < Math.abs(n); iIndex++) {
            pow = x * pow;
        }
        if (n < 0)
            return 1 / pow;
        else
            return pow;
    }


    //11 Backtracking: Find all possible subsets
    // it is harder
    public static void FindallpossiblesubsetsTest() {
        int[] nums = {1, 2, 3};
        //[],[1],[1, 2],[1, 2, 3],[1, 3],[2],[2, 3], [3]

        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), subsets);

        System.out.println("All subsets of the array:");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    private static void generateSubsets(int[] nums, int index, List<Integer> currentSubset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(currentSubset));

        for (int i = index; i < nums.length; i++) {
            currentSubset.add(nums[i]);
            generateSubsets(nums, i + 1, currentSubset, subsets);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    //12 Clone a Directed Graph
    static void clone_a_directed_graph() {

    }

    static class Node {
        public int data;
        public List<Node> neighbors = new ArrayList<Node>();

        public Node(int d) {
            data = d;
        }
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
                if (x == null) {
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

    //13. Design: Serialize / deserialize binary tree

    static void serialize_deserialize_binary_tree_test() {
        TreeNode root = new TreeNode(1);        //              1
        root.left = new TreeNode(2);             //      2               3
        root.right = new TreeNode(3);            //  4       5
        root.left.left = new TreeNode(4);        //
        root.left.right = new TreeNode(5);       //  => 1,2,3,4,5
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<TreeNode> relist = new ArrayList<>();
        serialize_binary_tree(list, relist);

        System.out.println(" ");
        for (TreeNode node : relist) {
            System.out.print(node.key + ",");
        }
        System.out.println(" ");

        root = new TreeNode() {
        };
        deserialize_binary_tree(relist, root);

    }

    static void deserialize_binary_tree(List<TreeNode> reList, TreeNode root) {
        if (reList.size() == 0) {
            return;
        }
        int index = 0;
        while (index < reList.size()) {
            TreeNode node = reList.get(index);
            System.out.print("index :" + index + ",");

            System.out.print("root :" + node.key + ",");
            if (node.left != null) {
                index++;
                root.left = reList.get(index);
                System.out.print("root.left :" + root.left.key + ",");
            }
            if (node.right != null) {
                index++;
                root.right = reList.get(index);
                System.out.print("root.right :" + root.right.key + ",");
            }
            index++;
        }
    }

    static void serialize_binary_tree(List<TreeNode> list, List<TreeNode> relist) {
        if ((list == null) || (list.size() == 0))
            return;
        List<TreeNode> sublist = new ArrayList<>();
        for (TreeNode node : list) {
            if (node.left != null) {
                sublist.add(node.left);
            }
            if (node.right != null) {
                sublist.add(node.right);
            }
            relist.add(node);
        }
        serialize_binary_tree(sublist, relist);
    }

    //sorting and searching
    //14 Search rotated Sorted array
    static void Search_rotated_array_test() {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};  //change point index -
        int key = 3;
        int key1 = Search_rotated_array(arr, key);
        System.out.println(key1);
    }

    static int findDividIndex(int[] arr) {
        int from = 0;
        int to = arr.length - 1;
        while (from < to) {   //from and to cloing to cut point in both said
            int mid = from + (to - from) / 2;
            if (arr[mid] > arr[to]) {
                from = from + 1;
            } else {
                to = mid;
            }
        }
        return from;
    }

    static int Search_rotated_array(int[] arr, int key) {
        int index = findDividIndex(arr);
        int from = 0;
        int to = 0;
        int mid = 0;
        if (key >= arr[0]) {
            from = 0;
            to = index - 1;

        } else {
            from = index;
            to = arr.length - 1;
        }

        while (from < to) {
            mid = from + (to - from) / 2;
            if (key == arr[mid])
                return mid;
            if (key < arr[mid])
                to = mid - 1;
            else
                from = mid + 1;
            if (key == arr[from])
                return from;
            if (key == arr[to])
                return to;
            if (to - from <= 1)
                return -1;
        }
        return -1;

    }

    // 15  Sorting and Searching: Find the high and low index
    //Given a sorted array of integers, return the low and high index of the given key>. You must return -1 if the indexes are not found.
    static void find_the_high_and_low_index_test() {
        int[] arr1 = {1, 2, 3, 3, 3, 3, 5, 5, 5, 5, 20};
        System.out.println(arr1);
        int[] arr2 = find_the_high_and_low_index(arr1, 5); // [6, 9]
        arr2 = find_the_high_and_low_index(arr1, 3); // [2, 5]
        System.out.println(arr2);
    }

    static int[] find_the_high_and_low_index(int[] arr, int key) {
        int[] index = {0, arr.length - 1};
        boolean bK = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                if (bK == false) {
                    index[0] = i;
                    bK = true;
                }
            } else {
                if (bK == true) {
                    index[1] = i - 1;
                    break;
                }
            }
        }

        return index;

    }

    static int find_the_high_and_low_index_util(int[] arr, int key, int low, int mid, int high) {
        if (key == arr[mid])
            return mid;
        if (key < arr[mid]) {
            high = mid;
            mid = (high - low) / 2 + low;
            return find_the_high_and_low_index_util(arr, key, low, mid, high);
        }
        if (key > arr[mid]) {
            low = mid;
            mid = (high - low) / 2 + low;
            return find_the_high_and_low_index_util(arr, key, low, mid, high);
        }
        return -1;
    }

    //16 Longest increasing subsequence from array of integers (dynamic programming arrays)
    static void longest_increasing_subsequence_from_array_of_integers_test() {
        int[] arr = {0, 2, 1, 7, 8, 9, 10, 4};  //=>{7,8,9}
        int max = longest_increasing_subsequence_from_array_of_integers(arr);
        System.out.println(max);
    }

    static int longest_increasing_subsequence_from_array_of_integers(int[] arr) {
        int iVal = 0;
        int start = 0;
        int sample = 0;
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                start = 0;
            } else {
                if (arr[i] > sample) {
                    if ((i - start) > maxLength)
                        maxLength = i - start;
                } else {
                    sample = arr[i];
                    start = i;
                }
            }
            sample = arr[i];
        }
        return maxLength;
    }

    //17. Unique paths in a grid (dynamic programming matrices)
    static void unique_paths_in_a_grid_test() {
        int steps = unique_paths_in_a_grid(6, 4); // 6x4 grid, 0=>56 steps
        System.out.println("7x6 grid steps: " + steps);
    }

    static int unique_paths_in_a_grid(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //18. Add two numbers as a list (lists)
    static void add_two_numbers_as_a_list_test() {
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        l1.add(4);
        l1.add(3);
        List<Integer> l2 = new ArrayList<>();
        l2.add(5);
        l2.add(6);
        l2.add(4);
        List<Integer> l3 = new ArrayList<>();
        int index1 = l1.size() - 1;
        int index2 = l2.size() - 1;
        int d1 = 0;
        int d2 = 0;
        int cy = 0;
        while (index1 >= 0 || index2 >= 0) {
            int sum = 0;
            if (index1 >= 0) {
                d1 = l1.get(index1);
                index1--;
            }
            if (index2 >= 0) {
                d2 = l2.get(index2);
                index2--;
            }
            sum = d1 + d2 + cy;
            if ((sum - 10) >= 0) {
                sum = sum - 10;
                cy = 1;
            } else {
                cy = 0;
                d1 = 0;
                d2 = 0;
            }
            l3.add(sum);
        }
        if (cy > 0) {
            l3.add(l3.size(), cy);
        }
        Collections.reverse(l3);
        System.out.println(Arrays.toString(l3.toArray()));  // 243+564 = 807

    }

    ;

    static int[][] rotate_a_matrix(int[][] arrs, int m, int n) {
        int[][] result = new int[m][n]; //create new int[][] for convert result
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                result[x][y] = arrs[m - 1 - x][n - 1 - y];
            }
        }
        return result;
    }

    //24. Find nth Fibonacci number (number theory)
    static void find_nth_Fibonacci_number_test() {
        int fib = find_nth_Fibonacci_number(6);
        System.out.println(fib);  // fib(6) = fib(5) + fib(4) + fib(3) + fib(2) + 1 + 0 = 21
    }

    ;

    static int find_nth_Fibonacci_number(int n) {
        if (n <= 0)
            return 0;
        return n + find_nth_Fibonacci_number(n - 1);
    }

    //25. Find the square root of an integer using binary search (math search answer)
    static void find_the_square_root_of_an_integer_using_binary_search_test() {
        int x = find_the_square_root_of_an_integer_using_binary_search(9);
        System.out.println(x);
        {
        }
    }

    ;

    static int find_the_square_root_of_an_integer_using_binary_search(int n) {
        if (n < 2)
            return n;

        double left = 0;
        double right = n;
        double epsilon = 0.000001;
        double mid = 0.0;
        double sqr = 0.0;

        while (true) {
            mid = left + (right - left) / 2.0;

            sqr = mid * mid;
            if ((sqr <= n + epsilon) && (sqr >= n - epsilon))
                return (int) mid;

            if (sqr > n + epsilon) {
                right = mid;
            } else {
                left = mid;
            }

        }
    }

    //26 Implement StrStr (string search)
    static void strStr_test() {
        String str1 = "Hello World";
        String str = "Wo";
        int istr = strStr1(str1, str);
        boolean bstr = strStr(str1, str);
        System.out.println(String.valueOf(istr) + " " + String.valueOf(bstr));
    }

    ;

    static boolean strStr(String str1, String str) {
        return str1.contains(str);
    }

    static int strStr1(String str1, String str) {
        return str1.indexOf(str);
    }

    //27 Minimum appends for Palindrome (strings) // string is same as string reverse  Malayal and Malayalam
    static void minimum_appends_for_Palindrome_test() {  //"Malayal" => "Malayalam" =
        String str = minimum_appends_for_Palindrome("Malayal");
        System.out.println(str);
    }

    static String minimum_appends_for_Palindrome(String str) {

        String str1 = new StringBuffer(str).reverse().toString();
        if (str1 == str)
            return str;
        for (int i = 1; i < str.length(); i++) {
            String strSub = str.substring(0, i);
            String strSub1 = new StringBuffer(strSub).reverse().toString();
            String str2 = str + strSub1;
            String str3 = strSub + str1;
            if (str2.equals(str3))
                return str2;
        }

        return str;
    }

    //28. Find the largest rectangle in a histogram (stacks)
    static void find_the_largest_rectangle_in_a_histogram_test() {
        int[] histogram = {2, 1, 5, 6, 2, 3};

        int area = find_the_largest_rectangle_in_a_histogram(histogram); //return 10.;
        System.out.println(area);
    }

    ;

    static int find_the_largest_rectangle_in_a_histogram(int[] height) {
        //??????????????????????????????????????????????????O(n^2). ??????LeetCode?????????????????????????????(Pruning)??????height[k] >= height[k - 1]??????????????height[k]?????height[k - 1]???????????????????????????height[k] < height[k - 1]?k????k - 1????????????????????
        //����������������
        //????????CSDN???Alf?????????CC 4.0 BY-SA?????????????????????
        //?????https://blog.csdn.net/abcbc/article/details/8943485

        // Start typing your Java solution below
        // DO NOT write main() function
        int maxArea = 0;

        int start = 0;
        for (int k = 1; k < height.length; k++) {
            if (height[k] < height[k - 1]) {
                start = k - 1;
                break;
            }
        }

        for (int i = start; i < height.length; i++) {
            int area = 0;
            int high = height[i];
            for (int j = start; j < height.length; j++) {
                if (high > height[j]) {

                    if (j > i)
                        break;
                    else
                        area = 0;
                } else {
                    area = area + high;
                }
            }
            if (area > maxArea)
                maxArea = area;
        }

        return maxArea;
    }

    static int find_the_largest_rectangle_in_a_histogram1(int[] height) {
        //??????????????????????????????????????????????????O(n^2). ??????LeetCode?????????????????????????????(Pruning)??????height[k] >= height[k - 1]??????????????height[k]?????height[k - 1]???????????????????????????height[k] < height[k - 1]?k????k - 1????????????????????
        //����������������
        //????????CSDN???Alf?????????CC 4.0 BY-SA?????????????????????
        //?????https://blog.csdn.net/abcbc/article/details/8943485

        // Start typing your Java solution below
        // DO NOT write main() function

        int area = 0;
        Stack<Integer> heightStack = new Stack<Integer>();
        Stack<Integer> indexStack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            if (heightStack.empty() || heightStack.peek() <= height[i]) {
                heightStack.push(height[i]);
                indexStack.push(i);
            } else if (heightStack.peek() > height[i]) {
                int j = 0;
                while (!heightStack.empty() && heightStack.peek() > height[i]) {
                    j = indexStack.pop();
                    int currArea = (i - j) * heightStack.pop();
                    if (currArea > area) {
                        area = currArea;
                    }
                }
                heightStack.push(height[i]);
                indexStack.push(j);
            }
        }
        while (!heightStack.empty()) {
            int currArea = (height.length - indexStack.pop()) * heightStack.pop();
            if (currArea > area) {
                area = currArea;
            }
        }
        return area;
    }

    //29. Substring concatenation (incremental hash)  ?????????
    static void substring_concatenation_test() {
        String S = "barfoothefoobarman";
        String[] L = {"foo", "bar"};
        List<Integer> iCon = substring_concatenation(S, L);   //barfoo at 0 foobar at 9
        System.out.println(iCon.toArray());
        S = "barfoofoothefoobarman";
        iCon = substring_concatenation(S, L);
        System.out.println(iCon.toArray());
    }

    static List<Integer> substring_concatenation(String s, String[] words) {
        if (s.length() == 0 || words.length == 0)
            return null;

        List<Integer> res = new ArrayList<>();
        int n = words.length, len = words[0].length();
        HashMap<String, Integer> wordCnt = new HashMap<>();
        for (String word : words)
            wordCnt.put(word, 1);

        int i = 0;
        int start = 0;
        while (i < s.length()) {
            String t = s.substring(i, i + len);
            Set<String> set1 = new HashSet<>();
            if (wordCnt.containsKey(t)) {
                set1.add(t);
                start = i;
                i = i + len;
                for (int j = 1; j < n; j++) {
                    String t1 = s.substring(i, i + len);
                    i = i + len;
                    if (wordCnt.containsKey(t1)) {
                        set1.add(t1);
                    } else {
                        start = 0;
                        break;
                    }
                }
                if (set1.size() == n) {
                    res.add(start);
                } else {
                    start = 0;
                }
            } else
                i = i + len;
            System.out.println();
        }
        return res;
    }

    //30. Find the least common ancestor (tree search)
    static void find_the_least_common_ancestor_test() {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);    //3, 5 least common ancestor is here 4.

        int rk = find_the_least_common_ancestor(root, 3, 5);
        System.out.println(rk);
    }

    ;

    static int find_the_least_common_ancestor(TreeNode root, int v1, int v2) {
        // Write your code here.

        if ((v1 < root.key) && (v2 < root.key))
            return find_the_least_common_ancestor(root.left, v1, v2);
        if ((v1 > root.key) && (v2 > root.key))
            return find_the_least_common_ancestor(root.right, v1, v2);
        return root.key;  //if ((v1 < root.data) && (v2 > root.data))
    }

    //31. Find largest distance between nodes in a tree (DFS)
    static void find_largest_distance_between_nodes_in_a_tree_test() {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        int rk = find_largest_distance_between_nodes_in_a_tree(root, 3, 5);
        System.out.println(rk);


    }

    ;

    static int find_largest_distance_between_nodes_in_a_tree(TreeNode root, int v1) {
        if (root.key == v1)
            return 0;
        if (v1 < root.key)
            return find_largest_distance_between_nodes_in_a_tree(root.left, v1) + 1;
        else
            return find_largest_distance_between_nodes_in_a_tree(root.right, v1) + 1;
    }

    static int find_largest_distance_between_nodes_in_a_tree(TreeNode root, int v1, int v2) {
        if (root == null)
            return 0;

        if ((root.key > v1) && (root.key > v2))
            return find_largest_distance_between_nodes_in_a_tree(root.left, v1, v2);

        if ((root.key < v1) && (root.key < v2))
            return find_largest_distance_between_nodes_in_a_tree(root.right, v1, v2);

        if ((root.key >= v1) && (root.key <= v2))
            return find_largest_distance_between_nodes_in_a_tree(root, v1) +
                    find_largest_distance_between_nodes_in_a_tree(root, v2);
        return -1;
    }


    //32. Find all unique triplets in an array, giving sum of zero (array)
    static void find_all_unique_triplets_in_an_array_test() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<int[]> results = find_all_unique_triplets_in_an_array(nums);// {{-1, 0, 1}, {-1, -1, 2}}
        System.out.println();
    }

    ;

    static List<int[]> find_all_unique_triplets_in_an_array(int[] arrs) {
        Arrays.sort(arrs);
        List<int[]> results = new LinkedList<>();
        for (int i = 0; i < arrs.length - 2; i++) {
            int front = i + 1;
            int end = arrs.length - 1;
            int target = -arrs[i];
            int[] result = {0, 0, 0};
            while (end > front) {
                int sum = arrs[front] + arrs[end];
                if (target > sum) {
                    //if smaller abs target number > range of sum(front, end) move target number to next
                    front++;
                } else if (target == sum) {
                    //if smaller abs target number = range of sum(front, end) the target, start, end are right number
                    result[0] = arrs[i];
                    result[1] = arrs[front];
                    result[2] = arrs[end];
                    results.add(result);
                    break;
                } else {
                    //if smaller abs target number < range of sum(front, end) move end back
                    end--;
                }
            }
        }
        return results;
    }

    //33. Find maximum path sum in non-empty binary tree
    static void find_maximum_path_sum_in_non_empty_binary_tree_test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int[] maxPath = {Integer.MIN_VALUE};
        find_maximum_path_sum_in_non_empty_binary_tree(root, maxPath);
        System.out.println(maxPath);
    }

    static int find_maximum_path_sum_in_non_empty_binary_tree(TreeNode root, int[] maxPath) {

        if (root == null) {
            return 0;
        }

        int leftSum = find_maximum_path_sum_in_non_empty_binary_tree(root.left, maxPath);
        int rightSum = find_maximum_path_sum_in_non_empty_binary_tree(root.right, maxPath);
        int current = Math.max(root.key, Math.max(leftSum + root.key, rightSum + root.key));
        maxPath[0] = Math.max(maxPath[0], leftSum + root.key + rightSum);
        return current;
    }

    //34. Find K closest points to origin for a list of points on a plane (search/sort)  4/25/2025 invertview Q1
    static void find_K_closest_points_to_origin_for_a_list_of_points_on_a_plane_test() {
        int[][] points = {{1, 4}, {1, -4}, {7, 5}, {-1, 3}, {5, 1}, {3, -2}, {5, 0}};
        int K = 5;

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for(int index = 0; index < points.length; index++){
            int sqr = points[index][0]*points[index][0] + points[index][1]*points[index][1];

            List<Integer> subList = new ArrayList<Integer>();
            subList.add(index);
            if(map.containsKey(sqr)){
                subList = map.get(sqr);
                subList.add(index);
            }
            map.put(sqr, subList);
            //System.out.println("sqr=" + sqr + " points[index] " + Arrays.toString(points[index]));
        }

        TreeMap<Integer, List<Integer>> tMap =
                new TreeMap<>(map);

        for(Integer ptKey : tMap.keySet())
        {
            List<Integer> indexSort = tMap.get(ptKey);
            for(Integer index : indexSort){
                System.out.println("ptKey=" + ptKey + " subIndex " + Arrays.toString(points[index]) );
                K--;
                if(K<=0){
                    return;
                }
            }
        }
    }

    //35. Write a function to compute intersection of arrays (sort/search)
    static void compute_intersection_of_arrays_test() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {5, 3, 2};
        Set<Integer> set = compute_intersection_of_arrays(arr1, arr2);  //{3,2}
    }

    ;

    static Set<Integer> compute_intersection_of_arrays(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>();   //first put arr1 to Set1 for compare
        Set<Integer> set2 = new HashSet<>();   //loop arr2 to compare Set1, same i add to Set2

        for (int i : arr1)
            set1.add(i);

        for (int j : arr2)
            if (set1.contains(j))
                set2.add(j);
        return set2;
    }

    //38. Group anagrams together in an array of strings (arrays/strings)
    static void group_anagrams_together_in_an_array_of_strings_test() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        HashMap<String, List<String>> strs1 = group_anagrams_together_in_an_array_of_strings(strs);
        //{{"eat", "tea", "ate"}, {"tan","nat"}, {"bat"}}
    }

    static HashMap<String, List<String>> group_anagrams_together_in_an_array_of_strings(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {               //convert each string to sorted chrs string as map key ot group same key strings.
            char[] chrs = str.toCharArray();
            Arrays.sort(chrs);
            String str1 = String.valueOf(chrs);
            List<String> list1;
            if (map.containsKey(str1)) {
                list1 = map.get(str1);
            } else {
                list1 = new ArrayList<>();
            }
            list1.add(str);
            map.put(str1, list1);
        }
        return map;
    }

    //39. Convert a BST to sorted circular doubly linked list (trees)
    static void convert_a_BST_to_sorted_circular_doubly_linked_list_test() {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        TreeNode node = convert_a_BST_to_sorted_circular_doubly_linked_list(root);
        System.out.println();
    }

    static private TreeNode prev;
    static private TreeNode head;

    static TreeNode convert_a_BST_to_sorted_circular_doubly_linked_list(TreeNode node) {
        if (node == null)
            return null;
        convert_a_BST_to_sorted_circular_doubly_linked_list(node.left);
        node.left = prev;
        if (prev != null) {
            prev.right = node;
        } else {
            head = node;
        }
        prev = node;
        TreeNode right = node.right;
        head.left = node;
        node.right = head;
        convert_a_BST_to_sorted_circular_doubly_linked_list(right);
        return head;

    }

    //40. Determine the order of letters in a dictionary (graphs/trees)
    static void determine_the_order_of_letters_in_a_dictionary_test() {
        String str = "Jim quickly realized that the beautiful gowns are expensive";
        HashMap<Character, Integer> result = determine_the_order_of_letters_in_a_dictionary(str);
        System.out.println(result.size());
    }

    static HashMap<Character, Integer> determine_the_order_of_letters_in_a_dictionary(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        char[] chrs = str.toLowerCase().toCharArray();
        for (char ch : chrs) {
            int count = 1;
            if (map.containsKey(ch)) {
                count = map.get(ch);
                count++;
            }
            map.put(ch, count);
        }
        return map;
    }

    //****************************************

    //from youtube Teh Problem: You have s sorted array of integers.
    //Write a function that returns a sorted array containing the squares of those integers.

    static void sortedArraySquaresTest() {
        int[] arr = {-2, 1, 3, 6};
        arr = sortedArraySquares(arr); // {1, 4, 9, 36}
    }

    static int[] sortedArraySquares(int[] arr) {   //start from both side of array to item*item
        for (int index = 0; index < arr.length; index++) {
            arr[index] = arr[index] * arr[index];
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        return arr;
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


    static void findAvargeValueOfLeaveOfBSTTest() {
        TreeNode root = new TreeNode(4);         //          4
        root.left = new TreeNode(7);             //      7       9
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(10);        //  10    2        4
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(4);
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        nodeList.add(root);

        travelGivenLevel(nodeList);
        //leave 1: 4 avg 4/1 = 4
        //leave 2: 7, 9 avg (7+9)/2 = 8
        //leave 3: 10, 2, 4 avg (10+2+4)/3 = 5
    }

    static void travelGivenLevel(List<TreeNode> list) {
        if (list.size() == 0) {
            return;
        }

        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        int sum = 0;
        for (int index = 0; index < list.size(); index++) {
            TreeNode node = list.get(index);
            if (node.left != null)
                nodeList.add(node.left);
            if (node.right != null)
                nodeList.add(node.right);
            sum = sum + node.key;
        }
        System.out.println(sum / list.size());
        travelGivenLevel(nodeList);
    }

//Tree


    //#1 InorderTravelTree and GetNextNode
// Facebook remote interviewer by zhouhui at 8/18/2017, 12PM CTS.
// 1. create inorder travel in a given binary tree.
// 2. create function to TreeNode* = getNextNode(TreeNode* root)
//  (a) Inorder (Left, Root, Right) : 4 2 5 1 3
//  (b) Preorder (Root, Left, Right) : 1 2 4 5 3
//  (c) Postorder (Left, Right, Root) : 4 5 2 3 1
//  Breadth First or Level Order Traversal : 1 2 3 4 5
//             1
//		     /   \
//         2       3
//        / \
//      4    5
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

        TreeNode getInorderNext() {
            //left, nood, right
            if (left != null)
                left.getInorderNext();
            System.out.println(key);
            if (right != null)
                right.getInorderNext();
            return this;
        }

        TreeNode getPreorderNext() {
            //nood, left, right
            System.out.println(key);
            if (left != null)
                left.getPreorderNext();
            if (right != null)
                right.getPreorderNext();
            return this;
        }

        TreeNode getPostorderNext() {
            //left, right, nood
            if (left != null)
                left.getPostorderNext();
            if (right != null)
                right.getPostorderNext();
            System.out.println(key);
            return this;
        }

        TreeNode getLeaveorderNext(LinkedList<TreeNode> list) {
            //noods
            if (list.size() == 0)
                return this;
            LinkedList<TreeNode> nodelist = new LinkedList<TreeNode>();
            for (TreeNode node : list) {
                System.out.println(node.key);
                if (node.left != null)
                    nodelist.add(node.left);
                if (node.right != null)
                    nodelist.add(node.right);
            }
            getLeaveorderNext(nodelist);
            return this;
        }
    }

    static void treeIndorderTravelofBSTTest() {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        travel_Inorder_of_BST(root);
        travel_Preorder_of_BST(root);
        travel_Postorder_of_BST(root);
        travel_Leaveorder_of_BST(root);
        return;
    }

    static void travel_Inorder_of_BST(TreeNode root) {
        System.out.println("travel_Inorder_of_BST");
        root.getInorderNext();
    }

    static void travel_Preorder_of_BST(TreeNode root) {
        System.out.println("travel_Preorder_of_BST");
        root.getPreorderNext();
    }

    static void travel_Postorder_of_BST(TreeNode root) {
        System.out.println("travel_Postorder_of_BST");
        root.getPostorderNext();
    }

    static void travel_Leaveorder_of_BST(TreeNode root) {
        System.out.println("travel_Leaveorder_of_BST");
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        root.getLeaveorderNext(list);
    }

///////////////////////////////////////////////////////////
    //youtube foacebook interview question

    static void findLongestSubarrayBySumTest() {
        int[] arr = {1, 2, 3, 7, 5};
        int s = 12;
        arr = findLongestSubarrayBySum(s, arr);
        //{1,3)  1+2+3 = 6 < 12 and 1+2+3 + 7 = 13 > 12
        System.out.println(arr);
    }

    static int[] findLongestSubarrayBySum(int s, int[] arr) {
        int[] result = {0, 0};
        if (arr == null)
            return result;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] > s) {
                if ((i - 1 - start) > (result[1] - result[0])) {
                    result[1] = i - 1;
                    result[0] = start;
                    sum = sum - arr[start];
                    start++;
                }
            } else {
                sum = sum + arr[i];
            }
        }
        result[0] = arr[result[0]];
        result[1] = arr[result[1]];
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

    static void canPartitionTest(){
        int[] arr = new int[]{1, 5, 11, 5};   //Yes {1,5,5} = {11}
        //int[] arr =new int[]{1, 2, 3, 5};   //No
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int leftIndex = 0;
        int rightIndex = arr.length-1;
        int sumLeft = arr[rightIndex];
        int sumRight = arr[leftIndex];;

        while(rightIndex>leftIndex)
        {
            if(sumLeft<sumRight){
                leftIndex++;
                sumLeft = sumLeft + arr[leftIndex];
            }
            if(sumLeft==sumRight){
                System.out.println("Yes");
                return;
            }
            if(sumLeft>sumRight){
                rightIndex--;
                sumRight = sumRight + arr[rightIndex];
            }
        }
        System.out.println("No!");
    }


    //leevcode
    //1. find two number sum in array equal target value

    static void findTwoNumbersSumInArrayEqualTargetValueTest() {
        int[] nums = {2, 7, 11, 15}; //[2, 7]
        int target = 9;

        int[] returns = {0, 0};
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                //System.out.println(nums[nums.get(nums[i])] + "," + nums[i]);
                return;

            } else {
                map.put(target - nums[i], i);
            }
        }
        return;
    }

    //2. Add two link nodes and return the value.
    //input (2->4->3) + (5->6->4)
    // 342+465 = 807
    // ouput (7->0->8)
    static void addTwoLinksTest() {
        TreeNode l1 = new TreeNode(2);
        l1.left = new TreeNode(4);
        l1.left.left = new TreeNode(3);

        TreeNode l2 = new TreeNode(5);
        l2.left = new TreeNode(6);
        l2.left.left = new TreeNode(4);

        List<Integer> list1 = new ArrayList<Integer>();
        while (l1 != null) {
            list1.add(l1.key);
            l1 = l1.left;
        }

        List<Integer> list2 = new ArrayList<Integer>();
        while (l2 != null) {
            list2.add(l2.key);
            l2 = l2.left;
        }
        int index1 = list1.size() - 1;
        int index2 = list2.size() - 1;
        String resurt = "";
        int c = 0;

        while (index1 >= 0 || index2 >= 0) {
            int a = 0;
            int b = 0;

            if (index1 >= 0) {
                a = list1.get(index1);
                index1--;
            }
            if (index2 >= 0) {
                b = list2.get(index2);
                index2--;
            }
            int d = (a + b + c);
            if (d < 10) {
                c = 0;
            }
            if (d == 10) {
                d = 0;
                c = 1;
            }
            if (d > 10) {
                d = d / 10;
                c = 1;
            }

            resurt = d + resurt;

        }

        if (c > 0)
            resurt = c + resurt;
        System.out.println(resurt);
    }

    //4/29/2025 Facebook interview.
    //1.  //34. Find K closest points to origin for a list of points on a plane (search/sort)  4/25/2025 invertview Q1.

    //2. using parrern to check strings.
    // f6k => facebook = true
    // f6yu7k => faccdddyuuuuuuuk = true
    // 6yu7k => faccdddyuuuuuuuk = fasle
    static void using_parrern_to_check_strings_test(){
        //String strPat = "f6k";
        String str = "facebook";

        String strPat = "6k";
        char[] chrs = strPat.toCharArray();
        List<Integer> list = new ArrayList<>();

        int strlength = 0;
        int numlength = 0;
        for(int index = 0; index < chrs.length; index++){
            if((chrs[index]>='0')&&(chrs[index]<='9')){
                numlength++;
                list.add(numlength);
                System.out.println("numlength=" + numlength);
                strlength = 0;
            }
            else
            {
                strlength++;
                list.add(strlength);
                System.out.println("strlength=" + strlength);
                numlength=0;
            }
        }
        list.add(strlength);

        int lenStr = 0;
        int lenNo = 0;
        int startIndex = 0;
        String strP = "";
        String strN = "";
        int startCheckIndex = 0;
        for(int index = 0; index<list.size(); index=index+2){
            lenStr = list.get(index);
            strP = strPat.substring(startIndex, startIndex + lenStr);
            String strCheck = str.substring(startCheckIndex, startCheckIndex + lenStr);
            if(!strP.equals(strCheck)){
                System.out.println(
                        "NO=>" + strCheck + "=" + strP);
                return;
            }
            System.out.println("strP=" + strP);
            startIndex = startIndex + lenStr;
            startCheckIndex = startCheckIndex + lenStr;
            if((index+1)<(list.size()-1)){
                lenStr = list.get(index+1);
                strP = strPat.substring(startIndex, startIndex + lenStr);
                System.out.println("strN=" + strP );
                startIndex = startIndex + lenStr;
                startCheckIndex = startCheckIndex
                        + Integer.valueOf(strP);
            }

        }
        System.out.println("YES");

    }



    //5/10/2021 Facebook interview.
    //1. Get Binary Search Tree max summary of leaves.
    static void get_binary_search_tree_max_summary_of_leaves_test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        //max sum in leave 2 => 5+7 =12
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int max = get_binary_search_tree_max_summary_of_leaves(list);
        System.out.println(max);
    }

    //recursions each leave summary
    static int get_binary_search_tree_max_summary_of_leaves(List<TreeNode> nodelist){
        int sum = 0;
        List<TreeNode> subnodelist = new ArrayList<TreeNode>();
        for(TreeNode node : nodelist){
            if(node.left!=null)
                subnodelist.add(node.left);
            if(node.right!=null)
                subnodelist.add(node.right);
            sum += node.key;
        }
        if(subnodelist.size()>0)
            sum = Math.max(sum, get_binary_search_tree_max_summary_of_leaves(subnodelist));

        return sum;
    }



    //2. Check words if sorted as char array.
    // String[] words = {"Check", "words", "if", "sorted"};
    // char[] alph = {'c', 'w', 'i', 's'}

    static void check_words_if_sorted_as_char_array_Test(){
        int a = '0';
        String[] words = {"Check", "words", "if", "sorted"};
        System.out.println(Arrays.toString(words));

        for(String word : words){
            int ichr = word.charAt(0);
            if(ichr<a){
                System.out.println("unsorted " + word);
                return;
            }
            else
                a = ichr;
        }
        System.out.println("sorted");

    }


/*
We are working on a security system for a badged-access room in our company's building.

Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:

1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)

Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.


*/
    static void mismatchesTest() {
        //All Test Cases:

        String[][] records1 = {
            {"Paul", "enter"},
            {"Pauline", "exit"},
            {"Paul", "enter"},
            {"Paul", "exit"},
            {"Martha", "exit"},
            {"Joe", "enter"},
            {"Martha", "enter"},
            {"Steve", "enter"},
            {"Martha", "exit"},
            {"Jennifer", "enter"},
            {"Joe", "enter"},
            {"Curtis", "exit"},
            {"Curtis", "enter"},
            {"Joe", "exit"},
            {"Martha", "enter"},
            {"Martha", "exit"},
            {"Jennifer", "exit"},
            {"Joe", "enter"},
            {"Joe", "enter"},
            {"Martha", "exit"},
            {"Joe", "exit"},
            {"Joe", "exit"},
        };
    
        String[][] records2 = {
            {"Paul", "enter"},
            {"Paul", "exit"},
        };
    
        String[][] records3 = {
            {"Paul", "enter"},
            {"Paul", "enter"},
            {"Paul", "exit"},
            {"Paul", "exit"},
        };
    
        String[][] records4 = {
            {"Raj", "enter"},
            {"Paul", "enter"},
            {"Paul", "exit"},
            {"Paul", "exit"},
            {"Paul", "enter"},
            {"Raj", "enter"},
        };
        // misExit, misEnter

        String[][] mismatches = records1;
        // => ["Paul", "Martha", "Pauline", "Curtis", "Joe"]
//    mismatches = records2; // => []
//    mismatches = records3; // => ["Paul"]
//    mismatches = records4; // => ["Raj", ["Paul"]

        HashSet<String> misList = new HashSet<>();
        HashMap<String, String> maps = new HashMap<>();
        for(String[] record : mismatches){
            String name = record[0];
            String action = record[1];
            if(maps.containsKey(name)){
                String preAction = maps.get(name);
                if(!preAction.equals(action)){
                    maps.put(name, action);
                }
                else {
                    if(!misList.contains(name))
                        misList.add(name);
                }
            }
            else {
                if(action.equals("exit")){
                    if(!misList.contains(name))
                        misList.add(name);
                }
                if(action.equals("enter")){
                    maps.put(name, action);
                }
            }
        }
        System.out.println(misList.toString());

    }
    //meta interview perpared video mock sample
    //find triplets (summary zero) in array
    public static void interview_mock_test(){

        int[] arrs = new int[]{3, 5, 8, 10, -9, -11};
        findTriplets(arrs);
        arrs = new int[]{3, 5, 4, 9, -16};
        findTriplets(arrs);
        arrs = new int[]{0,1,-1,2,-2};
        findTriplets(arrs);
    }

    //meta interview perpared video mock sample
    public static void findTriplets(int arr[]){
        boolean found = false;
        int n = arr.length;

        System.out.println(Arrays.toString(arr));
        // sort array elements
        Arrays.sort(arr);

        for(int i=0; i < n-1; i++ ){
            int l = i + 1;
            int r = n-1;
            int x = arr[i];
            while(l<r){
                int sum = x + arr[l] + arr[r];
                if(sum==0){ //found it
                    System.out.println(x + "," + arr[l] + "," + arr[r]);
                    l++;
                    r--;
                    found = true;
                }
                else if(sum < 0){
                    l++;
                }
                else {
                    r--;
                }
            }
        }
        if (found == false)
            System.out.println(" No Triplet Found");
    }

}
