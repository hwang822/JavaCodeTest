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

        // write your code in Java SE 8
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            int iCount = 1;
            if (map.containsKey(A[i])) {
                iCount = map.get(A[i]) + 1;
            }
            map.put(A[i], iCount);
        }
        int iMax = 0;
        int iIndex = 0;
        for (int val : map.values()) {
            if ((iIndex == 0) || (iIndex == map.size() - 1)) {
                if (val < 2)
                    continue;
            }
            iMax = iMax + val;
        }
        return iMax;
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

//        task2();
    }
/*
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



    public static void main(String[] args) throws IOException {

        int[] A = {1, 3, 6, 4, 1, 2};
        int res = solution(A);
        int[] A1 = {1, 2, 3};
        int res1 = solution(A1);
        int[] A2 = {-1, -2};
        int res2 = solution(A2);


    }

 */
}
