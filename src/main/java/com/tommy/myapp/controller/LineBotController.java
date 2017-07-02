package com.tommy.myapp.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.io.ByteStreams;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.CallbackRequest;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import com.tommy.SpringConfig;
import com.tommy.model.springjpa.Disease;
import com.tommy.model.springjpa.HospitalRegisteredRecord;
import com.tommy.model.springjpa.PersonRecord;
import com.tommy.mongo.HospitalGeoResponse;
import com.tommy.mongo.LineHospitalMessage;
import com.tommy.mongo.LineMessage;
import com.tommy.mongo.Statements;
import com.tommy.service.jpa.DiseaseService;
import com.tommy.service.jpa.HospitalRegisteredRecordService;
import com.tommy.service.jpa.PersonRecordService;
import com.tommy.service.mongo.HospitalGeoMongoService;
import com.tommy.service.mongo.LineHospitalMessageMongoService;
import com.tommy.service.mongo.LineMessageMongoService;
import com.tommy.service.mongo.StatementsMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Tommy on 2017/4/14.
 */
@RequestMapping("/line")
@Controller
public class LineBotController {


    private final ObjectMapper objectMapper=buildObjectMapper();

    @Autowired
    private StatementsMongoService statementsMongoService;
    @Autowired
    private LineMessageMongoService lineMessageService;
    @Autowired
    private LineHospitalMessageMongoService lineHospitalMessageMongoService;

    @Autowired
    HospitalGeoMongoService hospitalGeoMongoService;

    @Autowired
    PersonRecordService personRecordService;

    @Autowired
    DiseaseService diseaseService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    HospitalRegisteredRecordService hospitalRegisteredRecordService;
    @Autowired
    SpringConfig springConfig;

