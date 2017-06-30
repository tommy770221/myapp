package com.tommy.model.springjpa;


import javax.persistence.*;

@Entity
@Table
public class Disease {

    private transient int orderDis = 0;

    public int getOrderDis() {
        return orderDis;
    }

    public void setOrderDis(int orderDis) {
        this.orderDis = orderDis;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "doc_category")
    private String docCategory;

    @Column(name = "category")
    private String category;

    @Column(name = "big_category")
    private String bigCategory;

    @Column(name = "symptom")
    private String symptom;

    public String getDocCategory() {
        return docCategory;
    }

    public void setDocCategory(String docCategory) {
        this.docCategory = docCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(String bigCategory) {
        this.bigCategory = bigCategory;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
