import java.util.regex.Pattern;

public class Person {

    private String name, address, email, ssn;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return this.ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    Person(String name, String address, String email, String ssn) throws Exception {
        if(!checkValidEmail(email)) {
            throw new Exception("Invalid E-mail!");
        }
        else if(!checkValidSsn(ssn)) {
            throw new Exception("Invalid SSN!");
        }
        else {
            setName(name);
            setAddress(address);
            setEmail(email);
            setSsn(ssn);
            System.out.println("\nPerson Class Object created.");
        }
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public boolean checkValidEmail(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        return patternMatches(email,regexPattern);
    }

    public boolean checkValidSsn(String ssn) {
        String regexPattern = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
        return patternMatches(ssn,regexPattern);
    }

    @Override
    public String toString() {
        return "Person {" +
                "\n~Name -> '" + name + "'" +
                ", \n~Address -> '" + address + "'" +
                ", \n~E-mail -> '" + email + "'" +
                ", \n~SSN -> " + ssn +
                " }";
    }
}
