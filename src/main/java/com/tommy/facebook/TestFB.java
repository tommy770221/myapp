package com.tommy.facebook;

import com.restfb.*;
import com.restfb.json.JsonObject;
import com.restfb.types.Group;
import com.restfb.types.Message;
import com.restfb.types.Page;
import com.restfb.types.Post;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tommy on 2017/7/8.
 */
public class TestFB {

    public static void main(String[] args) {
        String pageAccessToken="EAAFPgrP0FZC4BADkFJEYZCIIwL9R1RAev8ZClkeWNP2lYSpH3IQbKIm8I9nGclFWTgnLuoXIbMZAsKHCzWPD18UajnFJ9JI1NtZBDGUR4afvaLXfFdjPKVOFgSlQjj6beanOYK5ZAIJ3QmE1bI4diKR9IDlTc1hMeIZAQdT6yaN2bNMoo01x4oN";
        FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.VERSION_2_8);
        Page page = facebookClient.fetchObject("cocacola", Page.class, Parameter.with("fields", "fan_count"));
        System.out.println("Page likes: " + page.getFanCount());
        Connection<Group> group = facebookClient.fetchConnection("me/groups", Group.class);
        if(!group.getData().isEmpty()){

            for(Group firstGroup:group.getData()){
                System.out.println(firstGroup.getName());
                Connection<JsonObject> memberPost = facebookClient.fetchConnection(firstGroup.getId()+"/members", JsonObject.class);
                for(JsonObject firstGroupPost:memberPost.getData()){
                    System.out.println("members : "+firstGroupPost.toString());
                }
                Connection<JsonObject> eventPost = facebookClient.fetchConnection(firstGroup.getId()+"/events", JsonObject.class);
                for(JsonObject firstGroupPost:eventPost.getData()){
                    System.out.println("events : "+firstGroupPost.toString());
                }
                System.out.println("------------------------------------------------------------------");
            }
        }

        Connection<Message> messageConnection = facebookClient.fetchConnection("711nutritionwarehouse/feed", Message.class);
        List<Message> messageList=messageConnection.getData();
        for(Message message:messageList){
            System.out.println(message.getId());
            Connection<JsonObject> eventComments = facebookClient.fetchConnection(message.getId()+"/comments", JsonObject.class);
            for(JsonObject firstGroupPost:eventComments.getData()){
                System.out.println("comments : "+firstGroupPost.get("message").asString());
            }

            Connection<JsonObject> eventLikes = facebookClient.fetchConnection(message.getId()+"/likes", JsonObject.class);
            for(JsonObject firstGroupPost:eventLikes.getData()){
                System.out.println("likes : "+firstGroupPost);
            }

        }


    }
}
