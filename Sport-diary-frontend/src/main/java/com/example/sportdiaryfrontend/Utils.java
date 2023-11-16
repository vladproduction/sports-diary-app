package com.example.sportdiaryfrontend;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sport.diary.api.signup.model.Customer;
import sport.diary.api.workout.model.Workout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Utils {

    public static Stage stage;
    public static Customer currentCustomer;
    public static Workout currentWorkout;
    public static void showError(Exception ex) {
        File file = new File("error.txt");
        try {
            PrintStream ps = new PrintStream(file);
            ex.printStackTrace(ps);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showErrorWithAlert(Exception ex) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PrintStream ps = new PrintStream(out);
            ex.printStackTrace(ps);
            byte [] content = out.toByteArray();
            String errorMessage = new String(content);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorMessage);
            alert.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void showErrorShortAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ex.getMessage());
        alert.show();

    }


}
