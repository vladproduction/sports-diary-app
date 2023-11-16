package com.example.sportdiaryfrontend;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import sport.diary.api.exercises.model.Exercise;
import sport.diary.api.exercises.repository.ExerciseRepository;
import sport.diary.api.exercises.repository.ExerciseRepositoryImpl;
import sport.diary.api.workout.model.Workout;
import sport.diary.api.workout.repository.WorkoutRepository;
import sport.diary.api.workout.repository.WorkoutRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class HomePageController {

    @FXML
    private Button addWorkoutBTN;
    @FXML
    private Button updateWorkoutBTN;
    @FXML
    private Button deleteWorkoutBTN;
    @FXML
    private Button addExercisesBTN;
    @FXML
    private Button updateExercisesBTN;
    @FXML
    private Button deleteExercisesBTN;

    @FXML
    private ListView<Workout> workoutLW;
    @FXML
    private ListView<Exercise> exercisesLW;

    private WorkoutRepository workoutRepository = new WorkoutRepositoryImpl();
    private ExerciseRepository exerciseRepository = new ExerciseRepositoryImpl();

    @FXML
    public void initialize() {

        List<Exercise> exerciseList = exerciseRepository.readAll(Utils.currentCustomer);
        exercisesLW.getItems().addAll(exerciseList);
        List<Workout> workoutList = workoutRepository.readAll(Utils.currentCustomer);
        workoutLW.getItems().addAll(workoutList);
        switchState(true);
        workoutLW.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int selectedIndex = workoutLW.getSelectionModel().getSelectedIndex();
                    List<Workout> workoutList = workoutLW.getItems();
                    Workout selectedWorkout = workoutList.get(selectedIndex);
                    Utils.currentWorkout = selectedWorkout;
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("workout-exercises-relations.fxml"));
                    Rectangle2D bounds = Screen.getPrimary().getBounds();
                    Scene scene = new Scene(fxmlLoader.load(), bounds.getWidth() * 0.50, bounds.getHeight() * 0.55);
                    Utils.stage.setScene(scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void switchState(boolean isWorkout) {
        if (isWorkout) {
            exercisesLW.setVisible(false);
            addExercisesBTN.setVisible(false);
            updateExercisesBTN.setVisible(false);
            deleteExercisesBTN.setVisible(false);

            workoutLW.setVisible(true);
            addWorkoutBTN.setVisible(true);
            updateWorkoutBTN.setVisible(true);
            deleteWorkoutBTN.setVisible(true);
        } else {
            exercisesLW.setVisible(true);
            addExercisesBTN.setVisible(true);
            updateExercisesBTN.setVisible(true);
            deleteExercisesBTN.setVisible(true);

            workoutLW.setVisible(false);
            addWorkoutBTN.setVisible(false);
            updateWorkoutBTN.setVisible(false);
            deleteWorkoutBTN.setVisible(false);
        }
    }

    //workoutActions
    public void addWorkoutAction() {

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Workout Name");
        Optional<String> rez = inputDialog.showAndWait();
        String workoutName = rez.get();
        System.out.println(workoutName);
        Workout workout = new Workout();
        workout.setName(workoutName);
        //workoutLW.getItems().add(workout);
        workoutRepository.create(workout, Utils.currentCustomer);
        workoutLW.getItems().clear();
        List<Workout> workoutList = workoutRepository.readAll(Utils.currentCustomer);
        workoutLW.getItems().addAll(workoutList);

    }

    public void deleteWorkoutAction() {

        int selectedIndex = workoutLW.getSelectionModel().getSelectedIndex();
        List<Workout> workoutList = workoutLW.getItems();
        Workout selectedToDelete = workoutList.get(selectedIndex);
        workoutRepository.delete(selectedToDelete.getName(), Utils.currentCustomer);

        workoutLW.getItems().clear();
        List<Workout> workoutListView = workoutRepository.readAll(Utils.currentCustomer);
        workoutLW.getItems().addAll(workoutListView);

    }

    public void updateWorkoutAction() {

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("New workout name");
        Optional<String> result = inputDialog.showAndWait();
        String workoutName = result.get();
        Workout candidate = new Workout();
        candidate.setName(workoutName);

        int selectedIndex = workoutLW.getSelectionModel().getSelectedIndex();
        List<Workout> workoutList = workoutLW.getItems();
        Workout old = workoutList.get(selectedIndex);
        workoutRepository.update(old, candidate, Utils.currentCustomer);

        workoutLW.getItems().clear();
        List<Workout> workoutListView = workoutRepository.readAll(Utils.currentCustomer);
        workoutLW.getItems().addAll(workoutListView);
    }

    //exerciseActions
    public void addExercisesAction() {

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Exercise title");
        Optional<String> rez = inputDialog.showAndWait();
        String exerciseName = rez.get();
        System.out.println(exerciseName);
        Exercise exercise = new Exercise();
        exercise.setTitle(exerciseName);
        //workoutLW.getItems().add(workout);
        exerciseRepository.create(exercise, Utils.currentCustomer);
        exercisesLW.getItems().clear();
        List<Exercise> exerciseList = exerciseRepository.readAll(Utils.currentCustomer);
        exercisesLW.getItems().addAll(exerciseList);

    }

    public void deleteExercisesAction() {

        int selectedIndex = exercisesLW.getSelectionModel().getSelectedIndex();
        List<Exercise> exerciseList = exercisesLW.getItems();
        Exercise selectedToDelete = exerciseList.get(selectedIndex);
        exerciseRepository.delete(selectedToDelete.getTitle(), Utils.currentCustomer);

        exercisesLW.getItems().clear();
        List<Exercise> exerciseListNew = exerciseRepository.readAll(Utils.currentCustomer);
        exercisesLW.getItems().addAll(exerciseListNew);

    }

    public void updateExercisesAction() {

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("New exercise title");
        Optional<String> result = inputDialog.showAndWait();
        String exerciseName = result.get();
        Exercise candidate = new Exercise();
        candidate.setTitle(exerciseName);

        int selectedIndex = exercisesLW.getSelectionModel().getSelectedIndex();
        List<Exercise> exerciseList = exercisesLW.getItems();
        Exercise old = exerciseList.get(selectedIndex);
        exerciseRepository.update(old, candidate, Utils.currentCustomer);

        exercisesLW.getItems().clear();
        List<Exercise> exerciseListNew = exerciseRepository.readAll(Utils.currentCustomer);
        exercisesLW.getItems().addAll(exerciseListNew);
    }

    public void exerciseActive() {
        switchState(false);
    }

    public void workoutActive() {
        switchState(true);
    }
}
