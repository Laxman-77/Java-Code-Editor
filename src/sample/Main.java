package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    public static Stage getStage() { return stage; }


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.getIcons().add(new Image("file:EditorLogo.png"));
      //  primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "EditorLogo.png" )));
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("OOM MINI PROJECT  Section 'A' Group 10");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setScene(new Scene(root,bounds.getWidth(),bounds.getHeight()-20));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
