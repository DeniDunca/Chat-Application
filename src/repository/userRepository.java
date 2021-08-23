package repository;

import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userRepository {
    public void addUser(String username,String password,String firstName,String secondName){
        String sql = "insert into users (username, password, firstname, lastname) values (?, ?, ?, ?)";

        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, secondName);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUser(){

    }

    public void deleteUser(){

    }

    public ArrayList<User> getUsersName(int userId){
        ArrayList<User> users = new ArrayList<>();

        String sql = "Select * from users where id <> "+ userId;
        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                users.add(new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();

        String sql = "Select * from users";

        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                users.add(new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public boolean checkUser(String username, String password){
        String sql = "Select username, password from users";

        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                if(rs.getString("username").equals(username))
                    if(rs.getString("password").equals(password))
                        return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public User getUser(String username, String password){
        String sql = "Select * from users";
        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                if(rs.getString("username").equals(username))
                    if(rs.getString("password").equals(password))
                        return new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("firstname"),rs.getString("lastname"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return new User(-1,"-","-","-","-");
    }

    public int getUserId(String username, String password){
        String sql = "Select * from users";
        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                if(rs.getString("username").equals(username))
                    if(rs.getString("password").equals(password))
                        return rs.getInt("id");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static ArrayList<Integer> getUserFriends(User user){
        ArrayList<Integer> userFriends = new ArrayList<>();
        String sql = "Select * from friendlist where userid1= " + user.getId() +" or userid2 = " + user.getId();
        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                int id = rs.getInt("userid1");
                if(id != user.getId())
                    userFriends.add(id);
                else {
                    userFriends.add(rs.getInt("userid2"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFriends;
    }

    public static User getUser(int id){
        String sql = "Select * from users where id = " + id;
        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                    return new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("firstname"),rs.getString("lastname"));
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return new User(-1,"-","-","-","-");
    }

    public void addInFriendList(int id1, int id2)
    {
        String sql = "Insert into friendlist (userid1, userid2) values (?,?)";
        try(Connection connection = new dbConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}

