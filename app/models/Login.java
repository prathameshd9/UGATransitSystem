package models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.db.DB;

/**
 * Created by RAKESH on 23/11/16.
 */

@Entity
public class Login {

    @Id
    public String userId;
    public String password;
    public String level;

    public String getUserId() {
    return userId;
    }
    
    public void setUserId(String id) {
    this.userId = id;
    }
    
    public String getPassword() {
    return password;
    }
    
    public void setPassword(String password) {
    this.password = password;
    }
    
    public String getLevel() {
    return level;
    }
    
    public void setLevel(String level) {
    this.level = level;
    }
    

}

