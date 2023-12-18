package com.example.project.service;

import com.example.project.domain.Comment;
import com.example.project.repository.CommentRepository;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void add(Comment comment) {
        commentRepository.add(comment);
    }

    //    아이디 검색
    public Comment findById(Long id) {
        Comment findComment = commentRepository.findById(id);
        return findComment;
    }

    //    모두 검색
    public List<Comment> getAll() {
        List<Comment> comments = commentRepository.getAll();
        return comments;
    }

    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
