package com.example.project.domain;

import com.example.project.dto.CommentDto;
import com.example.project.dto.PostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor
@Getter @Setter
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