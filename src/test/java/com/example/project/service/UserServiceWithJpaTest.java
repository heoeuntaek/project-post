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
class UserServiceWithJpaTest {
    @Autowired private UserServiceWithJpa userServiceWithJpa;

    @Test
    void join() {
        User user = new User();

        Long id = userServiceWithJpa.join(user);

        Assertions.assertThat(id).isEqualTo(2);
    }

    @Test
    void findOne() {
        User user = new User();
        userServiceWithJpa.join(user);

        User findUser = userServiceWithJpa.findById(1L);

        Assertions.assertThat(findUser).isEqualTo(user);
    }

    @Test
    void getUsers() {
        //given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();


        //when
        userServiceWithJpa.join(user1);
        userServiceWithJpa.join(user2);
        userServiceWithJpa.join(user3);

        //then
        List<User> users = userServiceWithJpa.getAll();
        Assertions.assertThat(users.size()).isEqualTo(4);

    }
}