package com.zerobase.townclub.model.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthSms {

  @Getter
  @Setter
  public static class Request {

    private String phoneNum;
    private Integer randomNum;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private String message;

    public static Response from(String message){
      return Response.builder()
          .message(message)
          .build();
    }
  }

}
