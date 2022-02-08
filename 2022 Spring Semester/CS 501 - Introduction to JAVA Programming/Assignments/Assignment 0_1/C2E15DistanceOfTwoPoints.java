import java.util.Scanner;

public class C2E15DistanceOfTwoPoints {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("");
        System.out.println("This program prompts user to enter two points (x1, y1) and (x2, y2) and displays their distance.");
        System.out.println("Points can be integer or double, positive or negative.");
        System.out.println("For each point type x and y values without the brackets, then press 'Enter'.");
        System.out.println("");

        System.out.print("Enter x1 and y1 separated by a space like x1 y1: ");
        double x1 = input.nextDouble();
        double y1 = input.nextDouble();

        System.out.print("Enter x2 and y2 separated by a space like x2 y2: ");
        double x2 = input.nextDouble();
        double y2 = input.nextDouble();

        double distance = Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5);

        System.out.println("");
        System.out.println("The distance between the two points is " + distance);
    }
}
