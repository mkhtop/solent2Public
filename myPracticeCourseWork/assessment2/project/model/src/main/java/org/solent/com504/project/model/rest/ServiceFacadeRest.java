package org.solent.com504.project.model.rest;

import java.util.List;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.ReplyMessage;

public interface ServiceFacadeRest {

    public ReplyMessage getHeartbeat();
    
    public List<Person> findNurses();
    
    public boolean changeStatus(String status, long id, String clockIn);
}
