package com.zerobase.townclub.model.sms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SendSms {

  @Getter
  @Setter
  public static class Request {

    @Pattern(regexp = "^01(?:0|1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$")
    private String phoneNum;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {

    private Long id;
    private String phoneNum;
    private Integer randomNum;
    private LocalDateTime createdAt;

    public static Response from(SmsDto smsDto) {
      return Response.builder()
          .id(smsDto.getId())
          .phoneNum(smsDto.getPhoneNum())
          .randomNum(smsDto.getRandomNum())
          .createdAt(smsDto.getCreatedAt())
          .build();
    }
  }

}
