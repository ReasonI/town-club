package com.zerobase.townclub.model.user;

import com.zerobase.townclub.model.constants.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.*;

public class SignUp {

  @Getter
  @Setter
  public static class Request{

    private String nickname;

    @Pattern(regexp = "^01(?:0|1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$")
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
    public static Response from(UserDto userDto){
      return Response.builder()
          .id(userDto.getId())
          .phoneNum(userDto.getPhoneNum())
          .build();
    }
  }

}
