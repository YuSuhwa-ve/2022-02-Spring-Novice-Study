package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.user.User;
import java.time.LocalDateTime;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private User user;

    private String userEmail;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.user= entity.getUser();
        this.userEmail=entity.getUser().getEmail();
        this.modifiedDate=entity.getModifiedDate();
    }
}
