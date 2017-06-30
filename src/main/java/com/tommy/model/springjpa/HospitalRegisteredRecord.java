package com.tommy.model.springjpa;

import javax.persistence.*;

/**
 * Created by tommy on 2017/6/10.
 */
@Entity
@Table
public class HospitalRegisteredRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;


    private String lineId;

    private String userName;

    private String symptom;

    private String didRegist;

    private String doctor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDidRegist() {
        return didRegist;
    }

    public void setDidRegist(String didRegist) {
        this.didRegist = didRegist;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }



}
