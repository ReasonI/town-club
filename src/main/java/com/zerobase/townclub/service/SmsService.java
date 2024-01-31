package com.zerobase.townclub.service;

import com.zerobase.townclub.exception.TownException;
import com.zerobase.townclub.model.constants.ErrorCode;
import com.zerobase.townclub.model.sms.AuthSms;
import com.zerobase.townclub.model.sms.SendSms;
import com.zerobase.townclub.model.sms.SmsDto;
import com.zerobase.townclub.persist.SmsRepository;
import com.zerobase.townclub.persist.entity.Sms;
import com.zerobase.townclub.sms.SmsUtil;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class SmsService {

  private final SmsRepository smsRepository;
  private final SmsUtil smsUtil;

  /**
   * 본인 확인 문자 보내기
   */
  @Transactional
  public SmsDto send(SendSms.Request request) {

    int verificationCode = (int) (Math.random() * 9000) + 1000;
    smsUtil.sendOne(request.getPhoneNum(), verificationCode);
    return SmsDto.fromEntity(
        smsRepository.save(
            Sms.builder()
                .phoneNum(request.getPhoneNum())
                .randomNum(verificationCode)
                .build()
        )
    );


  }

  /**
   * 본인 확인 문자 번호 검증
   * 전송된지 5분 이내인지 시간 확인
   */
  // 번호 확인 api
  @Transactional
  public String auth(AuthSms.Request request) {


    // 전송된 핸드폰 번호인지 확인
    Sms sms = smsRepository.findFirst1ByPhoneNumOrderByCreatedAtDesc(request.getPhoneNum())
        .orElseThrow(() -> new TownException(ErrorCode.PHONENUM_NOT_FOUND));

    // 5분 이내에 전송한 번호인지 확인
    if(!LocalDateTime.now().isBefore(sms.getCreatedAt().plusMinutes(5))){
      System.out.println(sms.getCreatedAt().plusMinutes(5));
      System.out.println(LocalDateTime.now());
      System.out.println(LocalDateTime.now().isBefore(sms.getCreatedAt().plusMinutes(5))+"?");
      return "5분이 지났습니다.";
    }
    System.out.println(LocalDateTime.now().isBefore(sms.getCreatedAt().plusMinutes(5)));
    // 올바른 randomNum인지 확인
    if(!Objects.equals(request.getRandomNum(), sms.getRandomNum())){
      return "인증번호가 다릅니다.";
    }
    return "인증되었습니다.";
  }

}
