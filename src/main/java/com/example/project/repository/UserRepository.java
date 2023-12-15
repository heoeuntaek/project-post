package com.example.project.repository;

import com.example.project.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
@ToString
public class UserRepository {
    private final EntityManager em;


    public void save(User user) {
        em.persist(user);
        log.info("user 생성 {}", user);
    }

    public User findById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    public List<User>getUsers(){
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        return users;

    }

    public User findByLoginId(String loginId){
        User user = em.createQuery("select u from User u where u.loginId=loginId", User.class).getResultList().getFirst();
        return user;

    }
}
