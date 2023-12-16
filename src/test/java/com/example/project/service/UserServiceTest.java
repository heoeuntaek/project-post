package com.example.project.service;

import com.example.project.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired private UserService userService;

    @Test
    void join() {
        User user = new User();

        Long id = userService.join(user);

        Assertions.assertThat(id).isEqualTo(2);
    }

    @Test
    void findOne() {
        User user = new User();
        userService.join(user);

        User findUser = userService.findById(1L);

        Assertions.assertThat(findUser).isEqualTo(user);
    }

    @Test
    void getUsers() {
        //given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();


        //when
        userService.join(user1);
        userService.join(user2);
        userService.join(user3);

        //then
        List<User> users = userService.getAll();
        Assertions.assertThat(users.size()).isEqualTo(4);

    }
}