package com.tommy.service.jpa;

import com.tommy.model.springjpa.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by Tommy on 2017/6/29.
 */
@Transactional(value = "transactionManager")
@Repository
public interface DiseaseService extends JpaRepository<Disease,Integer> {

    @Query("SELECT o FROM Disease o where o.symptom like :memberDisease ")
    List<Disease> searchAllSymtom(String memberDisease);
}
