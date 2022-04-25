public class Faculty extends Employee {

    private int officeHours;
    private String rank;

    public int getOfficeHours() {
        return this.officeHours;
    }

    public void setOfficeHours(int officeHours) {
        this.officeHours = officeHours;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    Faculty(String name, String address, String email, String ssn, String office, String empID, int salary, String dateEntered,
            int officeHours, String rank) throws Exception {
        super(name, address, email, ssn, office, empID, salary, dateEntered);
        if(officeHours <= 0)
            throw new Exception("Invalid office hours!");
        else {
            setOfficeHours(officeHours);
            setRank(rank);
            System.out.println("\nFaculty Class Object created.");
        }
    }

    @Override
    public String toString() {
        return "Faculty {" +
                "\n~Name -> '" + super.getName() + "'" +
                ", \n~Address -> '" + super.getAddress() + "'" +
                ", \n~E-mail -> '" + super.getEmail() + "'" +
                ", \n~SSN -> " + super.getSsn() +
                ", \n~Office -> '" + super.getOffice() + "'" +
                ", \n~empID -> " + super.getEmpID() +
                ", \n~Salary -> " + super.getSalary() +
                ", \n~dateHired -> " + super.getDateHired().toString() +
                ", \n~officeHours -> " + getOfficeHours() +
                ", \n~Rank -> '" + getRank() + "'" +
                " }";
    }
}
