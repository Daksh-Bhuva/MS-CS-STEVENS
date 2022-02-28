import java.util.Arrays;
import java.util.Scanner;

public class C7E9_C7E10_C7E20 {
    public static void Intro () {
        System.out.println("\n- This program prompts the user to enter 10 numbers and then displays the smallest element in an array of double values.");
        System.out.println("- Then it displays the index of the smallest element. If the number of such elements is greater than 1, displays the smallest index.");
        System.out.println("- Lastly displays the sorted array using Revised Selection Sort by finding the largest number and swapping it with the last");
        System.out.println("- Array elements can be integer or double, positive or negative.");
        System.out.println("- Type the array element separated by spaces, then press 'Enter'.");
        System.out.println("\n* Enter the 10 numbers: ");
    }
    public static double SmallestNumber (double[] arr) {
        double minim = Integer.MAX_VALUE;

        for (double num : arr) {
            if (num < minim) {
                minim = num;
            }
        }
        return minim;
    }
    public static int SmallestIndex (double[] arr) {
        double minim = SmallestNumber(arr);

        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == minim) {
                break;
            }
        }
        return i;
    }
    public static void ReviseSelectionSort (double[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            double currMax = arr[i];
            int currMaxIndex = i;
            for (int j = 0; j < i; j++) {
                if (currMax < arr[j]) {
                    currMax = arr[j];
                    currMaxIndex = j;
                }
            }
            if (currMaxIndex != i) {
                arr[currMaxIndex] = arr[i];
                arr[i] = currMax;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Intro();

        Scanner input = new Scanner(System.in);
        double[] array = new double[10];

        for (int i = 0; i < 10; i += 1){
            array[i] = input.nextDouble();
        }
        System.out.println("\nThe smallest number in the array is: \n=> " + SmallestNumber(array));
        System.out.println("\nThe index of smallest number in the array is: \n=> " + SmallestIndex(array));
        System.out.println("\nThe sorted array is: ");
        ReviseSelectionSort(array);
    }
}
