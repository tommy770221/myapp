package com.tommy.service.jpa;


import com.tommy.model.springjpa.PersonRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tommy on 2017/6/29.
 */
@Transactional(value = "transactionManager")
@Repository
public interface PersonRecordService extends JpaRepository<PersonRecord,Integer> {

    @Query("SELECT o FROM PersonRecord o where o.lineId=:lineId ")
    PersonRecord searchByLineId(String lineId);
}
