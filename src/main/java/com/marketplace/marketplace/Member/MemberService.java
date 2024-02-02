package com.marketplace.marketplace.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> isMember(@RequestBody MemberEntity member) {
        Optional<MemberEntity> opt = memberRepository.findByUsername(member.getUsername());
        Map<String, Object> map = new HashMap<>();
        if (opt.isPresent()) {
            if (!passwordEncoder.matches(member.getPassword(), opt.get().getPassword())) {
                map.put("Status", HttpStatus.BAD_REQUEST);
                map.put("Message", "비밀번호가 일치하지 않습니다.");
            } else {
                map.put("Status", HttpStatus.OK);
                map.put("Message", "로그인 성공");
            }
        } else {
            map.put("Status", HttpStatus.BAD_REQUEST);
            map.put("Message", "일치하는 유저 정보가 없습니다.");

        }
        return map;
    }
}
