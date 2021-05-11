import javax.swing.*;
import java.util.*;

public class Facebook {

    //****************************************
    //https://www.educative.io/blog/cracking-top-facebook-coding-interview-questions

    //Array
    //1. Given an integer array, move all elements that are equal to 0 to the left while maintaining the order of other elements in the array
    static void  MoveZerosLeft_test(){
        int arr[] = {1, 10, 20, 0, 59, 63, 0, 88, 0};
        System.out.println(arr);
        MoveZerosLeft(arr);  //{0, 0, 0, 1, 10, 20, 59, 63, 88}
        System.out.println(arr);
    }
    static void MoveZerosLeft(int[] arr){
        int wrightIndex = 0;
        for(int i = 0; i<arr.length; i++){
            int data = arr[arr.length-1-i]; //get each data from end to begin
            if(data!=0){  //data is 0 not move data. go next move data
                if(i > wrightIndex){   //if data != move this data to write location
                    arr[arr.length - 1 - wrightIndex] = data;
                    arr[arr.length-1-i] = 0;  //read locatin fill zero.
                }
                wrightIndex++;  //wirte next
            }
        }
    }

    //2: Merge overlapping intervals
    static void merge_overlapping_intervals_test(){
        int[][] arrs = {{1,5},{3,7},{4,6},{6,8},{10,12},{11,15}};
        List<int[]> list =  mergeIntervals(arrs); // list = {{1, 8},{10, 15}}
        System.out.println(list.toArray()); //check each interval with result interval. if overlap change result interval
    }
    static List<int[]> mergeIntervals(int[][] arrs) {
        List<int[]> list = new ArrayList<>();
        int[] intv = new int[2];
        for(int[] newIntv : arrs){
            if(intv[0]==intv[1]) {
                intv = newIntv;
                continue;
            }

            if(intv[1] < newIntv[0]) { //no over lap
                list.add(intv);
                intv = newIntv;
                continue;
            }
            else {
                if(newIntv[1]>intv[1])
                    intv[1] = newIntv[1];
            }
        }
        list.add(intv);
        return list;
    }

    //linked lists
    //3. Add two integers
    static void add_two_integers_test(){
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(0);
        list1.next.next = new ListNode(9);
        list1.next.next.next = new ListNode(9);
        ListNode list2 = new ListNode(7);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(2);
        ListNode list3 = add_two_integers(list1,list2);
        // 1->0->9->9 + 7->3->2 => 8->3->1->0->1
        System.out.println(list3);
    };
    static ListNode add_two_integers(ListNode list1, ListNode list2) {
        int carry= 0;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while((list1!=null)||(list2!=null)||(carry!=0)){
            int first = (list1==null)?0:list1.val;
            int second = (list2==null)?0:list2.val;
            int total = first + second + carry;
            carry = total/10;
            total = total- 10*carry;
            prev.next = new ListNode(total);
            prev = prev.next;
            if(list1!=null)
                list1 = list1.next;
            if(list2!=null)
                list2 = list2.next;
        }
        return dummy.next;
    }
    //4. Merge two sorted linked lists
    static void merge_two_sorted_linked_lists(){
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(8);
        head1.next.next = new ListNode(15);
        head1.next.next.next = new ListNode(19);    //4->8->15->19
        ListNode head2 = new ListNode(7);           //7->9->10->16
        head2.next = new ListNode(9);     //merage: 4->7-8->9->10->15->16->19
        head2.next.next = new ListNode(10);
        head2.next.next.next = new ListNode(16);
        ListNode head3 =  merge_two_sorted_linked(head1, head2);
        System.out.println(head3);
    }

    public static ListNode merge_two_sorted_linked(ListNode head1, ListNode head2) {
        ListNode dumyList = new ListNode(0);
        ListNode curr = dumyList;
        List<ListNode> list = new ArrayList<>();
        while((head1!=null)||(head2!=null)){
            if((head1!=null)&&(head2!=null)){
                if(head1.val<head2.val){
                    curr.next = head1;
                    if(head1!=null)
                        head1 = head1.next;
                }
                else{
                    curr.next = head2;
                    if(head2!=null)
                        head2 = head2.next;

                }


            }
            else if(head1!=null){
                curr.next = head1;
                if(head1!=null)
                    head1 = head1.next;
            }
            else if(head2!=null){
                curr.next = head2;
                if(head2!=null)
                    head2 = head2.next;
            }
            curr = curr.next;
        }
        return dumyList.next;
    }

    //5. Convert binary tree to doubly linked
    //  Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

