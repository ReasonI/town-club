package com.zerobase.townclub.model.user;

import com.zerobase.townclub.model.constants.UserStatus;
import com.zerobase.townclub.persist.entity.User;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  private Long id;

  private String nickname;
  private String phoneNum;

  private List<String> roles;

  private Double lat;
  private Double lon;

  private Integer distance;

  private UserStatus userStatus;

  public static UserDto fromEntity(User user){
    return UserDto.builder()
        .id(user.getId())
        .nickname(user.getNickname())
        .phoneNum(user.getPhoneNum())
        .roles(user.getRoles())
        .lat(user.getLat())
        .lon(user.getLon())
        .distance(user.getDistance())
        .userStatus(user.getUserStatus())
        .build();
  }

}
