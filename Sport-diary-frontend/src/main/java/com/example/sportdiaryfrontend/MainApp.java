package com.example.sportdiaryfrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //System.setOut(new PrintStream(new File("outPS.txt")));

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("register-login-page.fxml"));
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(fxmlLoader.load(), bounds.getWidth()*0.50, bounds.getHeight()*0.50);
        stage.setScene(scene);
        Utils.stage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}