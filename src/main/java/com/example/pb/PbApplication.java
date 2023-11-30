package main.java.com.example.pb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PbApplication.class.getResource("phonebook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 640);
        stage.setTitle("PhoneBook!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}