package com.zerobase.townclub.web;

import com.zerobase.townclub.model.user.SignUp;
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

  /**
   * 회원가입
   */
  @PostMapping("/signup")
  public SignUp.Response signup(@RequestBody @Validated SignUp.Request request){
    return SignUp.Response.from(
        userService.register(request)
    );
  }
}
