package com.zerobase.townclub.service;

import com.zerobase.townclub.exception.TownException;
import com.zerobase.townclub.model.constants.ErrorCode;
import com.zerobase.townclub.model.sms.AuthSms;
import com.zerobase.townclub.model.sms.SendSms;
import com.zerobase.townclub.model.sms.SmsDto;
import com.zerobase.townclub.persist.SmsRepository;
import com.zerobase.townclub.persist.entity.Sms;
import com.zerobase.townclub.external.SmsComponent;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class SmsService {

  private final SmsRepository smsRepository;
  private final SmsComponent smsUtil;

  private static final int AUTH_TIMEOUT = 5;
  private static final int MIN_VERIFICATION_CODE = 1000;
  private static final int MAX_VERIFICATION_CODE = 9000;

  /**
   * 본인 확인 문자 보내기
   */
  @Transactional
  public SmsDto send(SendSms.Request request) {

    int verificationCode = (int) (Math.random() * MAX_VERIFICATION_CODE) + MIN_VERIFICATION_CODE;
    smsUtil.sendOne(request.getPhoneNum(), verificationCode);

    Sms sms = smsRepository.findByPhoneNum(request.getPhoneNum()).orElseGet(() -> new Sms());

    sms.setPhoneNum(request.getPhoneNum());
    sms.setRandomNum(verificationCode);

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
   * 본인 확인 문자 번호 검증 전송된지 5분 이내인지 시간 확인
   */
  // 번호 확인 api
  @Transactional
  public String auth(AuthSms.Request request) {

    // 전송된 핸드폰 번호인지 확인
    Sms sms = smsRepository.findFirst1ByPhoneNumOrderByCreatedAtDesc(request.getPhoneNum())
        .orElseThrow(() -> new TownException(ErrorCode.PHONENUM_NOT_FOUND));

    // 5분 이내에 전송한 번호인지 확인
    if (!LocalDateTime.now().isBefore(sms.getCreatedAt().plusMinutes(AUTH_TIMEOUT))) {
      throw new TownException(ErrorCode.TIME_EXPIRED);
    }
    // 올바른 randomNum인지 확인
    if (!Objects.equals(request.getRandomNum(), sms.getRandomNum())) {
      throw new TownException(ErrorCode.WRONG_AUTH_NUM);
    }
    return "인증되었습니다.";
  }

}
