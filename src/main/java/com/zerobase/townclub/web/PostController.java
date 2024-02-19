package com.zerobase.townclub.web;

import com.zerobase.townclub.model.post.CreatePost;
import com.zerobase.townclub.model.post.ReadPost;
import com.zerobase.townclub.model.post.ReadPost.Response;
import com.zerobase.townclub.persist.entity.User;
import com.zerobase.townclub.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
  public CreatePost.Response createPost(@RequestBody @Valid CreatePost.Request request,
      @AuthenticationPrincipal User user) {
    return CreatePost.Response.from(
        postService.createPost(request, user)
    );
  }

  /**
   * 게시글 목록 조회
   */

  @GetMapping()
  public List<ReadPost.Response> readPostList(@RequestBody ReadPost.Request request,
      @AuthenticationPrincipal User user){
    return (List<Response>) ReadPost.Response.from(
        postService.readPostList(request, user)
    );
  }

  /**
   * 게시글 상세 조회
   * 게시글 삭제
   */
}
