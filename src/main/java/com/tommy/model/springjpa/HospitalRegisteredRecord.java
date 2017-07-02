package com.tommy.model.springjpa;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    private String lineId;

    @Column
    private String userName;

    @Column
    private String symptom;

    @Column
    private String didRegist;

    @Column
    private String doctor;

    @Column(name="createDate")
    private Date createDate;

    @Column(name="updateDate")
    private Date updateDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
