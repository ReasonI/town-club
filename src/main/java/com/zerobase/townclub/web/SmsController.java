package com.zerobase.townclub.web;

import com.zerobase.townclub.model.sms.AuthSms;
import com.zerobase.townclub.model.sms.SendSms;
import com.zerobase.townclub.service.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SmsController {

  private final SmsService smsService;

  /**
   * 본인 확인 문자 보내기
   */
  @PostMapping("/sms")
  public SendSms.Response send(@RequestBody @Valid SendSms.Request request){
    return SendSms.Response.from(
        smsService.send(request)
    );
  }

  /**
   * 본인 확인 번호 검증
   */
  @PostMapping("/auth-sms")
  public AuthSms.Response auth(@RequestBody @Validated AuthSms.Request request){
    return AuthSms.Response.from(
        smsService.auth(request)
    );
  }

}
