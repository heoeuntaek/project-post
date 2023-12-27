package com.example.project.domain;

import com.example.project.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Table(name = "User")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"posts", "comments", "friends"})

public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String loginPw;
    private String nickName;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Friend> friends = new ArrayList<>();


    public void addFriend(Friend friend) {

        friend.setUser(this);
        this.getFriends().add(friend);
    }

    public UserDto toUserDto() {

        UserDto userDto = new UserDto();
        if (this.getId() != null) {
            userDto.setId(this.getId());
        }

        userDto.setLoginId(this.getLoginId());
        userDto.setLoginPw(this.getLoginPw());
        userDto.setNickName(this.getNickName());
        return userDto;
    }


    public void update(UserDto userDto) {
        this.nickName = userDto.getNickName();
        this.loginPw = userDto.getLoginPw();
    }


}
