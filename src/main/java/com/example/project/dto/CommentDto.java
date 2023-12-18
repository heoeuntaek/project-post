package com.example.project.dto;

import com.example.project.domain.Comment;
import com.example.project.domain.Post;
import com.example.project.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter @Setter
public class CommentDto {

    private Long id;

    private String content;
    private LocalDateTime time;

    private User user;
    private Post post;

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setId(this.getId());
        comment.setContent(this.getContent());
        comment.setUser(this.getUser());
        comment.setTime(this.getTime());
        comment.setPost(this.getPost());
        return comment;
    } 
    
}
