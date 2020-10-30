package com.example.kafkastudy.application.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {

  private int code;

  private String message;

  private Object data;

  public CommonResponse(int code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public CommonResponse(Object data) {
    this.data = data;
  }

  public CommonResponse(String message) {
    this.message = message;
  }
}
