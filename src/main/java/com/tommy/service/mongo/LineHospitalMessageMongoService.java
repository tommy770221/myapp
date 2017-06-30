package com.tommy.service.mongo;


import com.tommy.mongo.LineHospitalMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tommy on 2017/5/8.
 */
@Repository
public interface LineHospitalMessageMongoService extends MongoRepository<LineHospitalMessage,String> {
}
