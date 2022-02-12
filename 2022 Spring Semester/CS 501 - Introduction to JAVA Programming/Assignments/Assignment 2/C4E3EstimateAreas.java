public class C4E3EstimateAreas {
    public static void main(String[] args) {

        System.out.println("");
        System.out.println("- This program uses the GPS locations for Atlanta, Georgia; Orlando, Florida; Savannah, Georgia; and Charlotte, North Carolina");
        System.out.println("- Computes the estimated area enclosed by these four cities.");
        System.out.println("");

        double radius = 6371.01;
        double Ax = Math.toRadians(33.7489954), Ay = Math.toRadians(-84.3879824);
        double Ox = Math.toRadians(28.5383355), Oy = Math.toRadians(-81.3792365);
        double Sx = Math.toRadians(32.0835407), Sy = Math.toRadians(-81.0998342);
        double Cx = Math.toRadians(35.2270869), Cy = Math.toRadians(-80.8431267);

        System.out.println("- GPS locations (latitude and longitude) of these cities in 'degrees': ");
        System.out.println("Atlanta, Georgia: (33.7489954, 284.3879824)");
        System.out.println("Orlando, Florida: (28.5383355, 281.3792365)");
        System.out.println("Savannah, Georgia: (32.0835407, 281.0998342)");
        System.out.println("Charlotte, North Carolina: (35.2270869, 280.8431267)");
        System.out.println("");

        double dAO = radius * Math.acos(Math.sin(Ax)*Math.sin(Ox) + Math.cos(Ax)*Math.cos(Ox)*Math.cos(Ay - Oy));
        double dOS = radius * Math.acos(Math.sin(Ox)*Math.sin(Sx) + Math.cos(Ox)*Math.cos(Sx)*Math.cos(Oy - Sy));
        double dSC = radius * Math.acos(Math.sin(Sx)*Math.sin(Cx) + Math.cos(Sx)*Math.cos(Cx)*Math.cos(Sy - Cy));
        double dCA = radius * Math.acos(Math.sin(Cx)*Math.sin(Ax) + Math.cos(Cx)*Math.cos(Ax)*Math.cos(Cy - Ay));

        System.out.println("Distance between Atlanta and Orlando: " + dAO + " km");
        System.out.println("Distance between Orlando and Savannah: " + dOS + " km");
        System.out.println("Distance between Savannah and Charlotte: " + dSC + " km");
        System.out.println("Distance between Charlotte and Atlanta: " + dCA + " km");
        System.out.println("");

        double dAS = radius * Math.acos(Math.sin(Ax)*Math.sin(Sx) + Math.cos(Ax)*Math.cos(Sx)*Math.cos(Ay - Sy));

        System.out.println("Dividing the formed quadrilateral into two triangles: ");
        System.out.println("");
        System.out.println("Triangle 1 formed by Atlanta, Orlando and Savannah");
        System.out.println("Triangle 2 formed by Atlanta, Charlotte and Savannah");
        System.out.println("");

        double sAOS = (dAO + dOS + dAS) / 2;
        double sACS = (dCA + dSC + dAS) / 2;

        double areaAOS = Math.pow(sAOS * (sAOS - dAO) * (sAOS - dOS) * (sAOS - dAS), 0.5);
        double areaACS = Math.pow(sACS * (sACS - dCA) * (sACS - dSC) * (sACS - dAS), 0.5);

        System.out.println("Area of Triangle 1: " + areaAOS + " km^2");
        System.out.println("Area of Triangle 2: " + areaACS + " km^2");
        System.out.println("");

        System.out.println("=> The estimated area enclosed by the four cities is " + (areaAOS + areaACS) + " km^2");
    }
}
