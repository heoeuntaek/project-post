package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private  final UserRepository userRepository;


    @Transactional//            add
    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

//            getAll

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다")
        );

        return user;
    }

    @Transactional
    public void modify(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        user.update(userDto);

    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
