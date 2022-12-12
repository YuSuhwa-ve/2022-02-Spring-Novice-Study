package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.jojoldu.book.springboot.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity

public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable=false )
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "posts", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comments> comments;


    @Builder
    public Posts(String title, String content,User user){
        this.user=user;
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
