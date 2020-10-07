//import jdk.nashorn.api.tree.ArrayLiteralTree;
import org.w3c.dom.Node;

import java.util.*;

public class HackerRank {

    //https://www.hackerrank.com/interview/interview-preparation-kit/warmup/challenges
    //1. Warm-up Challenges

    //https://www.hackerrank.com/challenges/sock-merchant/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
    //1-1 Sock Merchant
    static void sockMerchantTest() {
        int[] ar = {1, 2, 1, 2, 1, 3, 2};
        int ret = sockMerchant(ar.length, ar); //ret = 2
    }


    static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> map = new HashMap<>();

        int iPairs = 0;
        for (int i = 0; i < n; i++) {
            int c = ar[i];
            if (map.containsKey(c)) {
                int count = map.get(c);
                map.put(c, ++count);
            } else
                map.put(c, 1);

        }
        for (int pair : map.values()) {
            iPairs = iPairs + pair / 2;
        }

        return iPairs;
    }

    //https://www.hackerrank.com/challenges/counting-valleys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
    //1-2 Counting Valleys

    static void countingValleysTest() {
        System.out.printf("1-1 Counting Valleys:  r = countingValleys(n, s);\n");

        String s = "UDDDUDUU";
        int n = 8;
        int r = HackerRank.countingValleys(n, s);
        System.out.printf("r=%s n=%s s=%s, \n", r, n, s);

        s = "DDUUDDUDUUUD";
        n = 12;
        r = countingValleys(n, s);
        System.out.printf("r=%s n=%s s=%s,  \n", r, n, s);

    }


    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int iValleys = 0;
        int iSs = 0;
        boolean bV = false;
        for (int i = 0; i < n; i++) {
            char ud = s.charAt(i);
            if (ud == 'U')
                iSs++;
            else
                iSs--;

            if (iSs > 0)
                bV = false;
            else if (iSs < 0)
                bV = true;
            if ((iSs == 0) && (bV == true))
                iValleys++;
        }
        return iValleys;

    }

    //https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
    //1-3 Jumping on the Clouds

    static void jumpingOnCloudsTest() {

        System.out.printf("1-2 Jumping on the Clouds:  r = HackerRank.jumpingOnClouds(c);\n");
        int[] c = {0, 0, 1, 0, 0, 1, 0};
        int r = HackerRank.jumpingOnClouds(c);
        System.out.printf("r=%s c=%s  \n", r, "{0, 0, 1, 0, 0, 1, 0}");

        int[] c1 = {0, 0, 0, 0, 1, 0};
        r = HackerRank.jumpingOnClouds(c1);
        System.out.printf("r=%s c=%s  \n", r, "{0, 0, 0, 0, 1, 0}");

        int[] c2 = {0, 0, 1, 0, 0, 0, 0, 1, 0, 0};
        r = HackerRank.jumpingOnClouds(c2);
        System.out.printf("r=%s c=%s  \n", r, "{0, 0, 1, 0, 0, 0, 0, 1, 0, 0}");

        int[] c3 = {0, 1, 0};
        r = HackerRank.jumpingOnClouds(c3);
        System.out.printf("r=%s c=%s  \n", r, "{0, 1, 0}");

    }

    // Complete the jumpingOnClouds function below.

    static int jumpingOnClouds(int[] c) {
        int count = 0;
        for (int i = 0; i < c.length - 1; i++) {
            if (c[i] == 0) i++;  //no, could jump 1 or 2
            count++;   // count in cloud`
        }
        return count;
    }

    //https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
    //1-4 Repeated String

    static void repeatedString() {
        long n = 10;
        String s = "aba";
        long r = repeatedString(s, n);
    }

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long count = s.length() - s.replace("a", "").length();
        long index = n % s.length();
        long count1 = s.substring(0, (int) index).length() - s.substring(0, (int) index).replace("a", "").length();
        return count * (n / s.length()) + count1;

    }

    //https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges
    //2. Arrays

    //https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges
    //2-1 New Year Chaos

    static void minimumBribesTest() {
        //int[] q = {2, 1, 5, 3, 4};
        int[] q = {1, 2, 5, 3, 7, 8, 6, 4};  // {1, 2, 3, 4, 5, 6, 7, 8} 3: 1, 5: 2, 7: 2, 8: 2,
        // {1, 2, 5, 4, 3, 7, 8, 6} 3
        // {1, 2, 5, 4, 3, 7, 8, 6}
        minimumBribes(q);
    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int totalBribes = 0;

        int expectedFirst = 1;
        int expectedSecond = 2;
        int expectedThird = 3;

        for (int i = 0; i < q.length; ++i) {
            if (q[i] == expectedFirst) {
                expectedFirst = expectedSecond;
                expectedSecond = expectedThird;
                ++expectedThird;
            } else if (q[i] == expectedSecond) {
                ++totalBribes;
                expectedSecond = expectedThird;
                ++expectedThird;
            } else if (q[i] == expectedThird) {
                totalBribes += 2;
                ++expectedThird;
            } else {
                System.out.printf("%s\n", "Too chaotic");
                return;
            }
        }
        System.out.printf("%s", totalBribes);
    }

    //https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    //2-2 2D Array - DS

    static void hourglassSumTest() {
        System.out.printf("\n2-1 2D Array - DS:  r = HackerRank.hourglassSum(arr[][]);");
        int n = 6;
        int[][] arr = {{-9, -9, -9, 1, 1, 1},
                {0, -9, 0, 4, 3, 2},
                {-9, -9, -9, 1, 2, 3},
                {0, 0, 8, 6, 6, 0},
                {0, 0, 0, -2, 0, 0},
                {0, 0, 1, 2, 4, 0}};
        int r = HackerRank.hourglassSum(arr);
        System.out.printf("\nr=%s s=%s n=%s ", r, arr.toString(), n);

    }

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int iMaxSum = 0;
        int n = arr.length - 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                        + arr[i + 1][j + 1]
                        + arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
                if ((i == 0) && (j == 0))
                    iMaxSum = sum;
                else if (iMaxSum < sum)
                    iMaxSum = sum;
            }
        }
        return iMaxSum;
    }

    //https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    //2-3 Left Rotation

    static void rotLeftTest() {
        System.out.printf("\n2-2 Left Rotation:  r = HackerRank.rotLeft(arr, n);");
        int n = 4;
        int[] arr1 = {1, 2, 3, 4, 5};

        arr1 = rotLeft(arr1, n);

        System.out.printf("\nArrays: %s After rotation ", "{1,2,3,4,5}");
        for (int i = 0; i < arr1.length; i++) {
            System.out.printf("%s, ", arr1[i]);
        }
    }

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        int[] res = new int[a.length];
        int mvIndex = a.length - d;
        for (int index = 0; index < a.length; index++) {
            res[mvIndex] = a[index];
            mvIndex++;
            if (mvIndex >= a.length)
                mvIndex = 0;
        }

        return res;
    }

    //https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    //2-4 Minimum Swaps 2
    static void minimumSwapsTest() {
        System.out.printf("\n2-3 Minimum Swaps 2:  r = HackerRank.minimumSwaps(arr);\n");

        int[] arr = {4, 3, 1, 2};
        int r = HackerRank.minimumSwaps(arr);
        System.out.printf("r=%s, arr=%s \n", r, "{4,3,1,2}");

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

    //https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    //2-5 Array Manipulation

    static void arrayManipulationTest() {
        System.out.printf("\n2-4 Array Manipulation:  r = HackerRank.arrayManipulation(n, queries);\n");
        int n = 5;
        int[][] queries = {{1, 2, 100}, {2, 5, 100}, {3, 4, 100}};
        int r = (int) arrayManipulation(n, queries);  // r = 200
        System.out.printf("r=%s, queries=%s \n", r, "{{1, 2, 100},{2, 5, 100},{3, 4, 100}}");

    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        // Save interval endpoint's "k" values in array
        long[] array = new long[n + 1];

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            int k = queries[i][2];
            array[a - 1] += k;
            array[b] -= k;
        }


        // Find max value
        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
            max = Math.max(max, sum);
        }

        return max;
    }


    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //3. Dictionaries and Hashmaps

    //https://www.hackerrank.com/interview/interview-preparation-kit/dictionaries-hashmaps/challenges
    // 3-1 Two Strings

    static void twoStringsTest() {
        System.out.printf("\n3-2 two string:  r = HackerRank.twoStrings(s1, s2);\n");
        String s1 = "Hello";
        String s2 = "World";
        String sr = HackerRank.twoStrings(s1, s2);
    }

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {

        int[] table = new int[26];
        Arrays.fill(table, -1);

        for (int index = 0; index < s1.length(); index++) {
            char c = s1.charAt(index);
            int ci = Character.getNumericValue(c);
            int cj = Character.getNumericValue('a');
            table[ci - cj] = 1;
        }

        for (int index = 0; index < 26; index++) {
            if (table[index] == 1) {
                char c = (char) (index + 'a');
                String s = String.valueOf(c);
                if (s2.indexOf(s) >= 0) {
                    return "YES";
                }

            }
        }
        return "NO";

    }

    //3-2 Hash Tables: Ransom Note

    static void checkMagazineTest() {
        System.out.printf("\n3-1 Hash Tables: Ransom Note:  r = HackerRank.checkMagazine(magazine, note);\n");

        //String sM = "give me one grand today night";
        //String sN = "give one grand today";
        //String sM = "two times three is not four";
        //String sN = "two times two is four";

        String sM = "apgo clm w lxkvg mwz elo bg elo lxkvg elo apgo apgo w elo bg";
        String sN = "elo lxkvg bg mwz clm w";

        String[] magazine = sM.split(" ");
        String[] note = sN.split(" ");

        HackerRank.checkMagazine(magazine, note); //bRes = true
    }

    static void checkMagazine(String[] magazine, String[] note) {

        Hashtable<String, Integer> words = new Hashtable<String, Integer>();

        for (int i = 0; i < note.length; i++) {
            if (words.containsKey(note[i])) {
                int iValue = words.get(note[i]);
                words.put(note[i], ++iValue);
            } else {
                words.put(note[i], 1);
            }
        }

        for (int i = 0; i < magazine.length; i++) {
            if (words.containsKey(magazine[i])) {
                int iValue = words.get(magazine[i]);
                if (iValue > 0)
                    words.put(magazine[i], --iValue);
            }
        }

        for (int ck : words.values()) {
            if (ck != 0) {
                System.out.printf("No");
                return;
            }
        }
        System.out.printf("Yes");
        return;

    }

    //https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
    // 3-3 Sherlock and Anagrams
    static void sherlockAndAnagramsTest() {
        //String s = "abba";
        //String s = "ifailuhkqq";
        String s = "kkkk";
        sherlockAndAnagrams(s);
    }

    /*
        int check_anagram(char a[], char b[])
        {
        int first[26] = {0}, second[26] = {0}, c = 0;

        while (a[c] != '\0') {
            first[a[c]-'a']++;
            c++;
        }
        c = 0;
        while (b[c] != '\0') {
            second[b[c]-'a']++;
            c++;
        }

        for (c = 0; c < 26; c++) {
            if (first[c] != second[c])
                return 0;
        }

        return 1;
        }

        int main() {
            int t;
            scanf("%d", &t);
            while (t--) {
                char s[100];
                char sub1[100] = {0};
                char sub2[100] = {0};
                scanf("%s", s);

                int count = 0;
                for (int len = 1; len < strlen(s); len++) {
                    memset(sub1, 0, len);
                    for (int i = 0; i < strlen(s) - len; i++) {
                        strncpy(sub1, &s[i], len);
                        memset(sub2, 0, len);
                        for (int j = i + 1; j < strlen(s) - len + 1; j++) {
                            strncpy(sub2, &s[j], len);
                            if (check_anagram(sub1, sub2) == 1) {
                                count++;
                            }
                        }
                    }
                }

                printf("%d\n", count);


        }

    return 0;
    }

     */
    // Complete the sherlockAndAnagrams function below.
    // it is not working.
    static int sherlockAndAnagrams(String s) {

        int iCount = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int iLen = 0; iLen < s.length(); iLen++) {
            for (int j = 0; j < s.length() - iLen; j++) {
                String s1 = s.substring(j, j + iLen + 1);
                char[] c1 = s1.toCharArray();
                Arrays.sort(c1);
                s1 = String.valueOf(c1);
                if (map.containsKey(s1)) {
                    int ic = map.get(s1);
                    map.put(s1, ++ic);
                } else
                    map.put(s1, 1);

            }
        }
        for (String s1 : map.keySet()) {
            int ic = (map.get(s1) - 1) * map.get(s1) / 2; //only half count in (a, b) is same (b, a) count.
            iCount = iCount + ic;
        }

        return iCount;

    }


    //https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
    // 3-4 Count Triplets

    private static Map<Long, Long> getOccurenceMap(List<Long> test) {
        Map<Long, Long> occurenceMap = new HashMap<>();
        for (long val : test) {
            if (!occurenceMap.containsKey(val)) {
                occurenceMap.put(val, 1L);
            } else {
                Long occurence = occurenceMap.get(val);
                occurenceMap.put(val, occurence + 1L);
            }
        }
        return occurenceMap;
    }

    static void countTripletsTest() {
        //List<Long> arr = Arrays.asList(1L, 4L, 16L, 64L);  // ret = 2
        //Long r = 4L;

        //List<Long> arr = new ArrayList<Long>(Collections.nCopies(100, 1L));
        //Long r = 1L;

        //List<Long> arr = Arrays.asList(1L, 2L, 2L, 4L);  // ret = 2
        //Long r = 2L;

        List<Long> arr = Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L);  // ret = 6
        Long r = 3L;

        //countTriplets(arr, r);
        long ret = countTriplets(arr, r);
    }

    //can not passed time limt
    static long countTriplets(List<Long> arr, long r) {

        long count = 0;
        HashMap<Long, Long> dictPairs = new HashMap<Long, Long>();
        HashMap<Long, Long> dict = new HashMap<Long, Long>();

        Collections.reverse(arr);

        for (int i = 0; i < arr.size(); i++) {
            long it = arr.get((int) i);
            long ir = it * r;

            if (dictPairs.containsKey(ir)) {
                long iCount = dictPairs.get(ir);
                count += iCount;
            }

            if (dict.containsKey(ir)) {
                long iCount = dict.get(ir);
                long iCount1 = 0L;
                if (dictPairs.containsKey(it)) {
                    iCount1 = dictPairs.get(it);
                }
                dictPairs.put(it, iCount + iCount1);
            }

            if (dict.containsKey(it)) {
                long iCount = dict.get(it);
                dict.put(it, ++iCount);
            } else {
                dict.put(it, 1L);
            }

        }
        return count;


    }

    //can pass time
    static long countTriplets1(List<Long> arr, long r) {

        Map<Long, Long> rightMap = new HashMap<>();
        for (long val : arr) {
            if (!rightMap.containsKey(val)) {
                rightMap.put(val, 1L);
            } else {
                Long occurence = rightMap.get(val);
                rightMap.put(val, occurence + 1L);
            }
        }
        //1L-1, 4L-1, 16L-1, 64L-1  add each differnce Long to list with count


        Map<Long, Long> leftMap = new HashMap<>();
        long numberOfGeometricPairs = 0;
        for (long val : arr) {
            long countLeft = 0;
            long countRight = 0;
            long lhs = 0;
            long rhs = val * r;
            if (val % r == 0) {
                lhs = val / r;
            }

            if (!rightMap.containsKey(val))
                continue;  // if not right item go  next.

            Long occurence = rightMap.get(rhs);
            rightMap.put(val, occurence - 1L); // reduce current item count in right list  1 reduced 0

            if (rightMap.containsKey(rhs)) {      // get current item at right item count 2 has two,  get 2 right time 1.
                countRight = rightMap.get(rhs);
            }
            if (leftMap.containsKey(lhs)) {      // if left has rate R item, get 2 left itme
                countLeft = leftMap.get(lhs);
            }
            numberOfGeometricPairs += countLeft * countRight; // 1 item has not left item and two right rated item 2. no pair
            if (!leftMap.containsKey(val)) {                  // add 1 item to left list.
                leftMap.put(val, 1L);
            } else {
                Long occurenceLeft = leftMap.get(val);
                leftMap.put(val, occurenceLeft + 1L);
            }


        }
        return numberOfGeometricPairs;
    }

    // 3-4 Frequency Queries

    static void freqQueryTest() {
        List<List<Integer>> queries = Arrays.asList(
                Arrays.asList(1, 1),
                Arrays.asList(2, 2),
                Arrays.asList(3, 2),
                Arrays.asList(1, 1),
                Arrays.asList(1, 1),
                Arrays.asList(2, 1),
                Arrays.asList(3, 2)
        );

        List<List<Integer>> queries1 = Arrays.asList(
                Arrays.asList(1, 5),
                Arrays.asList(1, 6),
                Arrays.asList(3, 2),
                Arrays.asList(1, 10),
                Arrays.asList(1, 10),
                Arrays.asList(1, 6),
                Arrays.asList(2, 5),
                Arrays.asList(3, 2)
        );

        List<Integer> ret = freqQuery(queries1); // ret [0.1]
    }


    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> dataArray = new HashMap();
        List<Integer> ret = new ArrayList<Integer>();
        Map<Integer, Integer> freq = new HashMap();

        for (List<Integer> ops : queries) {
            int op = ops.get(0);
            int key = ops.get(1);

            if (op == 1) {
                int count = 1;
                if (dataArray.containsKey(key)) {
                    count = dataArray.get(key) + 1;
                }
                dataArray.put(key, count);
            } else if (op == 2) {
                if (dataArray.containsKey(key)) {
                    int count = dataArray.get(key);
                    count--;
                    if (count == 0)
                        dataArray.remove(key);
                    else
                        dataArray.put(key, count);
                }
            } else if (op == 3) {

                if (dataArray.containsValue(key))
                    ret.add(1);
                else
                    ret.add(0);
            }
        }
        return ret;
    }

    //4 Sorting:

    //4-1 Sorting: Bubble Sort

    //https://www.hackerrank.com/challenges/ctci-bubble-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    // Complete the countSwaps function below.
    static void countSwapsTest() {
        int[] arr = {3, 2, 1};
        countSwaps(arr);
    }

    static void countSwaps(int[] a) {
        int numSwaps = 0;
        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    numSwaps++;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    //swap(a[j], a[j + 1]);
                }
            }
        }
        System.out.printf("Array is sorted in %s swaps.\n", numSwaps);
        System.out.printf("First Element: %s\n", a[0]);
        System.out.printf("Last Element: %s\n", a[a.length - 1]);

    }

    //https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    //4-2 Sorting: Comparator

    //    Java Comparator interface is used to order the objects of a user-defined class.
    //    This interface is found in java.util package and contains 2 methods compare(Object obj1,Object obj2) and equals(Object element).
    //    It provides multiple sorting sequences, i.e., you can sort the elements on the basis of any data member, for example, rollno, name, age or anything else.


    static class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    static void ComparatorTest() {   // Comparator<T> java.unit.

        //Sorting: Comparator

        int n = 5;
        Player[] player = new HackerRank.Player[]
                {
                        new HackerRank.Player("amy", 100),
                        new HackerRank.Player("david", 100),
                        new HackerRank.Player("heraldo", 50),
                        new HackerRank.Player("aakansha", 75),
                        new HackerRank.Player("aleksa", 150)
                };

        Checker checker = new HackerRank.Checker();

        Arrays.sort(player, checker);

        System.out.printf("\nSorts: \n");
        for (int i = 0; i < player.length; i++) {
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }

    }

    static class Checker implements Comparator<Player> {   // Comparator<T> java.unit.
        // complete this method
        public int compare(Player a, Player b) {
            if (a.score > b.score) return -1;
            else if (a.score < b.score) return 1;
            else return a.name.compareTo(b.name);
        }
    }

    //https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    //4-3 Fraudulent Activity Notifications

    static void activityNotificationsTest() {
        int[] expenditure = {2, 3, 4, 2, 3, 6, 8, 4, 5};
        int d = 5;  // ret = 2

        //int d = 3;  // ret = 1
        //int[] expenditure = {10, 20, 30,40, 50};
        int ret = activityNotifications(expenditure, d); // ret = 2

    }

    // Complete the activityNotifications function below.

    static int activityNotifications(int[] expenditure, int d) {

        int[] midArray = new int[d];

        int iCount = 0;
        for (int i = d; i < expenditure.length; i++) {
            System.arraycopy(expenditure, i - d, midArray, 0, d);
            Arrays.sort(midArray);
            int mid = midArray[d / 2];
            if (expenditure[i] >= mid * 2)
                iCount++;
        }
        return iCount;
    }

