package com.zerobase.townclub.model.post;

import com.zerobase.townclub.model.constants.PostStatus;
import com.zerobase.townclub.persist.entity.Category;
import com.zerobase.townclub.persist.entity.User;
import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CreatePost {

  @Getter
  @Setter
  public static class Request {

    private Category category;

    private String title;
    private String contents;

    private Double lat;
    private Double lon;

    @Future
    private LocalDateTime date;

    @Future
    private LocalDateTime dueDate;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {

    private Long id;
    private Long userId;
    public static Response from(PostDto postDto) {
      return Response.builder()
          .id(postDto.getId())
          .userId(postDto.getUser().getId())
          .build();
    }
  }

}
