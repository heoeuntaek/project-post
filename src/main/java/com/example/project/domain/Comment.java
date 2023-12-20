package com.example.project.domain;

import com.example.project.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private LocalDateTime time;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public Comment(Long id, String content, LocalDateTime time, User user, Post post) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.user = user;
        this.post = post;
    }

    public void setUser(User newUser) {
        // 기존 user과 관계를 제거
        if (this.user != null) {
            this.user.getComments().remove(this);
        }

        // 새로운 newUser 설정
        this.user = newUser;

        // 새로운 user와의 관계 설정
        if (newUser != null) {
            newUser.getComments().add(this);
        }
    }

    public void setPost(Post newPost) {
        // 기존 post과 관계를 제거
        if (this.post != null) {
            this.post.getComments().remove(this);
        }

        // 새로운 newPost 설정
        this.post = newPost;

        // 새로운 post와의 관계 설정
        if (newPost != null) {
            newPost.getComments().add(this);
        }
    }
    


    public CommentDto toDto() {

        CommentDto commentDto = new CommentDto();

        //comment.id가 있다면
        if (this.id != null) {
            commentDto.setId(this.getId());
        }
        commentDto.setContent(this.getContent());
        commentDto.setTime(this.getTime());
        commentDto.setUser(this.getUser());
        commentDto.setPost(this.getPost());
        return commentDto;

    }
}