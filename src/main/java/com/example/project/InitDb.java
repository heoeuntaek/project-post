package com.example.project;

import com.example.project.domain.Post;
import com.example.project.domain.User;
import com.example.project.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.awt.print.Book;
import java.time.LocalDateTime;


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
            user.setNickName("젤리");

            em.persist(user);

            Post post = new Post();
            post.setUser(user);
            post.setTitle("가을이 푸른 밤");
            post.setContent("안녕하세요 제 이름은 푸른 밤이에요. 날이 점점 어두워져 가네요. 제 마음도 어두워져 가네요.");
            post.setTime(LocalDateTime.now());

            em.persist(post);





        }
    }
}


