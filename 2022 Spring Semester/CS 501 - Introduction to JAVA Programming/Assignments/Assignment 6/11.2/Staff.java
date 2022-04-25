public class Staff extends Employee {

    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Staff(String name, String address, String email, String ssn, String office, String empID, int salary, String dateEntered,
          String title) throws Exception {
        super(name, address, email, ssn, office, empID, salary, dateEntered);
        setTitle(title);
        System.out.println("\nStaff Class Object created.");
    }

    @Override
    public String toString() {
        return "Staff {" +
                "\n~Name -> '" + super.getName() + "'" +
                ", \n~Address -> '" + super.getAddress() + "'" +
                ", \n~E-mail -> '" + super.getEmail() + "'" +
                ", \n~SSN -> " + super.getSsn() +
                ", \n~Office -> '" + super.getOffice() + "'" +
                ", \n~empID -> " + super.getEmpID() +
                ", \n~Salary -> " + super.getSalary() +
                ", \n~dateHired -> " + super.getDateHired().toString() +
                ", \n~Title -> '" + getTitle() + "'" +
                " }";
    }
}
