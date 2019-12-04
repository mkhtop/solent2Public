package org.solent.com504.project.impl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;
import org.solent.com504.project.model.service.ServiceFacade;

public class ServiceFacadeImpl implements ServiceFacade {

    private PersonDAO personDao = null;
    
    final static Logger LOG = LogManager.getLogger(ServiceFacadeImpl.class);

    private AppointmentDAO appointmentDao = null;
    
       // used to concurently count heartbeat requests
    private static AtomicInteger heartbeatRequests = new AtomicInteger();

    // setters for DAOs
    public void setPersonDao(PersonDAO personDao) {
        this.personDao = personDao;
    }

    public void setAppointmentDao(AppointmentDAO appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    // Service facade methods

    @Override
    public String getHeartbeat() {
        return "heartbeat number "+heartbeatRequests.getAndIncrement()+ " "+ new Date().toString();
    }
    
    @Override
    public List<Person> getAllPersons(){
        return personDao.findAll();
    }
    
    @Override
    public Person addPerson(String fName, String sName, String role, String address){
        LOG.debug("addPerson called with " + fName + " " + sName + " " + role + " " + address);
        Person person = new Person();
        if ("Nurse".equals(role)){
            person.setRole(Role.NURSE);
        } else if ("Patient".equals(role)){
            person.setRole(Role.PATIENT);
        } else {
            throw new IllegalArgumentException("role not assigned");
        }
        person.setFirstName(fName);
        person.setSecondName(sName);
        person.setAddress(address);
        person.setStatus("NA");
        person.setActive("active");
        personDao.save(person);
        return person;
    }
    
    @Override
    public boolean changeStatus(String status, long id){
        LOG.debug("changeStatus called with " + id + " " +status);
        Person person = personDao.findById(id);
        person.setStatus(status);
        personDao.save(person);
        return true;
    }
    
    @Override
    public boolean deletePerson(long id){
        LOG.debug("deletePerson called with " + id);
        personDao.deleteById(id);
        return true;
    }
    
    @Override
    public boolean arrived(String username, String location){
        //person dao, find and change state of person so that main server updates states
        LOG.debug("arrvied called with " + username + " " + location);
        return true;
    };
    
    
    
    
}
