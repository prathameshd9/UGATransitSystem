package services;

import models.Login;
import play.db.DB;

import services.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by RAKESH on 26/11/16.
 */
public class LoginService {

    public boolean searchUser(Login user){

        Connection connection = DB.getConnection();
        PreparedStatement preparedStmt = null;
        PreparedStatement preparedStmt1 = null;
        ResultSet rs = null;
        try {
            //salted hashing
            byte[] salt="HDF3N".getBytes();
            byte[] hashPasswordByte = new EmployeeService().hashPassword(user.getPassword().toCharArray(),salt,3,25);
             String hashPassword = new String(hashPasswordByte);
        
           
            String query = "select level from Login WHERE userId='"+user.getUserId()+"' AND password='"+hashPassword+"'";
            preparedStmt = connection.prepareStatement(query);
            rs = preparedStmt.executeQuery();
            
            System.out.println(rs);
            
            if(rs.next()){
                String level=rs.getString("level");
                user.setLevel(level);
                rs.close();
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQl exception  "+e.getMessage());
        } finally {
            if (preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }

      return false;

    }
    
     public ArrayList<Login> getUserIDS() {

        Connection connection = DB.getConnection();
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        ArrayList<Login> userIDS = new ArrayList<Login>();
        String query = "select userId from Login WHERE userId NOT IN (SELECT userid from employee)";

        try {


        preparedStmt=connection.prepareStatement("select userId from Login WHERE userId NOT IN (SELECT userid from employee)");
        rs=preparedStmt.executeQuery();

        while(rs.next())
        {
            Login userID = new Login();
            userID.setUserId(rs.getString(1));
            System.out.println("USER ID "+userID.getUserId());
            userIDS.add(userID);
        }

        } catch (SQLException e) {
            System.out.println("SQl exception  "+e.getMessage());
        } finally {
            if (preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return userIDS;

    }
}
