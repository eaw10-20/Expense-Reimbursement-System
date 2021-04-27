package com.dao;

import com.model.Reimbursement;
import com.model.Request;
import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReimbDaoImpl implements ReimbDao {

    @Override
    public int addReimbursementRequest(Request req) {
        int key = -1;
        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            //create sql statement to insert request
            String sql = "INSERT INTO ers_reimbursement (reimb_amount, reib_submitted,\n" +
                    "reimb_description, reimb_author, reimb_status_id, reimb_type_id)\n" +
                    "VALUES (?, 'now', ?, ?, 1, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, req.getAmount());
            ps.setString(2,req.getDescription());
            ps.setInt(3,req.getAuthor());
            ps.setInt(4,req.getType());

            //execute query
            ps.execute();

            //get generaged key
            ResultSet generatedKeys = ps.getGeneratedKeys();

            generatedKeys.next();
            key = generatedKeys.getInt("reimb_id");


        }catch(SQLException e){
            e.printStackTrace();
        }
        return key;
    }

    @Override
    public List<Reimbursement> getAllReimb() {
        //declaring a new user obj
        List<Reimbursement> reimbursements = new ArrayList<>();


        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            String sql = "SELECT * FROM ers_reimbursement er \n" +
                    "INNER JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "INNER JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id \n" +
                    "ORDER BY reimb_id DESC";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            //get user map
            UserService us = new UserServiceImpl();
            Map<Integer, String> userMap = us.getUserMap();

            //create new user
            while(rs.next()){
                //create new reimbursement
                Reimbursement reimb = new Reimbursement();

                //add data from query
                reimb.setId(rs.getInt("reimb_id"));
                reimb.setAmount(rs.getDouble("reimb_amount"));
                reimb.setTimeSubmitted(rs.getTimestamp("reib_submitted").toLocalDateTime());
                if(rs.getTimestamp("reimb_resolved") != null) {
                    reimb.setTimeResolved(rs.getTimestamp("reimb_resolved").toLocalDateTime());
                }
                reimb.setDescription(rs.getString("reimb_description"));
                reimb.setStatus(rs.getString("reimb_status"));
                reimb.setType(rs.getString("reimb_type"));

                //add map stuff
                reimb.setAuthor(userMap.get(rs.getInt("reimb_author")));
                if(rs.getTimestamp("reimb_resolved") != null){
                    reimb.setResolver(userMap.get(rs.getInt("reimb_resolver")));
                }

                //add reimbursement to list
                reimbursements.add(reimb);
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public Reimbursement getReimbBySerial(int id) {
        //declaring a new user obj
        Reimbursement reimbursement = new Reimbursement();


        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            String sql = "SELECT * FROM ers_reimbursement er \n" +
                    "INNER JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "INNER JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id\n" +
                    "WHERE er.reimb_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            //create new user
            if(rs.next()){
                //add data from query
                reimbursement.setId(rs.getInt("reimb_id"));
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                reimbursement.setTimeSubmitted(rs.getTimestamp("reib_submitted").toLocalDateTime());
                if(rs.getTimestamp("reimb_resolved") != null) {
                    reimbursement.setTimeResolved(rs.getTimestamp("reimb_resolved").toLocalDateTime());
                }
                reimbursement.setDescription(rs.getString("reimb_description"));
                reimbursement.setStatus(rs.getString("reimb_status"));
                reimbursement.setType(rs.getString("reimb_type"));
            }

            //todo: need to make an sql request for the resolver of the reimbursement here


        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursement;
    }

    @Override
    public List<Reimbursement> getReimbByRequestee(int id) {
        //declaring a new user obj
        List<Reimbursement> reimbursements = new ArrayList<>();


        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){

            String sql = "SELECT * FROM ers_reimbursement er \n" +
                    "INNER JOIN ers_reimbursement_status ers ON er.reimb_status_id = ers.reimb_status_id\n" +
                    "INNER JOIN ers_reimbursement_type ert ON er.reimb_type_id = ert.reimb_type_id\n" +
                    "WHERE er.reimb_author = ?\n" +
                    "ORDER BY reimb_id DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            //get user map
            UserService us = new UserServiceImpl();
            Map<Integer, String> userMap = us.getUserMap();

            //create new user
            while(rs.next()){
                //create new reimbursement
                Reimbursement reimb = new Reimbursement();

                //add data from query
                reimb.setId(rs.getInt("reimb_id"));
                reimb.setAmount(rs.getDouble("reimb_amount"));
                reimb.setTimeSubmitted(rs.getTimestamp("reib_submitted").toLocalDateTime());
                if(rs.getTimestamp("reimb_resolved") != null) {
                    reimb.setTimeResolved(rs.getTimestamp("reimb_resolved").toLocalDateTime());
                }
                reimb.setDescription(rs.getString("reimb_description"));
                reimb.setStatus(rs.getString("reimb_status"));
                reimb.setType(rs.getString("reimb_type"));

                if(rs.getTimestamp("reimb_resolved") != null){
                    reimb.setResolver(userMap.get(rs.getInt("reimb_resolver")));
                }

                //add reimbursement to list
                reimbursements.add(reimb);
            }

            //todo: need to make an sql request for the resolver of the reimbursement here


        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public void updateStatus(int status, int resolver, List<Integer> ids) {
        try(Connection conn = DriverManager.getConnection(MyJDBC.url, MyJDBC.username, MyJDBC.password)){


//            UPDATE ers_reimbursement
//            SET reimb_resolved ='now', reimb_resolver = 2, reimb_status_id = 2
//            WHERE (reimb_id = 4 OR reimb_id = 5);

            //create sql statement to be executed using given info
            String sql = "UPDATE ers_reimbursement " +
                    "SET reimb_resolved ='now', reimb_resolver = ?, reimb_status_id = ? " +
                    "WHERE (";

            for(int i = 0; i < ids.size(); i++){
                sql += "reimb_id = "+ids.get(i);
                if(i < ids.size()-1) sql += " OR ";
            }
            sql += ")";

            System.out.println(sql);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,resolver);
            ps.setInt(2,status);

            //execute query
            ps.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