/*
vector<string> split_string(string);

int getTwiceMedian( vector<int> &A, vector<int> &count,int d)
{
    vector<int> countfreq(count);

    for(int i=1; i< countfreq.size(); i++)// O(1);
    {
        countfreq[i] += countfreq[i-1];
    }

    int median;
    int a = 0;
    int b = 0;

    //d is even -> median = a+b
    if(d%2==0){
        int first = d/2;
        int second = first+1;
        int i = 0;
        for(;i<201;i++){
            if(first<=countfreq[i]){
                a = i;
                break;
            }
        }
        for(;i<201;i++){
            if(second<=countfreq[i]){
                b = i;
                break;
            }
        }
    }
    else    //d is odd -> median = a + 0 = 2*(middle elem)
    {
        int first = d/2 + 1;
        for(int i=0;i<201;i++){
            if(first<=countfreq[i]){
                a = 2*i;
                break;
            }
        }
    }
    median = a + b;
    return median;
}
int activityNotifications(vector<int> A, int d) {
    int alerts = 0;
    vector<int> count(201, 0); //stores count of last 'd' numbers
    int n = A.size();

    for(int i =0; i<d; i++)
    {
        count[A[i]]++;
    }
    for(int i=d; i< n; i++)
    {
        int median = getTwiceMedian(A, count, d);
        if(A[i]>= median) alerts++;

        //update count array
        count[A[i]]++;
        count[A[i-d]]--;
    }
    return alerts;
}

 */

    //https://www.hackerrank.com/challenges/ctci-merge-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    //4-4 Merge Sort: Counting Inversions

    static void countInversionsTest() {
        //Sorting: Counting Inversions
        int[] arr = new int[]{2, 1, 3, 1, 2};
        countInversions(arr);
    }
    // https://www.hackerrank.com/challenges/ctci-merge-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    // Merge Sort: Counting Inversions
    // [2, 1, 3, 1, 2] => (1 swift) => [1, 2, 3, 1, 2] => (2 swift) => [1, 1, 2, 3, 2] => (1 swift) => [1, 1, 2, 2, 3]

    /*
        static long countInversions(int[] arr){

            return mergeSort(arr, 0, arr.length - 1);
        }

        static long mergeSort(int[] arr, int start, int end){
            if(start == end)
                return 0;
            int mid = (start + end) / 2;
            long count = 0;
            count += mergeSort(arr, start, mid); //left inversions
            count += mergeSort(arr, mid + 1, end);//right inversions
            count += merge(arr, start, end); // split inversions.
            return count;
        }

        static long merge(int[] arr, int start, int end){
            int mid = (start + end) / 2;
            int[] newArr = new int[end - start + 1];
            int curr = 0;
            int i = start;
            int j = mid + 1;
            long count = 0;
            while(i <= mid && j <=end) {
                if(arr[i] > arr[j]) {
                    newArr[curr++] = arr[j++];
                    count += mid - i + 1; // Tricky part.
                }
                else
                    newArr[curr++] = arr[i++];
            }
            // Leftover elements here.
            while(i <= mid) {
                newArr[curr++] = arr[i++];
            }

            while(j <= end) {
                newArr[curr++] = arr[j++];
            }

            System.arraycopy(newArr, 0, arr, start, end - start + 1); // Usual stuff from merge sort algorithm with arrays.
            return count;
        }
    */
    private static long countInversions(int[] arr) {
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


    //https://www.hackerrank.com/challenges/mark-and-toys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    //4-5 maximumToys
    static void maximumToysTest() {
        int[] prices = {1, 12, 5, 111, 200, 1000, 10};
        int k = 50;
        maximumToys(prices, k);
    }

    static int maximumToys(int[] prices, int k) {
        int iCount = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Arrays.sort(prices);

        for (int i = 0; i < prices.length; i++) {
            k = k - prices[i];
            if (k <= 0)
                break;
            iCount++;
        }
        return iCount;
    }

    // 5 String Manipulation

    // 5-1 Sherlock and the Valid String
    // Complete the isValid function below.
    static void isValidTest() {
        //String s ="abcdefghhgfedecba"; //YES  only one diffence
        //String s ="aabbccddeefghi"; //NO  tow more freq > 0 difference
        //String s ="aaaaabc";  //NO two  > 0 more difference

        String s = "aaaabbcc";  //NO
        String ret = isValid(s);
    }

    static String isValid(String s) {

        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        Arrays.sort(freq);

        for (int i = 0; i < 26; i++) {
            int it = freq[26 - i - 1];
            if (it == 0)
                break;
            if (freqMap.containsKey(it)) {
                int iCount = freqMap.get(it);
                freqMap.put(it, ++iCount);
            } else {
                if (freqMap.size() >= 2) {
                    return "NO";
                }
                freqMap.put(it, 1);
            }
        }

        return "YES";

    }

    // 5-2 Strings: Making Anagrams

    // Complete the makeAnagram function below.
    static void makeAnagramTest() {
        String a = "cde", b = "abc";
        makeAnagram(a, b);
    }

    static int makeAnagram(String a, String b) {
        int iCount = 0;
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        for (char c : a.toCharArray())
            freq[c - 'a']++;
        for (char c : b.toCharArray())
            freq[c - 'a']--;
        for (int val : freq)
            iCount += java.lang.StrictMath.abs(val);
        return iCount;
    }


    // 5-3 Alternating Characters
    static void alternatingCharactersTest() {
        String s = "ABABABAB";
        alternatingCharacters(s);
    }

    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
        int iCount = 0;
        for (int i = 1; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c0 = s.charAt(i - 1);
            if (c1 == c0)
                iCount++;
        }
        return iCount;
    }

    // 5-4 Special String Again

    static void substrCountTest() {
        String s = "asasd";
        int n = s.length(); // ret 7

        long ret = substrCount(n, s);

    }

    static class Point {
        public char key;
        public long count;

        public Point(char x, long y) {
            key = x;
            count = y;
        }
    }

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        s = s + " ";
        ArrayList<Point> l = new ArrayList<Point>();
        long count = 1;  // put each chr to list and count continue chars in the point. if all diff count = 1
        char ch = s.charAt(0);
        for (int i = 1; i <= n; i++) {
            if (ch == s.charAt(i))
                count++;
            else {
                l.add(new Point(ch, count));
                count = 1;
                ch = s.charAt(i);
            }
        }
        count = 0;
        if (l.size() >= 3) {
            Iterator<Point> itr = l.iterator();
            Point prev, curr, next;
            curr = (Point) itr.next();
            next = (Point) itr.next();
            count = (curr.count * (curr.count + 1)) / 2;
            for (int i = 1; i < l.size() - 1; i++) {
                prev = curr;
                curr = next;
                next = itr.next();
                count += (curr.count * (curr.count + 1)) / 2;
                if (prev.key == next.key && curr.count == 1)
                    count += prev.count > next.count ? next.count : prev.count;
            }
            count += (next.count * (next.count + 1)) / 2;
        } else {
            for (Point curr : l) {
                count += (curr.count * (curr.count + 1)) / 2;
            }
        }
        return count;
    }


    // 5-5 Common Child
    static void commonChildTest() {
        String a = "HARRY";
        String b = "SALLY";
        LCS = new int[a.length()][a.length()];
        compute(a, b, 0, a.length() - 1, 0, a.length() - 1);

    }

    class StringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            if (a.length() > b.length())
                return -1;
            else if (a.length() < b.length())
                return 1;
            return 0;

        }
    }

    public static int[][] LCS;

    public static int Max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int compute(String a, String b, int start, int end, int s2, int end2) {
        if (start > end || s2 > end2) {
            return 0;
        } else {
            if (a.charAt(start) == b.charAt(s2)) {
                if (LCS[start][s2] == 0)
                    return LCS[start][s2] = (1 + compute(a, b, start + 1, end, s2 + 1, end2));
                else
                    return LCS[start][s2];
            } else {
                if (LCS[start][s2] == 0)
                    return LCS[start][s2] = Max(compute(a, b, start + 1, end, s2, end2), compute(a, b, start, end, s2 + 1, end2));
                else
                    return LCS[start][s2];

            }
        }

    }

    //https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
    // 6. Greedy Algorithms

    //6-1
    static void minimumAbsoluteDifferenceTest() {
        int[] arr = {1, -3, 71, 68, 17,};  // ret 3
        //int[] arr = {-59, -36, -13, 1, -53, -92, -2, -96, -54, 75}; //ret 1
        int ret = minimumAbsoluteDifference(arr);
    }

    static int minimumAbsoluteDifference(int[] arr) {
        int minDiff = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            int absDiff = Math.abs(arr[i + 1] - arr[i]);
            if ((minDiff == 0) || (absDiff < minDiff))
                minDiff = absDiff;
            if (minDiff == 0)
                return 0;
        }
        return minDiff;

    }

    //https://www.hackerrank.com/challenges/greedy-florist/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
    //6-2 Greedy Florist
    static void getMinimumCostTest() {

/*   Python codes
def getMinimumCost(k, c):
        c_len = len(c)
        total = sum(c)
        if k >= c_len:
                return total

        c_s = sorted(c, reverse=True)
        total = sum(c_s[:k])
        c_diff = c_s[k:]
        i = 1
        j = 1
        for c_i in c_diff:
                total += c_i * (1 + i)
                if j < k:
                        j += 1
                else:
                        i += 1
                        j = 1

        return total
 */

/*
        int iCost = 0;
        for(int i=0; i < c.length; i++){
            iCost = iCost + c[i];
            if(i < c.length-k)
                iCost = iCost + c[i];
        }
        return iCost;

 */
    }

    //https://www.hackerrank.com/challenges/angry-children/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
    //6-3 Max Min
    static void MaxMinTest() {
        int[] arr = {1, 2, 3, 4, 10, 20, 30, 40, 100, 200};  // ret 3
        int k = 4;
        int ret = maxMin(k, arr);
    }

    static int maxMin(int k, int[] arr) {
        int iMin = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - k + 1; i++) {
            int diff = arr[i + k - 1] - arr[i];
            if (diff < iMin)
                iMin = diff;
        }
        return iMin;

    }

    //https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
    //6-4  Reverse Shuffle Merge
    static void reverseShuffleMergeTest() {
        //String s = "abcdefgabcdefg";  //ret agfedcb
        //String s ="eggegg"; // ret egg
        //String s = "aeiouuoiea"; // ret aeiou
        String s = "bdabaceadaedaaaeaecdeadababdbeaeeacacaba"; //aaaaaabaaceededecbdb
        String ret = reverseShuffleMerge(s);
    }

    ;

    static String reverseShuffleMerge(String s) {

/*

def frequency(s):
    res = defaultdict(int)
    for char in s:
        res[char] += 1
    return res

# Complete the reverseShuffleMerge function below.
def reverse_shuffle_merge(s):
    char_freq = frequency(s)
    used_chars = defaultdict(int)
    remain_chars = dict(char_freq)
    res = []

    def can_use(char):
        return (char_freq[char] // 2 - used_chars[char]) > 0

    def can_pop(char):
        needed_chars = char_freq[char] // 2
        return used_chars[char] + remain_chars[char] - 1 >= needed_chars

    for char in reversed(s):
        if can_use(char):
            while res and res[-1] > char and can_pop(res[-1]):
                removed_char = res.pop()
                used_chars[removed_char] -= 1

            used_chars[char] += 1
            res.append(char)

        remain_chars[char] -= 1

    return "".join(res)

*/
        return "";
    }

    //https://www.hackerrank.com/challenges/luck-balance/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
    //6-5 Luck Balance
    static void luckBalanceTest() {
        int[][] contests = {{5, 1}, {2, 1}, {1, 1}, {8, 1}, {10, 0}, {5, 0}};
        int k = 3;  // ret 29
        luckBalance(k, contests);
    }

    static int luckBalance(int k, int[][] contests) {
        int lucky = 0;
        int importCount = 0;
        int[] importsContest = new int[contests.length];
        for (int i = 0; i < contests.length; i++) {
            lucky = lucky + contests[i][0];
            if (contests[i][1] == 1)
                importsContest[importCount++] = contests[i][0];
        }
        Arrays.sort(importsContest);
        int loss = importCount - k;
        for (int j = 0; j < loss; j++) {
            lucky = lucky - importsContest[importsContest.length - importCount + j] * 2;
        }

        return lucky;
    }

    // 7. Search


    //https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-1 Hash Tables: Ice Cream Parlor
    static void whatFlavorsTest() {
        int[] cost = {1, 4, 5, 3, 2};  // ret 3 2
        int money = 4;
        whatFlavors(cost, money);
    }

    static void whatFlavors(int[] cost, int money) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < cost.length; i++) {
            int target = money - cost[i];
            if (map.containsKey(target)) {
                int index = map.get(target);
                System.out.printf("%s %s\n", index, i + 1);
                break;
            }
            map.put(cost[i], i + 1);

        }
    }

    //https://www.hackerrank.com/challenges/pairs/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-2 Pairs
    static void pairsTest() {
        int k = 2;
        int[] arr = {1, 5, 3, 4, 2};  //ret = 3
        int ret = pairs(k, arr);

    }

    static int pairs(int k, int[] arr) {

        int iCount = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], 1);
        }
        for (int j = 0; j < arr.length; j++) {
            int iTarget = arr[j];
            if (map.containsKey(iTarget + k)) {
                iCount++;
            }

        }
        return iCount;

    }

    //https://www.hackerrank.com/challenges/triple-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-3 Triple sum
    static void tripletsTest() {
//        int[] a = {1, 3, 5};
//        int[] b = {2, 3};
//        int[] c = {1, 2, 3};   // ret = 8

        int[] a = {1, 4, 5};
        int[] b = {2, 3, 3};
        int[] c = {1, 2, 3};   // ret = 5

        long ret = triplets(a, b, c);
    }

    static long triplets(int[] a, int[] b, int[] c) {

        Arrays.sort(a);
        a = Arrays.stream(a).distinct().toArray();
        Arrays.sort(b);
        b = Arrays.stream(b).distinct().toArray();
        Arrays.sort(c);
        c = Arrays.stream(c).distinct().toArray();
        int aIndex = 0;
        int bIndex = 0;
        int cIndex = 0;
        long result = 0;

        while (bIndex < b.length) {
            while (aIndex < a.length && a[aIndex] <= b[bIndex])
                aIndex++;

            while (cIndex < c.length && c[cIndex] <= b[bIndex])
                cIndex++;

            // you should convert int to long first,
            // avoid int overflow and lead to fail the test case 4
            result += ((long) aIndex) * ((long) cIndex);
            bIndex++;
        }
        return result;
    }

    //https://www.hackerrank.com/challenges/minimum-time-required/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-3 Minimum Time Required
    static void minTimeTest() {
        long[] machines = {2, 3, 2};  // ret = 8
        long goal = 10;
        long ret = minTime(machines, goal);
    }

    static long minTime(long[] machines, long goal) {

        Arrays.sort(machines); //sort the machine array
        long max = (machines[machines.length - 1]) * goal;
        long min = 0;
        long result = -1;
        //do a binary tree search
        while (max > min) {
            long midValue = (max + min) / 2;
            long unit = 0;
            for (long machine : machines) {
                unit += midValue / machine;
            }

            if (unit < goal) {
                min = midValue + 1;
                result = midValue + 1;
            } else {
                max = midValue;
                result = midValue;
            }

        }

        return result;
    }

    //https://www.hackerrank.com/challenges/maximum-subarray-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-4 Maximum Subarray Sum

    static void maximumSumTest() {
        //long[] a = {3, 3, 9, 9, 5}; //ret = 6
        //long m = 7;
        long[] a = {1, 5, 9};
        long m = 5;
        long ret = maximumSum(a, m);
    }

    static long maximumSum(long[] a, long m) {

        long maxMod = Integer.MIN_VALUE;
        long n = a.length;
        long maxSoFar = a[0];
        long maxEndingHere = a[0];

        for (int i = 1; i < n; i++) {
            if (a[i] % m > maxMod)
                maxMod = a[i] % m;
        }


        for (int i = 1; i < n; i++) {
            if (maxEndingHere + a[i] < a[i]) {
                maxEndingHere = a[i];
            } else {
                maxEndingHere = maxEndingHere + a[i];
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                if (maxSoFar % m > maxMod)
                    maxMod = maxSoFar % m;
            }
        }
        return maxMod;

/*
long maxSum(vector<long> a, long m) {
    long sum = 0, max = LONG_MIN, result = LONG_MAX;
    set<long> s;

    for (int i = 0; i < a.size(); i++) {
        sum = (sum + a[i]) % m;
        a[i] = sum;
        max = std::max(max, sum);
    }

    for (auto x: a) {
        auto p = s.insert(x);
        if (++p.first != s.end()) {
            result = min(*p.first - x, result);
        }
    }

    return std::max(max, m - result);
}
 */


    }

    //https://www.hackerrank.com/challenges/making-candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-5 Making Candies
    static void minimumPassesTest() {
        long m = 3, w = 1, p = 2, n = 12;
        long ret = minimumPasses(m, w, p, n);
    }

    static long minimumPasses(long m, long w, long p, long n) {
        long passes = 0;
        long candy = 0;
        long run = Integer.MAX_VALUE;
        long step;

        while (candy < n) {
            step = (m > Integer.MAX_VALUE / w) ? 0 : (p - candy) / (m * w);

            if (step <= 0) {
                long mw = candy / p;

                if (m >= w + mw) {
                    w += mw;
                } else if (w >= m + mw) {
                    m += mw;
                } else {
                    long total = m + w + mw;
                    m = total / 2;
                    w = total - m;
                }

                candy %= p;
                step = 1;
            }

            passes += step;

            if (step * m > Integer.MAX_VALUE / w) {
                candy = Integer.MAX_VALUE;
            } else {
                candy += step * m * w;
                run = Math.min(run, (long) (passes + Math.ceil((n - candy) / (m * w * 1.0))));
            }
        }

        return Math.min(passes, run);
    }


    //https://www.hackerrank.com/challenges/swap-nodes-algo/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
    //7-6 Swap Nodes [Algo]
    static void swapNodesTest() {
        //int[][] indexes = {{2,3}, {4, -1}, {5, -1}, {6, -1}, {7, 8}, {-1,9}, {-1, -1}, {10, 11}, {1, -1}, {-1, -1}, {-1, -1}};
        //int[] queries = {2};    // ret {{3, 1, 2}, {2, 1, 3}}
        int[][] indexes = {{2, 3}, {-1, 4}, {-1, 5}, {-1, -1}, {-1, -1}};
        int[] queries = {2, 4};  // {{2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10}, {2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11}}

        swapNodes(indexes, queries);
    }

    static TreeNode traverseAndSwift(TreeNode root, int query, int level, Vector<Integer> path) { // Each child of a tree is a root of its subtree.
        if (level == query) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

        if (root.left != null) {
            traverseAndSwift(root.left, query, level + 1, path);
        }
        //System.out.println(root);
        path.add(root.data);

        if (root.right != null) {
            traverseAndSwift(root.right, query, level + 1, path);
        }

        return root;

    }

    static TreeNode insertLevelOrder(TreeNode root, int[][] indexes, int leave, int index) {
        if (leave < indexes.length) {
            int childIndex = 0;
            // insert left child
            if (indexes[leave][0] == -1)
                root.left = null;
            else {
                TreeNode leftNode = new TreeNode(indexes[leave][0]);
                root.left = insertLevelOrder(leftNode, indexes, leave + 1, 0);
            }

            // insert right child
            if (indexes[leave][1] == -1)
                root.right = null;
            else {
                TreeNode rightNode = new TreeNode(indexes[leave][1]);
                root.right = insertLevelOrder(rightNode, indexes, leave + 2, 1);
            }
        }

        return root;
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int[][] ret = new int[queries.length][];
        TreeNode root = new TreeNode(1);
        TreeNode temp = insertLevelOrder(root, indexes, 0, 0);

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            Vector<Integer> path = new Vector<Integer>();
            temp = traverseAndSwift(temp, query - 1, 0, path);
            ret[i] = new int[path.size()];
            for (int j = 0; j < path.size(); j++) {
                ret[i][j] = path.get(j);
            }
        }

        return ret;
    }


    // 8. Dynamic Programming

    //https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    //8-1 Max Array Sum
    static void maxSubsetSumTest() {
        int[] arr = {3, 7, 4, 6, 5}; // ret = 13
        int ret = maxSubsetSum(arr);
    }

    static int maxSubsetSum(int[] arr) {

        if (arr.length == 0)
            return 0;
        arr[0] = Math.max(0, arr[0]);

        if (arr.length == 1)
            return arr[0];
        arr[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++)
            arr[i] = Math.max(arr[i - 1], arr[i] + arr[i - 2]);

        return arr[arr.length - 1];
    }

    //https://www.hackerrank.com/challenges/candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    //18-2 Candies  给定一个整数数组，相邻元素
    static void CandiesTest() {
//        int[] arr = {1, 2, 2};  // ret 4
        //int[] arr = {2, 4, 2, 6, 1, 7, 8, 9, 2, 1};  // ret 19

        int n = 100000;   //5000050000
        long[] arr = new long[n];

        for (int i = 0; i < n - 1; i++) {
            arr[i] = n - i;
        }
        long ret = candies(n, arr);

    }

    static long candies(int n, long[] arr) {
        long sum = 0;
        long[] candies = new long[n];
        Arrays.fill(candies, 1);
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] > arr[i])
                candies[i + 1] = candies[i] + 1;
        }
        for (int i = n - 1; i > 0; i--) {
            if ((arr[i - 1] > arr[i]) && (candies[i - 1] <= candies[i]))
                candies[i - 1] = candies[i] + 1;
        }

        for (int i = 0; i < n; i++) {
            sum = sum + candies[i];
        }

        return sum;
    }

    //https://www.hackerrank.com/challenges/decibinary-numbers/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    //18-3 Decibinary Number
    static void decibinaryNumbersTest() {
        long x = 10;
        long ret = decibinaryNumbers(x);
    }

    static long decibinaryNumbers(long x) {
        long count = 0;
        String s = String.valueOf(x);
        long sq = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            String sn = s.substring(i, i + 1);
            int n = Integer.valueOf(sn);
            count = count + n * sq;
            sq = sq * 2;
        }

        return count;
    }

    //https://www.hackerrank.com/challenges/abbr/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    //18-4 Abbreviation
    static void abbreviationTest() {
        int length = 6;
        String[] a = new String[length];
        String[] b = new String[length];
        String[] c = new String[length];
        a[0] = "daBcd";
        b[0] = "ABC";  //ret YES
        c[0] = "YES";

        a[1] = "AbcDE";
        b[1] = "ABDE";  //ret Yes, b-B, delete c at "AbcDE"
        c[1] = "YES";

        a[2] = "AfPZN";
        b[2] = "APZNC";  // ret NO 'C' is not at "AfPZN"
        c[2] = "NO";

        a[3] = "bBccC"; //YES
        b[3] = "BBC";
        c[3] = "YES";

        a[4] = "sYOCa";   //NO
        b[4] = "YOCN";
        c[4] = "NO";

        a[5] = "OPZFFVQLADBQFBXLOSUMZZWQUKASCUVQZZVWfPIRTytlvpijddqegbwitkhhsbuehtnpndvcandzjzyepvlnkayfkwzegvbratvwezddjqxrxocqgcghuohlmsondvicocltqhvqfqjpctxfomjoukrheijhhndcbipiobvpbskemgykepokluwqhhejdaimvdvlegfyrrwckgojsbsxmsvhhrlnvcrxfaxinjzsjgvvrlcczqlkvgtftsvktvhtfpaklumhkovphilrappbvkarfhvwxxtrugypracozyqyvaqjityoiyemyavpbchaoagrvujocpueczsgcqdjvkjckxhmnaseshjgecusrxozuxgeieleewwskmiprlqnshvmcp";
        b[5] = "OPZFFVQLADBQFBXLOSUMZZWQUKASCUVQZZVWPIRT"; //YES
        c[5] = "YES";

        String ret = "";
        for (int i = 0; i < length; i++) {
            ret = abbreviation(a[i], b[i]);
            if (ret != c[i])
                ret = "NO";
        }
    } //no passed

    static String abbreviation(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();

        if (bLength > aLength)
            return "NO";   //a could not remove any more to match

        int j = 0;
        String aNewStr = "";
        String bNewStr = "";
        for (int i = 0; i < bLength; i++) {
            char bch = b.charAt(i);
            String bstr = String.valueOf(bch);
            int index = a.indexOf(bstr, j);
            if (index >= 0) {
                aNewStr = aNewStr + a.substring(j, index);
                j = index + 1;
            } else {
                bNewStr = bNewStr + bstr;

            }
        }

        if (j < aLength)
            aNewStr = aNewStr + a.substring(j, aLength);

        j = 0;
        String aFinal = "";
        bLength = bNewStr.length();
        aLength = aNewStr.length();
        for (int i = 0; i < bLength; i++) {
            char bch = bNewStr.charAt(i);
            char bchL = (char) ((int) (bch) + 32);
            String bstr = String.valueOf(bch);
            String bstrL = String.valueOf(bchL);
            int indexL = aNewStr.indexOf(bstrL, j);
            int index = aNewStr.indexOf(bstr, j);

            if ((index >= 0) && (indexL >= 0))
                index = Math.min(index, indexL);
            else if (indexL >= 0)
                index = indexL;

            if (index >= 0) {
                aFinal = aFinal + aNewStr.substring(j, index);
                j = index + 1;
            } else {
                return "NO";
            }

        }
        if (j < aLength)
            aFinal = aFinal + aNewStr.substring(j, aLength);
        for (int l = 0; l < aFinal.length(); l++) {
            char chF = aFinal.charAt(l);
            if ((chF >= 'A') && (chF <= 'Z'))
                return "NO";
        }

        return "YES";
    }

    // 9. Stacks and Queues

    //https://www.hackerrank.com/challenges/poisonous-plants/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
    // 9-1 Poisonous Plants
    static void poisonousPlantsTest() {
        int[] p = {6, 5, 8, 4, 7, 10, 9}; // ret 2
        int ret = poisonousPlants(p);
    }

    static int poisonousPlants(int[] p) {
        int n = p.length;

        int[] days = new int[n];
        int min = p[0];
        int max = 0;
        Stack<Integer> s = new Stack<Integer>();
        s.push(0);

        for (int i = 1; i < n; i++) {

            if (p[i] > p[i - 1])
                days[i] = 1;

            min = min < p[i] ? min : p[i];

            while (!s.empty() && p[s.peek()] >= p[i]) {
                if (p[i] > min) {
                    days[i] = days[i] > (days[s.peek()] + 1) ? days[i] : (days[s.peek()] + 1);
                }
                s.pop();

            }

            max = max > days[i] ? max : days[i];
            s.push(i);

        }

        return max;
    }

    // 9-2 Queues: A Tale of Two Stacks

    static void MyQueueTest() {
//        https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues

        System.out.printf("Queue: peak \n");
        MyQueue<Integer> queue = new MyQueue<Integer>();

        int[] operate = {1, 42, 2, 0, 1, 14, 3, 0, 1, 28, 3, 0, 1, 60, 1, 72, 2, 0, 2, 0};

        for (int i = 0; i < operate.length; i = i + 2) {
            int operation = operate[i];
            if (operation == 1) { // enqueue
                queue.enqueue(operate[i + 1]);
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }

    }

    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        //A stack is a collection (queue) that is based on the last-in-first-out (LIFO) policy

        //Need change to operate to  First in First out (FIFO) in the queue
        public void enqueue(T value) { // Push (add) onto newest stack queue end.
            stackNewestOnTop.push(value);
        }

        public T peek() {   // print first in item in queue
            prepOld();
            return stackOldestOnTop.peek();
        }

        public T dequeue() { // delete first in item in queue
            prepOld();
            return stackOldestOnTop.pop();
        }

        public void prepOld() {   // push newQueue to oldQueue to move first in item to top
            if (stackOldestOnTop.isEmpty())
                while (!stackNewestOnTop.isEmpty())
                    stackOldestOnTop.push(stackNewestOnTop.pop());
        }
    }

    //https://www.hackerrank.com/challenges/largest-rectangle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
    //0-3 Largest Rectangle
    static void largestRectangleTest() {
        //int[] h = {1, 2, 3, 4, 5};  // ret 9
        int[] h = {1, 3, 5, 9, 11};  // ret 18
        long ret = largestRectangle(h);
    }

    static long largestRectangle(int[] h) {

        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < h.length) {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || h[s.peek()] <= h[i])
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = h[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false) {
            tp = s.peek();
            s.pop();
            area_with_top = h[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;

    }

    //https://www.hackerrank.com/challenges/min-max-riddle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
    //9-4 Min Max Riddle


    static void riddleTest() {
        long[] arr = {2, 6, 1, 12};  // ret {12, 2, 1, 1}
        long[] ret = riddle(arr);
    }

    static long[] riddle(long[] arr) {
        long[] lefts = buildLefts(arr);
        long[] rights = buildRights(arr);

        SortedMap<Integer, Integer> numberToLength = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            numberToLength.put((int) arr[i], Math.max(
                    numberToLength.getOrDefault((int) arr[i], -1),
                    (int) (lefts[i] + rights[i] + 1)));
        }

        Iterator<Integer> iter = numberToLength.keySet().iterator();
        int number = iter.next();
        long[] result = new long[arr.length];
        for (int i = 0; i < result.length; i++) {
            while (numberToLength.get(number) <= i) {
                number = iter.next();
            }

            result[i] = number;
        }
        return result;
    }

    static long[] buildLefts(long[] arr) {
        long[] lefts = new long[arr.length];

        Stack<Integer> indices = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!indices.empty() && arr[i] <= arr[indices.peek()]) {
                indices.pop();
            }

            lefts[i] = i - (indices.empty() ? -1 : indices.peek()) - 1;
            indices.push(i);
        }

        return lefts;
    }

    static long[] buildRights(long[] arr) {
        long[] rights = new long[arr.length];

        Stack<Integer> indices = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!indices.empty() && arr[i] <= arr[indices.peek()]) {
                indices.pop();
            }

            rights[i] = (indices.empty() ? arr.length : indices.peek()) - i - 1;
            indices.push(i);
        }

        return rights;
    }

    //https://www.hackerrank.com/challenges/castle-on-the-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
    //9-5 Castle on the Grid
    static void minimumMovesTest() {

        String[] grid = {".X.", ".X.", "..."};
        int startX = 0, startY = 0, goalX = 0, goalY = 2;
        minimumMoves(grid, startX, startY, goalX, goalY);
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


    //https://www.hackerrank.com/challenges/balanced-brackets/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
    //9-6 Balanced Brackets
    static void isBalancedTest() {
        //String s = "{{[[(())]]}}"; //ret YES
        String s = "{[()]}";        //ret YES
        String ret = isBalanced(s);
    }

    static String isBalanced(String s) {

        int length = 0;
        do {
            length = s.length();
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        } while ((s.length() != 0) && (s.length() != length));
        if (s.length() == 0)
            return "YES";
        else
            return "NO";

    }

    // 10. Graphs

    //https://www.hackerrank.com/challenges/torque-and-development/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
    //10-1 Roads and Libraries
    static void roadsAndLibrariesTest() {

        //int[] data = {3, 3, 2, 1};
        //int[][] cities = {{1, 2}, {3, 1}, {2, 3}};  // ret = 4

//        int[] data = {6, 6, 2, 5};
//        int[][] cities = {{1, 3}, {3, 4}, {2, 4}, {1, 2}, {2, 3}, {5, 6}};  // ret = 12

        int[] data = {5, 3, 6, 1};
        int[][] cities = {{1, 2}, {1, 3}, {1, 4}}; // ret 15

        int c = data[0];
        int r = data[1];
        int c_lib = data[2];
        int c_road = data[3];
        long ret = roadsAndLibraries(c, c_lib, c_road, cities);

    }

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {

        HashMap<Integer, ArrayList<Integer>> cityMap = new HashMap<>();
        int libraryCost = c_lib;
        int roadCost = c_road;
        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(i);
            cityMap.put(i, list);
        }

        for (int a1 = 0; a1 < cities.length; a1++) {
            int x = cities[a1][0];
            int y = cities[a1][1];
            ArrayList<Integer> list1 = cityMap.get(x);
            ArrayList<Integer> list2 = cityMap.get(y);
            if (list1 != list2) {
                list1.addAll(list2);
                list2.forEach(i -> cityMap.put(i, list1));
            }
        }

        long cost = 0;
        if (libraryCost < roadCost)
            return (long) n * libraryCost;
        else {
            for (ArrayList<Integer> list : cityMap.values()) {
                int size = list.size();
                if (size > 0) {
                    cost += libraryCost;
                    cost += (size - 1) * roadCost;
                    list.removeIf(i -> true);
                }
            }
        }
        return cost;
    }

    //https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
    //10-2 Find the nearest clone
    static void graphNodesTest() {
        int graphNodes = 4; // 3
        int[] graphFrom = {1, 1, 4};
        int[] graphTo = {2, 3, 2};

        int[] ids = {1, 2, 1, 1};  //ret 1
        int val = 1;
        int ret = findShortest(graphNodes, graphFrom, graphTo, ids, val);

    }
    // 1. The number of nodes is <name>Nodes.
    // 2. The number of edges is <name>Edges.
    // 3. An edge exists between <name>From[i] to <name>To[i].

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, int[] ids, int val) {

        LinkedList<Integer>[] map = new LinkedList[graphNodes];
        HashMap<Integer, Integer> distances = new HashMap();

        for (int i = 0; i < graphNodes; i++) {
            map[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < graphFrom.length; i++) {
            map[graphFrom[i] - 1].add(graphTo[i]);
            map[graphTo[i] - 1].add(graphFrom[i]);
        }

        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == val) {
                queue.add(i + 1);
                distances.put(i + 1, 0);
            }
        }

        if (queue.size() < 2) {
            return -1;
        }
        HashSet<Integer> seen = new HashSet();
        while (queue.size() > 0) {
            int current = queue.poll();
            seen.add(current);

            for (int a : map[current - 1]) {
                if (distances.containsKey(a) && !seen.contains(a)) {
                    return distances.get(a) + distances.get(current) + 1;
                } else {
                    queue.add(a);
                    distances.put(a, distances.get(current) + 1);
                }
            }
        }
        return -1;

    }


    //https://www.hackerrank.com/interview/interview-preparation-kit/graphs/challenges
    //10-3 BFS: Shortest Reach in a Graph   BFS: Breadth First Search or BFS for a Graph https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
    static void getDistanceTest() {

        int n = 4;
        int m = 2;
        int[][] sc = {{1, 2}, {1, 2}};
//        4 2
//        1 2
//        1 3


        GraphNode g = new GraphNode(n);
        for (int j = 1; j < m; j++) {
            int a = sc[j][0];
            int b = sc[j][1];
            g.addEdge(a - 1, b - 1);
        }

        int s = 1; //sc.nextInt();
        int[] results = new int[n];
        Arrays.fill(results, -1);
        getDistance(g.adjList, results, s - 1);

        for (int k = 0; k < n; k++) {
            if (k != s - 1)
                System.out.print(results[k] + " ");
        }

        //ret 1
    }

    static class GraphNode {
        int sumNodes;
        ArrayList<LinkedList<Integer>> adjList;

        public GraphNode(int numNodes) {
            this.sumNodes = numNodes;
            adjList = new ArrayList<LinkedList<Integer>>();
            for (int i = 0; i < numNodes; i++) {
                adjList.add(new LinkedList<Integer>());
            }
        }

        public void addEdge(int a, int b) {
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
    }

    public static void getDistance(ArrayList<LinkedList<Integer>> adjList, int[] results, int s) {
        LinkedList<Integer> q = new LinkedList();
        boolean[] isVisited = new boolean[adjList.size()];
        q.add(s);
        isVisited[s] = true;
        int count = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int removed = q.poll();
                results[removed] = count;
                for (int x : adjList.get(removed)) {
                    if (!isVisited[x]) {
                        q.add(x);
                        isVisited[x] = true;
                    }
                }
            }
            count += 6;
        }
    }

    //https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
    //10-4 DFS: Connected Cell in a Griph
    static void maxRegionTest() {
        int[][] matrix = {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}};  //ret = 5
        int ret = maxRegion(matrix);
    }

    public static int maxRegion(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, countCells(matrix, i, j));
            }
        }
        return max;
    }

    private static int countCells(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) return 0;
        if (matrix[i][j] == 0) return 0;
        int count = matrix[i][j]--;
        count += countCells(matrix, i + 1, j);
        count += countCells(matrix, i - 1, j);
        count += countCells(matrix, i, j + 1);
        count += countCells(matrix, i, j - 1);
        count += countCells(matrix, i + 1, j + 1);
        count += countCells(matrix, i - 1, j - 1);
        count += countCells(matrix, i - 1, j + 1);
        count += countCells(matrix, i + 1, j - 1);
        return count;
    }

    //Depth First Search or DFS for a Graph  图的深度优先搜索或DFS
    static void dfs(List<int[]> roadList, List<List<int[]>> groupCities) {
        if (roadList.size() == 0)
            return;

        List<int[]> roadListTemp = new ArrayList<int[]>();
        String sRoad = "";
        List<int[]> group = new ArrayList<int[]>();

        for (int[] road : roadList) {
            String start = String.valueOf(road[0]);
            String end = String.valueOf(road[1]);

            if (sRoad == "") {
                sRoad = start + end;
                group.add(road);
                continue;
            }

            if (!sRoad.contains(start) && !sRoad.contains(end)) {
                roadListTemp.add(road);
                continue;
            }

            if (!sRoad.contains(start)) {
                sRoad = sRoad + start;
            }
            if (!sRoad.contains(end)) {
                sRoad = sRoad + end;
            }

            if ((start != "") || (end != "")) {
                group.add(road);
            }
            if ((start == "") && (end == "")) {
                roadListTemp.add(road);
            }
        }
        if (group.size() != 0)
            groupCities.add(group);

        if (roadListTemp.size() == 0)
            return;
        dfs(roadListTemp, groupCities);
        return;
    }


    //https://www.hackerrank.com/challenges/matrix/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
    //10-6 Matrix
    static void minTimeRoadTest() {
        int[][] roads = {{2, 1, 8}, {1, 0, 5}, {2, 4, 5}, {1, 3, 4}}; // ret 10
        int[] machines = {2, 4, 0};
        int ret = minTime(roads, machines);
    }

    // Complete the minTime function below.
    static int minTime(int[][] roads, int[] machines) {

        int n = roads.length + 1;
        int[] sum_edge = new int[n];
        int[] max_edge = new int[n];
        int[] processed_connections = java.util.stream.IntStream.generate(() -> 2).limit(n).toArray();
        boolean[] is_visited = new boolean[n];
        boolean[] is_machine = new boolean[n];
        Arrays.stream(machines).forEach(m -> is_machine[m] = true);
        List<int[]>[] edge_list = java.util.stream.Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
        for (int[] road : roads) {
            edge_list[road[0]].add(road);
            edge_list[road[1]].add(road);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (edge_list[i].size() != 1 || is_visited[i]) continue;

            is_visited[i] = true;
            boolean found = is_machine[i];
            int curr_node = i;
            int[] next_edge = edge_list[curr_node].get(0);
            int min_edge_time = next_edge[2];
            for (boolean finished = false; !finished; ) {
                curr_node = (curr_node == next_edge[0] ? next_edge[1] : next_edge[0]);
                int size = edge_list[curr_node].size();
                if (size == 1) {
                    if (found && is_machine[curr_node]) result += min_edge_time;
                    is_visited[curr_node] = true;
                    finished = true;
                } else if (size > 2) {
                    if (is_machine[curr_node]) {
                        if (found) result += min_edge_time;
                        found = true;
                    } else if (found) {
                        sum_edge[curr_node] += min_edge_time;
                        max_edge[curr_node] = Math.max(max_edge[curr_node], min_edge_time);
                    }
                    next_edge[2] = 0;
                    if (++processed_connections[curr_node] <= size) finished = true;
                    else {
                        boolean has_more = false;
                        for (int[] edge : edge_list[curr_node]) {
                            if (edge[2] != 0) {
                                next_edge = edge;
                                has_more = true;
                                break;
                            }
                        }

                        if (has_more) {
                            if (is_machine[curr_node]) min_edge_time = next_edge[2];
                            else {
                                result += sum_edge[curr_node] - max_edge[curr_node];
                                found = true;
                                min_edge_time = Math.min(max_edge[curr_node], next_edge[2]);
                            }
                        } else finished = true;
                    }
                } else {
                    next_edge = (next_edge == edge_list[curr_node].get(0) ? edge_list[curr_node].get(1) : edge_list[curr_node].get(0));
                    if (!is_machine[curr_node]) min_edge_time = Math.min(min_edge_time, next_edge[2]);
                    else {
                        if (found) result += min_edge_time;
                        found = true;
                        min_edge_time = next_edge[2];
                    }
                }
            }
        }
        return result;
    }

    //https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
    //10-7 Depth First Search or DFS for a Graph
    static void dfsTest(){
        //Input:
        //2                 // tow test cases                                  3
        //5 4               // number of nodes 5 and number of edges 4         |
        //0 1 0 2 0 3 2 4   // edges 0-1, 0-2, 0-3, 2-4                      1-0-2-4
        //4 3               // number of nodes 4 and number of edges 3
        //0 1 1 2 0 3       // edges 0-1, 1-2, 0-3                           0-1-2
                            //                                               |
                            //                                               3


        int[][] edges1 = {{0,1},{0,2},{0,3},{2,4}};
        int index1 = edges1.length-1;
        int[] ret = deepFirstSeraching(index1, edges1);
        int[][] edges2 = {{0,1},{1,2},{0,3}};
        int index2 = edges2.length-1;
        ret = deepFirstSeraching(index2, edges2);

        ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<edges1.length; i++){
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            list1.add(edges1[i][0]);
            list1.add(edges1[i][1]);
            list2.add(list1);
        }
        int N = 5;  // {0, 1, 2, 3, 4}

        ArrayList<Integer> retList = dfs(list2, N);

    }

    static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> g, int N){
        int n = g.size()-1;
        HashMap<Integer, Boolean> visted = new HashMap<Integer, Boolean>();
        int[][] edges = new int[g.size()][2];
        for(int i = 0; i < g.size(); i++){
            ArrayList<Integer> temp = g.get(i);
            edges[i][0] = temp.get(0);
            edges[i][1] = temp.get(1);
        }
        visted = deepFirstSerachingUtil(n, edges, visted);

        ArrayList<Integer> ret = new ArrayList<Integer>();

        for(int key : visted.keySet()){
            ret.add(key);
        }

        return ret;
    }


    static int[] deepFirstSeraching(int index, int[][] edges){
        int[] ret = new int[index+1];
        HashMap<Integer, Boolean> visted = new HashMap<Integer, Boolean>();
        visted = deepFirstSerachingUtil(index, edges, visted);

        //visted = new HashMap<Integer, Boolean>();
        //visted = deepFirstSerachingUtil(index, edges, visted);
        return ret;
    }

    static HashMap<Integer, Boolean> deepFirstSerachingUtil(int index, int[][] edges, HashMap<Integer, Boolean> visted){
        if(index<0)
            return visted;
        int node = edges[index][0];
        if(!visted.containsKey(node))
            visted.put(node, true);
        node = edges[index][1];
        if(!visted.containsKey(node)){
            visted.put(node, true);
        }
        return deepFirstSerachingUtil(--index, edges, visted);
    }

    //https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
    //10-8 Breadth First Search or BFS for a Graph
    static void bfsTest(){

        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        g.BFS(2);
    }

    static class Graph {
        private int V;   // No. of vertices
        private LinkedList<Integer> adj[]; //Adjacency Lists

        // Constructor
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        // Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        // prints BFS traversal from a given source s
        void BFS(int s) {
            // Mark all the vertices as not visited(By default
            // set as false)
            boolean visited[] = new boolean[V];

            // Create a queue for BFS
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // Mark the current node as visited and enqueue it
            visited[s] = true;
            queue.add(s);

            while (queue.size() != 0) {
                // Dequeue a vertex from queue and print it
                s = queue.poll();
                System.out.print(s + " ");

                // Get all adjacent vertices of the dequeued vertex s
                // If a adjacent has not been visited, then mark it
                // visited and enqueue it
                Iterator<Integer> i = adj[s].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }

    // 11. Trees

    // Tree
    // https://www.hackerrank.com/interview/interview-preparation-kit/trees/challenges

    // Lowest Common Ancestor

    static class TreeNode {
        TreeNode(int d) {
            data = d;
        }

        ;
        int data;
        TreeNode left;
        TreeNode right;

    }

    //11-1 Binary Search Tree : Lowest Common Ancestor (lca)

    static void lcaTest() {
        TreeNode root = new TreeNode(4);
        int v1 = 1, v2 = 7;
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);

        lca(root, v1, v2);
    }

    public static TreeNode lca(TreeNode root, int v1, int v2) {
        // Write your code here.

        if ((v1 < root.data) && (v2 < root.data))
            return lca(root.left, v1, v2);
        if ((v1 > root.data) && (v2 > root.data))
            return lca(root.right, v1, v2);
        return root;  //if ((v1 < root.data) && (v2 > root.data))
    }


    //11-2 Tree: Height of a Binary Tree

    static void TreeHeightTest() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);  // ret yes

        int height = height(root);
        System.out.println("\nTree height:");
        System.out.println(height);

    }

    public static int height(TreeNode root) {
        int leftHeight = 0;
        int rightHeight = 0;

        if (root.left != null) {
            leftHeight = 1 + height(root.left);
        }

        if (root.right != null) {
            rightHeight = 1 + height(root.right);
        }

        return leftHeight > rightHeight ? leftHeight : rightHeight;
    }

    //https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
    //11-3 Trees: Is This a Binary Search Tree?
    static void checkBSTTest() { //Hackertrank no main()
/*
//input array: 1 2 3 4 5 6 7
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);  // ret yes
*/
/*
//input array: 1 2 4 3 5 6 7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);  // ret no
*/
// input array: 3 5 7 9 11 13 15
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(5);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(15);  // ret yes

        boolean bBST = checkBST(root);
    }

    public static boolean checkBST(TreeNode root) {
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean check(TreeNode n, int min, int max) {
        if (n == null)
            return true;
        if (n.data <= min || n.data >= max)
            return false;
        boolean left = check(n.left, min, n.data);
        boolean right = check(n.right, n.data, max);
        return left && right;
    }

    //https://www.hackerrank.com/challenges/balanced-forest/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
    //11-4 Tree: Huffman Decoding

    static class HuffmanNode {

        public int frequency; // the frequency of this tree
        public char data;
        public HuffmanNode left, right;

        public HuffmanNode(char chData, int freq) {
            frequency = freq;
            data = chData;
        }
    }


    static void decodeTest() {
        String s = "1001011";
        HuffmanNode root = new HuffmanNode('F', 5);
        root.left = new HuffmanNode('F', 2);
        root.right = new HuffmanNode('A', 3);
        root.left.left = new HuffmanNode('B', 1);
        root.left.right = new HuffmanNode('C', 1);
        decode(s, root);  //output "ABACA"
    }

    static void decode(String s, HuffmanNode root) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode c = root;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i) == '1' ? c.right : c.left;
            if (c.left == null && c.right == null) {
                sb.append(c.data);
                c = root;
            }
        }
        System.out.print(sb);
    }


    //https://www.hackerrank.com/challenges/balanced-forest/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
    //11-5 Balanced Forest

    static void balancedForestTest() {
        int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {4, 5}};
        int[] c = {1, 2, 2, 1, 1};  // ret 19
        long ret = balancedForest(c, edges);
    }

    static class ForestNode {
        long cost;
        boolean visited = false, v2 = false;
        ArrayList<Integer> adjacent = new ArrayList<>();

        @Override
        public String toString() {
            return "Node{" +
                    "cost=" + cost +
                    ", v1=" + visited +
                    ", v2=" + v2 +
                    ", adjacent=" + adjacent +
                    '}';
        }

        public ForestNode(long cost) {
            this.cost = cost;
        }
    }

    static long mini, sum;
    static HashSet<Long> s = new HashSet<>();
    static HashSet<Long> q = new HashSet<>();


    static long balancedForest(int[] node_values, int[][] edges) {
        s = new HashSet<>();
        q = new HashSet<>();

        ArrayList<ForestNode> nodes = new ArrayList<>();
        for (int i = 0; i < node_values.length; i++) {
            nodes.add(new ForestNode(node_values[i]));
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            nodes.get(a).adjacent.add(b);
            nodes.get(b).adjacent.add(a);
        }

        mini = sum = dfs(0, nodes);
        solve(0, nodes);

        return mini == sum ? -1 : mini;
    }

    // s contains sums encountered during depth first search excluding those from the root to the current node.
    // q contains sums encountered during depth first search from the root to current node.
    private static void solve(int p, ArrayList<ForestNode> nodes) {
        ForestNode node = nodes.get(p);
        if (node.v2) return;
        node.v2 = true;

        long[] x = {2 * node.cost,
                2 * sum - 4 * node.cost,
                sum - node.cost};
        long[] y = {3 * node.cost - sum,
                (sum - node.cost) / 2 - node.cost};

        // If removing the edge above the current node (node variable defined at the top of this method)
        // gives two trees whose total values are the same, then the node we add should have that
        // same value too to get 3 trees (one of which is our single node that we added) with the same value.
        if (sum % 2 == 0 && node.cost == (sum / 2)) mini = Math.min(mini, sum / 2);

        // case 1a: When two non-overlapping subtrees in the overall tree have the same total value.
        if (s.contains(node.cost)) {// ||                      // case 1a
//                q.contains(2*node.cost) ) {                  // ?
            if (y[0] >= 0) mini = Math.min(mini, y[0]);
        }

        // case 1b: (part B): Two non-overlapping subtrees in the overall tree.
        // case 2b: One edge to be removed is below the other edge to be removed in the overall tree.
        if (s.contains(sum - 2 * node.cost) ||                 // case 1b (part B)
                q.contains(sum - node.cost)) {                // case 2b
            if (y[0] >= 0) mini = Math.min(mini, y[0]);
        }

        // case 1b: (part A): Two non-overlapping subtrees in the overall tree.
        // case 2a: One edge to be removed is below the other edge to be removed in the overall tree.
        if ((sum - node.cost) % 2 == 0) {
            if (s.contains((sum - node.cost) / 2) ||            // case 1b (part A)
                    q.contains((sum + node.cost) / 2)) {        // case 2a
                if (y[1] >= 0) mini = Math.min(mini, y[1]);
            }
        }

        q.add(node.cost);

        for (int i = 0; i < node.adjacent.size(); i++) {  // DFS!!
            solve(node.adjacent.get(i), nodes);           // recursive call
        }

        q.remove(node.cost);
        s.add(node.cost);
    }
    private static long dfs(int p, ArrayList<ForestNode> nodes) {
        ForestNode node = nodes.get(p);
        if (node.visited) return 0;
        node.visited = true;
        for (int i = 0; i < node.adjacent.size(); i++) {
            node.cost += dfs(node.adjacent.get(i), nodes);
        }
        return node.cost;
    }


    //11-6 Tree Trravesal Test
    public static void TreeTravsalTest()
    {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder(tree.root);

        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder(tree.root);

        System.out.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder(tree.root);
    }

    static class BinaryTree {
        // Root of Binary Tree
        TreeNode root;

        BinaryTree() {
            root = null;
        }

        //Given a binary tree, print its nodes according to the
        //bottom-up" postorder traversal.
        void printPostorder(TreeNode node) {
            if (node == null)
                return;

            // first recur on left subtree
            printPostorder(node.left);

            // then recur on right subtree
            printPostorder(node.right);

            // now deal with the node
            System.out.print(node.data + " ");
        }

        // Given a binary tree, print its nodes in inorder
        void printInorder(TreeNode node) {
            if (node == null)
                return;

            //first recur on left child
            printInorder(node.left);

            //then print the data of node
            System.out.print(node.data + " ");

            //now recur on right child
            printInorder(node.right);
        }

        //Given a binary tree, print its nodes in preorder
        void printPreorder(TreeNode node) {
            if (node == null)
                return;

            //first print data of node
            System.out.print(node.data + " ");

            //then recur on left sutree
            printPreorder(node.left);

            //now recur on right subtree
            printPreorder(node.right);
        }

    }


    // 12. Linked Lists

    // 12-1 Insert a node at a specific position in a linked list

    static void insertNodeAtPositionTest() {
/*
        LinkNode head = new LinkNode(11);
        head.next = new LinkNode(9);
        head.next.next = new LinkNode(19);
        head.next.next.next = new LinkNode(10);
        head.next.next.next.next = new LinkNode(14);

        int data = 20, position = 3;
*/

//        LinkNode head = new LinkNode(1);
//        head.next = new LinkNode(2);
//        head.next.next = new LinkNode(3);
//        head.next.next.next = new LinkNode(4);
//        head.next.next.next.next = new LinkNode(5);
//        head.next.next.next.next.next = new LinkNode(6);
//        int data = 7, position = 5;
        LinkNode head = new LinkNode(16);
        head.next = new LinkNode(13);
        head.next.next = new LinkNode(7);
        int data = 1, position = 2;

        insertNodeAtPosition(head, data, position);
    }

    static LinkNode insertNodeAtPosition(LinkNode head, int data, int position) {
        insertNodeAtPositionUtil(head, data, --position);
        return head;
    }

    static void insertNodeAtPositionUtil(LinkNode head, int data, int position) {

        if (position == 0) {
            LinkNode temp = head.next;
            head.next = new LinkNode(data);
            head.next.next = temp;
        } else {
            insertNodeAtPositionUtil(head.next, data, --position);
        }
        return;
    }

    //https://www.hackerrank.com/challenges/insert-a-node-into-a-sorted-doubly-linked-list/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
    // 12-2 Inserting a Node Into a Sorted Doubly Linked List
    static void sortedInsertTest() {
        LinkNode head = new LinkNode(1);
        head.next = new LinkNode(3);
        head.next.prev = head;
        head.next.next = new LinkNode(4);
        head.next.next.prev = head.next;
        head.next.next.next = new LinkNode(10);
        head.next.next.next.prev = head.next.next;

        int data = 5;

        sortedInsert(head, data);

    }

    static LinkNode sortedInsert(LinkNode head, int data) {
        LinkNode n = new LinkNode(data);
        if (head == null) {
            return n;
        } else if (data <= head.data) {
            n.next = head;
            head.prev = n;
            return n;
        } else {
            LinkNode rest = sortedInsert(head.next, data);
            head.next = rest;
            rest.prev = head;
            return head;
        }
    }


    //12-3 Reverse a doubly linked list
    static void DoubleLinkedListTest() {
        //Reverse a doubly linked list
        // Let us create a sorted linked list to test the functions, Created linked list will be 10->8->4->2

        DoubleLinkedList list = new DoubleLinkedList();

        list.push(2);
        list.push(4);
        list.push(8);
        list.push(10);

        System.out.println("Original linked list ");
        list.printList(list.head);

        list.reverse();
        System.out.println("");
        System.out.println("The reversed Linked List is ");
        list.printList(list.head);

    }

    //Reverse a doubly linked list
    // https://www.hackerrank.com/challenges/reverse-a-doubly-linked-list/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists

    static class LinkNode {

        int data;
        LinkNode next, prev;

        LinkNode(int d) {
            data = d;
            next = prev = null;
        }
    }


    static class DoubleLinkedList {

        static LinkNode head;


        /* Function to reverse a Doubly Linked List */
        void reverse() {
            LinkNode temp = null;
            LinkNode current = head;

        /* swap next and prev for all nodes of
         doubly linked list */
            while (current != null) {
                temp = current.prev;
                current.prev = current.next;
                current.next = temp;
                current = current.prev;
            }

        /* Before changing head, check for the cases like empty
         list and list with only one node */
            if (temp != null) {
                head = temp.prev;
            }
        }

        /* UTILITY FUNCTIONS */
        /* Function to insert a node at the beginning of the Doubly Linked List */
        void push(int new_data) {
            /* allocate node */
            LinkNode new_node = new LinkNode(new_data);

        /* since we are adding at the beginning,
         prev is always NULL */
            new_node.prev = null;

            /* link the old list off the new node */
            new_node.next = head;

            /* change prev of head node to new node */
            if (head != null) {
                head.prev = new_node;
            }

            /* move the head to point to the new node */
            head = new_node;
        }

        /* Function to print nodes in a given doubly linked list
         This function is same as printList() of singly linked list */
        void printList(LinkNode node) {
            while (node != null) {
                System.out.print(node.data + " ");
                node = node.next;
            }
        }

    }

    //https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
    //12.4 Find Merge Point of Two Lists
    static void findMergeNodeTest() {
        LinkNode head1 = new LinkNode(1);
        head1.next = new LinkNode(2);
        head1.next.next = new LinkNode(3);

        LinkNode head2 = new LinkNode(1);
        head2.next = new LinkNode(3);   // it is not working in this end less loop, but it works at hackerank
//        int ret = findMergeNode(head1, head2);
    }

    //        https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/forum?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
    //        Find Merge Point of Two Lists
    static int findMergeNode(LinkNode head1, LinkNode head2) {

        LinkNode currentA = head1;
        LinkNode currentB = head2;

        //Do till the two nodes are the same
        while (currentA != currentB) {
            //If you reached the end of one list start at the beginning of the other one
            //currentA
            if (currentA.next == null) {
                currentA = head2;
            } else {
                currentA = currentA.next;
            }
            //currentB
            if (currentB.next == null) {
                currentB = head1;
            } else {
                currentB = currentB.next;
            }
        }
        return currentB.data;
    }

    // 12-5 Linked Lists: Detect a Cycle

    static void has_cycleTest() {
        LinkNode head = new LinkNode(1);
        head.next = new LinkNode(2);
        head.next.next = new LinkNode(3);
        head.next.next.next = new LinkNode(4);
        head.next.next.next.next = new LinkNode(5);
        head.next.next.next.next.next = head.next.next; // 3 -> 4 -> 5 -> 3 circle  // true

        boolean ret = has_cycle(head);
    }

    static boolean has_cycle(LinkNode head) {
        if (head == null) return false;

        LinkNode slow = head;
        LinkNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    // 13. Recursion and Backtracking
    // 13-1 Recursion: Fibonacci Numbers

    static void fibonacciTest() {

        //Recursion: Fibonacci Numbers
        //https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=recursion-backtracking

        System.out.println("fibonacci(3) = ");
        int n = 10;
        System.out.println(HackerRank.fibonacci(n));

    }

    //Recursion: Fibonacci Numbers
    //https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=recursion-backtracking

    public static int fibonacci(int n) {
        // Complete the function.
        if (n < 2)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);

    }

    // 13-2 Recursion: Davis' Staircase

    //Approach: We can easily find the recursive nature in the above problem.
    // The person can reach nth stair from either (n-1)th stair or from (n-2)th stair.
    // Hence, for each stair n, we try to find out the number of ways to reach n-1th stair and n-2th stair
    // and add them to give the answer for the nth stair.
    // Therefore the expression for such an approach comes out to be : ways(n) = ways(n-1) + ways(n-2)

    static void stepPermsTest() {
        int n = 7;
        int ret = stepPerms(n);
    }

    static int stepPerms(int n) {

        int[] array = new int[n];
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }
        array[0] = 1;
        array[1] = 2;
        array[2] = 4;
        for (int i = 3; i < n; i++) {
            array[i] = array[i - 1] + array[i - 2] + array[i - 3];
        }
        return array[array.length - 1];
    }

    //    https://solution.programmingoneonone.com/2020/07/hackerrank-crossword-puzzle-solution.html
    // 13-3 Crossword Puzzle

    static void crosswordPuzzleTest() {
        //Scanner scan = new Scanner(System.in);
        startingPoints = new HashMap<Integer, ArrayList<StartingPoint>>();
        String[] crossword = {"XXXXXX-XXX", "XX------XX", "XXXXXX-XXX", "XXXXXX-XXX", "XXX------X",
                "XXXXXX-X-X", "XXXXXX-X-X", "XXXXXXXX-X", "XXXXXXXX-X", "XXXXXXXX-X"};

        String words = "ICELAND;MEXICO;PANAMA;ALMATY";
        crossword = crosswordPuzzle(crossword, words);

    }

    static class StartingPoint {
        int x, y;
        int length;
        boolean across;

        public StartingPoint(int x, int y, boolean across) {
            this.x = x;
            this.y = y;
            this.across = across;
        }
    }

    static char[][] c = new char[10][];
    static HashMap<Integer, ArrayList<StartingPoint>> startingPoints;
    static String[] words;

    static boolean fill(int n) {
        if (n == words.length) {
            // Output the array
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    //System.out.print(c[y][x]);
                }
                //System.out.println();
            }

            return true;
        }

        String word = words[n];
        ArrayList<StartingPoint> sps = startingPoints.get(word.length());
        Stack<Integer> si = new Stack<Integer>();
        // go through each works
        for (StartingPoint sp : sps) {
            int dx = sp.across ? 1 : 0;
            int dy = sp.across ? 0 : 1;

            for (int i = 0; i < word.length(); i++) {
                if (c[sp.x + i * dx][sp.y + i * dy] != '-') {
                    if (c[sp.x + i * dx][sp.y + i * dy] != word.charAt(i)) {
                        break;
                    }
                } else {
                    c[sp.x + i * dx][sp.y + i * dy] = word.charAt(i);
                    si.push(i);
                }

                if (i == word.length() - 1) {
                    if (fill(n + 1))  // fill next words
                        return true;
                }
            }

            // Restore the previous state
            for (Integer i : si) {
                c[sp.x + i * dx][sp.y + i * dy] = '-';
            }
        }

        return false;
    }

    static String[] crosswordPuzzle(String[] crossword, String words1) {
        String[] ret = {"", "", "", "", "", "", "", "", "", ""};

        words = words1.split(";"); // new String[] {"LONDON", "DELHI", "ICELAND", "ANKARA"};

        for (int i = 0; i < 10; i++) {
            c[i] = crossword[i].toCharArray(); // scan.nextLine().toCharArray();
        }

        // travel all cross cell '-' and add length of x-y '-' at points
        for (int y = 0; y < 10; y++) {  //from up to doqn y diectory check
            for (int x = 0; x < 10; x++) {
                if (c[x][y] == '-') {  // get check "-" at x, it would be a cross cell in x axis
                    if (x == 0 || c[x - 1][y] == 'X') {
                        StartingPoint sp = new StartingPoint(x, y, true);  //create a atart point at '-' cell
                        for (int i = x; i < 10; i++) {
                            if (c[i][y] == '-')
                                sp.length++;  // get how many '-' cells in x axis
                            else
                                break;
                        }

                        ArrayList<StartingPoint> list = startingPoints.get(sp.length);  // add to StartingPoint list for fill
                        if (list == null) {                         // startingPonts is a HashMap for x-y axis both lengths
                            list = new ArrayList<StartingPoint>();
                            startingPoints.put(sp.length, list);
                        }

                        list.add(sp); //add to StartingPoint list for fill
                    }

                    if (y == 0 || c[x][y - 1] == 'X') {  // check '-' cell at y axis
                        StartingPoint sp = new StartingPoint(x, y, false);  //add y axis '-' cells length
                        for (int i = y; i < 10; i++) {
                            if (c[x][i] == '-')
                                sp.length++;
                            else
                                break;
                        }

                        ArrayList<StartingPoint> list = startingPoints.get(sp.length);
                        if (list == null) {
                            list = new ArrayList<StartingPoint>();
                            startingPoints.put(sp.length, list);
                        }

                        list.add(sp);
                    }
                }
            }
        }
//        assert(fill(0));
        fill(0);

        for (int i = 0; i < 10; i++) {
            String str = new String(c[i]);
            ret[i] = str;
        }
        return ret;
    }

    // 13-4 Recursive Digit Sum

    static void superDigitTest() {
        //String n = "148"; //ret 3
        //int k = 3;
        //String n = "123"; // ret 9
        //int k = 3;
        String n = "9875";  // ret 8
        int k = 4;
        int ret = superDigit(n, k);
    }

    static int superDigit(String n, int k) {

        if (n.length() == 1) {
            return Integer.valueOf(n);
        }

        char[] nch = n.toCharArray();
        int sum = 0;
        for (int i = 0; i < nch.length; i++) {
            sum = sum + (nch[i] - '0');
        }
        if (n.length() >= k)   // do not cobmin string and remember property : (a+b)%x = ((a%x)+(b%x))%x to sum * k
            sum = sum * k;
        n = String.valueOf(sum);
        return superDigit(n, k);

    }


    // 14. Miscellaneous
    // 14-1 Flipping bits

    static void flippingBitsTest() {
        //int n = 2147483647;

        int n = 2147483647;  //Integer.MAX_VALUE;
        long ret = flippingBits(35601423);
    }


    static long flippingBits(int n) {
        char[] ch = new char[32];
        Arrays.fill(ch, '1');

        String s = Integer.toBinaryString((int) n);
        for (int i = s.length() - 1; i >= 0; i--) {
            char tc = s.charAt(i);
            tc = tc == '1' ? '0' : '1';
            ch[i + 32 - s.length()] = tc;
        }

        long sum = 0;
        long pow = 1;
        for (int i = 0; i < 32; i++) {
            if (i == 0)
                pow = 1;
            else
                pow = pow * 2;
            if (ch[32 - 1 - i] == '1')
                sum = sum + pow;
        }

        return sum;

    }

    // 14-2 Time Complexity: Primality

    static void primalityTest() {
        int n = 7;
        primality(n);
    }

    static String primality(int n) {
        if (n < 2) {
            return "Not prime";
        } else if (n == 2) {
            return "Prime";
        } else if (n % 2 == 0) {
            return "Not prime";
        }


        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return "Not prime";
            }
        }

        return "Prime";
    }

    // 14-3 Friend Circle Queries

    static void maxCircleTest() {

        //int[][] queries = {{1,2}, {3, 4}, {2, 3}};
        //int[][] queries = {{1000000000,23}, {11, 3778}, {7, 47}, {11, 1000000000}};
        int[][] queries = {{1, 2}, {3, 4}, {1, 3}, {5, 7}, {5, 6}, {7, 4}};

        maxCircle(queries);
    }

    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
        UnionFind uf = new UnionFind();
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            uf.union(queries[i][0], queries[i][1]);
            res[i] = uf.max;
        }
        return res;
    }

    static class UnionFind {

        Map<Integer, Integer> parents;
        Map<Integer, Integer> sizes;
        int max;

        public UnionFind() {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            max = 0;
        }

        public void union(int v1, int v2) {
            if (!parents.containsKey(v1)) {
                parents.put(v1, v1);
                sizes.put(v1, 1);
            }

            if (!parents.containsKey(v2)) {
                parents.put(v2, v2);
                sizes.put(v2, 1);
            }

            int p1 = find(v1), p2 = find(v2);
            if (p1 == p2) return;
            int s1 = sizes.get(p1), s2 = sizes.get(p2);
            if (s1 < s2) {
                parents.put(p1, p2);
                sizes.put(p2, s1 + s2);
                if (s1 + s2 > max) max = s1 + s2;
            } else {
                parents.put(p2, p1);
                sizes.put(p1, s1 + s2);
                if (s1 + s2 > max) max = s1 + s2;
            }
        }

        public int find(int v) {
            while (parents.get(v) != v) {
                parents.put(v, parents.get(parents.get(v)));
                v = parents.get(v);
            }
            return v;
        }
    }

    // 14-4 Maximum Xor
    static void maxXorTest() {
        int[] arr = {0, 1, 2,};
        int[] queries = {3, 3, 7, 2};
        int[] ret = maxXor(arr, queries);

    }

    static int[] maxXor(int[] arr, int[] queries) {
        int max = Integer.MIN_VALUE;
        int len = queries.length;
        int[] ret = new int[len];

        for (int i = 0; i < len; i++) {
            max = Integer.MIN_VALUE;
            for (int j = 0; j < arr.length; j++)
            {
                max = Math.max(max, arr[j] ^ queries[i]);
            }
            ret[i] = max;
        }
        return ret;
        // has three time cost could not passed
    }
/*   Python could passed all time case
def maxXor(arr, queries):    ans = []
    trie = {}
    k = len(bin(max(arr+queries))) - 2
    for number in ['{:b}'.format(x).zfill(k) for x in arr]:
        node = trie
        for char in number:
            node = node.setdefault(char, {})
    for n in queries:
        node = trie
        s = ''
        for char in'{:b}'.format(n).zfill(k) :
            tmp = str(int(char) ^ 1)
            tmp = tmp if tmp in node else char
            s += tmp
            node = node[tmp]
        ans.append(int(s, 2) ^ n)
    return ans
 */

}
