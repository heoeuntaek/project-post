package com.example.project.service;

import com.example.project.domain.Post;
import com.example.project.dto.PostDto;
import com.example.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostService {

    private final PostRepository postRepository;

//    join

    @Transactional
    public void add(Post post) {
        postRepository.add(post);
    }

    @Transactional
    public void edit(PostDto postdto, Long id) {
        postRepository.edit(postdto, id);

    }

    //    아이디 검색
    public Post findById(Long id) {
        Post findPost = postRepository.findById(id);
        return findPost;
    }

    //    모두 검색
    public List<Post> getAll() {
        List<Post> posts = postRepository.getAll();
        return posts;
    }

    @Transactional
    public void deleteByPostId(Long postId, Long userId){
        postRepository.deleteByPostId(postId,userId);
    }

}
