import java.applet.*;
import java.awt.*;
import java.beans.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.nio.*;
import java.security.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.net.*;

/*
数据和操作内型：
Tree：  走树节点操作用Recursion从根到递减到每个叶。 O（ln(n)).  BST 是 root.left.data < root.data < root.right.data。
        走树三种：（中间优先）前叶-根-后叶（前叶优先）根-前叶-后叶；（后叶优先）前叶-后叶-根；
        //11-1 Binary Search Tree : Lowest Common Ancestor      //树中两个结点的最低共同祖先， if ((v1 < root.data) && (v2 > root.data)) lca(root) return root， 或 return lca(root.left) 或 return lca(root.right)
        //11-2 Tree: Height of a Binary Tree                    //二叉树的高度
        //11-3 Trees: Is This a Binary Search Tree?             //是二叉搜索树吗？
        //11-4 Tree: Huffman Decoding                           //树：霍夫曼解码  按每字再字串的字数，编0，1数叶码字串，再解吗。
        //11-5 Balanced Forest                                  //平衡森林
        //11-6 Tree Travelsal Test                              //树遍历:  Inorder (Left, Root, Right); Preorder (Root, Left, Right); Postorder (Left, Right, Root)
        //7-6 Swap Nodes [Algo]                                 //交换节点 交换节点的子树意味着，如果最初节点具有左子树L和右子树R，则在交换之后，左子树将是R，右子树L。

Graph:  图形的广度优先搜索或BFS 和 图的深度优先搜索或DFS
        //10-1 Roads and Libraries                      //道路与图书馆  使用了类似图形的广度优先搜索或BFS， 先把路放到HashMap 中， 在由城市找是否与路连接放在一起形成不同的组。
        //10-2 Find the nearest clone                   //查找最近的结点 使用了类似图形的广度优先搜索或BFS
        //10-3 BFS: Shortest Reach in a Graph           //图表中最短的结点 使用了类似图形的广度优先搜索或BFS
        //10-5 DFS: Connected Cell in a Gri DFS：       //网格中的连接的单元格 ， 每有用搜索，而是用固定的工字网格算



Linked List:  走节点操作用 while(node==null){操作， node = node.next}. O(n)
        //12-1 Insert a node at a specific position in a linked list    //在链接列表中的特定位节点置插入节点
        //12-2 Inserting a Node Into a Sorted Doubly Linked List        //将节点插入到已排序的双链表中
        //12-3 Reverse a doubly linked list                             //反向双向链表
        //12-4 Find Merge Point of Two Lists                            //查找两个列表的合并点
        //12-5 Linked Lists: Detect a Cycle                             //捡查链接列表是否有循环
Array：
        //1-1 Sock Merchant             //找出配对数in array {10, 20, 20, 10, 10, 30, 50, 10, 20}, put all integer at Hashmap with integer as key and counts as value, then sum even count.
        //1-2 Counting Valleys          //找出 +1， -1 in array {U，D，D，D，U，D，U，U} ， cross 0 times.
        //1-3 Jumping on the Clouds     //找出 2 或 3 连续 0 或 1 in {0， 0， 0， 0， 1， 0}
        //2-1 New Year Chaos            //找出 3 integers in array int[] q = {1, 2, 5, 3, 7, 8, 6, 4}; need to be sort, 先定三个☞针
        //2-2 2D Array - DS             //遍历6x6数组，计算每个 工 {x,x, x},{x},{x,,x, x}, max.(max, new sum)
        //2-3 Left Rotation             //将数组中的项目移动到所需的左侧距离。 创建一个新数组并在现在位置复制数组项 move items in the array to required left distance. crete a new array and copy array items in now location
        //2-5 Array Manipulation        //查询数组中的增加增量子数组。 Query add increase sub array in array. Create operate array for boundary, and set amount increase and decrease.
        //6-1 Minimum Absolute Difference in an Array   //数组中的最小绝对差
        //6-2 Greedy Florist                            //花店希望最大限度地利用新客户和赚钱的方式。
        //6-3 Max Min                                   //给定一个整数列表和一个整数。 您必须根据的元素创建一个长度数组，以使其不公平最小化。 调用该数组。 数组的不公平性计算为
        //6-5 Luck Balance                              //Lena正在为重要的编码竞赛做准备，此竞赛之前先进行了一系列连续的初步竞赛。 最初，
        //7-1 Hash Tables: Ice Cream Parlor             //三人买冰淇淋， 用完最多的可用钱，为每个人买
        //7-2 Pairs                     //确定差值等于目标值的数组元素对的数量。
        //7-3 Triple sum                //给定三不同大小的数组，组和不同的三元组的数量
        //7-3 Minimum Time Required     //有许多机器，每台机器都有固定的生产天数。
        //7-4 Maximum Subarray Sum      //数组的子数组总和的最大值，
        //7-5 Making Candies            //生产糖果， 必须从开始积累糖果买机器和工人
        //8-1 Max Array Sum             //给定一个整数数组，找到具有最大和的不相邻元素的子集。 计算该子集的总和。 Set maxSum  i = 0, i = 1 at  x[0], loop i = 2 to lenth,  get maxSum x[i] Math.max(x[i-1], x[i] + x[i-2])
        //8-2 Candies                   //给定一个整数数组，相邻元素， 学生的评分是[4、6、4、5、6、2]。 她以最小量给学生糖果：[1、2、1、2、3、1]。 她必须至少购买10个糖果。
        //8-3 Decibinary Number         //十， 二进制数 两组表示方法
        //9-3 Largest Rectangle         //拆除许多空置的旧建筑物，并在其附近建造一个购物中心。 您的任务是找到可以在其中建造购物中心的最大实体区域。
        //9-4 Min Max Riddle            //从一个整数数组返回一个整数数组表示每个大小子数组最大值。
        //9-5 Castle on the Grid        //网格上的城堡 有节点不可过， 找出对角路径。 可靠虑递归

Sort;
        //4-1 Sorting: Bubble Sort              //数组排序
        //4-2 Sorting: Comparator               //从较低的价格排序整数和求和整数
        //4-3 Fraudulent Activity Notifications //连续早期数字中期
        //4-4 Merge Sort: Counting Inversions   //计数反转
        //4-5 maximumToys                       //整数，计算较低价格或较高价格

String：
        //5-1 Sherlock and the Valid String     //夏洛克和有效字符串
        //5-2 Alternating Characters            //交替字符
        //5-3 Strings: Making Anagrams          //如果第一个字符串的字母可以重新排列以形成第二个字符串，则我们认为两个字符串彼此相似。 换句话说，两个字符串必须以相同的准确频率包含相同的准确字母
        //5-4 Special String Again              //再次特殊字符串
        //5-5 Common Child                      //共同子字符
        //8-4 Abbreviation                      //对字符串的操作：改小写字母零个或多个到大写。删除中所有剩余的小写字母。给定两个字符串和，确定是否有可能使等于。 daBcd 和 ABC
        //6-4  Reverse Shuffle Merge            //反向随机合并 eggegg egg

Integer:
        //14-1 Flipping bits                    //一数据的二进制0-1变反后的正数据。4 =》4294967291。 数变字串再一一算每个0和1的，2. check digital '0->1' one by one to  add pow 2.
        //14-2 Time Complexity: Primality       //一数据是否为质数，只能被自己整除。 12 不是质数， 5是质数


Dictionaries and Hashmaps
        //3-1 two string                        //比较两个字符串共享subString。 双方共享的任何一封信都可以说共享。 要映射的所有字母都应与其他表进行对照。 compare two string share subString. Any one letter shared at both could say share. All letters to mpa than check with other table.
        //3-2 Hash Tables: Ransom Note          //将两个字符串添加到哈希表，然后将字符串与哈希表进行比较
        //3-3 Sherlock and Anagrams  /          //计算映射中具有相同键的已排序子字符串. count = count + map(key-1)*map(key)/2
        //3-4 Count Triplets                    //计算三胞胎，创建两个要包含的地图
        //3-5 Frequency Queries                 //在del之后在del中创建地图检查计数，添加项目。

Queries operator： 只对操作矩阵进行寻环计算
        //14-3 Friend Circle Queries            //加朋友圈操作，一对朋友可将自己的朋友圈的朋友加入。 加朋友圈到HashMap {1，2} =》 {1， 2， 。。。}
        //14-4Maximum Xor                       //用操作数对每个数列的数进行Xor。 得出最大结果 2 => {5, 1, 7, 4, 3} = 2
        //9-1 Poisonous Plants                  //花园里有很多植物。 这些植物中的每一种都经过一定量的农药处理。 每天过后，如果任何一种植物的杀虫剂比左侧的植物多，比左侧的植物弱，它就会死亡。 {6 5 8 4 7 10 9}
        //9-2 Queues: A Tale of Two Stacks      //队列：用两个堆栈操作一个队列， 1 x：将元素放入队列末尾 2：使元素排在队列的最前面。 3：将元素打印在队列的最前面。

Recursion： 递归，设方程初始输入值， 再调用方程输入递减至初始值，参数可带入固定式
        //13-1 Recursion: Fibonacci Numbers     //复利叶公式： n=0 f(0) = 0, n=1, f(1) = 1, f(n) = f(n-1) + f(n-2) for n--
        //13-2 Recursion: Davis' Staircase      //一次n步上s高楼梯， 有几种上法。 f(1) = 1, f(2) = 2, f(3) = 3, f(n) = f(n-1) + f(n-2) + f(n-3)
        //13-3 Crossword Puzzle                 //填字游戏。 first get array has '-' cell in lengths and put in list. then fill the words in diff cell length
        //13-4 Recursive Digit Sum              //数据是否超级数， 如果只有一个数字，则为超级数字, 或位数的超位数之和。 n length - 1, f(sum) = n, n length > 1, f(sum) for n-1       // x%k + y%k = (x+y)%k to make x + y small

 */