    @RequestMapping(value = "block",method ={RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String blockTemplate(HttpServletRequest httpServletRequest) throws IOException {
        String channelToken=springConfig.lineChannelToken;
       //LineBotCallbackRequestParser lineBotCallbackRequestParser=new LineBotCallbackRequestParser(new LineSignatureValidator(channelToken.getBytes()));// StringWriter writer = new StringWriter();
       // IOUtils.copy(httpServletRequest.getInputStream(), writer, "utf-8");
       String theString ="";
        boolean isMiss;
       // System.out.println(theString);
        try {
           // CallbackRequest callbackRequest = lineBotCallbackRequestParser.handle(httpServletRequest);
            byte[] json = ByteStreams.toByteArray(httpServletRequest.getInputStream());
            CallbackRequest callbackRequest = (CallbackRequest) objectMapper.readValue(json, CallbackRequest.class);
            List<Event> events= callbackRequest.getEvents();
            for(Event event:events){
                if(event instanceof MessageEvent) {
                    MessageEvent env=(MessageEvent) event;

                    PersonRecord personRecord=null;
                    try {
                        personRecord=personRecordService.searchByLineId(env.getSource().getUserId());

                    }catch (Exception e){
                        Response<UserProfileResponse> response=LineMessagingServiceBuilder
                                .create(channelToken)
                                .build().getProfile(env.getSource().getUserId()).execute();
                        UserProfileResponse userProfileResponse=response.body();
                        System.out.println("user profile : "+userProfileResponse.getUserId());
                        personRecord=new PersonRecord();
                        personRecord.setLineId(userProfileResponse.getUserId());
                        personRecord.setUserName(userProfileResponse.getDisplayName());
                        personRecordService.save(personRecord);


                        e.printStackTrace();
                        return"";
                    }

                    if(env.getMessage() instanceof TextMessageContent) {
                    MessageEvent<TextMessageContent> askContent=(MessageEvent<TextMessageContent>) event;
                    TextMessage ask=new TextMessage(askContent.getMessage().getText());

                    String askForJeiba=".*";
                    String responseAns= "對不起,我聽不懂你再說什麼";

                        JiebaSegmenter segmenter = new JiebaSegmenter();
                        System.out.println(segmenter.process(ask.getText(), JiebaSegmenter.SegMode.SEARCH).toString());
                        List<SegToken> segTokenList=segmenter.process(ask.getText(), JiebaSegmenter.SegMode.SEARCH);
                        for(SegToken segToken:segTokenList){
                            System.out.println(segToken.word.toString());
                            askForJeiba=askForJeiba+segToken.word.toString()+".*";
                        }
                        List<Statements> statementsList=statementsMongoService.findStatementsByRegexpResponse(askForJeiba);

                        if(! (statementsList==null)){
                            int ran= (int)(Math.random()*300+1);
                            if(statementsList.size()!=0){
                                int i=ran % statementsList.size();
                                responseAns=statementsList.get(i).getText();
                                TextMessage textMessage = new TextMessage(responseAns);
                                ReplyMessage replyMessage = new ReplyMessage(
                                        env.getReplyToken() ,
                                        textMessage
                                );
                                Response<BotApiResponse> response =
                                        LineMessagingServiceBuilder
                                                .create(channelToken)
                                                .build()
                                                .replyMessage(replyMessage)
                                                .execute();
                            }else{
                                TextMessage textMessage = new TextMessage(askContent.getMessage().getText());
                                ReplyMessage replyMessage = new ReplyMessage(
                                        env.getReplyToken() ,
                                        textMessage
                                );
                                Response<BotApiResponse> response =
                                        LineMessagingServiceBuilder
                                                .create(channelToken)
                                                .build()
                                                .replyMessage(replyMessage)
                                                .execute();
                            }
                        }


                    LineMessage lineMessage=new LineMessage();
                    lineMessage.setAskMessage(ask.getText());
                    lineMessage.setResponseMessage(responseAns);
                    lineMessage.setUserLineId(event.getSource().getUserId());
                    lineMessage.setCreateDate(new Date());
                    lineMessageService.save(lineMessage);
                    }else if(env.getMessage() instanceof LocationMessageContent){
                        LocationMessageContent locationMessageContent=(LocationMessageContent) env.getMessage();
                        personRecord.setLatitude(locationMessageContent.getLatitude());
                        personRecord.setLongtitude(locationMessageContent.getLongitude());
                        personRecord.setCurrentStatus("location");
                        personRecordService.save(personRecord);

                    }
                    else{
                        System.out.println(env.getMessage().getClass().getClass().getName());
                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"test\":\""+theString+"\"}";
    }

    @RequestMapping(value = "hospital",method ={RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String hospital(HttpServletRequest httpServletRequest) throws IOException {
        String channelToken=springConfig.lineChannelToken;
        //LineBotCallbackRequestParser lineBotCallbackRequestParser=new LineBotCallbackRequestParser(new LineSignatureValidator(channelToken.getBytes()));// StringWriter writer = new StringWriter();
        // IOUtils.copy(httpServletRequest.getInputStream(), writer, "utf-8");
        String theString ="";
        boolean isMiss;
        // System.out.println(theString);
        try {
            // CallbackRequest callbackRequest = lineBotCallbackRequestParser.handle(httpServletRequest);
            byte[] json = ByteStreams.toByteArray(httpServletRequest.getInputStream());
            System.out.println(new String(json));
            CallbackRequest callbackRequest = (CallbackRequest) objectMapper.readValue(json, CallbackRequest.class);

            List<Event> events= callbackRequest.getEvents();
            for(Event event:events){

                if(event instanceof MessageEvent) {
                    MessageEvent env=(MessageEvent) event;
                    PersonRecord personRecord=null;
                    try {
                        personRecord=personRecordService.searchByLineId(env.getSource().getUserId());

                    }catch (EmptyResultDataAccessException e){
                        Response<UserProfileResponse> response=LineMessagingServiceBuilder
                                .create(channelToken)
                                .build().getProfile(env.getSource().getUserId()).execute();
                        UserProfileResponse userProfileResponse=response.body();
                        System.out.println("user profile : "+userProfileResponse.getDisplayName());
                        personRecord=new PersonRecord();
                        personRecord.setLineId(userProfileResponse.getUserId());
                        personRecord.setUserName(hex2Str(userProfileResponse.getDisplayName()));
                        personRecord.setCreateDate(new Date());
                        personRecordService.save(personRecord);
                        e.printStackTrace();
                        return"";
                    }catch (Exception e){
                        e.printStackTrace();
                        return"";
                    }


                    if(env.getMessage() instanceof TextMessageContent) {
                        MessageEvent<TextMessageContent> askContent=(MessageEvent<TextMessageContent>) event;
                        TextMessage ask=new TextMessage(askContent.getMessage().getText());

                        if("".equals(ask.getText().trim())){
                            TextMessage textMessage = new TextMessage("請輸入不舒服的症狀喔！");
                            ReplyMessage replyMessage = new ReplyMessage(
                                    ((PostbackEvent) event).getReplyToken(),
                                    textMessage
                            );
                        }
                        if(ask.getText().contains("不舒服")){
                            TextMessage textMessage = new TextMessage("保持愉快的心情會好的快一點喔  இдஇ");
                            ReplyMessage replyMessage = new ReplyMessage(
                                    ((PostbackEvent) event).getReplyToken(),
                                    textMessage
                            );
                            Response<BotApiResponse> response =
                                    LineMessagingServiceBuilder
                                            .create(channelToken)
                                            .build()
                                            .replyMessage(replyMessage)
                                            .execute();
                        }

                        JiebaSegmenter segmenter = new JiebaSegmenter();
                        System.out.println(segmenter.process(ask.getText(), JiebaSegmenter.SegMode.SEARCH).toString());
                        List<SegToken> segTokenList = segmenter.process(ask.getText(), JiebaSegmenter.SegMode.SEARCH);
                        List<Disease> diseaseList = null;
                        Set<Disease> diseaseSet = new HashSet<Disease>();
                        for (SegToken segToken : segTokenList) {
                            System.out.println(segToken.word.toString());
                            diseaseList = diseaseService.searchAllSymtom(segToken.word.trim());
                            diseaseSet.addAll(diseaseList);
                        }
                        if (diseaseList != null) {
                            for (Disease disease : diseaseSet) {
                                for (SegToken segToken : segTokenList) {
                                    if (disease.getSymptom().contains(segToken.word)) {
                                        disease.setOrderDis(disease.getOrderDis() + 1);
                                    }
                                }
                            }
                        }

                        Collections.sort(diseaseList, new Comparator<Disease>() {
                            @Override
                            public int compare(Disease lhs, Disease rhs) {
                                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                                return lhs.getOrderDis() > rhs.getOrderDis() ? -1 : (lhs.getOrderDis() < rhs.getOrderDis()) ? 1 : 0;
                            }
                        });
                        String responseCat = "";
                        for (Disease disease : diseaseList) {
                            responseCat += "" + disease.getCategory() + ",";
                            System.out.println("症狀 : "+disease.getCategory());
                        }

                        TextMessage textMessage = new TextMessage(responseCat);
                        ReplyMessage replyMessage = new ReplyMessage(
                                        env.getReplyToken() ,
                                        textMessage
                                       );
                        Response<BotApiResponse> response =
                                        LineMessagingServiceBuilder
                                                .create(channelToken)
                                                .build()
                                                .replyMessage(replyMessage)
                                                .execute();
                        personRecord.setSymptom(ask.getText());
                        personRecord.setCurrentStatus("disease");
                        personRecord.setUpdateDate(new Date());
                        personRecordService.save(personRecord);
                        LineHospitalMessage lineHospitalMessage=new LineHospitalMessage();
                        lineHospitalMessage.setAskMessage(ask.getText());
                        lineHospitalMessage.setResponseMessage(responseCat);
                        lineHospitalMessage.setUserLineId(event.getSource().getUserId());
                        lineHospitalMessage.setCreateDate(new Date());
                        lineHospitalMessageMongoService.save(lineHospitalMessage);

                    }else if(env.getMessage() instanceof LocationMessageContent){
                        LocationMessageContent locationMessageContent=(LocationMessageContent) env.getMessage();
                        personRecord.setLatitude(locationMessageContent.getLatitude());
                        personRecord.setLongtitude(locationMessageContent.getLongitude());
                        personRecord.setCurrentStatus("location");
                        personRecord.setUpdateDate(new Date());
                        personRecordService.save(personRecord);

                        Point DUS = new Point( personRecord.getLongtitude(), personRecord.getLatitude() );
                        JiebaSegmenter segmenter = new JiebaSegmenter();
                        System.out.println(segmenter.process(personRecord.getSymptom(), JiebaSegmenter.SegMode.SEARCH).toString());
                        List<SegToken> segTokenList = segmenter.process(personRecord.getSymptom(), JiebaSegmenter.SegMode.SEARCH);
                        List<Disease> diseaseList = null;
                        Set<Disease> diseaseSet = new HashSet<Disease>();
                        for (SegToken segToken : segTokenList) {
                            System.out.println(segToken.word.toString());
                            diseaseList = diseaseService.searchAllSymtom(segToken.word.trim());
                            diseaseSet.addAll(diseaseList);
                        }
                        if (diseaseList != null) {
                            for (Disease disease : diseaseSet) {
                                for (SegToken segToken : segTokenList) {
                                    if (disease.getSymptom().contains(segToken.word)) {
                                        disease.setOrderDis(disease.getOrderDis() + 1);
                                    }
                                }
                            }
                        }

                        Collections.sort(diseaseList, new Comparator<Disease>() {
                            @Override
                            public int compare(Disease lhs, Disease rhs) {
                                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                                return lhs.getOrderDis() > rhs.getOrderDis() ? -1 : (lhs.getOrderDis() < rhs.getOrderDis()) ? 1 : 0;
                            }
                        });
                        Criteria criteria = new Criteria();
                        List<Criteria> criteriaList=new ArrayList<Criteria>();
                        Query query = new Query(Criteria.where("name").regex("(醫院)|(診所)"));
                        String str="";
                        int i=0;
                        for(Disease disease:diseaseSet){
                            i++;
                            if(i<diseaseSet.size()){
                                str=str+"("+disease.getCategory()+")|";
                            }else{
                                str=str+"("+disease.getCategory()+")";
                            }

                            System.out.println(disease.getCategory());

                        }
                        System.out.println(query.toString());
                        NearQuery nearQuery = NearQuery.near(DUS).maxDistance(new Distance(5, Metrics.KILOMETERS));
                        nearQuery.query(query);
                        nearQuery.num(5);
                        GeoResults<HospitalGeoResponse> data = mongoTemplate.geoNear(nearQuery, HospitalGeoResponse.class);
                        CarouselColumn column=null;
                        List<CarouselColumn> columns=new ArrayList<CarouselColumn>();
                        for(GeoResult<HospitalGeoResponse> hospitalGeoResponse:data){

                            HospitalGeoResponse hospitalGeoResponse1=hospitalGeoResponse.getContent();
                            String title=(hospitalGeoResponse1.getName() ==null ? "": hospitalGeoResponse1.getName());
                            String text= ((hospitalGeoResponse1.getServiceItem()==null || hospitalGeoResponse1.getServiceItem().equals("")) ? "一般門診": hospitalGeoResponse1.getServiceItem());
                            List<Action> actions=new ArrayList<Action>();
                            String labelMap="地圖";
                            String uriMap="https://www.google.com/maps/place/"+ URLEncoder.encode(hospitalGeoResponse1.getAddress());
                            String labelPhone="電話";
                            String uriPhone="tel:"+hospitalGeoResponse1.getPhone().replaceAll("-","");
                            URIAction action=new URIAction(labelMap,uriMap);
                            URIAction actionPh=new URIAction(labelPhone,uriPhone);
                          //  PostbackAction postbackAction=new PostbackAction("掛號","registered");
                            actions.add(action);
                            actions.add(actionPh);
                          //  actions.add(postbackAction);
                            column=new CarouselColumn(null,title,text,actions);
                            System.out.println(hospitalGeoResponse.getContent().getAddress());
                            System.out.println(hospitalGeoResponse.getContent().getName());
                            System.out.println(hospitalGeoResponse.getContent().getCategory());
                            columns.add(column);
                        }



                        CarouselTemplate template=new CarouselTemplate(columns);
                        for(CarouselColumn carouselColumn:template.getColumns()){
                            System.out.println(carouselColumn.getText());
                            System.out.println(carouselColumn.getActions());
                        }
                        TemplateMessage templateMessage=new TemplateMessage("附近醫院",template);
                        System.out.print(templateMessage.getAltText());
                        System.out.print(templateMessage);
                        ReplyMessage replyMessage = new ReplyMessage(
                                env.getReplyToken() ,
                                templateMessage
                        );
                        Response<BotApiResponse> response =
                                LineMessagingServiceBuilder
                                        .create(channelToken)
                                        .build()
                                        .replyMessage(replyMessage)
                                        .execute();
                        if(response!=null){
                            if(response.errorBody() !=null){
                                System.out.println(response.isSuccessful());
                                System.out.println(response.errorBody().string());
                            }else{
                                System.out.println(response);
                                System.out.println(response.isSuccessful());
                            }
                        }

                    }else{
                        System.out.println(env.getMessage().getClass().getClass().getName());
                    }

                }else if(event instanceof PostbackEvent){
                    PostbackEvent postbackEvent=(PostbackEvent) event;
                    PersonRecord personRecord=null;
                    personRecord=personRecordService.searchByLineId(event.getSource().getUserId());
                    String postback=postbackEvent.getPostbackContent().getData();
                    personRecord.setCurrentStatus("registered");
                    personRecord.setUpdateDate(new Date());
                    personRecordService.save(personRecord);
                    System.out.println("postback data : "+postback);
                    HospitalRegisteredRecord hospitalRegisteredRecord=new HospitalRegisteredRecord();
                    hospitalRegisteredRecord.setDidRegist("false");
                    hospitalRegisteredRecord.setSymptom(personRecord.getSymptom());
                    hospitalRegisteredRecord.setUpdateDate(new Date());
                    hospitalRegisteredRecordService.save(hospitalRegisteredRecord);

                    TextMessage textMessage = new TextMessage("恭喜掛號成功  ε٩(๑> ₃ <)۶з");
                    ReplyMessage replyMessage = new ReplyMessage(
                            ((PostbackEvent) event).getReplyToken(),
                            textMessage
                    );
                    Response<BotApiResponse> response =
                            LineMessagingServiceBuilder
                                    .create(channelToken)
                                    .build()
                                    .replyMessage(replyMessage)
                                    .execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"test\":\""+theString+"\"}";
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule()).configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        return objectMapper;
    }

    public static String hex2Str(String str) throws UnsupportedEncodingException {
        String strArr[] = str.split("\\\\"); // 分割拿到形如 xE9 的16进制数据
        byte[] byteArr = new byte[strArr.length - 1];
        for (int i = 1; i < strArr.length; i++) {
            Integer hexInt = Integer.decode("0" + strArr[i]);
            byteArr[i - 1] = hexInt.byteValue();
        }

        return new String(byteArr, "UTF-8");
    }

}
