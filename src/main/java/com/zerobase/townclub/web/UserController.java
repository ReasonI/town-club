package com.zerobase.townclub.web;

import com.zerobase.townclub.model.user.SignIn;
import com.zerobase.townclub.model.user.SignUp;
import com.zerobase.townclub.model.user.UserDto;
import com.zerobase.townclub.persist.entity.User;
import com.zerobase.townclub.security.TokenProvider;
import com.zerobase.townclub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final TokenProvider tokenProvider;

  private static final String identify = "USER";

  /**
   * 회원가입
   */
  @PostMapping("/signup")
  public SignUp.Response signup(@RequestBody @Validated SignUp.Request request) {
    return SignUp.Response.from(
        userService.register(request)
    );
  }

  /**
   * 로그인
   */
  @PostMapping("/signin")
  public SignIn.Response signin(@RequestBody SignIn.Request request){

    User user = this.userService.authenticate(request);

    String token = this.tokenProvider.generateToken(identify, user.getPhoneNum(), user.getRoles());
    return SignIn.Response.from(
        UserDto.fromEntity(user), token
    );
  }
  /**
   * 동네설정
   */

}
