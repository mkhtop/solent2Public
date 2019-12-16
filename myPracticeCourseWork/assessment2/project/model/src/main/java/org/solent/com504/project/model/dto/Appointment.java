package org.solent.com504.project.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /*private Integer hr;

    private Integer day;

    private Integer mth;

    private Integer yr;*/

    private Integer durationMinutes;
    
    private String active;
    
    private Date appDate;

   /* public Integer getHr() {
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
    }*/

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

    //@Temporal(javax.persistence.TemporalType.DATE)
    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        Date dateToSave;
        try {
            dateToSave = format.parse(appDate);
            this.appDate = dateToSave;
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String toString() {
        return "Appointment{" + "descripton=" + descripton + ", personA=" + personA + ", personB=" + personB + ", id=" + id + ", durationMinutes=" + durationMinutes + ", active=" + active + ", appDate=" + appDate + '}';
    }
    

    

    

}