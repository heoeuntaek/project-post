package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.repository.UserRepositoryWithJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
//@Service
public class UserServiceWithJpa {

    private final UserRepositoryWithJpa userRepositoryWithJpa;

    @Transactional
    public Long join(User user) {
        userRepositoryWithJpa.add(user);
        return user.getId();
    }

    public User findById(Long id) {
        User user = userRepositoryWithJpa.findById(id);
        return user;
    }

    public List<User> getAll(){
        List<User> users = userRepositoryWithJpa.getAll();
        return users;
    }

    public User findByLoginId(String loginId){
        User user =  userRepositoryWithJpa.findByLoginId(loginId);
        return user;
    }


    @Transactional
    public void update(UserDto userDto, Long id) {
        userRepositoryWithJpa.update(userDto, id);
    }

    @Transactional
    public void delete(Long id) {
        userRepositoryWithJpa.deleteById(id);
    }
}
