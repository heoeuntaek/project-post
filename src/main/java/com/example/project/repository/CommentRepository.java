package com.example.project.repository;

import com.example.project.domain.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class CommentRepository {

    private final EntityManager em;
    public void add(Comment comment) {
        log.error("3");
        em.persist(comment);
    }

    public Comment findById(Long id) {
        Comment comment = em.find(Comment.class, id);
        return comment;
    }

    public List<Comment> getAll() {
        List comments = em.createQuery("select p from Comment p", Comment.class).getResultList();
        return comments;
    }


    public void deleteById(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }

}
