package com.example.kafkastudy.domain.issue;

import com.example.kafkastudy.domain.coupon.entity.Coupon;
import com.example.kafkastudy.domain.issue.entity.CouponIssue;
import com.example.kafkastudy.domain.issue.repository.CouponIssueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponIssueDomainService {

  private final CouponIssueRepository couponIssueRepository;

  @Transactional
  public CouponIssue createOne(CouponIssue couponIssue) {
    return couponIssueRepository.save(couponIssue);
  }

  @Transactional
  public List<CouponIssue> createAll(List<CouponIssue> couponIssues) {
    return couponIssueRepository.saveAll(couponIssues);
  }

  @Transactional(readOnly = true)
  public List<CouponIssue> getList() {
    return couponIssueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<CouponIssue> getAvailableList(Coupon coupon) {
    return couponIssueRepository.findAllByCouponIdAndIsUsedFalseAndIsDeletedFalse(coupon);
  }

  @Transactional(readOnly = true)
  public long getAvailableListCount() {
    return couponIssueRepository.countByIsUsedFalseAndIsDeletedFalse();
  }

  @Transactional(readOnly = true)
  public long count() {
    return couponIssueRepository.count();
  }

}
