//package com.leslie.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//@Service
//public class MessageProducer {
//
//    @Autowired
//    KafkaTemplate<String,String> kafkaTemplate;
//
//    public void sendMessage(String message , String topic){
//
//
//        kafkaTemplate.send(topic, message).addCallback(new ListenableFutureCallback() {
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("message send fail!");
//            }
//
//            @Override
//            public void onSuccess(Object result) {
//                System.out.println("result = " + result);
//                System.out.println("message send success!");
//            }
//        });
//
//    }
//
//}
