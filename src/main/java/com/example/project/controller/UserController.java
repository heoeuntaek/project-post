package com.example.project.controller;

import com.example.project.SessionConst;
import com.example.project.domain.Friend;
import com.example.project.domain.User;
import com.example.project.dto.FriendDto;
import com.example.project.dto.LoginDto;
import com.example.project.dto.UserDto;
import com.example.project.service.FriendService;
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
    private final FriendService friendService;


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


        User user = new User(null, userDto.getLoginId(), userDto.getLoginPw(),
                userDto.getNickName(), null, null, null);

        userService.save(user);
        log.info("user join success {}", userDto);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserDto userDto = user.toUserDto();

        return userDto;
    }

    @ResponseBody
    @GetMapping("/users/loginId/{loginId}")
    public UserDto findByLoginId(@PathVariable("loginId") String loginId) {
        User user = userService.findByLoginId(loginId);
        UserDto userDto = user.toUserDto();
        return userDto;
    }

    @GetMapping("/users")
    public String getUsers(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                           Model model) {
        List<User> users = userService.findAll();
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

        UserDto userDto = user.toUserDto();
        model.addAttribute("userDto", userDto);
        return "user/users";

    }

    //로그인
    @PostMapping("/login")
    public String Login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
                        @RequestParam(name = "redirectURL", defaultValue = "") String redirectURL, HttpServletRequest request) {


        //ID, PW 검증
        //ID Null 아닌지

        log.error("loginDto {}", loginDto);

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

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

        log.error("redirectURL ={}", redirectURL);
        return "redirect:/" + redirectURL;
//        return "redirect:" + redirectURL;

//        redirect:/posts

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

        User findUser = userService.findById(user.getId());
        UserDto userDto = findUser.toUserDto();
        model.addAttribute("userDto", userDto);
        return "main";
    }


    @GetMapping("/users/edit")
    public String editUserForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user, Model model) {
        UserDto userDto = user.toUserDto();
        model.addAttribute("userDto", userDto);

        return "user/userEditForm";
    }

    //회원수정
    @PostMapping("/users/edit")
    public String editUser(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                           @ModelAttribute UserDto userDto) {

        userDto.setId(user.getId());
        userService.update(userDto);

        return "redirect:/";

    }

    @GetMapping("/users/delete/{userId}")
    public String deleteUser(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                             @PathVariable("userId") Long id, Model model) {
        userService.deleteById(id);

        UserDto userDto = user.toUserDto();
        model.addAttribute("userDto", userDto);
        return "redirect:/users";


    }

    //친구추가 폼
    @GetMapping("/friends/form")
    public String saveFriend(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                             Model model) {
        model.addAttribute("friendDto", new FriendDto());
        return "friend/friendForm";
    }

    //친구추가
    @PostMapping("/friends")
    public String saveFriend(@Validated @ModelAttribute FriendDto friendDto, BindingResult bindingResult,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user) {

        Friend friend = friendDto.toEntity();
        User friendUser = userService.findByLoginId(friend.getLoginId());
        if (friendUser == null) {
            bindingResult.reject("id", "친구 아이디가 조회되지 않습니다");
        }

        User findUser = userService.findById(user.getId());
        if (friendUser == findUser) {
            bindingResult.reject("id", "자기 자신은 영원한 친구입니다");
        }

        if (bindingResult.hasErrors()) {
            log.error("err {}", bindingResult);
            return "friend/friendForm";
        }

        friendService.save(user, friend);
        return "redirect:/friends";
    }

    //친구 조회
    @GetMapping("/friends/{friendId}")
    public String findFriend(@PathVariable("friendId") Long friendId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                             Model model) {
        Friend friend = friendService.findById(friendId);

        model.addAttribute("friend", friend);

        return "findFriend";
    }

    //친구들 조회
    @GetMapping("/friends")
    public String findFriends(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                              Model model) {

        User findUser = userService.findById(user.getId());
        List<Friend> friends = findUser.getFriends();
        model.addAttribute("friends", friends);

        UserDto userDto = findUser.toUserDto();
        model.addAttribute("userDto", userDto);

        return "friend/friends";
    }

    //친구 삭제

    @GetMapping("/friends/delete/{friendId}")
    public String deleteFriend(@PathVariable("friendId") Long friendId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User user,
                               Model model) {
        friendService.deleteById(user, friendId);


        return "redirect:/friends";
    }
}