    static void Convert_binary_tree_to_doubly_linked_test(){ //best search tree B
        TreeNode root = new TreeNode(4);             //              4
        root.left = new TreeNode(2);                 //           /     \
        root.left.left = new TreeNode(1);            //        2          5
        root.left.right = new TreeNode(3);           //     /     \
        root.right = new TreeNode(5);                //  1          3
                                                        // double linked list
                                                        // head-> = 1 = 2 = 3 = 4 = 5 =
                                                        //       ||                  ||
                                                        //        =====================
        TreeNode node = convert_binary_tree_to_doubly_linked(root);
        System.out.println();
    }

    static TreeNode convert_binary_tree_to_doubly_linked(TreeNode root) {
        if(root == null)
            return null;
        TreeNode dumyHead = new TreeNode(0);
        TreeNode head = dumyHead, pre = dumyHead;
        List<TreeNode> list = new ArrayList<>();
        travel_Inorder_of_BST(root, list);
        for(TreeNode node : list){
            head.right = node;
            node.left = head;
            head = node;
        }
        head.right = dumyHead.right;   // make circular double linker
        dumyHead.right.left = head;    //
        return dumyHead.right;
    }

    static void travel_Preorder_of_BST(TreeNode node, List<TreeNode> list) {
        if(node==null)    // using internal trave: recurse tree root:  root.left -> root -> root.right
            return;
        list.add(node);
        travel_Preorder_of_BST(node.left, list);
        travel_Preorder_of_BST(node.right, list);
        return;
    }

    static void travel_Inorder_of_BST(TreeNode node, List<TreeNode> list) {
        if(node==null)    // using internal trave: recurse tree root:  root.left -> root -> root.right
            return;
        travel_Inorder_of_BST(node.left, list);
        list.add(node);
        travel_Inorder_of_BST(node.right, list);
        return;
    }

    static void travel_Postorder_of_BST(TreeNode node, List<TreeNode> list) {
        if(node==null)    // using internal trave: recurse tree root:  root.left -> root -> root.right
            return;
        travel_Postorder_of_BST(node.left, list);
        list.add(node);
        travel_Postorder_of_BST(node.right, list);
        return;
    }

    //6. Trees: Level order traversal of binary tree
    static void level_order_traversal_of_binary_tree_test(){
        TreeNode root = new TreeNode(1);         //              1
        root.left= new TreeNode(2);              //       2            3
        root.right= new TreeNode(3);             //   4       5
        root.left.left= new TreeNode(4);
        root.left.right= new TreeNode(5);

        level_order_traversal_of_binary_tree(root);
        //{1, 2, 3, 4, 5}

    }

