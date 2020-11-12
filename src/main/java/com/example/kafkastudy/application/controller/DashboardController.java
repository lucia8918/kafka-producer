package com.example.kafkastudy.application.controller;

import com.example.kafkastudy.application.response.dashboard.CouponSummaryResponse;
import com.example.kafkastudy.application.response.dashboard.UserResponse;
import com.example.kafkastudy.application.service.CouponService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboards")
public class DashboardController {

  private final CouponService couponService;

  @GetMapping("/summary")
  public ResponseEntity<CouponSummaryResponse> summary() {
    return ResponseEntity.ok(couponService.summary());
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> getUsers() {

    List<UserResponse> userResponses = Arrays.asList(
        UserResponse.builder().id(1).name("paden").username("페이든").build()
    );

    return ResponseEntity.ok(userResponses);
  }

}
