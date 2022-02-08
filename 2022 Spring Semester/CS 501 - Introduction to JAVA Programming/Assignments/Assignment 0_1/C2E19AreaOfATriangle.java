import java.util.Scanner;

public class C2E19AreaOfATriangle {
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);

        System.out.println("");
        System.out.println("This program prompts user to enter three points (x1, y1), (x2, y2) and (x3, y3), of a triangle, then displays it's area.");
        System.out.println("Points can be integer or double, positive or negative.");
        System.out.println("For each point type x and y values without the brackets, then press 'Enter'.");
        System.out.println("");

        System.out.print("Enter the coordinates of three points separated by spaces like x1 y1 x2 y2 x3 y3: ");
        double x1 = inputs.nextDouble();
        double y1 = inputs.nextDouble();

        double x2 = inputs.nextDouble();
        double y2 = inputs.nextDouble();

        double x3 = inputs.nextDouble();
        double y3 = inputs.nextDouble();

        double side1 = Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5);
        double side2 = Math.pow(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2), 0.5);
        double side3 = Math.pow(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2), 0.5);

        double s = (side1 + side2 + side3) / 2;

        double area = Math.pow(s * (s - side1) * (s - side2) * (s - side3), 0.5);

        System.out.println("");
        System.out.println("Area of the triangle is " + area);
    }
}
