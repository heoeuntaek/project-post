package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.FriendRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Transactional//            add
    public void save(User user) {
        userRepository.save(user);
    }

//            getAll

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElse(null);
        return user;
    }

    @Transactional
    public void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.update(userDto);

    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
