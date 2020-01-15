/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.project.impl.service.ServiceObjectFactoryJpaImpl;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;


/**
 *
 * @author gallenc
 */
public class ServiceFacadeJpaTest {
    
    ServiceObjectFactory serviceObjectFactory = null;
    ServiceFacade serviceFacade = null;
  
    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();
    private PersonDAO personDao = null;
    
    @Before
    public void loadFactory() {

        serviceObjectFactory = new ServiceObjectFactoryJpaImpl();
        
        serviceFacade = serviceObjectFactory.getServiceFacade();

    }
    @Before
    public void loadPersonDao() {
        personDao = daoFactory.getPersonDAO();
        assertNotNull(personDao);
    }
    private void init() {
        // delete all in database
        System.out.println("init called");
        
        personDao.deleteAll();
        System.out.println("deleted all called");
        // add 5 patients
        for (int i = 1; i < 6; i++) {
            Person p = new Person();
            p.setAddress("address_" + i);
            p.setFirstName("firstName_" + i);
            p.setSecondName("secondName_" + i);
            p.setActive("active");
            p.setStatus("arrived");
            p.setRole(Role.PATIENT);
            p.setClockIn("not available");
            personDao.save(p);
        }
    }
    
    @Test
    public void testFactory() {
        System.out.println("start ServiceFacadeTest testFactory");
        assertNotNull(serviceFacade);

        System.out.println("end ServiceFacadeTest testFactory");
    }

    @Test
    public void testGetHeartbeat() {
        System.out.println("start ServiceFacadeTest testGetHeartbeat()");
        assertNotNull(serviceFacade);
        
        String heartbeat = serviceFacade.getHeartbeat();
        System.out.println("recieved heartbeat: "+heartbeat);
        assertNotNull(heartbeat);
        
        System.out.println("end ServiceFacadeTest testGetHeartbeat()");
    }

    // WHAT OTEHR TESTS DO YOU NEED FOR HE SERVICE?
    
    @Test
    public void testGetAllPerson() {
        System.out.println("start ServiceFacadeTest testGetAllPerson()");
        init();
        
        List<Person> pList = serviceFacade.getAllPersons();
        
        int i = pList.size();
        System.out.println("Number of persons in list: " + i);
        assertEquals(5,i);
        System.out.println("end ServiceFacadeTest testGetAllPerson()");
    }
    
    @Test
    public void testFindNurses() {
        System.out.println("start ServiceFacadeTest testFindNurses()");
        init();
        
        List<Person> pList = serviceFacade.findNurses();
        
        int i = pList.size();
        System.out.println("Number of patients in list: " + i);
        
        for (Person p : pList){
            assertEquals(Role.NURSE,p.getRole());
            System.out.println(p.toString());
        }
        
        
        System.out.println("end ServiceFacadeTest testFindNurses()");
    }
   
    @Test
    public void testFindPatients() {
        System.out.println("start ServiceFacadeTest testFindPatients()");
        init();
        
        List<Person> pList = serviceFacade.findPatients();
        
        int i = pList.size();
        System.out.println("Number of patients in list: " + i);
        assertEquals(5,i);
        
        for (Person p : pList){
            assertEquals(Role.PATIENT,p.getRole());
            System.out.println(p.toString());
        }
        
        System.out.println("end ServiceFacadeTest testFindPatients()");
    }
    
    
    @Test
    public void testFindByName() {
        System.out.println("start ServiceFacadeTest testFindByName()");
        init();
        String fName = "firstName_1";
        String sName = "secondName_1";
        List<Person> pList = serviceFacade.findByName(fName, sName);
        Person p = pList.get(0);
        System.out.println("person found = " + p.toString());
        assertTrue(p.getFirstName().equals(fName));
        assertTrue(p.getSecondName().equals(sName));
        
        System.out.println("end ServiceFacadeTest testFindByName()");
    }
    
    @Test
    public void testAddPerson() {
        System.out.println("start ServiceFacadeTest test()");
        init();
        List<Person> pList = serviceFacade.getAllPersons();
        assertEquals(5,pList.size());
        serviceFacade.addPerson("test_fName", "test_sName", "Nurse", "test_address");
        pList = serviceFacade.getAllPersons();
        assertEquals(6,pList.size());
        System.out.println("end ServiceFacadeTest test()");
    }
    
    @Test
    public void testAddAppointment() {
        System.out.println("start ServiceFacadeTest testAddAppointment()");
        init();
        
        
        System.out.println("end ServiceFacadeTest testAddAppointment()");
    }
    
    @Test
    public void testChangeStatus() {
        System.out.println("start ServiceFacadeTest testChangeStatus()");
        init();
        List<Person> pList = serviceFacade.getAllPersons();
        Person p = pList.get(0);
        System.out.println(p.toString());
        assertEquals(p.getStatus(),"arrived");
        serviceFacade.changeStatus("leaving", p.getId(), "2020-12-31 14:59");
        System.out.println(p.toString());
        assertEquals(p.getStatus(),"leaving");
        System.out.println("end ServiceFacadeTest testChangeStatus()");
    }
    
    @Test
    public void testDeletePerson() {
        System.out.println("start ServiceFacadeTest testDeletePerson()");
        init();
        List<Person> pList = serviceFacade.getAllPersons();
        assertEquals(5, pList.size());
        long i = pList.get(0).getId();
        System.out.println("deleting person: " + pList.get(0).toString());
        serviceFacade.deletePerson(i);
        System.out.println("person deleted: " + pList.get(0).toString());
        assertEquals(pList.get(0).getActive(), "N");
        
        System.out.println("end ServiceFacadeTest testDeletePerson()");
    }
    
    @Test
    public void testCheckIfLate() {
        System.out.println("start ServiceFacadeTest testCheckIfLate()");
        
        System.out.println("end ServiceFacadeTest testCheckIfLate()");
    }
}


















