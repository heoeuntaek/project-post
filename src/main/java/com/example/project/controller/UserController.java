package com.example.project.controller;

import com.example.project.SessionConst;
import com.example.project.domain.User;
import com.example.project.dto.LoginDto;
import com.example.project.dto.UserDto;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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
    public String create(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
//        특정 필드가 아닌 복합 룰 검증

//        중복 회원검증
        User findOne = userService.findByLoginId(userDto.getLoginId());
        if (findOne != null) {
            log.error("아이디 중복");
            bindingResult.reject("user", "아이디 중복입니다");
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

    @GetMapping("/users")
    public String getUsers(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                           Model model) {
        List<User> users = userService.getAll();
        List<UserDto> userDtos = users.stream().
                map(mapUser -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(mapUser.getId());
                    userDto.setLoginId((mapUser.getLoginId()));
                    userDto.setLoginPw(mapUser.getLoginPw());
                    userDto.setNickName(mapUser.getNickName());
                    return userDto;
                }).collect(Collectors.toList());

        model.addAttribute("userDtos", userDtos);

        UserDto userDto = user.toUserDto(user);
        model.addAttribute("userDto", userDto);
        return "user/users";

    }

    //로그인
    @PostMapping("/login")
    public String Login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
                        @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL, HttpServletRequest request) {


        //ID, PW 검증
        //ID Null 아닌지


        User loginUser = userService.findByLoginId(loginDto.getLoginId());
        if (loginUser == null) {
            bindingResult.reject("id", "아이디가 없습니다");
            return "loginForm";
        }


        //비번 검증
        if (!loginUser.getLoginPw().equals(loginDto.getLoginPw())) {
            bindingResult.reject("id", "비번이 틀렸습니다");
            return "loginForm";
        }

        if (bindingResult.hasErrors()) {
            log.error("err {}", bindingResult);
            return "loginForm";
        }

        log.info("redirectURL {}", redirectURL);
        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

        return "redirect:" + redirectURL;

    }

    @GetMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    //    로그인
    @GetMapping("/")
    public String loginForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {

        model.addAttribute("loginDto", new LoginDto());

        if (user == null) {
            return "loginForm";
        }

        UserDto userDto = user.toUserDto(user);
        model.addAttribute("userDto", userDto);
        return "main";
    }


    @GetMapping("/users/edit")
    public String editUserForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {
        UserDto userDto = user.toUserDto(user);
        model.addAttribute("userDto", userDto);

        return "user/userEditForm";
    }

    @PostMapping("/users/edit")
    public String editUser(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                           @ModelAttribute UserDto userDto) {


        userService.update(userDto, user.getId());

        return "redirect:/";

    }

    @GetMapping("/users/delete/{userId}")
    public String deleteUser(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                             @PathVariable("userId") Long id, Model model) {
        log.error("error");
        userService.delete(id);

        UserDto userDto = user.toUserDto(user);
        model.addAttribute("userDto", userDto);
        return "redirect:/users";


    }
}

