public class Student extends Person {

    private static String status;
    private String studentID;

    public static String getStatus() {
        return Student.status;
    }

    public static void setStatus(String status) {
        Student.status = status;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    Student(String name, String address, String email, String ssn, String status, String studentID) throws Exception {
        super(name, address, email, ssn);
        if(!checkValidID(studentID))
            throw new Exception("Invalid studentID!");
        else {
            setStatus(status);
            setStudentID(studentID);
            System.out.println("\nStudent Class Object created.");
        }
    }

    public boolean checkValidID(String id) {
        for(int i = 0; i < id.length(); i++) {
            if(!Character.isDigit(id.charAt(i)))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student {" +
                "\n~Name -> '" + super.getName() + "'" +
                ", \n~Address -> '" + super.getAddress() + "'" +
                ", \n~E-mail -> '" + super.getEmail() + "'" +
                ", \n~SSN -> " + super.getSsn() +
                ", \n~Status -> '" + Student.getStatus() + "'" +
                ", \n~Student id -> " + this.getStudentID() +
                " }";
    }
}
