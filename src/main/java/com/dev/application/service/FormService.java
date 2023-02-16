package com.dev.application.service;

import com.dev.application.data.entity.AltertMessage;
//import com.dev.application.kafka.producer.KafkaProducer;

import com.dev.application.data.entity.Notification;
import com.google.gson.Gson;
import com.vaadin.flow.component.grid.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormService {
    public static List<AltertMessage> alertMessages = new ArrayList<>();
    public static List<Notification> notifications = new ArrayList<>();

    MessageProcessService messageProcessService = new MessageProcessService();
    private Gson gson = new Gson();


    public List<AltertMessage> getAltertMessages() {
        return alertMessages;
    }
    public List<Notification> getNotifications(){return  notifications;}




    public void addKafkaError(String incomingKafkaMsg){

            AltertMessage altertMessage = messageProcessService.processAlert(incomingKafkaMsg);
            if(altertMessage != null){
                if (alertMessages.size() > 10000){
                    alertMessages.remove(alertMessages.size()-1);
                }

                alertMessages.add(altertMessage);
            }else {
                System.out.println("error at addKafkaError method");
            }

//         AltertMessage newAlertMessage = new AltertMessage(altertMessage.getLevel(),altertMessage.getComponent(),altertMessage.getVersion(),altertMessage.getIp(),altertMessage.getType(),altertMessage.getMessageText(),altertMessage.getStacktrace());

    }

    public void addKafkaNotification(String incomingNotification){


        Notification notification = messageProcessService.processNotification(incomingNotification);
        if(notification != null){
//            if(notifications.size() > 10000000){
//                notifications.remove(notifications.size()-1);
//            }
//            for (int i = 0;i<10000;i++){
//                notifications.add(notification);
//            }

            if(notifications.size() > 100){
                notifications.remove(notifications.size()-1);
            }
            notifications.add(notification);

        }else {
            System.out.println("error at addKafkaNotification method");
        }

    }
    
    

    public  List<AltertMessage> findAllAlertMessages(){
        return this.getAltertMessages();
    }


    public void refreshGrid(Grid grid){

    }
}
