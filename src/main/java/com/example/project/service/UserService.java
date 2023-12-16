package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        userRepository.add(user);
        return user.getId();
    }

    public User findById(Long id) {
        User user = userRepository.findById(id);
        return user;
    }

    public List<User> getAll(){
        List<User> users = userRepository.getAll();
        return users;
    }

    public User findByLoginId(String loginId){
        User user =  userRepository.findByLoginId(loginId);
        return user;
    }


    @Transactional
    public void update(User user) {
        userRepository.update(user);
    }
}
