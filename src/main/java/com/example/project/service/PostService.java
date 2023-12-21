package com.example.project.service;

import com.example.project.domain.Post;
import com.example.project.dto.PostDto;
import com.example.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostService {

    private final PostRepository postRepository;

//    join

    @Transactional
    public void add(Post post, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);//parent -경로, child - name

        post.setFile(fileName, projectPath);


        postRepository.save(post);
    }

    @Transactional
    public void edit(PostDto postdto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다")
        );
        post.edit(postdto);

    }

    //    아이디 검색
    public Post findById(Long id) {
        Post findPost = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다")
        );
        return findPost;
    }

    //    모두 검색
    public Page<Post> getAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts;
    }

    @Transactional
    public void deleteById(Long postId){
        postRepository.deleteById(postId);
    }



}
