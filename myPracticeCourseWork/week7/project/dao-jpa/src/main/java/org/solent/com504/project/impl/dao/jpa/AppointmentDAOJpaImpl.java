/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOJpaImpl implements AppointmentDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public AppointmentDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        return appointment;
    }

    @Override
    public Appointment save(Appointment appointment) {
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a", Appointment.class);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;
    }

    @Override
    public Appointment delete(Appointment appointment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setActive("N");
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Appointment ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Appointment> findByPersonA(Person personA) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.personA = :person", Appointment.class);
        q.setParameter("person", personA);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;
    }

    @Override
    public List<Appointment> findByPersonB(Person personB) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.personB = :person", Appointment.class);
        q.setParameter("person", personB);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;
    }

    @Override
    public List<Appointment> findByDate(Integer day, Integer year, Integer month) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.appDay = :day AND a.mth =:month AND a.yr =:year", Appointment.class);
        q.setParameter("day", day);
        q.setParameter("month", month);
        q.setParameter("year", year);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;}

}
