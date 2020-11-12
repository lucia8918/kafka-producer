package com.example.kafkastudy.application.response.dashboard;

import com.example.kafkastudy.domain.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouponSummaryResponse {

  // 쿠폰 정책 개수
  private long totalPolicyCnt;

  // 지급 쿠폰 개수
  private long totalIssuedCouponCnt;

  // 사용 쿠폰 개수
  private long usedIssuedCouponCnt;

}
