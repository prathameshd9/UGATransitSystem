package controllers;


import play.*;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import models.*;
import services.*;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.*;

import static play.libs.Json.*;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }
    
    @Transactional(readOnly = true)
    public Result addEmployee() {
        
        //display add employee form
        LoginService loginService = new LoginService();
        ArrayList<Login> userIDs = loginService.getUserIDS();
        return ok(views.html.addEmployee.render(userIDs));
    }
    
    @Transactional(readOnly = true)
    public Result createUser() {
        
        //display add employee form
        
        return ok(views.html.createNewUser.render());
    }
    
    //user details page
    @Transactional(readOnly = true)
      public Result userDetails() {
        
        List<Login> users = (List<Login>) JPA.em().createQuery("select u from Login u").getResultList();
        
        return ok(views.html.userDetails.render(users));
    }
    
    // public Result assignShift() {
        
    //     //get all parameters {shift id, employee id, bus no., start and end date }
    //     //check if employee already has shift assigned for same day; if yes redirect error page with message
    //     //else add shift in schedule table
    //     //display popup with message. Dont display new page: Note use Bootstrap modal
        
    //     return ok(views.html.shifts.render());
    // }
    
    // public Result displayShifts() {
        
        
    //     return ok(displayshifts.render());
    // }
    
    
    // public Result displayBuses() {
    @Transactional(readOnly = true)
      public Result displayBuses() {
        
        List<Bus> buses = (List<Bus>) JPA.em().createQuery("select b from Bus b").getResultList();
        
        return ok(views.html.busDetails.render(buses));
    }
    
    
    //  public Result availableShifts() {
    //     return ok(availableshifts.render());
    // }
    
    // public Result employeeDetails() {
    @Transactional(readOnly = true)
      public Result employeeDetails() {
        
        List<Employee> emps = (List<Employee>) JPA.em().createQuery("select e from Employee e").getResultList();
        
        return ok(views.html.employeeDetails.render(emps));
    }
    
   
     
    
}
