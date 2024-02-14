package com.zerobase.townclub.model.user;

import com.zerobase.townclub.model.constants.UserStatus;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.*;

public class SignUp {

  @Getter
  @Setter
  public static class Request{

    private String nickname;

    //TODO : 핸드폰 번호 validation
    @NotNull
    private String phoneNum;

    private List<String> roles;

    private Double lat;
    private Double lon;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response{

    private Long id;

    private String phoneNum;
    private UserStatus userStatus;
    public static Response from(UserDto userDto){
      return Response.builder()
          .id(userDto.getId())
          .phoneNum(userDto.getPhoneNum())
          .userStatus(userDto.getUserStatus())
          .build();
    }
  }

}
