package com.example.project;

import com.example.project.domain.Post;
import com.example.project.domain.User;
import com.example.project.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
            User user = new User(null, "a", "a", "젤리", null, null);
            em.persist(user);

            User user2 = new User(null, "s", "s", "젤리양", null, null);
            em.persist(user2);

            String title = "가을이 푸른 밤";
            String content = "안녕하세요 제 이름은 푸른 밤이에요. 날이 점점 어두워져 가네요. 제 마음도 어두워져 가네요.";
            Post post = new Post(null, title, content, null, LocalDateTime.now(), null, null
                    , user, null);


            //파일 첨부
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            String fileName = "860fd6b9-b4fa-4403-9f51-1f46e9b859ef_자유2.txt";
            post.setFile(fileName, projectPath);
            em.persist(post);


        }
    }
}


