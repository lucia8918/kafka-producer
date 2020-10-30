package com.example.kafkastudy.domain.coupon;

import com.example.kafkastudy.domain.coupon.entity.Coupon;
import com.example.kafkastudy.domain.coupon.repository.CouponRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponDomainService {

  private final CouponRepository couponRepository;

  public Coupon createOne(Coupon coupon) {
    return couponRepository.save(coupon);
  }

  public Coupon getOne(Long id) {
    return getOptional(id).orElseThrow(() -> new RuntimeException("Data Not Exist"));
  }

  public Optional<Coupon> getOptional(Long id) {
    return couponRepository.findById(id);
  }

  public List<Coupon> getList() {
    return couponRepository.findAll();
  }

  public long count() {
    return couponRepository.count();
  }
}
