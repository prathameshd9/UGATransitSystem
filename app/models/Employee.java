package models;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import play.db.DB;
/**
 * Created by RAKESH on 27/11/16.
 */

@Entity
public class Employee{

    @Id
    public int empid;
    
    public String userid;
    public String fname;
    public String lname;
    public Date dateofjoining;
    public String empstatus;


    public int getEmpId() {
        return empid;
    }

    public void setEmpId(int empid) {
        this.empid = empid;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getfName() {
        return fname;
    }

    public void setfName(String fname) {
        this.fname = fname;
    }

    public String getlName() {
        return lname;
    }

    public void setlName(String lname) {
        this.lname = lname;
    }
    
    
    public Date getDateOfJoining() {
        
        return dateofjoining;
    }

    public void setDateOfJoining(Date dateofjoining) {
        this.dateofjoining = dateofjoining;
    }
    
    public String getEmpStatus() {
        return empstatus;
    }

    public void setEmpStatus(String empstatus) {
        this.empstatus = empstatus;
    }
}
