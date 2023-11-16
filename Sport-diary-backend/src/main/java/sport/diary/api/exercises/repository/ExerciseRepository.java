package sport.diary.api.exercises.repository;

import sport.diary.api.exercises.model.Exercise;
import sport.diary.api.signup.model.Customer;
import sport.diary.api.workout.model.Workout;

import java.util.List;

public interface ExerciseRepository {
    public void create(Exercise exercise, Customer customer);
    public List<Exercise> readAll(Customer customer);
    public void update(Exercise old, Exercise candidate,Customer customer);
    public void delete(String title,Customer customer);
}
