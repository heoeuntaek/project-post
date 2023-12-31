package com.example.project.repository;

import com.example.project.domain.Post;
import com.example.project.dto.PostDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class PostRepositoryOld {
    private final EntityManager em;


    public void add(Post post) {
        em.persist(post);
    }

    public Post findById(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    public List<Post> getAll() {
        List posts = em.createQuery("select p from Post p", Post.class).getResultList();
        return posts;
    }


    public void edit(PostDto postDto, Long id) {
        Post findPost = em.find(Post.class, id);

        findPost.edit(postDto);

    }

    public void deleteByPostId(Long postId) {
        Post post = em.find(Post.class, postId);
        em.remove(post);

//        em.createQuery("delete from Post p where p.id=:postId and p.user.id =:userId")
//                .setParameter("postId", postId)
//                .setParameter("userId", userId)
//                .executeUpdate();
    }


}
