package com.zerobase.townclub.sms;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
  @Value("${coolsms.api.key}")
  private String apiKey;
  @Value("${coolsms.api.secret}")
  private String apiSecretKey;

  private DefaultMessageService messageService;

  @PostConstruct
  private void init(){
    this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
  }

  // 단일 메시지 발송 예제
  public SingleMessageSentResponse sendOne(String to, int verificationCode) {
    Message message = new Message();
    message.setFrom("01065841187");
    message.setTo(to);
    message.setText("[townClub] 아래의 인증번호를 입력해주세요\n" + verificationCode);

    SingleMessageSentResponse response = this.messageService.sendOne(
        new SingleMessageSendingRequest(message));
    return response;
  }
}