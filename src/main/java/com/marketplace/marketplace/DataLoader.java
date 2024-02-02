package com.marketplace.marketplace;

import org.springframework.boot.CommandLineRunner;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.marketplace.marketplace.Member.MemberEntity;
import com.marketplace.marketplace.Member.MemberRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public DataLoader(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @SuppressWarnings("null")
    public void run(String... args) throws Exception {
        MemberEntity member = MemberEntity.builder()
                .username("test1")
                .password(passwordEncoder.encode("1234a"))
                .build();
        memberRepository.save(member);
    }
}
