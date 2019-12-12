/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.rest.client.test.manual;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.factoryandfacade.impl.service.rest.client.ClientObjectFactoryImpl;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class RestClientServiceFacadeTest {

    final static Logger LOG = LogManager.getLogger(RestClientServiceFacadeTest.class);

    ServiceObjectFactory serviceObjectFactory = null;
    ServiceFacade serviceFacade = null;

    List<String> supportedAnimalTypes = null;

    @Before
    public void loadFactory() {
        serviceObjectFactory = new ClientObjectFactoryImpl();
        serviceFacade = serviceObjectFactory.getServiceFacade();
        assertNotNull(serviceFacade);
    }

    @Test
    public void testGetHeartbeat() {
        LOG.debug("start of testGetHeartbeat()");

        String heartbeat = serviceFacade.getHeartbeat();
        assertNotNull(heartbeat);
        LOG.debug("heartbeat received :" + heartbeat);

        LOG.debug("end of testGetHeartbeat()");
    }

    @Test
    public void testChangeStatus() {
        LOG.debug("start of testChangeStatus()");

        List<Person> pList = serviceFacade.findNurses();
        Person p = pList.get(0);
        long id = p.getId();
        LOG.debug("changing status of person " + p);
        serviceFacade.changeStatus("arrived", id, "2020-12-31 14:59");

        pList = serviceFacade.findNurses();
        p = pList.get(0);
        id = p.getId();
        LOG.debug("changed status of person " + p);

        assertEquals("arrived", p.getStatus());

        LOG.debug("end of testChangeStatus()");
    }

}
