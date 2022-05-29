/*
    (This program will require JavaFX to run)
    Final Project
    Name: Distribution of the number of different prime factors of non-prime numbers
    Prepared by:
    Siddharth Pansuria (CWID: 20005837)
    Daksh Bhuva (CWID: 10475468)
    Arpit Patel (CWID: 20006859)

 */

import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class FinalProject extends Application {

    private HashMap<Integer,Boolean> primes; //map which stores only primes
    private HashMap<Integer,Integer> primeFactors; //map storing number of unique prime factors
    private HashMap<Integer,Double> finalDistribution; //get the distribution
    private DataPane dataPane = new DataPane(); //to show texts and text fields
    private BarChart chartPane = new BarChart(); //to show final bar chart

    //getters/setters
    public HashMap<Integer, Boolean> getPrimes() {
        return primes;
    }

    public void setPrimes(HashMap<Integer, Boolean> primes) {
        this.primes = primes;
    }

    public HashMap<Integer, Double> getFinalDistribution() {
        return finalDistribution;
    }

    public void setFinalDistribution(HashMap<Integer, Double> finalDistribution) {
        this.finalDistribution = finalDistribution;
    }

    public HashMap<Integer, Integer> getPrimeFactors() {
        return primeFactors;
    }

    public void setPrimeFactors(HashMap<Integer, Integer> primeFactors) {
        this.primeFactors = primeFactors;
    }

    //finds all primes smaller than or equal to n using Sieve of Eratosthenes
    public void calculatePrimes(int n) {

        HashMap<Integer,Boolean> primes = new HashMap<Integer,Boolean>();

        boolean sieve[] = new boolean[n + 1];

        Arrays.fill(sieve, true);

        sieve[0] = false;
        sieve[1] = false;

        int start = 2;

        for(int i = start; i * i <= n; i++) {
            if(sieve[i] == false) {
                continue;
            }
            for(int j = i * i; j <= n; j += i) {
                sieve[j] = false;
            }
        }

        for(int i = 0; i <= n; i++) {
            if(sieve[i] == true) {
                primes.put(i, true);
            }
        }

        setPrimes(primes);

    }

    //gets number of unique prime factors of a non prime number
    public int getPrimeFactors(int n) {

        int numOfPrimeFactors = 0;

        for(int key: this.primes.keySet()) {

            if(n % key == 0) {
                numOfPrimeFactors += 1;
            }
            else {
                continue;
            }

            while(n % key == 0) {
                n /= key;
            }

            if(this.primes.containsKey(n)) {
                numOfPrimeFactors += 1;
                n = 1;
            }

            if(n == 1) {
                break;
            }

        }
        return numOfPrimeFactors;
    }

    //fills the hashmap that stores the number and the number of unique prime factors it has
    public void calculatePrimeFactors(int n) {
        HashMap<Integer,Integer> primeFactors = new HashMap<>();

        for(int i = 2; i < n + 1; i++) {

            if(!this.primes.containsKey(i)) {
                primeFactors.put(i,getPrimeFactors(i));
            }

        }

        setPrimeFactors(primeFactors);

    }

    //calculates final distribution
    public void calculateDistribution() {

        HashMap<Integer,Double> finalDistribution = new HashMap<Integer,Double>();
        for (int key: this.primeFactors.keySet()) {
            int numOfPrimeFactors = this.primeFactors.get(key);
            if(finalDistribution.containsKey(numOfPrimeFactors)) {
                finalDistribution.put(numOfPrimeFactors,finalDistribution.get(numOfPrimeFactors) + 1);
            }
            else {
                finalDistribution.put(numOfPrimeFactors,1.0);
            }
        }

        double total = 0;
        for (int key : finalDistribution.keySet()) {
            total += finalDistribution.get(key);
        }

        for(int key : finalDistribution.keySet()) {
            finalDistribution.put(key, (finalDistribution.get(key)/total) * 100);
        }

        setFinalDistribution(finalDistribution);

    }

    //for JavaFX i.e. display
    @Override
    public void start(Stage primaryStage) {

        GridPane mainPane = new GridPane();
        Button btUpdate = new Button("Update");
        mainPane.add(btUpdate, 0, 1);
        GridPane.setHalignment(btUpdate, HPos.CENTER);

        //Create and Register the handler
        btUpdate.setOnAction(new UpdateHandlerClass());

        //Put Text-fields and Barchart in the main pane
        mainPane.add(dataPane,0,0);
        mainPane.add(chartPane, 1, 0);

        //Set up scene and stage with the main pane
        Scene scene = new Scene(mainPane);
        primaryStage.setTitle("Bar chart");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    class UpdateHandlerClass implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if(dataPane.update() == true) //Clears the text-fields, checks if inputs were valid
            {
                dataPane.removeError(); //Clears any previous error messages
                chartPane.removeAll(); //Cleans the Bar Chart
                chartPane.update(dataPane.getData()); //Re draws the bar chart with new data from user
            }
        }
    }

    //subclass that shows and handles the data displayed and the user provides
    class DataPane extends GridPane {

        private HashMap<Integer,Double> data;

        private TextField totalNumbersTF = new TextField();

        private Text errorMessage = new Text();

        private Text currentN = new Text("Current n: 100");

        public DataPane() {

            setAlignment(Pos.CENTER);
            setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
            setHgap(5.5);
            setVgap(5.5);

            errorMessage.setStyle("-fx-stroke: #ff0000; -fx-fill: #ff0000;");
            Text rules = new Text("Please enter a number between 4 and 1000000.");
            rules.setStyle("-fx-stroke: #002aff; -fx-fill: #002aff;");

            add(new Label("This program gives the distribution"), 0, 0);
            add(new Label("of the number of different prime factors"),0,1);
            add(new Label(" of non-prime numbers. Here,"), 0, 2);
            add(new Label("UNF = Unique Prime Factors"),0,3);
            add(rules, 0,4);
            add(new Label("Enter the number n:"), 0, 5);
            add(totalNumbersTF, 1, 5);
            add(errorMessage, 0, 6);
            add(currentN,0,7);
        }

        //Used to transfer new user data to the BarChart class
        public HashMap<Integer, Double> getData() {
            return data;
        }

        //Clears error message from the stage
        public void removeError() {
            errorMessage.setText("");
        }

        //gets input from user and stores it if true, rejects it if the user inputs something else
        public boolean update() {

            //Clear Text Fields
            int totalNumbers = Integer.parseInt(totalNumbersTF.getText());

            if(totalNumbers < 4 || totalNumbers > 10000000) {
                errorMessage.setText("Enter an integer between 1 and 1000000");
                return false;
            }

            FinalProject obj = new FinalProject();
            obj.calculatePrimes(totalNumbers);
            obj.calculatePrimeFactors(totalNumbers);
            obj.calculateDistribution();
            data = obj.getFinalDistribution();
            currentN.setText("Current n: " + totalNumbers);
            return true;
        }//end boolean update

    }//end class DataPane


    //subclass that draws the bar chart based on the user input
    class BarChart extends Pane {

        Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.PINK,
                Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.BLUE,
                Color.GRAY};
        String [] dataName = {"1 UPF", "2 UPF", "3 UPF", "4 UPF", "5 UPF", "6 UPF", "7 UPF", "8 UPF", "9 UPF"};
        HashMap<Integer,Double> data = new HashMap<Integer,Double>();

        double width = 800;
        double height = 800;

        public BarChart() {
            //initial values
            data.put(1,13.514);
            data.put(2,75.676);
            data.put(3,10.811);
            paint();
        }

        //Updates the data array for the percentage weights
        public void update(HashMap<Integer,Double> newData) {

            data.clear();
            for (int key : newData.keySet()) {
                data.put(key, newData.get(key));
            }

            paint();
        }

        //Clears the pane of all Nodes so Chart can be redrawn
        public void removeAll() {
            getChildren().remove(0,data.size()*3+1);
        }

        //Creates Bar Chart
        public void paint()
        {

            double max = data.get(1);

            for (int key : data.keySet()) {
                max = Math.max(max, data.get(key));
            }

            double barWidth = (width - 10.0) / data.size() - 10;
            double maxBarHeight = height - 30;

            getChildren().add(new Line(5, height - 10, width - 5, height - 10));

            int x = 15;
            int ctr = 0;
            for (int key : data.keySet()) {
                double newHeight = maxBarHeight * data.get(key) / max;
                double y = height - 10 - newHeight;
                Rectangle rectangle = new Rectangle(x, y, barWidth, newHeight);
                rectangle.setFill(colors[(ctr + 2) % colors.length]);

                getChildren().add(rectangle);
                getChildren().add(new Text(x, y - 7, dataName[key - 1]));
                getChildren().add(new Text(x+3, height-newHeight+10, (String.format("%.3f", data.get(key)))+"%"));
                x += barWidth + 7;
                ctr++;
            }
        }//end void paint

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