public class Solution { // total 50 solved
//https://www.hackerrank.com/interview/interview-preparation-kit
//The HackerRank Interview Preparation Kit
    public static void main(String[] args) {

        //https://www.hackerrank.com/interview/interview-preparation-kit/warmup/challenges

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
// 1. Warm-up Challenges
System.out.printf("\n1. Warm-up Challenges");

        //https://www.hackerrank.com/challenges/sock-merchant/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
        //1-1 Sock Merchant   找出配对数in array {10, 20, 20, 10, 10, 30, 50, 10, 20}, put all integer at Hashmap with integer as key and counts as value, then sum even count.
        HackerRank.sockMerchantTest();  //Solved

        //https://www.hackerrank.com/challenges/sock-merchant/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
        // 1-2 Counting Valleys  找出 +1， -1 in array {U，D，D，D，U，D，U，U} ， cross 0 times.
        HackerRank.countingValleysTest(); //Solved

        //https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
        //1-3 Jumping on the Clouds  找出 2 或 3 连续 0 或 1 in {0， 0， 0， 0， 1， 0}
        HackerRank.jumpingOnCloudsTest();  //Solved

        //https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
        //1-4 Repeated String  找出 a in string count. repeat append string n times, first a show at = (n/string len)* count + (n/string len)n
        HackerRank.repeatedString();  //solved

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//2. Arrays
System.out.printf("\n2. Arrays");

        //https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges
        //2-1 New Year Chaos   // 找出 3 integers in array int[] q = {1, 2, 5, 3, 7, 8, 6, 4}; need to be sort, 先定三个☞针
        HackerRank.minimumBribesTest(); //Solved

        //https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
        //2-2 2D Array - DS   // go over 6x6 array, calculate every 工 {x,x, x},{x},{x,,x, x}, max.(max, new sum)
        HackerRank.hourglassSumTest(); //Solved

        //https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
        //2-3 Left Rotation    move items in the array to required left distance. curete new array and copy array items in now location
        HackerRank.rotLeftTest();  //Solved

        //https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
        //2-4 Minimum Swaps 2      // integer in array would be same as index. if not swift, [i] and a[i]
        HackerRank.minimumSwapsTest();  //Solved

        //https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
        //2-5 Array Manipulation   // Query add incease sub array in array. Create operate array for boundary, and set amount increase and decrease.
        HackerRank.arrayManipulationTest(); //Solved


//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//3. Dictionaries and Hashmaps
System.out.printf("\n3. Dictionaries and Hashmaps");

        //https://www.hackerrank.com/interview/interview-preparation-kit/dictionaries-hashmaps/challenges
        //3-1 two string   compare two string share subString. Any one character shared at both could say share. All character to mpa than check with other table.
        HackerRank.twoStringsTest();  //Solved

        //https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
        //3-2 Hash Tables: Ransom Note    // add two strings to has table then compar strings with hashtable
        HackerRank.checkMagazineTest();   //Solved

        //https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
        //3-3 Sherlock and Anagrams
            // count sorted sub string with same key in map. count = count + map(key-1)*map(key)/2
        HackerRank.sherlockAndAnagramsTest(); //no Solved has c codes

        //https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
        //3-4 Count Triplets   //create tow maps to contain
        HackerRank.countTripletsTest();  //Solved

        //https://www.hackerrank.com/challenges/frequency-queries/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
        //3-5 Frequency Queries  create map check count in isde after del, add items.
        HackerRank.freqQueryTest();  // no Solved for test case 11 time out

//https://www.hackerrank.com/interview/interview-preparation-kit/sorting/challenges
//4 Sorting:
System.out.printf("\n4. Sorting");

        //https://www.hackerrank.com/challenges/ctci-bubble-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
        //4-1 Sorting: Bubble Sort     //Array sort
        HackerRank.countSwapsTest();  // Solved

        //https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
        //4-2 Sorting: Comparator     // sort integer and sum integer from lower price
        HackerRank.ComparatorTest();   //Solved

        //https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
        //4-3 Fraudulent Activity Notifications    //cont early days mid digital
        HackerRank.activityNotificationsTest();   //No Solved in time limit has c++ code to pass all

        //https://www.hackerrank.com/challenges/ctci-merge-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
        //4-4 Merge Sort: Counting Inversions
        HackerRank.countInversionsTest();   // Solved

        //https://www.hackerrank.com/challenges/mark-and-toys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
        //4-5 maximumToys  //soft integer, calculate lower price ot higher price
        HackerRank.maximumToysTest();  //Solved

//https://www.hackerrank.com/interview/interview-preparation-kit/strings/challenges
// 5. String Manipulation
System.out.printf("\n5. String Manipulation");

        //https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings
        // 5-1 Sherlock and the Valid String   //夏洛克和有效字符串
        HackerRank.isValidTest();  //Solved

        // 5-2 Alternating Characters           //交替字符
        HackerRank.alternatingCharactersTest(); //Solved

        // 5-3 Strings: Making Anagrams         //如果第一个字符串的字母可以重新排列以形成第二个字符串，则我们认为两个字符串彼此相似。 换句话说，两个字符串必须以相同的准确频率包含相同的准确字母
        HackerRank.makeAnagramTest();  //Solved

        // 5-4 Special String Again             //特殊字符串
        HackerRank.substrCountTest(); //Solved

        // 5-5 Common Child                     //共同子字符
        HackerRank.commonChildTest();  //No Solved    error by input

//https://www.hackerrank.com/interview/interview-preparation-kit/greedy-algorithms/challenges
// 6. Greedy Algorithms
System.out.printf("\n6. Greedy Algorithms");

        //https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
        //6-1 Minimum Absolute Difference in an Array  //数组中的最小绝对差
        HackerRank.minimumAbsoluteDifferenceTest();  // Solved
        // sort array and check neighbor pair abs(min).

        //https://www.hackerrank.com/challenges/greedy-florist/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
        //6-2 Greedy Florist   // no passed by system. 花店希望最大限度地利用新客户和赚钱的方式。
        HackerRank.getMinimumCostTest();  // Solved
        //https://www.hackerrank.com/challenges/angry-children/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
        //6-3 Max Min  //给定一个整数列表和一个整数。 您必须根据的元素创建一个长度数组，以使其不公平最小化。 调用该数组。 数组的不公平性计算为
        HackerRank.MaxMinTest();   // Solved

        //https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
        //6-4  Reverse Shuffle Merge  反向随机合并 eggegg egg
        System.out.printf("\n7. Search");
        HackerRank.reverseShuffleMergeTest();   //Sloved.  has python codes passed

        //https://www.hackerrank.com/challenges/luck-balance/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
        //6-5 Luck Balance   //Lena正在为重要的编码竞赛做准备，此竞赛之前先进行了一系列连续的初步竞赛。 最初，
            // 她的运气平衡为0。她相信“可以节省运气”，并希望检验自己的理论。 每个比赛用两个整数L T来描述，并且 是与比赛相关的运气量。 如果莉娜（Lena）赢得比赛，她的运气余额将减少; 如果她输了，她的运气余额将增加。
            // 表示比赛的重要性等级。 等于比赛是否重要，等于不重要。
            //如果莉娜仅输掉重要的比赛，那么在参加所有预赛之后她能获得的最大运气是多少？ 此值可能为负。
        HackerRank.luckBalanceTest();   // Solved

//https://www.hackerrank.com/challenges/swap-nodes-algo/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
// 7. Search
System.out.printf("\n7. Search");

        //https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        //7-1 Hash Tables: Ice Cream Parlor   //三人买冰淇淋， 用完最多的可用钱，为每个人买
        HackerRank.whatFlavorsTest();  //Solved

        //https://www.hackerrank.com/challenges/pairs/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        //7-2 Pairs  //确定差值等于目标值的数组元素对的数量。
        HackerRank.pairsTest();  //Solved

        //https://www.hackerrank.com/challenges/triple-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        //7-3 Triple sum   //给定三不同大小的数组，组和不同的三元组的数量
        HackerRank.tripletsTest(); //Solved

        //https://www.hackerrank.com/challenges/minimum-time-required/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        // 7-3 Minimum Time Required  //有许多机器，每台机器都有固定的生产天数。
        HackerRank.minTimeTest();   //Solved no passed by time

        //https://www.hackerrank.com/challenges/maximum-subarray-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        //7-4 Maximum Subarray Sum  //数组的子数组总和的最大值，
        HackerRank.maximumSumTest(); //Solved in c++

        //https://www.hackerrank.com/challenges/making-candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        //7-5 Making Candies   //Making Candies， 必须从开始积累糖果买机器和工人
        HackerRank.minimumPassesTest();  //No passed

        //https://www.hackerrank.com/challenges/swap-nodes-algo/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
        //7-6 Swap Nodes [Algo]   //交换节点 交换节点的子树意味着，如果最初节点具有左子树L和右子树R，则在交换之后，左子树将是R，右子树L。
        HackerRank.swapNodesTest();  //no passed by stysem




//https://www.hackerrank.com/challenges/swap-nodes-algo/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
//8. Dynamic Programming
System.out.printf("\n8. Dynamic Programming");

        //https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
        //8-1 Max Array Sum   //给定一个整数数组，找到具有最大和的不相邻元素的子集。 计算该子集的总和。 Set maxSum  i = 0, i = 1 at  x[0], loop i = 2 to lenth,  get maxSum x[i] Math.max(x[i-1], x[i] + x[i-2])
        HackerRank.maxSubsetSumTest(); //Solved

        //https://www.hackerrank.com/challenges/candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
        //8-2 Candies  给定一个整数数组，相邻元素
        HackerRank.CandiesTest();  //Solved

        //https://www.hackerrank.com/challenges/decibinary-numbers/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
        //8-3 Decibinary Number  //十， 二进制数
        HackerRank.decibinaryNumbersTest();  //no pass very hard to give up

        //https://www.hackerrank.com/challenges/abbr/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
        //8-4 Abbreviation  //对字符串的操作：改小写字母零个或多个到大写。删除中所有剩余的小写字母。给定两个字符串和，确定是否有可能使等于。 daBcd 和 ABC
        HackerRank.abbreviationTest(); //no passed by system.


//https://www.hackerrank.com/interview/interview-preparation-kit/stacks-queues/challenges
// 9. Stacks and Queues
System.out.printf("\n9. Stacks and Queues");

        //https://www.hackerrank.com/challenges/poisonous-plants/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
        // 9-1 Poisonous Plants   //花园里有很多植物。 这些植物中的每一种都经过一定量的农药处理。 每天过后，如果任何一种植物的杀虫剂比左侧的植物多，比左侧的植物弱，它就会死亡。 {6 5 8 4 7 10 9}
        HackerRank.poisonousPlantsTest();  //Solved

        // 9-2 Queues: A Tale of Two Stacks  //队列：用两个堆栈操作一个队列， 1 x：将元素放入队列末尾 2：使元素排在队列的最前面。 3：将元素打印在队列的最前面。 //可用一个双向对列 和前后指针
        HackerRank.MyQueueTest();  //Solved

        //https://www.hackerrank.com/challenges/largest-rectangle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
        //9-3 Largest Rectangle  //拆除许多空置的旧建筑物，并在其附近建造一个购物中心。 您的任务是找到可以在其中建造购物中心的最大实体区域。
        HackerRank.largestRectangleTest();  //Solved

        //https://www.hackerrank.com/challenges/min-max-riddle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
        //9-4 Min Max Riddle      //从一个整数数组返回一个整数数组表示每个大小子数组最大值。
        HackerRank.riddleTest();  //Solved

        //https://www.hackerrank.com/challenges/castle-on-the-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
        //9-5 Castle on the Grid  //网格上的城堡 有节点不可过， 找出对角路径。 可靠虑递归
        HackerRank.minimumMovesTest();  //Solved. No Main load

        //https://www.hackerrank.com/challenges/balanced-brackets/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
        //9-6 Balanced Brackets   //括号苻平衡 {{[[(())]]}}
        HackerRank.isBalancedTest();  //Solved

//https://www.hackerrank.com/interview/interview-preparation-kit/graphs/challenges
// 10. Graphs
System.out.printf("\n10. Graphs");

        //https://www.hackerrank.com/challenges/torque-and-development/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
        //10-1 Roads and Libraries //道路与图书馆  使用了类似图形的广度优先搜索或BFS， 先把路放到HashMap 中， 在由城市找是否与路连接放在一起形成不同的组。
        HackerRank.roadsAndLibrariesTest();  //Solved

        //https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
        //10-2 Find the nearest clone  //查找最近的结点 使用了类似图形的广度优先搜索或BFS
        HackerRank.graphNodesTest(); //Solved

        //https://www.hackerrank.com/interview/interview-preparation-kit/graphs/challenges
        //10-3 BFS: Shortest Reach in a Graph  //图表中最短的结点 使用了类似图形的广度优先搜索或BFS
        HackerRank.getDistanceTest(); //Solved

        //https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
        //10-5 DFS: Connected Cell in a Gri
        HackerRank.maxRegionTest(); //Solved

        https://www.hackerrank.com/challenges/matrix/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
        //10-6 Matrix //矩阵  锡安王国的城市之间通过双向道路相连， 每条道路都需要花费大量时间才能摧毁，一次只能修整一条。 给定边沿和时间列表，请确定停止攻击的最短时间。
        HackerRank.minTimeRoadTest(); //Solved


https://www.hackerrank.com/interview/interview-preparation-kit/trees/challenges
// 11. Trees
System.out.printf("\n11. Trees");

        //11-1 Binary Search Tree : Lowest Common Ancestor  //树中两个结点的最低共同祖先， if ((v1 < root.data) && (v2 > root.data)) lca(root) return root， 或 return lca(root.left) 或 return lca(root.right)
        HackerRank.lcaTest();  // Solved

        //11-2 Tree: Height of a Binary Tree  //树：二叉树的高度
        HackerRank.TreeHeightTest(); //Solved

        //https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
        //11-3 Trees: Is This a Binary Search Tree? //这是二叉搜索树吗？
        HackerRank.checkBSTTest();   //Solved, No main loading in test case

        //https://www.hackerrank.com/challenges/balanced-forest/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
        //11-4 Tree: Huffman Decoding  //树：霍夫曼解码  按每字再字串的字数，编0，1数叶码字串，再解吗。
        HackerRank.decodeTest();    //Solved

        //https://www.hackerrank.com/challenges/balanced-forest/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
        //11-5 Balanced Forest  //平衡森林
        HackerRank.balancedForestTest();  //Solved in two timing limits cases

        //11-6 Tree Travelsal Test //树遍历:  Inorder (Left, Root, Right); Preorder (Root, Left, Right); Postorder (Left, Right, Root)
        HackerRank.TreeTravsalTest();     //Solved in two timing limits cases
//https://www.hackerrank.com/interview/interview-preparation-kit/linked-lists/challenges
// 12. Linked Lists
System.out.printf("\n12. Linked Lists");

        //12-1 Insert a node at a specific position in a linked list //在链接列表中的特定位置插入节点
        HackerRank.insertNodeAtPositionTest();  //Solved

        //https://www.hackerrank.com/challenges/insert-a-node-into-a-sorted-doubly-linked-list/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
        //12-2 Inserting a Node Into a Sorted Doubly Linked List  //将节点插入到已排序的双链表中
        HackerRank.sortedInsertTest();  //Solved

        //12-3 Reverse a doubly linked list  //反向双向链表
        HackerRank.DoubleLinkedListTest();  //Solved

        //https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
        // 12-4 Find Merge Point of Two Lists  //查找两个列表的合并点
        HackerRank.findMergeNodeTest();  //Solved

        //12-5 Linked Lists: Detect a Cycle  //捡查链接列表是否有循环
        HackerRank.has_cycleTest();  //Solved

//https://www.hackerrank.com/interview/interview-preparation-kit/recursion-backtracking/challenges
// 13. Recursion and Backtracking
System.out.printf("\n13. Recursion and Backtracking");

        //13-1 Recursion: Fibonacci Numbers  // 复利叶公式： n=0 f(0) = 0, n=1, f(1) = 1, f(n) = f(n-1) + f(n-2) for n--
        HackerRank.fibonacciTest();  //Solved

        //13-2 Recursion: Davis' Staircase   //一次n步上s高楼梯， 有几种上法。 f(1) = 1, f(2) = 2, f(3) = 3, f(n) = f(n-1) + f(n-2) + f(n-3)
        HackerRank.stepPermsTest();  //Solved

        // https://solution.programmingoneonone.com/2020/07/hackerrank-crossword-puzzle-solution.html
        //13-3 Crossword Puzzle  //填字游戏。 first get array has '-' cell in lengths and put in list. then fill the words in diff cell length
        HackerRank.crosswordPuzzleTest(); // Solved. But No passed in the web build.

        //13-4 Recursive Digit Sum  //数据是否超级数， 如果只有一个数字，则为超级数字, 或位数的超位数之和。 n length - 1, f(sum) = n, n length > 1, f(sum) for n-1       // x%k + y%k = (x+y)%k to make x + y small
        HackerRank.superDigitTest();    // Solved only one test case 8  no passed

//https://www.hackerrank.com/interview/interview-preparation-kit/miscellaneous/challenges
// 14. Miscellaneous
System.out.printf("\n14. Miscellaneous");

        //14-1 Flipping bits   //一数据的二进制0-1变反后的正数据。4 =》4294967291。 数变字串再一一算每个0和1的，2. check digital '0->1' one by one to  add pow 2.
        HackerRank.flippingBitsTest(); //Solved

        //https://www.hackerrank.com/challenges/ctci-big-o/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=miscellaneous
        //14-2 Time Complexity: Primality //一数据是否为质数，只能被自己整除。 12 不是质数， 5是质数
        HackerRank.primalityTest();  //Solved

        //14-3 Friend Circle Queries //加朋友圈操作，一对朋友可将自己的朋友圈的朋友加入。 加朋友圈到HashMap {1，2} =》 {1， 2， 。。。}
        HackerRank.maxCircleTest(); //Solved

        //https://www.hackerrank.com/challenges/maximum-xor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=miscellaneous
        //14-4Maximum Xor   //用操作数对每个数列的数进行Xor。 得出最大结果 2 => {5, 1, 7, 4, 3} = 2
        HackerRank.maxXorTest(); //Solved，has python resolve codes to passed all time limit


//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

//1-1. Printing brackets in Matrix Chain Multiplication Problem         //矩阵链乘法问题中的打印括号
//     (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations  and  A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.

//1-2. Count total set bits in all numbers from 1 to n                  //计算从1到n的所有数字中的总置位位数
//     n = 3, 0, 1, 2, 3 => 0, 01, 10, 11 => 4

//1-3. Number of subsequences of the form a^i b^j c^k                   //形式为a ^ i b ^ j c ^ k的子序列数
//     abbc 有 3 种  abc, abc and abbc

//1-4. Replace every element with the greatest element on right side    //用右侧的最大元素替换每个元素
//      {16, 17, 4, 3, 5, 2}, => {17, 5, 5, 5, 2, -1}.

//1-5. Highest power of 2 less than or equal to given number            //小于或等于给定数的2的最高幂
//     n = 10 有 2*2*3 = 8

//1-6. Find a pair with maximum product in array of Integers            //在整数数组中查找最大乘积对
//     arr[] = {1, 4, 3, 6, 7, 0} =》 {6,7}

//1-7. Count all possible paths from top left to bottom right of a mXn matrix                   //计算从mXn矩阵的左上到右下的所有可能路径
//     2x2 [0,0]...[1,1] = > (0, 0) -> (0, 1) -> (1, 1)   (0, 0) -> (1, 0) -> (1, 1)

//1-8. Given a binary string, count number of substrings that start and end with 1.             //给定一个二进制字符串，计算以1开头和结尾的子字符串的数量
//     “00100101” => “1001”, “100101” and “101”.

//1-9. Given only a pointer/reference to a node to be deleted in a singly linked list, how do you delete it? //在单链列表中，仅给出要删除的节点的指针/引用，如何删除它？
//     delPoint = node, => node.data = node.next.data, => node.next = node.next.next

//1-10. Write a Program to Find the Maximum Depth or Height of a Tree                           //编写程序以查找树的最大深度或高度
//     find（root){if root = null return 0; else return max(find(root->left) + 1,  find(root->right) + 1)

//1-11. Print nodes at k distance from root                                                     //打印节点到根的距离为k
//      if k == find（root){if root = null return 0; else return max(find(root->left) + 1,  find(root->right) + 1) print

//1-12. Difference between sums of odd level and even level nodes of a Binary Tree              //二叉树的奇数级和偶数级节点之和之间的差
//      return chird tree high and sum to even sum and odd sum.

//1-13 Construct a special tree from given preorder traversal 根据给定的遍历遍历构造一棵特殊的树
//     new root, root.left = new node root.right = new node,

//1-14. Tree Isomorphism Problem  //树同构问题
//     左， 右树叉对应

//1-15. Replace all ‘0’ with ‘5’ in an input Integer    //输入整数中的所有“ 0”替换为“ 5”
//      102 =》 152

//2-1. Non Repeating String Characters                  //非重复字符串字符
//     HashMap to story all char and count repeated.

//2-2. Deadlock in OS //操作系统死锁
//      其他进程需要资源才能继续执行

//2-3. Linked List Intersection                         //链表交叉点
//     现在，两个链接列表都在同一点。 同时遍历两个列表，如果有公共节点，则该节点为交点。

//2-4. Find 2nd min element from given array ?          //从给定数组中查找第二个min元素
//     sort array and find next min one.

//2-5. Pairs with Sum = S                               //与Sum = S成对
//     sort the array .  initilaize the pointers i and j to 0 and arr.length-1， if(arr[i]+arr[j]==sum ) return pair of i and j. else if (arr[i]+arr[j]<sum){ i++
//j--

//2-6. Perform Level Order Tree Traversal  \\执行级别顺序树遍历
//      对于每个节点，首先访问该节点，然后将其子节点放入FIFO队列中。

//3-1. Kadane's Algorithm                       \\卡丹算法 给定一个由N个整数组成的数组arr。 查找具有最大和的连续子数组。
//    1 2 3 -2 5 => 9

//3-2. Missing number in array                  //数组中缺少数字
//     1 2 3 5 => 4

//3-3. Longest Increasing Subsequence           \\最长递增子序列
//      5 8 3 7 9 1 => 3 7 9 => 3

//3-4. Key Pair                                 \\密钥对
//     Arr[] = {1, 4, 45, 6, 10, 8} = Arr[3] + Arr[4] = 6 + 10 = 16

//3-5. Subarray with given sum                  //给定总和的子数组
//      5 12/1 2 3 7 5 =》 2， 4

//3-6. Check for BST                            \\检查BST

//3-7. Finding middle element in a linked list  \\在链接列表中查找中间元素

//3-8. Root to leaf path sum                    \\根到叶路径总和

//3-9. Sum Tree                                 \\求和树

//3-10. Detect Loop in linked list              \\检测链表中的循环

//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


        //Amazon
        //https://www.geeksforgeeks.org/amazon-interview-preparation/

        //Popular Articles

        //https://www.geeksforgeeks.org/printing-brackets-matrix-chain-multiplication-problem/
        //1-1. Printing brackets in Matrix Chain Multiplication Problem
        Amazon.matrixChainOrderTest();

        //https://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
        //1-2. Count total set bits in all numbers from 1 to n //计算从1到n的所有数字中的总置位位数
        Amazon.countSetBitsUtilTest();

        //https://www.geeksforgeeks.org/number-subsequences-form-ai-bj-ck/
        //1-3. Number of subsequences of the form a^i b^j c^k   //Number of subsequences of the form a^i b^j c^k
        Amazon.countSubsequencesTest();

        //https://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/
        //1-4. Replace every element with the greatest element on right side //用右侧的最大元素替换每个元素
        Amazon.nextGreatestTest();

        //https://www.geeksforgeeks.org/highest-power-2-less-equal-given-number/
        //1-5. Highest power of 2 less than or equal to given number //小于或等于给定数的2的最高幂
        Amazon.highestPowerof2Test();

        //https://www.geeksforgeeks.org/return-a-pair-with-maximum-product-in-array-of-integers/
        //1=6. Find a pair with maximum product in array of Integers //在整数数组中查找最大乘积对
        Amazon.maxProductTest();

        //https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
        //1-7. Count all possible paths from top left to bottom right of a mXn matrix //计算从mXn矩阵的左上到右下的所有可能路径
        Amazon.numberOfPathsTest();

        //https://www.geeksforgeeks.org/given-binary-string-count-number-substrings-start-end-1/
        //1-8. Given a binary string, count number of substrings that start and end with 1. //给定一个二进制字符串，计算以1开头和结尾的子字符串的数量
        Amazon.countSubStrTest();

        //https://www.geeksforgeeks.org/given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
        //1-9. Given only a pointer/reference to a node to be deleted in a singly linked list, how do you delete it? //在单链列表中，仅给出要删除的节点的指针/引用，如何删除它？
        Amazon.deleteNodeTest();


        //https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
        //1-10. Write a Program to Find the Maximum Depth or Height of a Tree //编写程序以查找树的最大深度或高度
        Amazon.maxDepthTest();

        //https://www.geeksforgeeks.org/print-nodes-at-k-distance-from-root/
        //1-11. Print nodes at k distance from root  //打印节点到根的距离为k
        Amazon.printKDistantTest();

        //https://www.geeksforgeeks.org/difference-between-sums-of-odd-and-even-levels/
        //1-12. Difference between sums of odd level and even level nodes of a Binary Tree  //二叉树的奇数级和偶数级节点之和之间的差
        Amazon.getLevelDiffTest();

        //https://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
        //1-13 Construct a special tree from given preorder traversal 根据给定的遍历遍历构造一棵特殊的树
        Amazon.constructTreeTest();

        //https://www.geeksforgeeks.org/tree-isomorphism-problem/
        //1-14. Tree Isomorphism Problem  //树同构问题
        Amazon.isIsomorphicTest();

        //https://www.geeksforgeeks.org/replace-0-5-input-integer/
        //1-15. Replace all ‘0’ with ‘5’ in an input Integer   //输入整数中的所有“ 0”替换为“ 5”
        Amazon.replace0with5Test();

        //Popular Subjective Problems

        //https://practice.geeksforgeeks.org/problems/non-repeating-string-characters/
        //2-1. Non Repeating String Characters   //非重复字符串字符
        Amazon.firstNoRepeatChrTest();

        //https://practice.geeksforgeeks.org/problems/deadlock-in-os/
        //2-2. Deadlock in OS //操作系统死锁

        //https://practice.geeksforgeeks.org/problems/linked-list-intersection/
        //2-3. Linked List Intersection  //链表交叉点
        Amazon.intersectPointTest();

        //https://practice.geeksforgeeks.org/problems/array-min-element/
        //2-4. Find 2nd min element from given array ?   //从给定数组中查找第二个min元素
        Amazon.find2ndMinTeset();

        //https://practice.geeksforgeeks.org/problems/pairs-with-sum-s/
        //2-5. Pairs with Sum = S   //与Sum = S成对
        Amazon.pairsWithSumTest();

        //https://practice.geeksforgeeks.org/problems/level-order-traversal/
        //2-6. Perform Level Order Tree Traversal  \\执行级别顺序树遍历
        Amazon.treeTraversalTest();

        //https://practice.geeksforgeeks.org/problems/difference-between-process-and-thread/
        //2- 7. Difference between Process and thread.
        // Process is basically a program in execution. It is an active entity.
        // Some operating systems use the term ‘task‘ to refer to a program that is being executed.
        // A process is always stored in the main memory also termed as the primary memory or random access memory.
        // Therefore, a process is termed as an active entity. It disappears if the machine is rebooted.
        // Several process may be associated with a same

        //https://practice.geeksforgeeks.org/problems/linked-list-types/
        //2-8. What are the types of link list? How to detect and remove a loop in link list?
        //  Types of linked list :
        //  1- Singly linked list
        //  2- Doubly linked list
        //  3- Circular linked list
        //  4- Circular doubly linked list

        //https://practice.geeksforgeeks.org/problems/acid-properties/
        //2-9.What are ACID properties?
        // ACID properties stands for Atomicity, Consistency, Isolation, Durability.

        //Popular Coding Problems

        //https://practice.geeksforgeeks.org/problems/kadanes-algorithm/0
        //3-1. Kadane's Algorithm \\卡丹算法 给定一个由N个整数组成的数组arr。 查找具有最大和的连续子数组。
        Amazon.KadanesAlgorithmTest();

        //https://practice.geeksforgeeks.org/problems/missing-number-in-array/0
        //3-2. Missing number in array  //数组中缺少数字  1 2 3 5 => 4
        Amazon.misNumberInArrarTest();

        //https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence/0
        //3-3. Longest Increasing Subsequence  \\最长递增子序列   5 8 3 7 9 1 => 3 7 9 => 3
        Amazon.LongIncSubsTest();

        //https://practice.geeksforgeeks.org/problems/key-pair/0
        //3-4. Key Pair \\密钥对   Arr[] = {1, 4, 45, 6, 10, 8} = Arr[3] + Arr[4] = 6 + 10 = 16
        Amazon.pairsWithSumTest();

        https://practice.geeksforgeeks.org/problems/subarray-with-given-sum/0
        //3-5. Subarray with given sum   //给定总和的子数组   5 12/1 2 3 7 5 =》 2， 4
        Amazon.sumSubarrayTest();

        //https://practice.geeksforgeeks.org/problems/check-for-bst/1
        //3-6. Check for BST            \\检查BST
        HackerRank.checkBSTTest();

        //https://practice.geeksforgeeks.org/problems/finding-middle-element-in-a-linked-list/1
        //3-7. Finding middle element in a linked list  \\在链接列表中查找中间元素
        Amazon.getMiddleTest();

        //https://practice.geeksforgeeks.org/problems/root-to-leaf-path-sum/1
        //3-8. Root to leaf path sum  \\根到叶路径总和
        Amazon.hasPathSumTest();

        //https://practice.geeksforgeeks.org/problems/sum-tree/1
        //3-9. Sum Tree  \\求和树
        Amazon.isSumTreeTest();

        //https://practice.geeksforgeeks.org/problems/detect-loop-in-linked-list/1
        //3-10. Detect Loop in linked list \\检测链表中的循环
        HackerRank.has_cycleTest();  //no passed

//2020-9-14  Amazon codeing test
        //4-1 find find top 2 strings using at statements.
        Amazon.findTop2StringsUsedInStatements();


        //4-2 find two integer in BST of integer array distance.
        Amazon.findTwoNodesDistanceInBST();


    }

}