    static void level_order_traversal_of_binary_tree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (list.size()>0){
            list  = travelGivenLevel(list);
        }
        System.out.println();
    }

    static List<TreeNode> travelGivenLevel(List<TreeNode> list){
        List<TreeNode> list1 = new ArrayList<>();
        for(TreeNode node : list){
            System.out.println(node.key);
            if(node.left!=null)
                list1.add(node.left);
            if(node.right!=null)
                list1.add(node.right);
        }
        return list1;
    }

    //7. Strings: Reverse words in a sentence
    static void reverse_words_in_a_sentence_test(){
        String str = reverse_words_in_a_sentence("Hello World !");  //"! World Hello"
        System.out.println(str);    // splite  " " then reverse
    }
    static String reverse_words_in_a_sentence(String str){
        String[] strWords = str.split(" ");
        String strRet = "";
        for(int i = 0; i < strWords.length; i++){
            if(i==0)
                strRet = strWords[strWords.length-1 - i];
            else
                strRet = strRet + " " + strWords[strWords.length-1 - i];
        }
        return strRet;
    }

    //8. Strings: String segmentation
    static void string_segmetatio_test(){
        String s = "hellonow";          //?? words char by char, seach new string in set, Recursion seach next paration
        Set <String> direcotry = new HashSet<>();
        direcotry.add("hello");
        direcotry.add("hell");
        direcotry.add("on");
        direcotry.add("now");
        string_segmetatio(s, direcotry);  //{"now", "Hello"}
    }

    static boolean string_segmetatio(String s, Set <String> dictionary){
        for (int i = 1; i <= s.length(); ++i) {
            String first = s.substring(0, i);
            if (dictionary.contains(first)) {  //found new string in word collection
                String second = s.substring(i);   // get other part of sentence
                if (second == null || second.length() == 0) {  //other part sentence empy
                    return true;
                }
                if(dictionary.contains(second))    //other part of sentence contain word in words.
                    return true;
                if(string_segmetatio(second, dictionary)) //recuraton to find other part of sentence contain word in words.
                    return true;
            }
        }
        return false;
    }

    //9. Dynamic Programming: Find maximum single sell profit
    static void findBuySellStockPrices_test(){
        int[] prices = {8, 5, 12, 9, 19, 1};
        int[] result = findBuySellStockPrices(prices);
        int[] prices1 = {21, 12, 11, 9, 6, 3};
        result = findBuySellStockPrices(prices1);
        System.out.println(result);
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

        return result;
    }


    //10. Math and Stats: Calculate the power of a number
    static void calculate_the_power_of_a_number_test(){
        calculate_the_power_of_a_number(2, 5);
        calculate_the_power_of_a_number(2, -2);
    }
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


    //11 Backtracking: Find all possible subsets
    static int N = 5;
    static int M = 3;
    static int[] a = {1,2,3,4,5};
    static int[] b = new int[M];

    static void C(int m, int n){
        int i, j;
        for(i=n; i<=m; i++){
            b[n-1] = i-1;
            if(n>1)
                C(i-1, n-1);
            else {
                for(j=0; j<=M; j++)
                    System.out.print(a[b[j]] + " ") ;
                System.out.println();
            }

        }
    }

    static void get_all_subsets_test(){

        //C(N,M);

        int[] myints = {8,13,3,22, 17, 39, 87, 45, 36};
        List<Integer> v = new ArrayList<>();
        for (int i : myints) {
            v.add(i);
        }
        List<HashSet<Integer>> subsets = new ArrayList<>();
        get_all_subsets(v, subsets);
        System.out.println();
    }
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

    //12 Clone a Directed Graph
    static void clone_a_directed_graph(){

    }

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

    //13. Design: Serialize / deserialize binary tree

    static void serialize_deserialize_binary_tree_test(){
        TreeNode root = new TreeNode(1);        //              1
        root.left= new TreeNode(2);             //      2               3
        root.right= new TreeNode(3);            //  4       5
        root.left.left= new TreeNode(4);        //
        root.left.right= new TreeNode(5);       //

        List<TreeNode> selist =serialize_deserialize_binary_tree(root);  // convert tree to double link tree (no circie)
            // inorder travel: 4->2->5->1->3
        //TreeNode deroot = deserialize_deserialize_binary_tree(selist);
        System.out.println();
    }

    static List<TreeNode> serialize_deserialize_binary_tree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        travel_Inorder_of_BST(root, list);
        return list;
    }

    static TreeNode deserialize_deserialize_binary_tree(List<TreeNode> list) {
        if(list==null)
            return null;

        TreeNode root = null;

        for(TreeNode node : list){
            if(root==null)
                root = node;
            else{
                if(root.key==node.left.key){
                    TreeNode temp = root;
                    root = node;
                    root.left = temp;
                }
                else{
                    root.right = node;
                }
            }
        }

        return root;
    }

    //sorting and searching
    //14 Search rotated Sorted array
    static void Search_rotated_array_test(){
        int[] arr = {4,5,6,7,0,1,2,3};  //change point index -
        int key =3;
        int key1 = Search_rotated_array(arr, key);
        System.out.println(key1);
    }

    static int findDividIndex(int[]arr){
        int from = 0;
        int to = arr.length-1;
        while(from<to){   //from and to cloing to cut point in both said
            int mid = from + (to-from)/2;
            if(arr[mid]>arr[to]){
                from = from +1;
            }
            else {
                to = mid;
            }
        }
        return from;
    }

    static int Search_rotated_array(int[] arr, int key){
        int index = findDividIndex(arr);
        int from = 0;
        int to = 0;
        int mid = 0;
        if(key>=arr[0]){
            from = 0;
            to = index-1;

        }
        else {
            from = index;
            to = arr.length-1;
        }

        while(from<to){
            mid = from + (to-from)/2;
            if(key==arr[mid])
                return mid;
            if(key < arr[mid])
                to = mid-1;
            else
                from = mid+1;
            if(key == arr[from])
                return from;
            if(key == arr[to])
                return to;
            if(to-from<=1)
                return -1;
        }
        return -1;

    }

    // 15  Sorting and Searching: Find the high and low index
    //Given a sorted array of integers, return the low and high index of the given key>. You must return -1 if the indexes are not found.
    static void find_the_high_and_low_index_test(){
        int[] arr1 = {1, 2, 3, 3, 3, 3, 5, 5, 5, 5, 20};
        System.out.println(arr1);
        int[] arr2 = find_the_high_and_low_index(arr1, 5); // [6, 9]
        arr2 = find_the_high_and_low_index(arr1, 3); // [2, 5]
        System.out.println(arr2);
    }

    static int[] find_the_high_and_low_index(int[] arr, int key){
        int[] index = {0, arr.length-1};
        boolean bK = false;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                if(bK==false){
                    index[0] = i;
                    bK = true;
                }
            }
            else{
                if(bK==true){
                    index[1] = i-1;
                    break;
                }
            }
        }

        return index;

    /*
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

     */
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


    //16 Longest increasing subsequence from array of integers (dynamic programming arrays)
    static void longest_increasing_subsequence_from_array_of_integers_test(){
        int[] arr = {0,2,1,7,8,9,10,4};
        int max = longest_increasing_subsequence_from_array_of_integers(arr);
        System.out.println(max);
    }
    static int longest_increasing_subsequence_from_array_of_integers(int[] arr){
        int iVal = 0;
        int start = 0;
        int sample = 0;
        int maxLength = 0;
        for(int i = 0; i < arr.length; i++){
            if(i==0){
                start = 0;
            }
            else{
                if(arr[i] > sample){
                    if((i-start)>maxLength)
                        maxLength = i-start;
                }
                else{
                    sample = arr[i];
                    start = i;
                }
            }
            sample = arr[i];
        }
        return maxLength;
    }

    //17. Unique paths in a grid (dynamic programming matrices)
    static void unique_paths_in_a_grid_test(){
        int steps = unique_paths_in_a_grid(6, 4); // 6x4 grid, 0=>56 steps
        System.out.println("7x6 grid steps: " + steps);
    }

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

    //18. Add two numbers as a list (lists)
    static void add_two_numbers_as_a_list_test(){
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        l1.add(4);
        l1.add(3);
        List<Integer> l2 = new ArrayList<>();
        l2.add(5);
        l2.add(6);
        l2.add(4);
        List<Integer> l3 = add_two_numbers_as_a_list(l1, l2);
        System.out.println(l3.toArray());
    };

    static List<Integer> add_two_numbers_as_a_list(List<Integer> l1, List<Integer> l2){
        List<Integer> list = new ArrayList<>();
        int l1n = 0;
        int l2n = 0;
        int carry = 0;
        int sum = 0;

        while(l1.size()>0||l2.size()>0){
            l1n = 0;
            if(l1.size()>0)
                l1n = l1.remove(0);
            l2n = 0;
            if(l2.size()>0)
                l2n = l2.remove(0);
            sum = l1n + l2n + carry;
            carry = sum/10;
            sum = sum - carry*10;
            list.add(sum);
        }
        if(carry>0)
            list.add(carry);

        return list;
    }

    //21. Rotate a matrix (arrays)
    static void rotate_a_matrix_test(){
        int[][] arrs1= {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] arrs2= rotate_a_matrix(arrs1, 3, 4); //{{12,11,10,9},{8,7,6,5},{4,3,2,1}};
        System.out.println(arrs2);
    }

    static int[][] rotate_a_matrix(int[][] arrs, int m, int n){
        int[][] result = new int[m][n]; //create new int[][] for convert result
        for(int x = 0; x<m; x++){
            for(int y = 0; y<n; y++){
                result[x][y] = arrs[m-1-x][n-1-y];
            }
        }
        return result;
    }

    //24. Find nth Fibonacci number (number theory)
    static void find_nth_Fibonacci_number_test(){
        int fib = find_nth_Fibonacci_number(6);
        System.out.println(fib);  // fib(6) = fib(5) + fib(4) + fib(3) + fib(2) + 1 + 0 = 8
    };

    static int find_nth_Fibonacci_number(int n){
        if(n<2)
            return n;
        return find_nth_Fibonacci_number(n-1) + find_nth_Fibonacci_number(n-2);
    }

    //25. Find the square root of an integer using binary search (math search answer)
    static void find_the_square_root_of_an_integer_using_binary_search_test(){
        int x= find_the_square_root_of_an_integer_using_binary_search(9);
        System.out.println(x);
    };
    static int find_the_square_root_of_an_integer_using_binary_search(int n){
        if(n<2)
            return n;

        double left = 0;
        double right = n;
        double epsilon = 0.000001;
        double mid = 0.0;
        double sqr = 0.0;

        while(true){
            mid = left + (right-left)/2.0;

            sqr = mid * mid;
            if((sqr<=n+epsilon)&&(sqr>=n-epsilon))
                return (int)mid;

            if(sqr>n+epsilon){
                right = mid;
            }
            else{
                left = mid;
            }

        }
    }

    //26 Implement StrStr (string search)
    static void strStr_test(){
        String str1 = "Hello World";
        String str = "Wo";
        int istr = strStr1(str1, str);
        boolean bstr = strStr(str1, str);
        System.out.println(String.valueOf(istr) + " " + String.valueOf(bstr));
    };

    static boolean strStr(String str1, String str){
        return str1.contains(str);
    }

    static int strStr1(String str1, String str){
        return str1.indexOf(str);
    }

    //27 Minimum appends for Palindrome (strings) // string is same as string reverse  Malayal and Malayalam
    static void minimum_appends_for_Palindrome_test(){  //"Malayal" => "Malayalam" =
        String str = minimum_appends_for_Palindrome("Malayal");
        System.out.println(str);
    }

    static String minimum_appends_for_Palindrome(String str){

        String str1 = new StringBuffer(str).reverse().toString();
        if(str1==str)
            return str;
        for(int i = 1; i<str.length(); i++){
            String strSub = str.substring(0,i);
            String strSub1 = new StringBuffer(strSub).reverse().toString();
            String str2 = str + strSub1;
            String str3 = strSub+str1;
            if(str2.equals(str3))
                return str2;
        }

        return str;
    }

    //28. Find the largest rectangle in a histogram (stacks)
    static void find_the_largest_rectangle_in_a_histogram_test(){
        int[] histogram = {2,1,5,6,2,3};

        int area = find_the_largest_rectangle_in_a_histogram(histogram); //return 10.;
        System.out.println(area);
    };
    static int find_the_largest_rectangle_in_a_histogram(int[] height){
        //??????????????????????????????????????????????????O(n^2). ??????LeetCode?????????????????????????????(Pruning)??????height[k] >= height[k - 1]??????????????height[k]?????height[k - 1]???????????????????????????height[k] < height[k - 1]?k????k - 1????????????????????
        //————————————————
        //????????CSDN???Alf?????????CC 4.0 BY-SA?????????????????????
        //?????https://blog.csdn.net/abcbc/article/details/8943485

        // Start typing your Java solution below
        // DO NOT write main() function
        int maxArea = 0;

        int start = 0;
        for(int k = 1; k<height.length; k++){
            if(height[k]<height[k-1]){
                start = k-1;
                break;
            }
        }

        for(int i = start; i < height.length; i++){
            int area = 0;
            int high = height[i];
            for(int j = start; j < height.length; j++){
                if(high>height[j]){

                    if(j>i)
                        break;
                    else
                        area = 0;
                }
                else{
                    area = area + high;
                }
            }
            if(area>maxArea)
                maxArea = area;
        }

        return maxArea;
    }

    static int find_the_largest_rectangle_in_a_histogram1(int[] height){
        //??????????????????????????????????????????????????O(n^2). ??????LeetCode?????????????????????????????(Pruning)??????height[k] >= height[k - 1]??????????????height[k]?????height[k - 1]???????????????????????????height[k] < height[k - 1]?k????k - 1????????????????????
        //————————————————
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
    static void substring_concatenation_test(){
        String S = "barfoothefoobarman";
        String[] L = {"foo", "bar"};
        List<Integer> iCon = substring_concatenation(S, L);   //barfoo at 0 foobar at 9
        System.out.println(iCon.toArray());
        S = "barfoofoothefoobarman";
        iCon = substring_concatenation(S, L);
        System.out.println(iCon.toArray());
    }

    static List<Integer> substring_concatenation(String s, String[] words){
        if (s.length()==0 || words.length==0)
            return null;

        List<Integer> res = new ArrayList<>();
        int n = words.length, len = words[0].length();
        HashMap<String, Integer> wordCnt = new HashMap<>();
        for (String word : words)
            wordCnt.put(word, 1);

        int i = 0;
        int start = 0;
        while( i < s.length()) {
            String t = s.substring(i, i+len);
            Set<String> set1 = new HashSet<>();
            if(wordCnt.containsKey(t)){
                set1.add(t);
                start = i;
                i = i + len;
                for(int j = 1; j < n; j++){
                    String t1 = s.substring(i, i+len);
                    i = i + len;
                    if(wordCnt.containsKey(t1)){
                        set1.add(t1);                     }
                    else{
                        start = 0;
                        break;
                    }
                }
                if(set1.size()==n){
                    res.add(start);
                }
                else{
                    start = 0;
                }
            }
            else
                i = i + len;
            System.out.println();
        }
        return res;
    }

    //30. Find the least common ancestor (tree search)
    static void find_the_least_common_ancestor_test(){
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
    };

    static int find_the_least_common_ancestor(TreeNode root, int v1, int v2){
        // Write your code here.

        if ((v1 < root.key) && (v2 < root.key))
            return find_the_least_common_ancestor(root.left, v1, v2);
        if ((v1 > root.key) && (v2 > root.key))
            return find_the_least_common_ancestor(root.right, v1, v2);
        return root.key;  //if ((v1 < root.data) && (v2 > root.data))
    }

    //31. Find largest distance between nodes in a tree (DFS)
    static void find_largest_distance_between_nodes_in_a_tree_test(){
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


    };

    static int find_largest_distance_between_nodes_in_a_tree(TreeNode root, int v1){
        if(root.key==v1)
            return 0;
        if(v1<root.key)
            return find_largest_distance_between_nodes_in_a_tree(root.left, v1) + 1;
        else
            return find_largest_distance_between_nodes_in_a_tree(root.right, v1) + 1;
    }

    static int find_largest_distance_between_nodes_in_a_tree(TreeNode root, int v1, int v2){
        if(root==null)
            return 0;

        if((root.key>v1) && (root.key>v2))
            return find_largest_distance_between_nodes_in_a_tree(root.left, v1, v2) ;

        if((root.key<v1) && (root.key<v2))
            return find_largest_distance_between_nodes_in_a_tree(root.right, v1, v2);

        if((root.key>=v1) && (root.key<=v2))
            return find_largest_distance_between_nodes_in_a_tree(root, v1) +
                find_largest_distance_between_nodes_in_a_tree(root, v2);
        return -1;
    }


    //32. Find all unique triplets in an array, giving sum of zero (array)
    static void find_all_unique_triplets_in_an_array_test(){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<int[]> results = find_all_unique_triplets_in_an_array(nums);// {{-1, 0, 1}, {-1, -1, 2}}
        System.out.println();
    };

    static List<int[]> find_all_unique_triplets_in_an_array(int[] arrs) {
        Arrays.sort(arrs);
        List<int[]> results = new LinkedList<>();
        for(int i = 0; i < arrs.length-2; i++){
            int front = i + 1;
            int end = arrs.length-1;
            int target = -arrs[i];
            int[] result = {0,0,0};
            while(end>front){
                int sum = arrs[front] + arrs[end];
                if(target>sum) {
                    //if smaller abs target number > range of sum(front, end) move target number to next
                    front++;
                }
                else if(target==sum) {
                    //if smaller abs target number = range of sum(front, end) the target, start, end are right number
                    result[0] = arrs[i];
                    result[1] = arrs[front];
                    result[2] = arrs[end];
                    results.add(result);
                    break;
                }
                else{
                    //if smaller abs target number < range of sum(front, end) move end back
                    end--;
                }
            }
        }
        return results;
    }

    //33. Find maximum path sum in non-empty binary tree
    static void find_maximum_path_sum_in_non_empty_binary_tree_test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int[] maxPath = {Integer.MIN_VALUE};
        find_maximum_path_sum_in_non_empty_binary_tree(root, maxPath);
        System.out.println(maxPath);
    }

    static int find_maximum_path_sum_in_non_empty_binary_tree(TreeNode root, int[] maxPath){

        if(root==null){
            return 0;
        }

        int leftSum = find_maximum_path_sum_in_non_empty_binary_tree(root.left, maxPath);
        int rightSum = find_maximum_path_sum_in_non_empty_binary_tree(root.right, maxPath);
        int current = Math.max( root.key, Math.max( leftSum + root.key, rightSum + root.key));
        maxPath[0] = Math.max(maxPath[0], leftSum + root.key+ rightSum);
        return current;
    }

    //34. Find K closest points to origin for a list of points on a plane (search/sort)
    static void find_K_closest_points_to_origin_for_a_list_of_points_on_a_plane_test() {
        int[][] points = {{1, 4}, {7, 5}, {-1, 3}, {5, 1}, {3, -2}, {5, 0}};
        int[][] points1 = find_K_closest_points_to_origin_for_a_list_of_points_on_a_plane(points, 2);
        System.out.println();
    }

    static int[][] find_K_closest_points_to_origin_for_a_list_of_points_on_a_plane(int[][] points, int K){
        int[][] result = new int[K][];

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // coeded compare interface function distance X1*X1+Y1*Y1-X0*X0+Y0*Y0 to compare each pair point o1,o2
                return (o1[1]*o1[1]+o1[0]*o1[0])-(o2[1]*o2[1]+o2[0]*o2[0]);
            }
        });

        for(int i=0; i<K; i++) {
            result[i] = points[i];
        };
        return result;
    }

    //35. Write a function to compute intersection of arrays (sort/search)
    static void compute_intersection_of_arrays_test(){
        int[] arr1 = {1,2,3};
        int[] arr2 = {5,3,2};
        Set<Integer> set = compute_intersection_of_arrays(arr1, arr2);  //{3,2}
    };

    static Set<Integer> compute_intersection_of_arrays(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>();   //first put arr1 to Set1 for compare
        Set<Integer> set2 = new HashSet<>();   //loop arr2 to compare Set1, same i add to Set2

        for(int i : arr1)
            set1.add(i);

        for(int j : arr2)
            if(set1.contains(j))
                set2.add(j);
        return set2;
    }

    //38. Group anagrams together in an array of strings (arrays/strings)
    static void group_anagrams_together_in_an_array_of_strings_test(){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        HashMap<String, List<String>> strs1 = group_anagrams_together_in_an_array_of_strings(strs);
        //{{"eat", "tea", "ate"}, {"tan","nat"}, {"bat"}}
    }

    static HashMap<String, List<String>>  group_anagrams_together_in_an_array_of_strings(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){               //convert each string to sorted chrs string as map key ot group same key strings.
            char[] chrs = str.toCharArray();
            Arrays.sort(chrs);
            String str1 = String.valueOf(chrs);
            List<String> list1;
            if(map.containsKey(str1)){
                list1 = map.get(str1);
            }
            else {
                list1 = new ArrayList<>();
            }
            list1.add(str);
            map.put(str1, list1);
        }
        return map;
    }

    //39. Convert a BST to sorted circular doubly linked list (trees)
    static void convert_a_BST_to_sorted_circular_doubly_linked_list_test(){
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
        TreeNode node = convert_binary_tree_to_doubly_linked(root);
        System.out.println();
    }
