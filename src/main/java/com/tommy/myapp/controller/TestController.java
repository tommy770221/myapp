package com.tommy.myapp.controller;



import com.tommy.mongo.HospitalGeoResponse;
import com.tommy.service.mongo.HospitalGeoMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Tommy on 2017/6/9.
 */
@RequestMapping("/mongo")
@Controller
public class TestController {

    @Autowired
    HospitalGeoMongoService hospitalGeoMongoService;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html")
    public String create(Model uiModel, HttpServletRequest httpServletRequest) {
        Point DUS = new Point( 121.48, 24.99 );
        Query query = new Query(Criteria.where("category").regex("內科"));
        NearQuery nearQuery = NearQuery.near(DUS).maxDistance(new Distance(10, Metrics.KILOMETERS));
        nearQuery.query(query);
        nearQuery.num(10);
        GeoResults<HospitalGeoResponse> data = mongoTemplate.geoNear(nearQuery, HospitalGeoResponse.class);
        for(GeoResult<HospitalGeoResponse> hospitalGeoResponse:data){
            System.out.println(hospitalGeoResponse.getContent().getAddress());
            System.out.println(hospitalGeoResponse.getContent().getName());
        }
        return "redirect:/hospitalinfoes/";
    }
}
