package com.example.project.service;

import com.example.project.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;
    @Test
    void add() throws IOException {
        Post post = new Post();

        postService.add(post, null);

//        Assertions.assertThat(postId).isEqualTo(1L);

    }

    @Test
    void findById() {
        Post post = new Post();

//        Long postId = postService.add(post);
//
//        Post findPost = postService.findById(postId);
//        Assertions.assertThat(findPost).isEqualTo(post);

    }

    @Test
    void getAll() throws IOException {

        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();

        postService.add(post1, null);
        postService.add(post2, null);
        postService.add(post3, null);


        List<Post> posts = (List<Post>) postService.getAll();
        Assertions.assertThat(posts.size()).isEqualTo(3);


    }

    @Test
    public void time(){
        //given
        Post post = new Post();


        System.out.println("post.getTime() = " + post.getTime().toString());

        // DateTimeFormatter를 사용하여 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        String formattedDateTime = post.getTime().format(formatter);

        System.out.println("formattedDateTime = " + formattedDateTime);
        //when

        //then

    }
}


