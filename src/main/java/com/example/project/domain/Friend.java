package com.example.project.domain;

import com.example.project.dto.FriendDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")

public class Friend {

    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private Long id;

    private String loginId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public FriendDto toDto() {
        FriendDto friendDto = new FriendDto();
        friendDto.setId(this.id);
        friendDto.setLoginId(this.loginId);
        return friendDto;
    }
}
