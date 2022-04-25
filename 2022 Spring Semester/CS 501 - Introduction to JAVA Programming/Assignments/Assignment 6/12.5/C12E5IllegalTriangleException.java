import java.util.Scanner;

public class C12E5IllegalTriangleException {

    public static void main(String[] args){

        System.out.println("""
                \nDefined the Triangle class with three sides.
                In a triangle, the sum of any two sides is greater than the other side. The Triangle class must adhere to this rule.
                Created the IllegalTriangleException class, and modified the constructor of the Triangle class
                to throw an IllegalTriangleException object if a triangle is created with sides that violate the rule. 
                """);

        Scanner getInput = new Scanner(System.in);
        double side1,side2,side3;
        String color;
        int choice;

        boolean repeat = true;

        while(repeat){

            try {
                System.out.println("Enter the dimensions of triangle, integer or double-");

                System.out.println("Enter First Side: ");
                side1 = getInput.nextDouble();

                System.out.println("Enter Second Side: ");
                side2 = getInput.nextDouble();

                System.out.println("Enter Third Side: ");
                side3 = getInput.nextDouble();

                Triangle t1 = new Triangle(side1, side2, side3);

                System.out.println("Enter the color: ");
                color = getInput.next();
                t1.setColor(color);

                System.out.println("Enter 1 for filled triangle, else enter 0: ");
                choice = getInput.nextInt();

                if(choice == 1){
                    t1.setFilled(true);
                }else if(choice == 0){
                    t1.setFilled(false);
                }

                System.out.println("~The Triangle -> " + t1);
                System.out.println("~The color is -> " + t1.getColor());
                System.out.println("~The perimeter is -> " + t1.getPerimeter());
                System.out.println("~The area is -> " + t1.getArea());
                System.out.print("~Is the triangle filled? -> ");
                if(t1.isFilled())
                {
                    System.out.print("Filled");
                }else
                {
                    System.out.print("Not Filled");
                }

            } catch (IllegalTriangleException e) {
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("\n\nEnter 0 to EXIT | 1 to Try Again: ");
            choice = getInput.nextInt();

            if(choice == 0){
                repeat = false;

            }
        }
        getInput.close();
    }

}