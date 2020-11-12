package com.example.kafkastudy.application.controller;

import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/time-attack")
// @RestController
public class TimeAttackController {

  @Qualifier("ReplyingKafkaTemplate")
  private final ReplyingKafkaTemplate<String, String, String> template;


  @GetMapping("/")
  public String timeAttackCoupon() throws ExecutionException, InterruptedException {

    // create producer record
    ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", "foo");

    // set reply topic in header
    record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "kReplies".getBytes()));

    // post in kafka topic
    RequestReplyFuture<String, String, String> replyFuture = template.sendAndReceive(record);

    // confirm if producer produced successfully
    SendResult<String, String> sendResult = replyFuture.getSendFuture().get();

    //print all headers
    sendResult.getProducerRecord().headers()
        .forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

    // get consumer record
    ConsumerRecord<String, String> consumerRecord = replyFuture.get();

    // consumer value
    System.out.println(consumerRecord.value());

    return "OK";
  }
}
