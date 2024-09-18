package sport.diary.api.workout.repository;

import sport.diary.api.signup.model.Customer;
import sport.diary.api.workout.model.Workout;

import java.util.List;

public interface WorkoutRepository {
    public void create(Workout workout, Customer customer);
    public List<Workout> readAll(Customer customer);
    public Workout read(String name,Customer customer);
    public void update(Workout old, Workout candidate,Customer customer);
    public void delete(String name,Customer customer);

}
