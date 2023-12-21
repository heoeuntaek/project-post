package com.example.project.repository;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
//@Repository
@Slf4j
public class UserRepositoryWithJpa {
    private final EntityManager em;


    public void add(User user) {
        log.error("3");

        em.persist(user);
    }

    public User findById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    public List<User> getAll() {
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        return users;

    }

    public User findByLoginId(String loginId) {
        try {
            User user = em.createQuery("select u from User u where u.loginId = :loginId", User.class)
                    .setParameter("loginId", loginId).getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }


    public void update(UserDto userDto, Long id) {
        User findUser = em.find(User.class, id);
        findUser.update(userDto);

    }

    public void deleteById(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
