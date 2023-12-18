package com.example.project.service;

import com.example.project.domain.Post;
import com.example.project.dto.PostDto;
import com.example.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

        post.setFileName(fileName);
        post.setFilePath(projectPath);



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
    public void deleteByPostId(Long postId){
        postRepository.deleteByPostId(postId);
    }

}
