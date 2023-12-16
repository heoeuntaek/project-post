package com.example.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter @Setter
public class PostDto {

    private Long id;
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private LocalDateTime time;
}
