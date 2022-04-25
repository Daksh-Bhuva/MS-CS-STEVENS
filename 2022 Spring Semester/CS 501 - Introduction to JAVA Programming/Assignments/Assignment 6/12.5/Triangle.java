public class Triangle extends GeometricObject{
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    public Triangle(double s1, double s2, double s3) throws IllegalTriangleException, Exception{

        this.isValid(s1);
        this.isValid(s2);
        this.isValid(s3);

        if( !( ((s1+s2) > s3)  && ((s1+s3) > s2) && ((s2+s3) > s1) ) )  {
            throw new IllegalTriangleException("Sum of two side is not greater than third side !");

        }
        else{
            this.setSide1(s1);
            this.setSide2(s2);
            this.setSide3(s3);
        }
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double s1) throws Exception {
        if(isValid(s1)){
            this.side1 = s1;
        }
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double s2) throws Exception{
        if(isValid(s2)){
            this.side2 = s2;
        }
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double s3) throws Exception{
        if(isValid(s3)){
            this.side3 = s3;
        }
    }

    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * ((s - side1) * (s - side2) * (s - side3)));
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public String toString() {
        return "Side-1 = " + side1 + ", Side-2 = " + side2
                + ", Side-3 = " + side3;
    }

    private boolean isValid(double _input) throws Exception{
        if(_input == 0){
            throw new Exception("Dimensions never be zero. \nTry next time.");
        }
        else if(_input < 0){
            throw new Exception("Dimensions never be negative value. \nTry next time");
        }
        return true;
    }
}