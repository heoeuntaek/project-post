package com.example.project.controller;

import com.example.project.domain.User;
import com.example.project.dto.UserDto;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    //    @RequiredArgsConstructor 가 아래 생략함
//    @Autowired
//    public UserController(UserService userService, UserValidater userValidater) {
//        this.userService = userService;
//        this.userValidater = userValidater;
//    }
    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "user/userJoinForm";


    }

    @PostMapping("/users/new")
    public String create(@Validated @ModelAttribute UserDto userDto, BindingResult bindingResult) {
//        특정 필드가 아닌 복합 룰 검증

//        중복 회원검증
        User findOne = userService.findByLoginId(userDto.getLoginId());
        if (findOne != null) {
            log.info("중복회원");
            bindingResult.reject("user");
            return "user/userJoinForm";
        }


        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "user/userJoinForm";
        }


        User user = new User();
        user.setLoginId(userDto.getLoginId());
        user.setLoginPw(userDto.getLoginPw());
        user.setNickName(userDto.getNickName());

        userService.join(user);
        log.info("user join success {}", userDto);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserDto userDto = user.toUserDto(user);

        return userDto;
    }

    @ResponseBody
    @GetMapping("/users/loginId/{loginId}")
    public UserDto findByLoginId(@PathVariable("loginId") String loginId) {
        User user = userService.findByLoginId(loginId);
        UserDto userDto = user.toUserDto(user);
        return userDto;
    }
}
