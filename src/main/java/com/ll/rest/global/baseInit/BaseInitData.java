package com.ll.rest.global.baseInit;

import com.ll.rest.domain.post.member.entity.Member;
import com.ll.rest.domain.post.member.service.MemberService;
import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final MemberService memberService;
    private final PostService postService;

    @Autowired
    @Lazy
    private BaseInitData self;

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        if (memberService.count() > 0) return;

        Member memberSystem = memberService.join("system","1234", "시스템");
        Member memberAdmin = memberService.join("admin","1234", "관리자");
        Member memberUser1 = memberService.join("userId1","1234", "유저1");
        Member memberUser2 = memberService.join("userId2","1234", "유저2");
        Member memberUser3 = memberService.join("userId3","1234", "유저3");

    }

    @Transactional
    public void work2() {
        if (postService.count() > 0) return;

        Post post1 = postService.write("글1", "내용1");
        Post post2 = postService.write("글2", "내용2");
        Post post3 = postService.write("글3", "내용3");
    }
}