package com.zerobase.townclub.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  //USER
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
  USER_EXIST(HttpStatus.BAD_REQUEST, "이미 사용 중인 핸드폰 번호입니다."),

  //SMS
  PHONENUM_NOT_FOUND(HttpStatus.NOT_FOUND, "문자 전송을 하지 않은 번호입니다.");


  private final HttpStatus httpStatus;
  private final String message;



}
