package com.example.project.controller;

import com.example.project.domain.Friend;
import com.example.project.domain.User;
import com.example.project.service.FriendService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.project.SessionConst.LOGIN_MEMBER;

@SpringBootTest
@Transactional
class UserControllerTest {

    HttpServletRequest request = new MockHttpServletRequest();
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    public User login() {
        //로그인
        User user = userService.findById(1L);
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(LOGIN_MEMBER, user);

        User loginUser = (User) session.getAttribute(LOGIN_MEMBER);
        return loginUser;

    }


    @Test
    void saveFriend() {
        User user = login();
        Friend friend = new Friend(null, "sdf", "nick", user);
        friendService.save(user, friend);
        System.out.println("friend = " + friend);
    }

    @Test
    void findFriend() {
        User user = login();

        Friend friend = new Friend(null, "sdf", "nick", user);
        friendService.save(user, friend);

        Friend findFriend = friendService.findById(1L);
        System.out.println("findFriend = " + findFriend);

    }

    @Test
    void findFriends() {
        User user = login();

        Friend friend1 = new Friend(null, "sdf", "nick", user);
        Friend friend2 = new Friend(null, "hi", "nick2", user);
        Friend friend3 = new Friend(null, "sdff", "nick3", user);

        friendService.save(user, friend1);
        friendService.save(user, friend2);
        friendService.save(user, friend3);


        List<Friend> friends = user.getFriends();
        System.out.println("friends = " + friends);

    }

    @Test
    void deleteFriend() {
        User user = login();

        Friend friend = new Friend(null, "sdf", "nick", user);
        friendService.save(user, friend);

        Friend findFriend = friendService.findById(1L);

        boolean contains = user.getFriends().contains(findFriend);
        System.out.println("contains1 = " + contains);

        friendService.deleteById(user, 1L);
        boolean contains2 = user.getFriends().contains(findFriend);
        System.out.println("contains2 = " + contains2);

    }
}