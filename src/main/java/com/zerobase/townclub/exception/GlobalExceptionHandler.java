package com.zerobase.townclub.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({TownException.class})
  public ResponseEntity<TownException.TownExceptionResponse> exceptionHandler(HttpServletRequest request, final TownException e){
    return ResponseEntity
        .status(e.getStatus())
        .body(TownException.TownExceptionResponse.builder()
            .errorMessage(e.getMessage())
            .code(e.getErrorCode().name())
            .status(e.getStatus())
            .build());
  }

}
