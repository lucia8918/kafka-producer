package com.example.kafkastudy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class KafkaStudyApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(KafkaStudyApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // doSomething,,,,

  }
//  @Autowired
//  private KafkaTemplate<String, String> template;
//
//  private final CountDownLatch latch = new CountDownLatch(3);
//
//  @Override
//  public void run(String... args) throws Exception {
//    /*
//    this.template.send("test-2", "foo1");
//    this.template.send("test-2", "foo2");
//    this.template.send("test-2", "foo3");
//    */
//    latch.await(300, TimeUnit.SECONDS);
//    log.info("All received");
//  }
//
//  @KafkaListener(topics = "test-2")
//  public void listen(ConsumerRecord<?, ?> cr) throws Exception {
//    log.info(cr.toString());
//    latch.countDown();
//  }
}
