package com.marketplace.marketplace.Member;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberEntity member, HttpSession session) {
        UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid")))
                .orElse(UUID.randomUUID());
        session.setAttribute("uid", uid);
        Map<String, Object> map = new HashMap<>();
        map.put("Status", HttpStatus.OK);
        map.put("Message", "로그인 성공");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
