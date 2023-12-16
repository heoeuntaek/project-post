package com.example.project.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class LoginDto {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String loginId;

    @NotEmpty
    private String loginPw;





}
