//package com.example.project.service;
//
//import com.example.project.domain.Post;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class PostServiceTest {
//
//    @Autowired
//    PostService postService;
//    @Test
//    void add() {
//        Post post = new Post();
//
//        postService.add(post, file);
//
////        Assertions.assertThat(postId).isEqualTo(1L);
//
//    }
//
//    @Test
//    void findById() {
//        Post post = new Post();
//
////        Long postId = postService.add(post);
////
////        Post findPost = postService.findById(postId);
////        Assertions.assertThat(findPost).isEqualTo(post);
//
//    }
//
//    @Test
////    void getAll() {
//
//        Post post1 = new Post();
//        Post post2 = new Post();
//        Post post3 = new Post();
//
//        postService.add(post1);
//        postService.add(post2);
//        postService.add(post3);
//
//        List<Post> posts = postService.getAll();
//        Assertions.assertThat(posts.size()).isEqualTo(3);
//
//
//    }
//
//    @Test
//    public void time(){
//        //given
//        Post post = new Post();
//
//
//        System.out.println("post.getTime() = " + post.getTime().toString());
//
//        post.setTime(LocalDateTime.now());
//        // DateTimeFormatter를 사용하여 원하는 형식으로 포맷팅
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
//        String formattedDateTime = post.getTime().format(formatter);
//
//        System.out.println("formattedDateTime = " + formattedDateTime);
//        //when
//
//        //then
//
//    }
//}