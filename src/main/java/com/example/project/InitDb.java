package com.example.project;

import com.example.project.domain.User;
import com.example.project.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;


@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final UserService userService;

        public void dbInit1() {
            User user = new User();
            user.setLoginId("a");
            user.setLoginPw("a");
            user.setNickName("a");

            em.persist(user);


        }
    }
}


