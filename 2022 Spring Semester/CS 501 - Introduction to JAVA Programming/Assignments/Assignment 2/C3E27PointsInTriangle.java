import java.util.Scanner;

public class C3E27PointsInTriangle {
    public static void main(String[] args) {

        System.out.println("");
        System.out.println("- A right triangle is placed in a plane with right-angle point placed at (0, 0), and the other two points are placed at (200, 0) and (0, 100).");
        System.out.println("- This program prompts the user to enter a point with x- and y-coordinates and determines whether the point is inside the triangle.");
        System.out.println("- Points can be integer or double, positive or negative.");
        System.out.println("- For each point type x and y values without the brackets, then press 'Enter'.");
        System.out.println("");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the coordinates of the point separated by spaces like x1 y1: ");
        double x = input.nextDouble();
        double y = input.nextDouble();

        if ((x > 0) && (y > 0) && (x + 2*y < 200)) {
            System.out.println("=> The point is in the triangle");
        }
        else {
            System.out.println("=> The point is not in the triangle");
        }
    }
}
