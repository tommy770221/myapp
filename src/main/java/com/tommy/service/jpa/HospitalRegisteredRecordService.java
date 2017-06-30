package com.tommy.service.jpa;


import com.tommy.model.springjpa.HospitalRegisteredRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tommy on 2017/6/29.
 */
@Transactional(value = "transactionManager")
@Repository
public interface HospitalRegisteredRecordService extends JpaRepository<HospitalRegisteredRecord,Integer> {
}
