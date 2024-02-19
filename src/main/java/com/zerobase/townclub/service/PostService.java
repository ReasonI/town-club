package com.zerobase.townclub.service;

import com.zerobase.townclub.exception.TownException;
import com.zerobase.townclub.model.constants.ErrorCode;
import com.zerobase.townclub.model.post.CreatePost;
import com.zerobase.townclub.model.post.PostDto;
import com.zerobase.townclub.model.post.ReadPost;
import com.zerobase.townclub.persist.CategoryRepository;
import com.zerobase.townclub.persist.PostRepository;
import com.zerobase.townclub.persist.UserRepository;
import com.zerobase.townclub.persist.entity.Category;
import com.zerobase.townclub.persist.entity.Post;
import com.zerobase.townclub.persist.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;


  /**
   * 모집 게시글 작성
   */

  @Transactional
  public PostDto createPost(CreatePost.Request request, User user){

    Category category = categoryRepository.findById(request.getCategory().getId())
        .orElseThrow(() -> new TownException(ErrorCode.CATEGORY_NOT_FOUND));

    return PostDto.fromEntity(
        postRepository.save(Post.builder()
                .user(user)
                .category(category)
                .title(request.getTitle())
                .contents(request.getContents())
                .lat(request.getLat())
                .lon(request.getLon())
                .joinDateTime(request.getJoinDateTime())
                .dueDateTime(request.getDueDateTime())
            .build()
        )
    );
  }

  /**
   * 게시글 목록 조회
   */

  @Transactional
  public PostDto readPostList(ReadPost.Request request, User user){

    // user 전방 거리 구하기

    double lat = user.getLat();
    double lon = user.getLon();

    int distance = user.getDistance();
    // 특정 거리 이내의 게시글만 불러오기

    return null;
  }
}
