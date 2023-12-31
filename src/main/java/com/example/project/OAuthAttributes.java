//package com.example.project;
//
//import com.example.project.domain.Role;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Map;
//
//@Getter
//@Builder
//@RequiredArgsConstructor
//public class OAuthAttributes {
//    private final Map<String, Object> attributes;
//    private final String nameAttributeKey;
//    private final String name;
//    private final String email;
//    private final String picture;
//    private final String gender;
//    private final String age;
//
//    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) {
//        if("naver".equals(registrationId)) {
//            return ofNaver("id", attributes);
//        }
//        else {
//            return ofKakao("id", attributes);
//        }
//    }
//
//
//    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        return OAuthAttributes.builder()
//                .name((String) response.get("nickname"))
//                .email((String) response.get("email"))
//                .picture((String) response.get("profile_image"))
//                .gender((String) response.get("gender"))
//                .age((String) response.get("age"))
//                .attributes(response)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity() {
//        return User.builder()
//                .name(name)
//                .email(email)
//                .picture(picture)
//                .gender(gender)
//                .age(age)
//                .role(Role.GUEST)
//                .build();
//    }
//}
