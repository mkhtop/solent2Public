/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import static org.solent.com504.project.impl.dao.jpa.test.PersonDAOTest.LOG;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;


/**
 *
 * @author cgallen
 */
public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

    private AppointmentDAO appointmentDao = null;
    private PersonDAO personDao = null;
    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();

    @Before
    public void before() {
        appointmentDao = daoFactory.getAppointmentDAO();
        assertNotNull(appointmentDao);
        personDao = daoFactory.getPersonDAO();
        assertNotNull(personDao);
    }
    
    private void init() {
        // delete all in database
        appointmentDao.deleteAll();
        // add 5 patients
        
        Person p = new Person();
        p.setAddress("address_1");
        p.setFirstName("firstName_1");
        p.setSecondName("secondName_1");
        p.setActive("active");
        p.setStatus("arrived");
        p.setRole(Role.PATIENT);
        p.setClockIn("not available");
        personDao.save(p);
        Person n = new Person();
        n.setAddress("address_2");
        n.setFirstName("firstName_2");
        n.setSecondName("secondName_2");
        n.setActive("active");
        n.setStatus("arrived");
        n.setRole(Role.NURSE);
        n.setClockIn("not available");
        personDao.save(n);
        Appointment a = new Appointment();
        a.setPersonA(n);
        a.setPersonB(p);
        a.setDescripton("appointmentTest");
        a.setHr(12);
        a.setAppDay(5);
        a.setMth(2);
        a.setYr(2019);
        a.setDurationMinutes(30);
        a.setActive("active");
        appointmentDao.save(a);
        
    }

    @Test
    public void createAppointmentDAOTest() {
        LOG.debug("start of createAppointmentDAOTest(");
        // this test simply runs the before method
        LOG.debug("end of createAppointmentDAOTest(");
    }
    
    @Test
    public void saveTest() {
        LOG.debug("start of appointment saveTest()");
        //TODO implement test
        assertNotNull(appointmentDao);
        init(); // initialise database

        List<Appointment> appointments = appointmentDao.findAll();
        assertFalse(appointments.isEmpty());

        // get person in middle of index
        int i = appointments.size()-1;
        Appointment a = appointments.get(i);
        LOG.debug("initial person index:" + i + " " + a);

        // change values
        a.setAppDay(15);
        a.setHr(14);
        LOG.debug("new person details " + a);

        // save animal
        appointmentDao.save(a);
        Long id = a.getId();

        // retrieve another copy of the animal
        Appointment newA = appointmentDao.findById(id);
        LOG.debug("retrieved  details " + newA);
        // quick and dirty equals
        assertTrue(a.toString().equals(a.toString()));
        
        LOG.debug("end of appointment saveTest()");
    }
    
    
    
    
    
    
}
