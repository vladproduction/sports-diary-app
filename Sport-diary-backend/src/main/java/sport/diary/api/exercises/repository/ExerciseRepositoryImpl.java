package sport.diary.api.exercises.repository;

import sport.diary.api.common.repository.AbstractRepository;
import sport.diary.api.exercises.model.Exercise;
import sport.diary.api.signup.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExerciseRepositoryImpl extends AbstractRepository implements ExerciseRepository {
    @Override
    public void create(Exercise exercise, Customer customer) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into exercises (title, customers_id) values " +
                    "(?, (select id from customers where login = ?));");
            ps.setString(1, exercise.getTitle());
            ps.setString(2, customer.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Exercise> readAll(Customer customer) {
        try(Connection c = getConnection()){
            List<Exercise> exerciseList = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM exercises where customers_id = " +
                    "(select id from customers where login = ?);");
            ps.setString(1, customer.getLogin());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                String exerciseName = resultSet.getString("title");
                Exercise exercise = new Exercise();
                exercise.setTitle(exerciseName);
                exerciseList.add(exercise);
            }
            return exerciseList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Exercise old, Exercise candidate, Customer customer) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("update exercises set title = ? where title = ? " +
                    "and customers_id = (select id from customers where login = ?);");
            ps.setString(1,candidate.getTitle());
            ps.setString(2, old.getTitle());
            ps.setString(3, customer.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String exerciseName, Customer customer) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("delete from exercises where title = ? and " +
                    "customers_id = (select id from customers where login = ?);");
            ps.setString(1,exerciseName);
            ps.setString(2,customer.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
