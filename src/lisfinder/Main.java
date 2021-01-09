package lisfinder;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // finding LIS with test numbers
        int[] test = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
        System.out.println("Test Array: ");
        printArray(test);
        findLIS(test, test.length);
        // finding LIS with random numbers
        int[] numbers = randomNumbers();
        System.out.println("Random Array: ");
        printArray(numbers);
        findLIS(numbers, numbers.length);
    }
    // randomNumbers() returns an int array of a random size (1-100) of random numbers (0-99)
    public static int[] randomNumbers() {
        int size = (int) ((Math.random() * 100) + 1);
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        return numbers;
    }
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
    // finds longest increasing subsequence of numbers with length n
    static void findLIS(int numbers[],int n)
    {
        /*
         * lisLength[i] holds the length of the longest increasing subsequence
         * that ends in the element at index i. (prefilled with value of 1)
         */
        int lisLength[] = new int[n];
        Arrays.fill(lisLength, 1);
        /*
         * lisIndex[i] holds the index j of the second last element in the longest increasing subsequence ending in i.
         * lisIndex[i] is the same index j at which the highest value lisLength[i] was obtained.
         * prefilled with value of -1, so for elements with lisIndex[i]=1, the value will remain −1
         */
        int[] lisIndex = new int[n];
        Arrays.fill(lisIndex, -1);

        // loops through numbers
        int i,j = 0;
        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
                // if a number (at i) is bigger than a previous number(at j): numbers[i] > numbers[j]
                // then its lis length (up to index i) is the length of lis (up to index) j + itself (1)
                // : lisLength[i] = lisLength[j] + 1;
                if (numbers[i] > numbers[j] && lisLength[i] < lisLength[j] + 1) {
                    // so you need to set it if: lisLength[i] < lisLength[j] + 1
                    lisLength[i] = lisLength[j] + 1;
                    // lisIndex[i] holds j, which is the index of the number that numbers[i] is bigger than
                    // so it holds the index where it consumed the biggest lis length of
                    lisIndex[i] = j;
                }

            }
        }
        // finds the largest length, which is the length of LIS
        int maxLIS = lisLength[0], pos = 0;
        for (i = 1; i < n; i++) {
            if (lisLength[i] > maxLIS) {
                maxLIS = lisLength[i];
                // pos is the index at which the lis ends in numbers = index of last number in lis
                pos = i;
            }
        }

        ArrayList<Integer> subsequence = new ArrayList<>();
        while (pos != -1) {
            // starts by adding number at pos (index at which the lis ends in numbers = index of last number in lis)
            subsequence.add(numbers[pos]);
            // then gets the index stored lisIndex[pos],
            // which which is j, the previous number that numbers[i or pos] is bigger than
            pos = lisIndex[pos];
            // runs till it pos = −1, which is when lisIndex[i]=1
        }
        // reverse the list, because it started adding from the last index
        Collections.reverse(subsequence);

        printLIS(subsequence, maxLIS);
    }
    // prints arraylist and length of LIS
    private static void printLIS(ArrayList<Integer> lis, int lengthLIS) {
        System.out.println("\nLongest Increasing Subsequence: ");
        for (int i : lis) {
            System.out.print(i + " ");
        }
        System.out.println("\nLength of LIS: " + lengthLIS);
    }
}
