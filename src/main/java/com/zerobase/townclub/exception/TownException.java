package com.zerobase.townclub.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.townclub.model.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TownException extends RuntimeException {

  private ErrorCode errorCode;
  private int status;
  private static final ObjectMapper mapper = new ObjectMapper();

  public TownException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.status = errorCode.getHttpStatus().value();
  }

  @Getter
  @AllArgsConstructor
  @Builder
  @NoArgsConstructor
  public static class TownExceptionResponse {

    private int status;
    private String code;
    private String errorMessage;
  }

}
