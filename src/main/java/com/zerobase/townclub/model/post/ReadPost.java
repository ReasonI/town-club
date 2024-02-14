package com.zerobase.townclub.model.post;

import com.zerobase.townclub.model.constants.PostStatus;
import com.zerobase.townclub.persist.entity.Category;
import com.zerobase.townclub.persist.entity.User;
import java.time.LocalDateTime;
import lombok.*;

public class ReadPost {

  @Getter
  @Setter
  public static class Request {

    private Long id;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {

    private User user;
    private Category category;

    private String title;

    private Double lat;
    private Double lon;

    private LocalDateTime joinDateTime;
    private LocalDateTime dueDateTime;

    private PostStatus postStatus;

    public static Response from(PostDto postDto) {
      return Response.builder()
          .user(postDto.getUser())
          .category(postDto.getCategory())
          .title(postDto.getTitle())
          .lat(postDto.getLat())
          .lon(postDto.getLon())
          .joinDateTime(postDto.getJoinDateTime())
          .dueDateTime(postDto.getDueDateTime())
          .postStatus(postDto.getPostStatus())
          .build();
    }
  }

}
