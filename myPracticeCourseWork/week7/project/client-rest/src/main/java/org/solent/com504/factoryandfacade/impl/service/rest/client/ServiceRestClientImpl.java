/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.rest.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.service.ServiceFacade;

/**
 *
 * @author gallenc
 */
public class ServiceRestClientImpl implements ServiceFacade {

    final static Logger LOG = LogManager.getLogger(ServiceRestClientImpl.class);

    String baseUrl = "http://localhost:8084/projectfacadeweb/rest/appointmentService";

    public ServiceRestClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getHeartbeat() {
        LOG.debug("getHeartbeat() Called");

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(baseUrl).path("getHeartbeat");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        if (replyMessage == null) {
            return null;
        }

        return replyMessage.getDebugMessage();

    }

    @Override
    public Person addPerson(String fName, String sName, String role, String address) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePerson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeStatus(String status, long id, String clockIn) {
        LOG.debug("slient changeStatus() Called  with status status " + status + " id " + id + " date " + clockIn);
        
        String personId = Long.toString(id);

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(baseUrl).path("changeStatus");
        
        Person p = new Person();
        
        MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
        formData.add("status", status);
        formData.add("id", personId);
        formData.add("date", clockIn);
        
        LOG.debug("map with: " + formData.toString() );

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.post(Entity.form(formData));
        

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage.getDebugMessage());

        //if(replyMessage==null) return null;
        return true;
    }

    @Override
    public List<Person> findNurses() {
        LOG.debug("findNurses() Called");

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(baseUrl).path("findNurses");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        if (replyMessage == null) {
            return null;
        }

        return replyMessage.getPersonList();
    }

    @Override
    public List<Person> findPatients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Appointment addAppointment(Person nurse, Person patient, Integer hr, Integer day, Integer mnth, Integer year, String desc, Integer duration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointment> findAllAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person findByName(String fName, String sName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
