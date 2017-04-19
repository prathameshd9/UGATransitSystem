

CREATE TABLE bus (busno INT NOT NULL, model VARCHAR(10),busstatus VARCHAR(10), PRIMARY KEY(busno));



CREATE TABLE employee (empid INT NOT NULL, userid VARCHAR(10) NOT NULL, fname VARCHAR(15), lname VARCHAR(15), 
empstatus VARCHAR(10), PRIMARY KEY(empid),
);


CREATE TABLE employeeschedule (shiftid VARCHAR(10) NOT NULL, empid INT NOT NULL, day VARCHAR(10), 
route VARCHAR(10), starttime varchar, endtime varchar, PRIMARY KEY(shiftid));



CREATE TABLE login(userid VARCHAR(10) NOT NULL, password VARCHAR(50) NOT NULL, level VARCHAR(10), PRIMARY KEY(userid));


CREATE TABLE training( shiftid VARCHAR(20) NOT NULL, trainer INT, trainee INT, PRIMARY KEY(shiftid), 
INDEX(trainer,trainee), FOREIGN KEY(trainer) REFERENCES employee(empid), FOREIGN KEY(trainee) REFERENCES employee(empid));



ALTER TABLE employee ADD CONSTRAINT fk_user FOREIGN KEY(userid) REFERENCES login(userid);

ALTER TABLE emp_sch ADD CONSTRAINT fk_bus FOREIGN KEY(busno) REFERENCES bus(busno);

ALTER TABLE emp_sch ADD CONSTRAINT fk_emp FOREIGN KEY(empid) REFERENCES employee(empid);

ALTER TABLE training ADD CONSTRAINT fk_empsch FOREIGN KEY(shiftid) REFERENCES emp_schedule(shiftid);

ALTER TABLE training ADD CONSTRAINT fk_emp FOREIGN KEY(trainee) REFERENCES employee(empid);

ALTER TABLE training ADD CONSTRAINT fk_emp_trainer FOREIGN KEY(trainer) REFERENCES employee(empid);