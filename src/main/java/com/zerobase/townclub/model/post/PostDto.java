package com.zerobase.townclub.model.post;

import com.zerobase.townclub.model.constants.PostStatus;
import com.zerobase.townclub.persist.entity.Category;
import com.zerobase.townclub.persist.entity.Post;
import com.zerobase.townclub.persist.entity.User;
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
public class PostDto {

  private Long id;

  private User user;

  private Category category;

  private String title;
  private String contents;

  private Double lat;
  private Double lon;

  private LocalDateTime date;
  private LocalDateTime dueDate;

  private PostStatus postStatus;

  public static PostDto fromEntity(Post post){
    return PostDto.builder()
        .id(post.getId())
        .user(post.getUser())
        .category(post.getCategory())
        .title(post.getTitle())
        .contents(post.getContents())
        .lat(post.getLat())
        .lon(post.getLon())
        .date(post.getDate())
        .dueDate(post.getDueDate())
        .postStatus(post.getPostStatus())
        .build();
  }
}
