package com.dao;

import com.model.Employee;
import com.model.FinanceManager;
import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{
    @Override
    public int insertUser(User u, int role) {
        int key = -1;

        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            //create sql statement to be executed using given info
            String sql = "ers_users (ers_username, ers_password, user_first_name," +
                    " user_last_name, user_email, user_role_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getFirstName());
            ps.setString(4,u.getLastName());
            ps.setString(5,u.getEmail());
            ps.setInt(6, role);


            //execute query
            ps.execute();

            //get generaged key
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next()){
                key = generatedKeys.getInt("ers_users_id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return key;
    }

    @Override
    public Map<Integer, String> getUserMap() {

        //create map
        Map<Integer, String> userMap = new HashMap<>();

        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            String sql = "Select * FROM ers_users";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(); //<----query not update

            //create new user
            while(rs.next()) {
                userMap.put(rs.getInt("ers_users_id"),
                        rs.getString("user_first_name") + " " + (rs.getString("user_last_name"))
                );
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        return userMap;
    }

    @Override
    public User selectUserByID(int id) {
        //declaring a new user obj
        User newUser = null;

        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            String sql = "Select * FROM ers_users WHERE ers_users_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery(); //<----query not update

            //create new user
            rs.next();
            newUser = getUserType(rs.getInt("user_role_id"));
            newUser.setUsername(rs.getString("ers_username"));
            newUser.setPassword(rs.getString("ers_password"));
            newUser.setFirstName(rs.getString("user_first_name"));
            newUser.setLastName(rs.getString("user_last_name"));
            newUser.setEmail(rs.getString("user_email"));


        }catch(SQLException e){
            e.printStackTrace();
        }
        return newUser;
    }

    @Override
    public User selectUserByUsername(String username) {
        //declaring a new user obj
        User newUser = null;

        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            String sql = "SELECT * FROM ers_users WHERE ers_username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery(); //<----query not update

            //create new user
            if(rs.next()){
                newUser = getUserType(rs.getInt("user_role_id"));
                newUser.setUserID(rs.getInt("ers_users_id"));
                newUser.setUsername(rs.getString("ers_username"));
                newUser.setPassword(rs.getString("ers_password"));
                newUser.setFirstName(rs.getString("user_first_name"));
                newUser.setLastName(rs.getString("user_last_name"));
                newUser.setEmail(rs.getString("user_email"));
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        return newUser;
    }

    //determines which type of user to create
    //1 -> Employee
    //2 -> FinanceManager
    private User getUserType(int type){
        if(type == 2) return new FinanceManager();
        else return new Employee();
    }

//    @Override
//    public void h2InitDao() {
//
//    }
}
