public class MyDate {

    private int day = 0, month = 0, year = 0;

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    MyDate(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    @Override
    public String toString() {
        String dd = getDay()/10 != 0 ? Integer.toString(getDay()): "0" + Integer.toString(getDay());
        String mm = getMonth()/10 != 0 ? Integer.toString(getMonth()): "0" + Integer.toString(getMonth());
        String yyyy = Integer.toString(getYear());
        return dd + '/' + mm + '/' + yyyy;
    }
}
