//package com.example.project.controller;
//
//import com.example.project.domain.User;
//import com.example.project.dto.UserDto;
//import com.example.project.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class UserControllerAPI {
//    private final UserService userService;
//
//
//    @PostMapping("/users/new")
//    public String create(@RequestBody UserDto userDto){
//        User user = new User();
//        user.setLoginId(userDto.getLoginId());
//        user.setLoginPw(userDto.getLoginPw());
//        user.setNickName(userDto.getNickName());
//
//        userService.join(user);
//        log.info("user join {}",userDto);
//        return "redirect:/";
//    }
//}
