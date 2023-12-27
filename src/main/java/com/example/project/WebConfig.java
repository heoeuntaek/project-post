package com.example.project;


import com.example.project.intercepter.LogInterceptor;
import com.example.project.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/logout",
                        "/css/**", "/*.ico", "/error", "/js/**", "/users/new");
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/profile").permitAll()
//                .requestMatchers("/api/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated().and()
//                .logout().logoutSuccessUrl("/").and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(userService);
//
//        return http.build();
//    }


}
