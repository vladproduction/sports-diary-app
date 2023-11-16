package sport.diary.api.workout.repository;

import sport.diary.api.common.repository.AbstractRepository;
import sport.diary.api.signup.model.Customer;
import sport.diary.api.workout.model.Workout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRepositoryImpl extends AbstractRepository implements WorkoutRepository {
    @Override
    public void create(Workout workout, Customer customer) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into workout (name, customers_id) " +
                    "values (?, (select id from customers where login = ?))");
            ps.setString(1, workout.getName());
            ps.setString(2, customer.getLogin());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Workout> readAll(Customer customer) {
        try(Connection c = getConnection()){
            List<Workout> workoutList = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM workout where customers_id = " +
                    "(select id from customers where login = ?);");
            ps.setString(1, customer.getLogin());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                String workoutName = resultSet.getString("name");
                Workout workout = new Workout();
                workout.setName(workoutName);
                workoutList.add(workout);
            }
            return workoutList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Workout read(String name, Customer customer) {

        return null;
    }

    @Override
    public void update(Workout old, Workout candidate, Customer customer) {

        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("update workout set name = ? where name = ? and " +
                    "customers_id = (select id from customers where login = ?);");
            ps.setString(1,candidate.getName());
            ps.setString(2, old.getName());
            ps.setString(3, customer.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String workoutName, Customer customer) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("delete from workout where name = ? and " +
                    "customers_id = (select id from customers where login = ?);");
            ps.setString(1,workoutName);
            ps.setString(2, customer.getLogin());
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
