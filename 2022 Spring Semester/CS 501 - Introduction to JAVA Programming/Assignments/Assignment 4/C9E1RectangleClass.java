import java.util.Scanner;

public class C9E1RectangleClass {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double width;
        double height;

        System.out.println("\n- The following program allows you to test the 'Rectangle' class that represents a rectangle\n");
        System.out.println("- The class contains: \n # Two double data fields named width and height that specify the width and height of the rectangle. The default values are 1 for both width and height.");
        System.out.println(" # A no-arg constructor that creates a default rectangle.");
        System.out.println(" # A constructor that creates a rectangle with the specified width and height.");
        System.out.println(" # A method named getArea() that returns the area of this rectangle.");
        System.out.println(" # A method named getPerimeter() that returns the perimeter.\n");
        System.out.println("- This programs displays the perimeter and area of the Rectangle object.");
        System.out.println("- The width and height can be integer or double, greater than 0.");

        int repeatInt = 1;
        while(repeatInt == 1)
        {
            System.out.print("\n* Enter the width and the height of a rectangle seperated by spaces like w h: ");
            width = input.nextDouble();
            height = input.nextDouble();

            try {
                Rectangle rect = new Rectangle(width, height);
                rect.print();
                System.out.println("\n* Want to continue? | Enter 1 for 'YES' or 0 for 'NO': ");
                repeatInt = input.nextInt();
            }
            catch(Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
                System.out.println("- TRY AGAIN - ");
            }
        }
    }
}
