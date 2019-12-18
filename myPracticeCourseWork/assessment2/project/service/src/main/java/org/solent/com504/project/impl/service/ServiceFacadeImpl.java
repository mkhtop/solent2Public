package org.solent.com504.project.impl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.dto.Appointment;
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
    public List<Person> findNurses(){
        return personDao.findByRole(Role.NURSE);
    }
    
    @Override
    public List<Person> findPatients(){
        return personDao.findByRole(Role.PATIENT);
    };
    
    @Override
    public Person findById(long id){
        return personDao.findById(id);
    };
    
    @Override
    public List<Person> findByName(String fName, String sName){
        List<Person> pList = personDao.findByName(fName, sName);
        return pList;
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
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        Date clockIn = new Date();
        String dateStr = format.format(clocked);
        person.setClockIn(dateStr);
        personDao.save(person);
        return person;
    }
    
    @Override
    public Appointment addAppointment(Person nurse, Person patient, String appDate, String desc, Integer duration){
        LOG.debug("addAppointment called with: " + nurse + " " + patient + " " + appDate);
        Appointment appoint = new Appointment();
        appoint.setPersonA(nurse);
        appoint.setPersonB(patient);
        appoint.setDescripton(desc);
        appoint.setAppDate(appDate);
        appoint.setDurationMinutes(duration);
        appoint.setActive("active");
        appointmentDao.save(appoint);
        return appoint;
    };
    
    @Override
    public List<Appointment> findAllAppointments(){
        LOG.debug("findAllAppointments called");
        return appointmentDao.findAll();
    };
    
    @Override
    public boolean changeStatus(String status, long id, String clockIn){
        LOG.debug("changeStatus called with id=" + id + " status=" +status + " time=" + clockIn);
        Person person = personDao.findById(id);
        if(person==null){
            LOG.debug("changeStatus called with id=" + id + " not found ");
            return false;
        } else {
        person.setStatus(status);
        person.setClockIn(clockIn);
        personDao.save(person);
        return true;
        }
    }
    
    @Override
    public boolean deleteAll(){
        LOG.debug("deleteAll called");
        personDao.deleteAll();
        return true;
    }
        
    @Override
    public boolean deletePerson(long id){
        LOG.debug("deletePerson called with " + id);
        personDao.deleteById(id);
        return true;
    }
    
    @Override
    public List<String> checkIfLate(Date currentTime, Date checkTime){
        LOG.debug("service checkIfLate called with " + currentTime + " " + checkTime);
        long currentT = currentTime.getTime();
        long checkT = checkTime.getTime();
        checkT = checkT + 60000;
        List<String> sList = new ArrayList<>();
        if (checkT < currentT) {
            sList.add("late");
            return sList;
        } else {
            sList.add("onTime");
            return sList;
        }
    }
    
    
    
    
    
    
}