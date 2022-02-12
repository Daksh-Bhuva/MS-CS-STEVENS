import java.util.Scanner;

public class C4E2GreatCircleDistance {
    public static void main(String[] args) {

        System.out.println("");
        System.out.println("- This program prompts user to enter the latitude and longitude of two points on the earth in degrees and displays its great circle distance.");
        System.out.println("- Points can be integer or double, positive or negative.");
        System.out.println("- For each point type x and y values without the brackets, then press 'Enter'.");
        System.out.println("");

        double radius = 6371.01;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the geographical latitude and longitude of two points separated by spaces like x1 y2 x2 y2: ");

        double x1 = Math.toRadians(input.nextDouble());
        double y1 = Math.toRadians(input.nextDouble());
        double x2 = Math.toRadians(input.nextDouble());
        double y2 = Math.toRadians(input.nextDouble());

        double d = radius * Math.acos(Math.sin(x1)*Math.sin(x2) + Math.cos(x1)*Math.cos(x2)*Math.cos(y1 - y2));

        System.out.println("=> The distance between the two points is " + d + " km");
    }
}
