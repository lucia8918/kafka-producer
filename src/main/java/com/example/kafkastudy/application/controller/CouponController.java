package com.example.kafkastudy.application.controller;

import com.example.kafkastudy.application.request.CouponCreateRequest;
import com.example.kafkastudy.application.response.CommonResponse;
import com.example.kafkastudy.application.service.CouponService;
import com.example.kafkastudy.domain.coupon.entity.Coupon;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {

  private final CouponService couponService;

  @PostMapping("")
  public CommonResponse createCoupon(@RequestBody CouponCreateRequest request) {
    return new CommonResponse(couponService.createCoupon(request));
  }

  @PostMapping("/kafka")
  public CommonResponse createCouponByKafka(@RequestBody CouponCreateRequest request) {
    return new CommonResponse(couponService.sendTopicForCreateCoupon(request));
  }

  @GetMapping("")
  public CommonResponse getCoupons() {
    return new CommonResponse(couponService.getAllCoupons());
  }

  @GetMapping("/count")
  public CommonResponse getCouponsCount() {
    return new CommonResponse(couponService.getAllCouponsCount());
  }

  @GetMapping("/simple")
  public List<Coupon> getCouponsSimple() {
    return couponService.getAllCoupons();
  }
}
