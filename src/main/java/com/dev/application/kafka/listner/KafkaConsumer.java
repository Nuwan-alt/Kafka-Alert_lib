package com.dev.application.kafka.listner;

import com.dev.application.service.MessageProcessService;
import com.dev.application.service.FormService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class KafkaConsumer implements ConsumerSeekAware {

    MessageProcessService alertProcessService = new MessageProcessService();
    FormService formService = new FormService();


//    @KafkaListener(topics = "alert-topic", groupId = "gtn_group_-02-13")
//    private void alertConsume(String message){
//
//        formService.addAlertMessages(message);
////        System.out.println("message coming from kafka : " + message);
//
//    }

    @KafkaListener(topics = {"notifications","errors"}, groupId = "gtn_group_-02-14")
    private void notificationConsume(ConsumerRecord<?, Map<String, String>> message){

//        System.out.println(message.topic().toString() + " -:topic");



            if(message.topic().toString().equals("errors")){
                try {
                    formService.addKafkaError(String.valueOf(message.value()));
                }catch (Exception e){
                    System.out.println("exception in error"+message.value());
//                    System.out.println(e);
                }

            }

            else if (message.topic().toString().equals("notifications") ) {
                try {
                    formService.addKafkaNotification(String.valueOf(message.value()));
                }catch (Exception e){
                    System.out.println(message.value());
                }

            }else {
                System.out.println("exception in notification"+message.value());
//                System.out.println("in the else");
            }









    }


    @Override
    public void registerSeekCallback(ConsumerSeekCallback callback) {
        System.out.println("registerSeekCallback " + callback);
    }

    @Override
    public void onPartitionsAssigned( Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        System.out.println("onPartitionsAssigned ###########################################################" + assignments);
        assignments.forEach((t,o) -> callback.seekToBeginning(t.topic(), t.partition()));
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        ConsumerSeekAware.super.onPartitionsRevoked(partitions);
    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        ConsumerSeekAware.super.onIdleContainer(assignments, callback);
    }


    public void onFirstPoll() {
        System.out.println("onFirstPoll");
    }

    @Override
    public void unregisterSeekCallback() {
        ConsumerSeekAware.super.unregisterSeekCallback();
    }
}