/*
    static private TreeNode prev;
    static private TreeNode head;
    static TreeNode convert_a_BST_to_sorted_circular_doubly_linked_list(TreeNode node){
        if(node == null)
            return null;
        convert_a_BST_to_sorted_circular_doubly_linked_list(node.left);
        node.left = prev;
        if(prev != null) {
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
*/
    //40. Determine the order of letters in a dictionary (graphs/trees)
    static void determine_the_order_of_letters_in_a_dictionary_test(){
        String str = "Jim quickly realized that the beautiful gowns are expensive";
        HashMap<Character , Integer> result = determine_the_order_of_letters_in_a_dictionary(str);
        System.out.println(result.size());
    }

    static HashMap<Character , Integer>  determine_the_order_of_letters_in_a_dictionary(String str){
        HashMap<Character, Integer> map = new HashMap<>();

        char[] chrs = str.toLowerCase().toCharArray();
        for(char ch : chrs){
            int count = 1;
            if(map.containsKey(ch)){
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

    static void sortedArraySquaresTest(){
        int[] arr = {-2, 1, 3, 6};
        arr = sortedArraySquares(arr); // {1, 4, 9, 36}
    }

    static int[] sortedArraySquares(int[] arr){   //start from both side of array to item*item
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

//#1 InorderTravelTree and GetNextNode
// Facebook remote interviewer by zhouhui at 8/18/2017, 12PM CTS.

//(a) Inorder (Left, Root, Right) : 4 2 5 1 3
//(b) Preorder (Root, Left, Right) : 1 2 4 5 3
//(c) Postorder (Left, Right, Root) : 4 5 2 3 1
// Breadth First or Level Order Traversal : 1 2 3 4 5

//             1
//		     /   \
//         2       3
//        / \
//      4    5
//


    static void findAvargeValueOfLeaveOfBSTTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(7);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(4);
        findAvargeValueOfLeaveOfBST(root);
        //leave 1: 4 avg 4/1 = 4
        //leave 2: 7, 9 avg (7+9)/2 = 8
        //leave 3: 10, 2, 4 avg (10+2+4)/3 = 5
    }
    static List<Integer> findAvargeValueOfLeaveOfBST(TreeNode root){
        List<Integer> avgs = new ArrayList<>();
        int avg = 0;
        level_order_traversal_of_binary_tree(root);
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (list.size()>0){
            avg = 0;
            for(TreeNode node : list){
               avg = avg + node.key;
            }
            avg = avg/list.size();
            avgs.add(avg);
            System.out.println(avg);
            list  = travelGivenLevel(list);
        }
        return avgs;
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
        List<TreeNode> list = new ArrayList<>();
        travel_Inorder_of_BST(root, list);
        TreeNode nextNode = getNextNode(root.left);
        return;
    }

    static TreeNode getNextNode(TreeNode node){
        if(node!=null)  //current node
            return node.right;   //Inoder: node.lert->node->node.right
        return null;
    };

    //youtube foacebook interview question

    static void findLongestSubarrayBySumTest(){
        int[] arr = {1, 2, 3, 7, 5};
        int s = 12;
        arr = findLongestSubarrayBySum(s, arr);
        //{1,3)  1+2+3 = 6 < 12 and 1+2+3 + 7 = 13 > 12
        System.out.println(arr);
    }

    static int[] findLongestSubarrayBySum(int s, int[] arr) {
        int[] result = {0, 0};
        if(arr==null)
            return result;
        int sum = 0;
        int start = 0;
        for(int i = 0; i<arr.length; i++){
            if(sum + arr[i]>s){
                if((i-1-start)>(result[1]-result[0])){
                    result[1] = i-1;
                    result[0] = start;
                    sum = sum - arr[start];
                    start++;
                }
            }
            else{
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
        int[] arr = new int[]{1, 5, 11, 5};   //{1,5,5} = {11}
        boolean bRe = canPartition(arr);
        System.out.println(bRe);
        arr =new int[]{1, 2, 3, 5};             // {1,2,3} <> {5}
        bRe = canPartition(arr);
        System.out.println(bRe);
    }

    static boolean canPartition(int[] arr) {
        if(arr.length<1)
            return true;
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        int leftTotal = 0;
        int rightTotal = 0;
        leftTotal = arr[left];
        rightTotal = arr[right];
        while(right>left){
            if(leftTotal<rightTotal){
                left++;
                leftTotal = leftTotal + arr[left];
            }
            else if(leftTotal==rightTotal){
                return true;
            }
            else{
                right--;
                rightTotal = rightTotal + arr[right];
            }
        }
        return false;
    }

    //leevcode
    //1. find two number sum in array equal target value

    static void findTwoNumbersSumInArrayEqualTargetValueTest(){
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] returns = findTwoNumbersSumInArrayEqualTargetValue(nums, target);
        //[2, 7]
        System.out.println(returns);
    }
    static int[] findTwoNumbersSumInArrayEqualTargetValue(int[] nums, int target){
        int[] returns = {0, 0};
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                return new int[]{nums[map.get(nums[i])], nums[i]};
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
        ListNode prev;
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

    //5/10/2021 Facebook interview.
    //1. Get Binary Search Tree max summary of leaves.
    static void get_binary_search_tree_max_summary_of_leaves_test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);      //max sum in leave 2 => 5+7 =12
        int max = get_binary_search_tree_max_summary_of_leaves(root);
        System.out.println(max);
    }
    static List<TreeNode> travelGivenLeaveofTree(List<TreeNode> list){
        List<TreeNode> list1 = new ArrayList<>();
        for(TreeNode node : list){
            if(node.left!=null)
                list1.add(node.left);
            if(node.right!=null)
                list1.add(node.right);
        }
        return list1;
    }

    static int get_binary_search_tree_max_summary_of_leaves(TreeNode root){
        int maxSum = Integer.MIN_VALUE;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while(list.size()>0){
            int sum = 0;
            for(TreeNode node : list){
                sum = sum + node.key;
            }
            if(sum>maxSum)
                maxSum = sum;
            list = travelGivenLeaveofTree(list);
        }
        return maxSum;
    }

    //2. Check words if sorted as char array.
    // String[] words = {"Check", "words", "if", "sorted"};
    // char[] alph = {'c', 'w', 'i', 's'}

    static void check_words_if_sorted_as_char_array_test(){
        String[] words = {"Check", "words", "if", "sorted"};
        char[] alph = {'C', 'i', 'w', 's'};
        boolean ret = check_words_if_sorted_as_char_array(words, alph);
        System.out.println(ret);
    }
    static boolean check_words_if_sorted_as_char_array(String[] words, char[] alph){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < alph.length; i++)
            map.put(alph[i], i);
        int index = 0;
        for(String word : words){
            char[] chS = word.toCharArray();
            char ch = chS[0];
            if(map.containsKey(ch)){
                int curr = map.get(ch);
                if(curr<index)
                    return false;
                else
                    index = curr;
            }
            else
                return false;
        }
        return true;
    }


}
