package com.example.project.domain;

import com.example.project.dto.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@ToString
@AllArgsConstructor
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


    public void setUser(User user) {
        // 기존 user과 관계를 제거
        if (this.user != null) {
            this.user.getPosts().remove(this);
        }

        // 새로운 user 설정
        this.user = user;

        // 새로운 user와의 관계 설정
        if (user != null) {
            user.getPosts().add(this);
        }
    }

    public void setFile(String fileName, String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }




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
        postDto.setComments(post.getComments());

        return postDto;

    }

    public void edit(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }



}