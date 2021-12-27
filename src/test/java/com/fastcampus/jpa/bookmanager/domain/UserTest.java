package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest extends Object{
    @Test
    void test() {
        User user = new User();
        user.setEmail("0804lee@naver.com");
        user.setName("gguma");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // User user1 = new User(null, "Yuju", "i_am_your_joo@naver.com", LocalDateTime.now(), LocalDateTime.now());


        User user2 = User.builder()
                .name("gguma")
                .email("0804lee@naver.com")
                .build();

        System.out.println(">>>> " + user.toString());
    }
}