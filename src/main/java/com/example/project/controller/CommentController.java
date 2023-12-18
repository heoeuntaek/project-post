package com.example.project.controller;

import com.example.project.domain.Comment;
import com.example.project.dto.CommentDto;
import com.example.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable("commentId") Long id) {
        Comment comment = commentService.findById(id);
        return comment;
    }

    @ResponseBody
    @PostMapping("/comments/add")
    public Comment addComment(@RequestParam("content") String content) {
//        Comment comment = commentDto.toComment();

        Comment comment = new Comment();
        comment.setContent(content);

        commentService.add(comment);
        return comment;


    }
}
