package com.example.kafkastudy.application.service;

import com.example.kafkastudy.application.request.coupon.CouponCreateRequest;
import com.example.kafkastudy.application.request.issue.CouponIssueCancelRequest;
import com.example.kafkastudy.application.response.CouponCreatedResponse;
import com.example.kafkastudy.application.response.dashboard.CouponSummaryResponse;
import com.example.kafkastudy.constant.KafkaConstant;
import com.example.kafkastudy.domain.coupon.CouponDomainService;
import com.example.kafkastudy.domain.coupon.entity.Coupon;
import com.example.kafkastudy.domain.issue.CouponIssueDomainService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

  private final CouponDomainService couponDomainService;
  private final CouponIssueDomainService couponIssueDomainService;


  @Qualifier("KafkaTemplate") private final KafkaTemplate<String, CouponCreateRequest> template;

  @Qualifier("KafkaTemplate") private final KafkaTemplate<String, CouponIssueCancelRequest> templateCancel;

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

  /**
   * 쿠폰 정책 등록 토픽 발행
   * @param couponCreateRequest
   * @return
   */
  public String sendTopicForCreateCoupon(CouponCreateRequest couponCreateRequest) {
    this.template.send(KafkaConstant.COUPON_CREATE_TOPIC, couponCreateRequest);
    return "OK";
  }

  /**
   * 쿠폰 회수 토픽 발행
   * @param couponId
   * @return
   */
  public String sendTopicForCancelCouponIssues(long couponId) {
    CouponIssueCancelRequest request = CouponIssueCancelRequest.builder().couponId(couponId).build();
    this.templateCancel.send(KafkaConstant.COUPON_CANCEL_ISSUED_TOPIC, request);
    return "OK";
  }

  public CouponSummaryResponse summary() {

    long totalIssuedCouponCnt = couponDomainService.count();
    long availableCouponIssuedCnt = couponIssueDomainService.getAvailableListCount();

    return CouponSummaryResponse.builder().totalIssuedCouponCnt(availableCouponIssuedCnt).totalPolicyCnt(totalIssuedCouponCnt)
        .usedIssuedCouponCnt(availableCouponIssuedCnt).build();
  }

}
