package services;

import models.Employee;
import models.Login;
import play.db.DB;

import java.sql.*;
import java.sql.Date;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;

/**
 * Created by RAKESH on 27/11/16.
 */
public class EmployeeService {


    //Salted Hash
     public byte[] hashPassword( char[] password, byte[] salt,  int iterations, int keyLength) {
         try {
                SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
                PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
                SecretKey key = skf.generateSecret(spec);
                byte[] res = key.getEncoded();
                 return res;
 
                } catch (Exception e) {
                     throw new RuntimeException(e);
                   }
        }

   public Employee addEmployee(Employee emp) {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        int empId;

        // DATE CHANGE START
        System.out.println("JAVA DATE IN SERVICE"+emp.getDateOfJoining());
        Date sqlDate = new java.sql.Date(emp.getDateOfJoining().getTime());


        ResultSet rs = null;
        try {
            preparedStmt=connection.prepareStatement("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'EMPLOYEE'");
            rs=preparedStmt.executeQuery();
            rs.next();
            empId= rs.getInt(1);
            emp.setEmpId(empId);

            connection=DB.getConnection();

            preparedStmt=connection.prepareStatement("insert into EMPLOYEE values(?,?,?,?,?,?)");
            stmt=connection.createStatement();
            System.out.println("inserting data into table");
            System.out.println("i m here ..");
            preparedStmt.setInt(1,empId);
            preparedStmt.setString(2,emp.getUserId());
            preparedStmt.setString(3,emp.getfName());
            preparedStmt.setString(4,emp.getlName());
            preparedStmt.setDate(5,sqlDate);
            System.out.println("DATE OF JOINING: "+sqlDate);
            preparedStmt.setString(6,emp.getEmpStatus());

            System.out.println("i m here 2..");
            int i=preparedStmt.executeUpdate();
            System.out.println("i m here 3..");

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

        return emp;

        // DATE CHANGE END

    }

    public boolean createNewUser(Login createUser) {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        boolean flag = false;

        ResultSet rs = null;
        try {
            preparedStmt=connection.prepareStatement("insert into LOGIN values(?,?,?)");
            stmt=connection.createStatement();
            System.out.println("inserting data into table");
            preparedStmt.setString(1,createUser.getUserId());
            
            //salted hashing
            byte[] salt="HDF3N".getBytes();
            byte[] hashPasswordByte = hashPassword(createUser.getPassword().toCharArray(),salt,3,25);
           
            String hashPassword = new String(hashPasswordByte);
            
            preparedStmt.setString(2,hashPassword);
            preparedStmt.setString(3,createUser.getLevel());

            int i=preparedStmt.executeUpdate();
            flag = true;

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

        return true;
    }

}
