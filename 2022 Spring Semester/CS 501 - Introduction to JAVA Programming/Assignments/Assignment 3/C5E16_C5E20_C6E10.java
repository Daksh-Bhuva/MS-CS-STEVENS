import java.util.Scanner;

public class C5E16_C5E20_C6E10 {
    public static void Intro () {
        System.out.println("\n- This program displays the first 50 Prime Numbers.");
        System.out.println("- Then it populates an array containing the Prime Numbers less than 1000, and");
        System.out.println("- in a repeat loop prompts the user to enter one number at a time to test if it is Prime by searching the array.");
        System.out.println("- If it is not a Prime, it shows smallest factors of that number.");
        System.out.println("- The number entered should be an integer, then press 'Enter'.\n");
    }
    public static void FactorsOfAnInteger (int num) {
        int counter = 2;

        while (num > 1) {
            if (num % counter == 0) {
                num /= counter;
                System.out.print(counter + " ");
            }
            else {
                counter++;
            }
        }
    }
    public static void DisplayPrimeNumbers (int NumberOfPrimes) {
        int per_line = 0;
        int count = 0;
        int number = 2;

        System.out.println("* The first 50 prime numbers are: \n");

        while (count <= NumberOfPrimes) {
            boolean isPrime = true;
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                per_line++;
                if (per_line % 8 == 0) {
                    System.out.printf("%-5d\n", number);
                }
                else {
                    System.out.printf("%-5d", number);
                }
            }
            number++;
        }
    }
    public static boolean IsPrime (int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static int NumberOfPrimes (int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (IsPrime(i)) {
                count++;
            }
        }
        return count;
    }
    public static void ArrayOfPrimes (int[] arr, int start, int end) {
        int i = 0;
        while(start <= end && i < arr.length) {
            if(IsPrime(start)) {
                arr[i++] = start;
            }
            start++;
        }
    }
    public static boolean BinarySearch(int[] arr, int key)
    {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            }
            else if (key > arr[mid]) {
                start = mid + 1;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Intro();

        Scanner input = new Scanner(System.in);

        DisplayPrimeNumbers(50);

        int LenPrimes = NumberOfPrimes(2,1000);
        int[] primes = new int[LenPrimes];
        ArrayOfPrimes(primes, 2, 1000);

        int repeatInt = 1;
        while (repeatInt == 1) {
            System.out.println("\n\n* Enter a number between 2 and 1000 (inclusive) to check if it is prime or not: ");
            int key = input.nextInt();

            if (key < 2 || key > 1000) {
                System.out.println("=> Number entered is not between 2 and 1000 (inclusive) !!!");
            }
            else if (BinarySearch(primes, key)) {
                System.out.println("=> " + key + " is a prime number");
            }
            else {
                System.out.println("=> " + key + " is not a prime number");
                System.out.println("-> Factors of " + key + " are: ");
                FactorsOfAnInteger(key);
            }
            System.out.println("\n\n* Want to continue? | Enter 1 for 'YES' or 0 for 'NO': ");
            repeatInt = input.nextInt();
        }
    }
}
