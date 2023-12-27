package com.example.project.controller;

import com.example.project.SessionConst;
import com.example.project.domain.Comment;
import com.example.project.domain.Post;
import com.example.project.domain.User;
import com.example.project.service.CommentService;
import com.example.project.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @ResponseBody
    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable("commentId") Long id) {
        Comment comment = commentService.findById(id);
        return comment;
    }

    @PostMapping("/comments/add/{postId}")
    public String addComment(@RequestParam("content") String content, @PathVariable("postId") Long postId,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user) {

        log.error("1");
        Comment comment = new Comment(null, content, LocalDateTime.now(), null, null);
        Post post = postService.findById(postId);

        comment.setPost(post);
        comment.setUser(user);

        log.error("2");
        commentService.add(comment);
        return "redirect:/posts/"+postId;
    }

    @GetMapping("/comments/delete/{commentId}")
    public String deleteOne(@PathVariable("commentId")Long id){

        Comment comment = commentService.findById(id);
        Post post = postService.findById(comment.getPost().getId());
        post.getComments().remove(comment);
        commentService.deleteById(id);
        return "redirect:/posts/" + id;
    }

}
