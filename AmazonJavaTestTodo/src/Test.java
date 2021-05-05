import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.io.Reader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
/*
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
class Solution { // total 50 solved
    static public int task1(int[] A) {
        int iMax = Integer.MIN_VALUE;
        // write your code in Java SE 8
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            int iCount = 1;
            if (map.containsKey(A[i])) {
                iCount = map.get(A[i]) + 1;
            }
            map.put(A[i], iCount);
            if(A[i] > iMax){
                iMax = A[i];
            }

        }
        for (int i = 1; i <= iMax+1; i++) {
            if (map.containsKey(i)==false) {
                return i;
            }

        }
        return 1;
    }

    class SolutionIter implements Iterable<Integer> {

        public SolutionIter(Reader inp){
            try {
                File myObj = new File("filename.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return;
        };
//137\n
// -104\n
// 2 58\n
// +0\n
// ++3\n
// +1\n
// 23.9\n
// 2000000000\n
// -0\
// nfive\n
// -1\n

//[137, -104, 0, 1, 0, ..)

        public Iterator<Integer> iterator(){
            ArrayList<Integer> intStrings = new ArrayList<Integer>();
            intStrings.add(1);
            intStrings.add(2);
            intStrings.add(3);
            intStrings.add(4);
            Iterator<Integer> it = intStrings.iterator();
            return it;
        };
    }

/*
    static void task2() {

        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (Integer x : new SolutionIter(reader)) {
            System.out.println(x);
        }
        System.out.println("Test1");
    }
*/

    /**
     * Example usage:
     *
     * for (Integer x : new SolutionIter(reader)) {
     *     System.out.println(x);
     * }
     */

    public static void main(String[] args) throws IOException {
        int[] A = {4, 2, 2, 4, 2};
        int res = task1(A);
        int[] A1 = {1,2,3,2};
        int res1 = task1(A1);
        int[] A2 = {0, 5, 4, 4, 5, 12};
        int res2 = task1(A2);
        int[] A3 = {4, 4};
        int res3 = task1(A3);
        int[] A4 = {-1, -3};
        int res4 = task1(A4);

  //      task2();
    }

    static public int solution(int[] A) {
        // write your code in Java SE 8
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<A.length; i++){
            int iCount = 1;
            if(map.containsKey(A[i])){
                iCount = map.get(A[i]);
                iCount++;
            }
            map.put(A[i], iCount);
        }
        for(int iMax = 0; iMax <= map.size(); iMax++){
            if(map.containsKey(iMax+1)==false){
                return iMax+1;
            }
        }
        return 1;

    }


/*
    public static void main(String[] args) throws IOException {

        int[] A = {1, 3, 6, 4, 1, 2};
        int res = solution(A);
        int[] A1 = {1, 2, 3};
        int res1 = solution(A1);
        int[] A2 = {-1, -2};
        int res2 = solution(A2);


    }

    void main(String[] args) throws IOException
    {
        //task1();
        //task2();
    }

    void task1() {
        char N1 = ' ';
        int N2 = 5;
        int[] N3 =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        getList(N1, N2, N3);
    }

    String getList(char N1, int N2, int[] N3){
        String list = String.valueOf (N1);
        int i = 0;
        while(++i<N2){
            int total = N3[i] * 3;
            if((total!=5)||(total!=11)||(total!=13))
                list = list + split(total) + i;
        }
        return list;
    }

    String split(int N){
        if(N%2!=0)
            return "foo";
        return "bar";
    }
*/
    void task2(){
        String[] wordsReader = {"Two", "households", "both", "alike", "in", "dignity", "Two", "Two", "both"};
        HashMap<String, Integer> map = new HashMap<>();
        String mostUsedWord = "";
        int mostUsedWordTimes = 0;
        List<String> wordsRemaid = new ArrayList<>();
        for(String word : wordsReader){
            int count = 1;
            if(map.containsKey(word)){
                count = map.get(word) + 1;
            }
            else{
                Set<Character> cSet = new HashSet<>();
                for(int i = 0; i < word.length(); i++){
                    char ch = word.toUpperCase().charAt(i);
                    if((ch=='R') || (ch=='S') || (ch=='T') || (ch=='L') || (ch=='N') || (ch==' ') ||
                            (ch=='A') || (ch=='E') || (ch=='I') || (ch=='O') || (ch=='U'))
                        continue;
                    cSet.add(ch);
                }
                if(cSet.size()>1){
                    wordsRemaid.add(word);
                }
            }
            map.put(word, count);

            if(count>mostUsedWordTimes) {
                mostUsedWordTimes = count;
                mostUsedWord = word;
            }
        }

        System.out.printf("Ramaid Wrods: %s, Most Used Word: %s, Most Used Word Times: %s",
                wordsRemaid.toArray(), mostUsedWord, mostUsedWordTimes);
        return;

    }

}
