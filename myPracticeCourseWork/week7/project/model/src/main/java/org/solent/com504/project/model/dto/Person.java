package org.solent.com504.project.model.dto;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Person {

    private Long id;

    private String firstName;

    private String secondName;

    private Role role;

    private String address;
    
    private String status;
    
    private String active;
    
    private Date clockIn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getActive(){
        return active;
    }
    
    public void setActive(String active){
        this.active = active;
    }
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getClockIn(){
        return clockIn;
    }
    
    public void setClockIn(Date clockIn){
        this.clockIn = clockIn;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", role=" + role + ", address=" + address + '}';
    }
    
    
    
    
}
