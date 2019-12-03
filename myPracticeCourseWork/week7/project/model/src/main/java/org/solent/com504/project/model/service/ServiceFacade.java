package org.solent.com504.project.model.service;

import java.util.List;
import org.solent.com504.project.model.dto.Person;

public interface ServiceFacade {
    
    public String getHeartbeat();
    
    public List<Person> getAllPersons();
    
    public Person addPerson(String fName, String sName, String role, String address);
    
    boolean arrived(String username, String location);
        
}
