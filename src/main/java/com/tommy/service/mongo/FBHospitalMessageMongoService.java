package com.tommy.service.mongo;


import com.tommy.mongo.FBHospitalMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tommy on 2017/5/13.
 */
@Repository
public interface FBHospitalMessageMongoService extends MongoRepository<FBHospitalMessage,String> {
}
