package com.example.project.controller;

import com.example.project.SessionConst;
import com.example.project.domain.Comment;
import com.example.project.domain.Post;
import com.example.project.domain.User;
import com.example.project.dto.CommentDto;
import com.example.project.dto.PostDto;
import com.example.project.dto.UserDto;
import com.example.project.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/add")
    public String addPost(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "post/postForm";
    }

    @PostMapping("/posts/add")
    public String addPost(@Validated @ModelAttribute("postDto") PostDto postDto,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                          BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "post/postForm";
        }

        Post post = new Post(null, postDto.getTitle(), postDto.getContent(), null,
                LocalDateTime.now(), null, null, 0,null, null);
        post.setUser(user);

        postService.add(post, file);

        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{postId}")
    public String getOne(Model model, @PathVariable("postId") Long postId,
                         @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user) {

        Post post = postService.findById(postId);
        postService.addViewCount(postId);
        PostDto postDto = post.toDto();
        model.addAttribute("postDto", postDto);


        UserDto userDto = user.toUserDto();
        model.addAttribute("userDto", userDto);

        List<Comment> comments = postDto.getComments();
        //List<Comment>를 List<CommentDto>로

        List<CommentDto> commentDtos = comments.stream().map(comment ->
        {
            CommentDto commentDto = comment.toDto();
            return commentDto;
        }).collect(Collectors.toList());

        model.addAttribute("commentDtos", commentDtos);

        int commentCount = comments.size();
        model.addAttribute("commentCount", commentCount);
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


    ////    @GetMapping("/posts")
//    public String getAll(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {
//
//        List<Post> posts = postService.getAll();
//
//        List<PostDto> postDtos = posts.stream().
//                map(post -> {
//                    PostDto postDto = new PostDto();
//                    postDto.setId(post.getId());
//                    postDto.setTitle(post.getTitle());
//                    postDto.setTime(post.getTime());
//                    postDto.setContent(post.getContent());
//                    postDto.setUser(post.getUser());
//                    return postDto;
//                })
//                .collect(Collectors.toList());
//        model.addAttribute("postDtos", postDtos);
//
//        UserDto userDto = user.toUserDto();
//        model.addAttribute("userDto", userDto);
//        return "post/posts";
//
//    }
    @GetMapping("/posts")
    public String getAllV2(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                           Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> posts = postService.getAll(pageable);

        int nowPage = posts.getPageable().getPageNumber() + 1; //처음 페이지가 0
        int startPage = nowPage;
        int endPage = Math.min(nowPage + 5, posts.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        List<PostDto> postDtos = posts.stream().
                map(post -> {
                    return post.toDto();
                }).collect(Collectors.toList());

        model.addAttribute("postDtos", postDtos);
        UserDto userDto = user.toUserDto();
        model.addAttribute("userDto", userDto);
        return "post/posts";

    }

    //삭제
    @PostMapping("/posts/delete/{postId}")
    public String deletePost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                             @PathVariable("postId") Long postId) {
        Post post = postService.findById(postId);
        user.getPosts().remove(post);
        postService.deleteById(postId);
        return "redirect:/posts";
    }

    /**
     * 파일 다운로드를 처리하는 컨트롤러 메서드
     *
     * @param fileName 다운로드할 파일명
     * @param response HttpServletResponse 객체
     * @throws IOException 입출력 예외 발생 시 처리
     */
    @GetMapping("/posts/down/{fileName}")
    public void fileDownload(@PathVariable("fileName") String fileName,
                             HttpServletResponse response) throws IOException {
        // 다운로드할 파일의 경로와 이름을 지정
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        File file = new File(filePath, fileName);

        // 다운로드 설정

        // Content-Type을 설정하여 브라우저에게 전송되는 파일의 타입을 명시
        response.setContentType("application/download; charset=UTF-8");

        // Content-Length를 설정하여 응답의 길이를 명시
        response.setContentLength((int) file.length());

        // Content-Disposition을 설정하여 브라우저에게 파일을 다운로드할 것임을 알림
        // 또한, 파일명을 UTF-8로 인코딩하여 전달하여 한글 파일명이 깨지지 않도록 함
        response.setHeader("Content-disposition", "attachment; " +
                "filename=" + URLEncoder.encode(fileName, "UTF-8"));

        // 응답 객체를 통해 서버로부터 파일 다운로드 진행
        OutputStream outputStream = response.getOutputStream();

        // 파일 입력 객체 생성
        FileInputStream fileInputStream = new FileInputStream(file);

        // 파일 복사를 통해 응답 스트림으로 전송
        FileCopyUtils.copy(fileInputStream, outputStream);

        // 사용이 끝난 파일 입력 스트림 및 응답 스트림을 닫음
        fileInputStream.close();
        outputStream.close();
    }

    //조회수 기@GetMapping("/posts/{postId}")

//    닉네임 눌러서 친구추가


}
