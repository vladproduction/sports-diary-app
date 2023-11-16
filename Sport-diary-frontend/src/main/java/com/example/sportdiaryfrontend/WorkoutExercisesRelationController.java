package com.example.sportdiaryfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WorkoutExercisesRelationController {
    @FXML
    private Label currentWorkoutLB;
    @FXML
    public void initialize(){
        String workoutName = Utils.currentWorkout.getName();
        currentWorkoutLB.setText(workoutName);

    }
}
