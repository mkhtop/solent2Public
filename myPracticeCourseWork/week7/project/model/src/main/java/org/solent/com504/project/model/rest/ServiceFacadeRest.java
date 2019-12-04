package org.solent.com504.project.model.rest;

import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.ReplyMessage;

public interface ServiceFacadeRest {

    public ReplyMessage getHeartbeat();
    
     public Person addPerson(String fName, String sName, String role, String address);
}
