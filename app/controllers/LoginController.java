package controllers;

import models.Login;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.*;
import play.db.jpa.*;
import views.html.*;
import models.*;
import services.*;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.*;
import play.db.jpa.Transactional;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;


import static play.mvc.Results.ok;

/**
 * Created by RAKESH on 24/11/16.
 */
public class LoginController extends Controller{
    
    
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
                
      
    @Transactional
    public Result submit(){

        final DynamicForm form = Form.form().bindFromRequest();
        
        final String userName = form.get("userName");
        final String userPassword = form.get("userPassword");
        
        Login user = new Login();
        user.setUserId(userName);
        user.setPassword(userPassword);
        
        LoginService loginService = new LoginService();

        boolean isPresent = loginService.searchUser(user);
        
        
        
        if (isPresent) {
            System.out.println("present, name = " + user.getUserId());
           
            // hashed password taken from db
            String query="select s.level from Login s where s.userId= '" + form.get("userName") + "'";
            String level= (String) JPA.em().createQuery(query).getSingleResult();
           
            if (level.equalsIgnoreCase("admin")) {
                return ok(adminPage.render(userName));
            }
            if (level.equalsIgnoreCase("employee")) {
                return ok(employeePage.render(userName));
            }
 
        }
        else {
            System.out.println("Password NOT FOUND");
            return ok("Password NOT FOUND");
 
        }
        return ok("Level NOT FOUND");
    }

    //Salted Hashing- Function end
    
}
