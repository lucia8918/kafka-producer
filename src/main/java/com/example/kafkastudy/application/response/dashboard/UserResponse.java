package com.example.kafkastudy.application.response.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
  long id;
  String username;
  String name;
}
