/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa.test;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.jpaexample1.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;
import org.solent.com504.jpaexample1.model.dto.Person;
import org.solent.com504.jpaexample1.model.dto.Role;

/**
 *
 * @author cgallen
 */
public class PersonDAOTest {

    final static Logger LOG = LogManager.getLogger(PersonDAOTest.class);

    private PersonDAO personDao = null;

    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();

    @Before
    public void before() {
        personDao = daoFactory.getPersonDAO();
        assertNotNull(personDao);
    }

    // initialises database for each test
    private void init() {
        // delete all in database
        personDao.deleteAll();
        // add 5 patients
        for (int i = 1; i < 6; i++) {
            Person p = new Person();
            p.setAddress("address_" + i);
            p.setFirstName("firstName_" + i);
            p.setSecondName("secondName_" + i);
            p.setRole(Role.PATIENT);
            personDao.save(p);
        }
    }

    @Test
    public void createPersonDAOTest() {
        LOG.debug("start of createPersonDAOTest");
        // this test simply runs the before method
        LOG.debug("end of createPersonDAOTest");
    }

    @Test
    public void findByIdTest() {
        LOG.debug("start of findByIdTest()");
        //TODO implement test
        init();
        
        for (Person p : personDao.findAll()){
            assertTrue(personDao.findById(p.getId()) == p);
        }
        
        //LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of findByIdTest()");
    }

    @Test
    public void saveTest() {
        LOG.debug("start of saveTest()");
        //TODO implement test
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of saveTest()");
    }

    @Test
    public void findAllTest() {
        LOG.debug("start of findAllTest()");

        init();
        List<Person> personList = personDao.findAll();
        assertNotNull(personList);
        
        // init should insert 5 people
        assertEquals(5, personList.size());

        // print out result
        String msg = "";
        for (Person person : personList) {
            msg = msg +"\n   " +  person.toString();
        }
        LOG.debug("findAllTest() retrieved:" + msg);

        //LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of findAllTest()");
    }

    @Test
    public void deleteByIdTest() {
        LOG.debug("start of deleteByIdTest()");
        //TODO implement test
        init();
        List<Person> personList = personDao.findAll();
        Person p = personList.get(1);
        long idToDel = p.getId();
        personDao.deleteById(idToDel);
        assertNull(personDao.findById(idToDel));
        
        //LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of deleteByIdTest()");
    }

    @Test
    public void deleteTest() {
        LOG.debug("start of deleteTest()");
        //TODO implement test
        init();
        List<Person> pList = personDao.findAll();
        Person pToDel = pList.get(1);
        long id = pToDel.getId();
        personDao.delete(pToDel);
        assertTrue(personDao.findById(id) == null);
        
        //LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of deleteTest()");
    }

    @Test
    public void deleteAllTest() {
        LOG.debug("start of deleteAllTest())");
        //TODO implement test
        init();
        
        personDao.deleteAll();
        for (Person p : personDao.findAll()){
            assertTrue(p.getFirstName() == null);
        }
        
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of deleteAllTest()");
    }

    @Test
    public void findByRoleTest() {
        LOG.debug("start of findByIdTest()");
        //TODO implement test
        init();
        List<Person> personList = personDao.findByRole(Role.PATIENT);
        for (Person p : personList){
            assertTrue(p.getRole() == Role.PATIENT);
        }
        
        //LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of findByIdTest()");
    }

    @Test
    public void findByNameTest() {
        LOG.debug("start of findByNameTest()");
        //TODO implement test
        init();
        
        List<Person> personList = personDao.findByName("firstName_1", "secondName_1");
        for (Person p : personList){
            assertEquals("firstName_1", p.getFirstName());
            assertEquals("secondName_1", p.getSecondName());
            //assertTrue(p.getSecondName() == "secondName_1");
        }
            
        //LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of findByNameTest())");

    }
}
