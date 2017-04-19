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
 * Created by RAKESH on 27/11/16.
 */

@Entity
public class Bus{

    @Id
    public int busno;
    
    public String model;
    public String busstatus;


    public int getBusNo() {
        return busno;
    }

    public void setBusNo(int busno) {
        this.busno = busno;
    }
 
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public String getBusStatus() {
        return busstatus;
    }

    public void setBusStatus(String busstatus) {
        this.busstatus = busstatus;
    }
    
}    