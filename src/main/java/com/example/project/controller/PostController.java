package com.example.project.controller;

import com.example.project.SessionConst;
import com.example.project.domain.Post;
import com.example.project.domain.User;
import com.example.project.dto.PostDto;
import com.example.project.service.PostService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@ToString
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/add")
    public String addPost(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "post/postForm";
    }

    @PostMapping("/posts/add")
    public String addPost(@Validated @ModelAttribute PostDto postDto,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "post/postForm";
        }

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);
        post.setTime(LocalDateTime.now());

        postService.add(post);

        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{postId}")
    public String getPost(Model model, @PathVariable("postId") Long postId,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user) {

        Post post = postService.findById(postId);
        PostDto postDto = post.toDto(post);
        model.addAttribute("postDto", postDto);
        model.addAttribute("user", user);
        return "post/getPost";
    }

    @GetMapping("/posts/edit/{postId}")
    public String editPost(Model model, @PathVariable("postId") Long postId) {
        Post post = postService.findById(postId);
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());

        model.addAttribute("postDto", postDto);
        model.addAttribute("postId", postId);
        return "post/editForm";
    }

    @PostMapping("/posts/edit/{postId}")
    public String editPost(@Validated @ModelAttribute PostDto postDto, @PathVariable("postId") Long postId,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                           BindingResult bindingResult) {



        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "post/postForm";
        }

        postService.edit(postDto, postId);

        return "redirect:/posts/" + postId;
    }

    @GetMapping("/posts")
    public String posts(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {

        List<Post> posts = postService.getAll();

        List<PostDto> postDtos = posts.stream().
                map(post -> {
                    PostDto postDto = new PostDto();
                    postDto.setId(post.getId());
                    postDto.setTitle(post.getTitle());
                    postDto.setTime(post.getTime());
                    postDto.setContent(post.getContent());
                    return postDto;
                })
                .collect(Collectors.toList());
        model.addAttribute("postDtos", postDtos);
        model.addAttribute("user", user);
        return "post/posts";

    }

    //삭제
    @PostMapping("/posts/delete/{postId}")
    public String deletePost( @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                             @PathVariable("postId") Long postId) {
        postService.deleteByPostId(postId,user.getId());
        log.error("1");
        return "redirect:/posts";
    }
}
