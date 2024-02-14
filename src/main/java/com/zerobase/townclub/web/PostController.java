package com.zerobase.townclub.web;

import com.zerobase.townclub.model.post.CreatePost;
import com.zerobase.townclub.service.PostService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

  private final PostService postService;

  /**
   * 모집 게시글 작성
   */

  @PostMapping
//  @PreAuthorize("hasRole('USER_WRITE')")
  public CreatePost.Response createPost(@RequestBody CreatePost.Request request, Principal principal){
    return CreatePost.Response.from(
        postService.createPost(request, principal)
    );
  }

  /**
   * 게시글 목록 조회
   * 게시글 상세 조회
   * 게시글 삭제
   */
}
