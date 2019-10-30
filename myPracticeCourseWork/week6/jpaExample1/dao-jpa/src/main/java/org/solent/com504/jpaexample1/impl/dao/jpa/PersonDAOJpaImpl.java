/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;
import org.solent.com504.jpaexample1.model.dto.Person;
import org.solent.com504.jpaexample1.model.dto.Role;

/**
 *
 * @author cgallen
 */
public class PersonDAOJpaImpl implements PersonDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public PersonDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Person findById(Long id) {
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public Person save(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);  // NOTE merge(animal) differnt semantics
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return person;
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> q = entityManager.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> personList = q.getResultList();
        return personList;
    }

    @Override
    public void deleteById(long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Person p WHERE p.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person delete(Person person) {
        //Person pToDel = person;
        //long idToDel = person.getId();
        //entityManager.getTransaction().begin();
        //Query q = entityManager.createQuery("DELETE FROM Person p WHERE p.id=:id");
        //q.setParameter("id", idToDel);
        //q.executeUpdate();
        entityManager.remove(person);
        //entityManager.getTransaction().commit();
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Person ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Person> findByRole(Role role) {        
        TypedQuery<Person> q = entityManager.createQuery("SELECT p FROM Person p WHERE p.role = :role", Person.class);
        q.setParameter("role", role);
        List<Person> personList = q.getResultList();
        return personList;
        
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findByName(String firstName, String secondName) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        TypedQuery<Person> q = entityManager.createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName AND p.secondName = :secondName", Person.class);
        q.setParameter("firstName", firstName);
        q.setParameter("secondName", secondName);
        List<Person> personList = q.getResultList();
        return personList;
        //To change body of generated methods, choose Tools | Templates.
    }

}
