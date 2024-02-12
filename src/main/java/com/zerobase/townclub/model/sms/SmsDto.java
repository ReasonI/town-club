package com.zerobase.townclub.model.sms;

import com.zerobase.townclub.persist.entity.Sms;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDto {

  private Long id;

  private String phoneNum;
  private Integer randomNum;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static SmsDto fromEntity(Sms sms){
    return SmsDto.builder()
        .id(sms.getId())
        .phoneNum(sms.getPhoneNum())
        .randomNum(sms.getRandomNum())
        .createdAt(sms.getCreatedAt())
        .updatedAt(sms.getUpdatedAt())
        .build();
  }

}
