package crs.sanjana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("scenes/MainScene.fxml"));
        primaryStage.setTitle("Classroom Reservation System For North South University by Sanjana Rubaiat");
        Image image = new Image(getClass().getResourceAsStream("resources/nsu_logo.png"));
        primaryStage.getIcons().add(image);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

