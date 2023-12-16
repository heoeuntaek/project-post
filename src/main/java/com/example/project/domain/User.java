package com.example.project.domain;

import com.example.project.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Table
@Entity
@NoArgsConstructor
@Getter @Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String loginPw;
    private String nickName;

    @OneToMany(mappedBy = "user",fetch = EAGER)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = EAGER)
    private List<Comment> comments = new ArrayList<>();

    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setLoginId(user.getLoginId());
        userDto.setLoginPw(user.getLoginPw());
        userDto.setNickName(user.getNickName());
        return userDto;
    }


}
