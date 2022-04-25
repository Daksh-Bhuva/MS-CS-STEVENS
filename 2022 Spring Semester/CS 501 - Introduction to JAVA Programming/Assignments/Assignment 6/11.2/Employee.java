import java.util.regex.Pattern;

public class Employee extends Person {

    private String office, empID;
    private int salary;
    private MyDate dateHired;
    private boolean empObjCreated;

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean getEmpObjCreated() {
        return this.empObjCreated;
    }

    public void setEmpObjCreated(boolean empObjCreated) {
        this.empObjCreated = empObjCreated;
    }

    public String getEmpID() {
        return this.empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public MyDate getDateHired() {
        if(this.dateHired == null)
            this.dateHired = new MyDate(0,0,0);
        return this.dateHired;
    }

    public void setDateHired(MyDate dateHired) {
        this.dateHired = dateHired;
    }

    Employee(String name, String address, String email, String ssn, String office, String empID, int salary, String dateEntered) throws
            Exception {
        super(name,address,email,ssn);
        if(!checkValidID(empID))
            throw new Exception("Invalid empID!");
        else if(salary <= 0)
            throw new Exception("Invalid Salary!");
        else if(!checkValidDate(dateEntered))
            throw new Exception("Invalid Date!");
        else {
            setOffice(office);
            setEmpID(empID);
            setSalary(salary);
            int day = Integer.parseInt(dateEntered.substring(0,2));
            int month = Integer.parseInt(dateEntered.substring(3,5));
            int year = Integer.parseInt(dateEntered.substring(6));
            MyDate date = new MyDate(day, month, year);
            setDateHired(date);
            System.out.println("\nEmployee Class Object created.");
        }
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public boolean checkValidDate(String date) {
        String regexPattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
                "(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)" +
                "?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)" +
                "(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        return patternMatches(date,regexPattern);
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
        return "Employee {" +
                "\n~Name -> '" + super.getName() + "'" +
                ", \n~Address -> '" + super.getAddress() + "'" +
                ", \n~E-mail -> '" + super.getEmail() + "'" +
                ", \n~SSN -> " + super.getSsn() +
                ", \n~Office -> '" + getOffice() + "'" +
                ", \n~empID -> '" + getEmpID() + "'" +
                ", \n~Salary -> " + getSalary() +
                ", \n~dateHired -> " + getDateHired().toString() +
                " }";
    }
}
