package com.example.project.service;

import com.example.project.domain.Friend;
import com.example.project.domain.User;
import com.example.project.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;

    @Transactional
    public void save(User user, Friend friend) {
        user.addFriend(friend);
        friendRepository.save(friend);
    }

    public Friend findById(Long id) {
        Friend friend = friendRepository.findById(id).orElse(null);
        return friend;
    }

    @Transactional
    public void deleteById(User user, Long id) {
        Friend friend = friendRepository.findById(id).orElse(null);
        user.getFriends().remove(friend);
        friendRepository.deleteById(id);
    }

    public List<Friend> findAll() {
        List<Friend> friends = friendRepository.findAll();
        return friends;
    }

}
