/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.project.impl.service.ServiceObjectFactoryJpaImpl;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;


/**
 *
 * @author gallenc
 */
public class ServiceFacadeJpaTest {
     final static Logger LOG = LogManager.getLogger(ServiceFacadeJpaTest.class);
    ServiceObjectFactory serviceObjectFactory = null;
    ServiceFacade serviceFacade = null;


    @Before
    public void loadFactory() {

        serviceObjectFactory = new ServiceObjectFactoryJpaImpl();
        
        serviceFacade = serviceObjectFactory.getServiceFacade();

    }
    
    private void init() {
        // delete all in database
        
        serviceFacade.deleteAll();
        // add 5 patients
        for (int i = 1; i < 4; i++) {
            String address = ("address_" + i);
            String fName = ("firstName_" + i);
            String sName = ("secondName_" + i);
            String role = "Nurse";
            serviceFacade.addPerson(fName, sName, role, address);   
        }
        for (int i = 4; i < 6; i++) {
            String address = ("address_" + i);
            String fName = ("firstName_" + i);
            String sName = ("secondName_" + i);
            String role = "Patient";
            serviceFacade.addPerson(fName, sName, role, address);   
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
        
        System.out.println("end FarmFacadeTest testGetHeartbeat()");
    }

   /* @Test
    public void testChangeStatus(){
        System.out.println("start ServiceFacadeTest testChangeStatus()");
        List<Person> pList = serviceFacade.findNurses();
        assertEquals(1, pList.size());
        Person p = pList.get(0);
        long id = p.getId();
        serviceFacade.changeStatus("arrived", id, "2020-12-31 14:59");
        
        assertEquals("arrived", p.getStatus());

        System.out.println("end of testChangeStatus()");
    }*/
    // WHAT OTEHR TESTS DO YOU NEED FOR HE SERVICE?
    
    @Test
    public void testGetAllPersons(){
        init();
        System.out.println("start ServiceFacadeTest testGetAllPerson()");
        List<Person> pList = serviceFacade.getAllPersons();
        assertNotNull(pList);
        LOG.debug("plist size " + pList.size());
        assertEquals(5, pList.size());
    }
    
}
