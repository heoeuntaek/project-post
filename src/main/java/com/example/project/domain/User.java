package com.example.project.domain;

import com.example.project.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Table(name = "UserT")
@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String loginPw;
    private String nickName;

    @OneToMany(mappedBy = "user", fetch = EAGER)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = EAGER)
    private List<Comment> comments = new ArrayList<>();



    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getId() != null) {
            userDto.setId(user.getId());
        }

        userDto.setLoginId(user.getLoginId());
        userDto.setLoginPw(user.getLoginPw());
        userDto.setNickName(user.getNickName());
        return userDto;
    }



    public void update(UserDto userDto) {
        this.nickName = userDto.getNickName();
        this.loginPw = userDto.getLoginPw();
    }


}
