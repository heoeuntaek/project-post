package com.example.project.domain;

import com.example.project.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Table
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;


    private String title;
    private String content;
    private String file;

    private LocalDateTime time;

    private String fileName;
    private String filePath;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", fetch = EAGER)
    private List<Comment> comments = new ArrayList<>();

    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        if (post.getId() != null) {
            postDto.setId(post.getId());
        }
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setTime(post.getTime());
        postDto.setUser(post.getUser());
        postDto.setFilePath(post.getFilePath());
        postDto.setFileName(post.getFileName());
        return postDto;

    }


}