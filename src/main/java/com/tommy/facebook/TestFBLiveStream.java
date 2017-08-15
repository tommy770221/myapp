package com.tommy.facebook;

import com.restfb.*;
import com.restfb.json.JsonObject;
import com.restfb.types.Comment;
import com.restfb.types.Likes;
import com.restfb.types.Message;
import com.restfb.types.Post;

import java.util.List;

/**
 * Created by Tommy on 2017/8/14.
 */
public class TestFBLiveStream {
    public static void main(String[] args) {
        String pageAccessToken="EAAFPgrP0FZC4BADkFJEYZCIIwL9R1RAev8ZClkeWNP2lYSpH3IQbKIm8I9nGclFWTgnLuoXIbMZAsKHCzWPD18UajnFJ9JI1NtZBDGUR4afvaLXfFdjPKVOFgSlQjj6beanOYK5ZAIJ3QmE1bI4diKR9IDlTc1hMeIZAQdT6yaN2bNMoo01x4oN";
        FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.VERSION_2_8);
        Connection<Message> messageConnection = facebookClient.fetchConnection("100558927196033/feed", Message.class);
        List<Message> messageList=messageConnection.getData();
        String nextURL=messageConnection.getNextPageUrl();
        System.out.println("getNextPageUrl : "+messageConnection.getNextPageUrl());
        System.out.println("getPreviousPageUrl : "+messageConnection.getPreviousPageUrl());
        System.out.println("getAfterCursor : "+messageConnection.getAfterCursor());
        System.out.println("getBeforeCursor : "+messageConnection.getBeforeCursor());
        for(Message message:messageList){
            System.out.println(message.getId());
            JsonObject liveStream = facebookClient.fetchObject(message.getId(), JsonObject.class,  Parameter.with("fields", "actions,link"));
            System.out.println(liveStream.toString());
            try {
                String liveUrl=liveStream.get("link").asString();
                System.out.println("link : "+liveUrl);
                String videoUrl=liveUrl.substring(liveUrl.indexOf("/videos/")+8);
                System.out.println("videoUrl : "+videoUrl);
                Connection<Comment> videoPost = facebookClient.fetchConnection(videoUrl+"comments", Comment.class);
                System.out.println("videoPost.getNextPageUrl() : "+videoPost.getNextPageUrl());
                System.out.println("videoPost.getPreviousPageUrl() : "+videoPost.getPreviousPageUrl());
                for(Comment comment:videoPost.getData()){
                    System.out.println(comment.getMessage());
                }

                while (videoPost.hasNext()) {
                    videoPost = facebookClient.fetchConnectionPage(videoPost.getNextPageUrl(),Comment.class);
                    for(Comment comment:videoPost.getData()){
                        System.out.println(comment.getMessage());
                    }
                }

                Connection<Likes> videoLike = facebookClient.fetchConnection(videoUrl+"likes", Likes.class);
                System.out.println("videoLike.getNextPageUrl() : "+videoLike.getNextPageUrl());
                System.out.println("videoLike.getPreviousPageUrl() : "+videoLike.getPreviousPageUrl());
                for(Likes like:videoLike.getData()){
                    System.out.println(like.getTotalCount());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }
}
