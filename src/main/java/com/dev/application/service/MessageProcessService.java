package com.dev.application.service;

import com.dev.application.data.entity.AltertMessage;
import com.dev.application.data.entity.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.annotation.Id;


public class MessageProcessService {
    private String level = "";
    private String component = "";
    private String version = "";
    @Id
    private String ip = "";
    private String type = "";
    private String messageText = "";
    private String stacktrace = "";



    Gson gson;

    public MessageProcessService(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        this.gson = new GsonBuilder().setPrettyPrinting().create();

    }



    public AltertMessage processAlert(String incomingAlert){

        AltertMessage processedAlertMessage = null;

//        String formattedString = incomingAlert
//                .replace("=",":")
//                .replace("\'","\"")
//                .replace("Alert","")
//                .replace("level","\"level\"")
//                .replace("component","\"component\"")
//                .replace("version","\"version\"")
//                .replace("ip","\"ip\"")
//                .replace("type","\"type\"")
//                .replace("messageText","\"messageText\"")
//                .replace("stacktrace","\"stacktrace\"");
////        System.out.println("case "+ formattedString);
        try{
             processedAlertMessage = gson.fromJson(incomingAlert,AltertMessage.class);
        }catch (Exception e){
            System.out.println(e);
        }

        return  processedAlertMessage;

    }

    public Notification processNotification(String incomingNotification){

        Notification processedNotification = null;

//        String formattedNotificationString = incomingNotification
//                .replace("=",":")
//                .replace("\'","\"")
//                .replace("Notification","")
//                .replace("level","\"level\"")
//                .replace("component","\"component\"")
//                .replace("version","\"version\"")
//                .replace("ip","\"ip\"")
//                .replace("type","\"type\"")
//                .replace("messageText","\"messageText\"");
////        System.out.println("test "+formattedNotificationString);
        try {
             processedNotification = gson.fromJson(incomingNotification,Notification.class);
        }catch (Exception e){
            System.out.println(e);
        }

        return processedNotification;

    }

}

