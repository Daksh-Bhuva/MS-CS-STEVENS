import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class C14E1DisplayImages extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Image in = new Image("tn_in-flag.gif");
        Image us = new Image("tn_us-flag.gif");
        Image ca = new Image("tn_ca-flag.gif");
        Image as = new Image("tn_as-flag.gif");

        ImageView india = new ImageView(in);
        ImageView usa = new ImageView(us);
        ImageView canada = new ImageView(ca);
        ImageView australia = new ImageView(as);

        pane.add(india, 0, 0);
        pane.add(usa, 0, 1);
        pane.add(canada, 1, 0);
        pane.add(australia, 1, 1);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Four Flags");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}