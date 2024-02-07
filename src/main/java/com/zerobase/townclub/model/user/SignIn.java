package com.zerobase.townclub.model.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SignIn {

  @Getter
  @Setter
  public static class Request {

    @NotNull
    private String phoneNum;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response{
    private Long id;
    private String phoneNum;
    private String token;

    public static Response from (UserDto userDto, String token){
      return Response.builder()
          .id(userDto.getId())
          .phoneNum(userDto.getPhoneNum())
          .token(token)
          .build();
    }
  }
}
