package com.marketplace.marketplace.Member;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session;

    public MemberController(MemberService memberService, HttpSession session) {
        this.memberService = memberService;
        this.session = session;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberEntity member) {

        Map<String, Object> map = memberService.isMember(member);

        if (map.get("Status").equals(HttpStatus.OK)) {
            UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid")))
                    .orElse(UUID.randomUUID());
            session.setAttribute("uid", uid);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
