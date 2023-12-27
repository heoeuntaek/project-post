package com.example.project.dto;

import com.example.project.domain.Comment;
import com.example.project.domain.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
public class PostDto {

    private Long id;
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private LocalDateTime time;

    private User user;

    private String fileName;
    private String filePath;

    private List<Comment> comments = new ArrayList<>();
    private int viewCount;
}
