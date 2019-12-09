package org.solent.com504.project.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String descripton;

    private Person personA;

    private Person personB;

    private Long id;
    
    private Integer hr;

    private Integer day;

    private Integer mth;

    private Integer yr;

    private Integer durationMinutes;
    
    private String active;

    public Integer getHr() {
        return hr;
    }

    public void setHr(Integer hr) {
        this.hr = hr;
    }
    
    public Integer getAppDay() {
        return day;
    }

    public void setAppDay(Integer day) {
        this.day = day;
    }

    public Integer getMth() {
        return mth;
    }

    public void setMth(Integer mth) {
        this.mth = mth;
    }

    public Integer getYr() {
        return yr;
    }

    public void setYr(Integer yr) {
        this.yr = yr;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Person getPersonA() {
        return personA;
    }

    public void setPersonA(Person personA) {
        this.personA = personA;
    }

    public Person getPersonB() {
        return personB;
    }

    public void setPersonB(Person personB) {
        this.personB = personB;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    
    public String getActive(){
        return active;
    }
    
    public void setActive(String active){
        this.active = active;
    }
    

    @Override
    public String toString() {
        return "Appointment{" + "descripton=" + descripton + ", personA=" + personA + ", personB=" + personB + ", id=" + id + ", hr=" + hr + ", day=" + day + ", mth=" + mth + ", yr=" + yr + ", durationMinutes=" + durationMinutes + '}';
    }

}
