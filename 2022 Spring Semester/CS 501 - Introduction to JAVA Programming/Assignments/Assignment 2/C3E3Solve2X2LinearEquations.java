import java.util.Scanner;

public class C3E3Solve2X2LinearEquations {
    public static void main(String[] args) {

        System.out.println("");
        System.out.println("- This program prompts user to enter a, b, c, d, e, and f and displays the result of the 2 X 2 linear equations.");
        System.out.println("- The linear equation is solved using the Cramer’s rule.");
        System.out.println("- Numbers can be integer or double, positive or negative.");
        System.out.println("");

        System.out.println("Equation 1 will be ax + by = e");
        System.out.println("Equation 1 will be cx + dy = f");
        System.out.println("Solving the two equations we get: x = ed - bf/ad - bc and y = af - ec/ad - bc");
        System.out.println("");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the numbers separated by spaces like a b c d e f: ");

        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();
        double d = input.nextDouble();
        double e = input.nextDouble();
        double f = input.nextDouble();

        if(a*d - b*c == 0) {
            System.out.println("=> The equation has no solution");
        }
        else {
            System.out.println("=> x is " + (e*d - b*f)/(a*d - b*c) + " and y is " + (a*f - e*c)/(a*d - b*c));
        }
    }
}
