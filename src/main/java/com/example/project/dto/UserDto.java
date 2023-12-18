package com.example.project.dto;


import com.example.project.domain.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserDto {

    private Long id;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String loginId;

    @NotEmpty
    private String loginPw;

    @NotEmpty
    private String nickName;




}
