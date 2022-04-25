public class Rectangle {

    private double width = 1.0;
    private double height = 1.0;
    private String errorMessage = "";

    public Rectangle() {
    }

    public Rectangle(double w, double h) throws Exception {
        setWidth(w);
        setHeight(h);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setWidth(double w) throws Exception {
        if(!isValidWidth(w))
            throw new Exception(errorMessage);
        width = w;
    }

    public void setHeight(double h) throws Exception {
        if(!isValidHeight(h))
            throw new Exception(errorMessage);
        height = h;
    }

    public void print() {
        System.out.println("\nRectangle Attributes:");
        System.out.println("~ Width     = " + width);
        System.out.println("~ Height    = " + height);
        System.out.println("~ Area      = " + getArea());
        System.out.println("~ Perimeter = " + getPerimeter());
    }

    public String toString() {
        return "[Width = " + width + "; Height = " + height + "]";
    }

    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        String s = obj.getClass().getName();
        if(!s.equals("Rectangle"))
            return false;

        Rectangle rect = (Rectangle) obj;
        if(width != rect.getWidth())
            return false;
        if(height != rect.getHeight())
            return false;
        else
            return true;
    }

    public boolean isValidWidth(double w) {
        if (w > 0)
            return true;
        else {
            errorMessage = "-> Invalid Input: Width must be greater than 0";
            return false;
        }
    }

    public boolean isValidHeight(double h) {
        if(h > 0)
            return true;
        else {
            errorMessage = "-> Invalid Input: Height must be greater than 0";
            return false;
        }
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return (2 * width) + (2 * height);
    }
}
