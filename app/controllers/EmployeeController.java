package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import services.*;
import java.sql.SQLException;

import play.mvc.*;
import play.*;
import play.db.jpa.*;
import views.html.*;
import models.*;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.*;
import play.db.jpa.Transactional;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by RAKESH on 27/11/16.
 */
public class EmployeeController extends Controller{

    //salted hash
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
        
    //get employee details and persist
    @Transactional(readOnly = true)
    public Result addEmployee() {

        final DynamicForm form = Form.form().bindFromRequest();
        final String userId = form.get("userId");
        final String fName = form.get("fName");
        final String lName = form.get("lName");
        final  String empStatus = form.get("empStatus");
        
        Employee emp = new Employee();
        emp.setUserId(userId);
        emp.setfName(fName);
        emp.setlName(lName);
        emp.setEmpStatus(empStatus);
        
        // DATE CHANGE STARTED
        String doj = form.get("doj");
        System.out.println("DOJ:"+doj);
        DateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date_temp=null;
        try {
            date_temp = (Date) formatter.parse(doj);
            
            //set date
            emp.setDateOfJoining(date_temp);
            System.out.println("JAVA DATE: "+emp.getDateOfJoining());
            
        } catch (ParseException ex) {

        }
        System.out.println("output: "+date_temp);
        // DATE CHANGE END


        EmployeeService empService = new EmployeeService();
        Employee emp2= empService.addEmployee(emp);
        
        String message= "Employee " + emp2.getEmpId() + " added successfully";
        return ok(views.html.message.render(message));

    }

    //get user details and persist
    @Transactional(readOnly = true)
    public Result createUser(){

        final DynamicForm form = Form.form().bindFromRequest();
        final String userId = form.get("userId");
        final String password = form.get("password");
        final String level = form.get("level");

        System.out.println("userId ID: "+userId);
        System.out.println("fName ID: "+password);
        System.out.println("lName ID: "+level);

        Login createUser = new Login();
        createUser.setUserId(userId);
        createUser.setPassword(password);
        createUser.setLevel(level);


        EmployeeService empService = new EmployeeService();
        boolean isUserCreated = empService.createNewUser(createUser);
        
        String message= "User" + userId + "added successfully";
        return ok(views.html.message.render(message));

    }
    
}
