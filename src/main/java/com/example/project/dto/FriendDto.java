package com.example.project.dto;


import com.example.project.domain.Friend;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class FriendDto {
    private Long id;
    private String loginId;


    public Friend toEntity() {
        Friend friend = new Friend(this.getId(), this.getLoginId(), null);
        return friend;

    }
}
