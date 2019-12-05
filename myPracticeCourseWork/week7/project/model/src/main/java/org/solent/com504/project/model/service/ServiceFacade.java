package org.solent.com504.project.model.service;

import java.util.Date;
import java.util.List;
import org.solent.com504.project.model.dto.Person;

public interface ServiceFacade {
    
    public String getHeartbeat();
    
    public List<Person> getAllPersons();
    
    public Person addPerson(String fName, String sName, String role, String address);
    
    public boolean changeStatus(String status, long id, Date clockIn);
    
    public boolean deletePerson(long id);
    
    boolean arrived(String username, String location);
        
}
