package org.solent.com504.project.model.service;

import java.util.Date;
import java.util.List;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;

public interface ServiceFacade {
    
    public String getHeartbeat();
    
    public List<Person> getAllPersons();
    
    public List<Person> findNurses();
    
    public List<Person> findPatients();
    
    public Person findById(long id);
    
    public Person findByName(String fName, String sName);
    
    public Person addPerson(String fName, String sName, String role, String address);
    
    public Appointment addAppointment(Person nurse, Person patient, Integer hr, Integer day, Integer mnth, Integer year, String desc, Integer duration);
    
    public List<Appointment> findAllAppointments();
    
    public boolean changeStatus(String status, long id, String clockIn);
       
    public boolean deletePerson(long id);
    
    public boolean deleteAll();
    
    
        
}
