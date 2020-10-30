package com.example.kafkastudy.application.service;

import com.example.kafkastudy.application.request.CouponCreateRequest;
import com.example.kafkastudy.application.response.CouponCreatedResponse;
import com.example.kafkastudy.constant.KafkaConstant;
import com.example.kafkastudy.domain.coupon.CouponDomainService;
import com.example.kafkastudy.domain.coupon.entity.Coupon;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

  private final CouponDomainService couponDomainService;

  private final KafkaTemplate<Object, CouponCreateRequest> template;

  public CouponCreatedResponse createCoupon(CouponCreateRequest couponCreateRequest) {
    return CouponCreatedResponse.of(couponDomainService.createOne(couponCreateRequest.toEntity()));
  }

  public Coupon getCoupon(Long id) {
    return couponDomainService.getOne(id);
  }

  public List<Coupon> getAllCoupons() {
    return couponDomainService.getList();
  }

  public long getAllCouponsCount() {
    return couponDomainService.count();
  }

  public String sendTopicForCreateCoupon(CouponCreateRequest couponCreateRequest) {
    this.template.send(KafkaConstant.COUPON_CREATE_TOPIC, couponCreateRequest);
    return "OK";
  }

  // @KafkaListener(topics = KafkaConstant.COUPON_CREATE_TOPIC)
  public void listen(ConsumerRecord<?, ?> cr) {
    log.info(">>>> Subscribe value {}", cr.value());
    log.info(">>>> Created Coupon Info {}", createCoupon((CouponCreateRequest) cr.value()));
  }

}
