package com.zerobase.townclub.service;

import com.zerobase.townclub.exception.TownException;
import com.zerobase.townclub.model.constants.ErrorCode;
import com.zerobase.townclub.model.user.SignUp;
import com.zerobase.townclub.model.user.UserDto;
import com.zerobase.townclub.persist.UserRepository;
import com.zerobase.townclub.persist.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  /**
   *회원가입된 핸드폰번호인지 확인
   */
  @Override
  public UserDetails loadUserByUsername(String phoneNum) throws UsernameNotFoundException {
    return this.userRepository.findByPhoneNum(phoneNum)
        .orElseThrow(() -> new TownException(ErrorCode.USER_NOT_FOUND));
  }
  /**
   * 본인 확인 문자 보내기
   */
  //TODO : 본인 확인 문자 보내기 api -랜덤 번호 6자리 생성

  /**
   * 본인 확인 문자 번호 검증
   */
  // 번호 확인 api

  /**
   * 회원가입
   */
  @Transactional
  public UserDto register(SignUp.Request request) {

    boolean exists = this.userRepository.existsByPhoneNum(request.getPhoneNum());
    if (exists) {
      throw new TownException(ErrorCode.USER_EXIST);
    }


    return UserDto.fromEntity(
        userRepository.save(User.builder()
            .nickname(request.getNickname())
            .phoneNum(request.getPhoneNum())
            .roles(request.getRoles())
            .lat(request.getLat())
            .lon(request.getLon())
            .build())
    );
  }


}
