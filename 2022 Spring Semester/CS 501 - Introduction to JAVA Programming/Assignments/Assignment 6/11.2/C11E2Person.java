import java.util.Scanner;

public class C11E2Person {

    public static void main(String[] args) {

        System.out.println("""
                \nDesigned a class named Person and its two subclasses named Student and Employee. Make Faculty and Staff subclasses of Employee.
                A person has a name, address, phone number, and e-mail address.
                A student has a class status (freshman, sophomore, junior, or senior).
                An employee has an office, salary, and date hired.
                A faculty member has office hours and a rank.
                A staff member has a title.
                This program, after successful object creation, prints the contents of the object Overriding the toString method.
                """);

        int val = 1;
        while(val == 1) {

            Scanner in = new Scanner(System.in);
            System.out.println("\nSelect from below- ");
            System.out.println("1.Person \n2.Student \n3.Employee \n4.Faculty \n5.Staff");
            System.out.println("\nEnter an integer from the above to start: ");
            int choice = in.nextInt();

            in.nextLine();
            String name, address, email, status, office, dateEntered, title, rank, ssn, empID, studentID;
            int salary, officeHours;
            System.out.print("\nEnter name: ");
            name = in.nextLine();
            System.out.print("Enter address: ");
            address = in.nextLine();
            System.out.print("Enter email: ");
            email = in.nextLine();
            System.out.print("Enter SNN in integer format XXX-XX-XXXX : ");
            ssn = in.nextLine();
            if(choice == 1) {
                try {
                    Person p = new Person(name, address, email, ssn);
                    System.out.println(p.toString());
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(choice == 2) {
                System.out.print("Enter class status (freshman" +
                        ", sophomore, junior, senior): ");
                status = in.nextLine();
                System.out.print("Enter student ID: ");
                studentID = in.nextLine();
                try {
                    Student s = new Student(name, address, email, ssn, status, studentID);
                    System.out.println(s.toString());
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                System.out.print("Enter office address: ");
                office = in.nextLine();
                System.out.print("Enter salary in integer: ");
                salary = in.nextInt();
                in.nextLine();
                System.out.print("Enter the date of hiring in integer format DD/MM//YYYY: ");
                dateEntered = in.nextLine();
                System.out.print("Enter empID: ");
                empID = in.nextLine();
                if(choice == 3) {
                    try {
                        Employee e = new Employee(name, address, email, ssn, office, empID, salary, dateEntered);
                        System.out.println(e.toString());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(choice == 4) {
                    System.out.print("Enter office hours in integer: ");
                    officeHours = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter the rank: ");
                    rank = in.nextLine();
                    try {
                        Faculty f = new Faculty(name, address, email, ssn, office, empID, salary, dateEntered,  officeHours, rank);
                        System.out.println(f.toString());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    System.out.print("Enter the tile: ");
                    title = in.nextLine();
                    try {
                        Staff st = new Staff(name, address, email, ssn, office, empID, salary, dateEntered, title);
                        System.out.println(st.toString());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("\nPress 0 to EXIT | Enter 1 to TRY AGAIN -");
            val = in.nextInt();
        }

    }

}
