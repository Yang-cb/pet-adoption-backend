package com.ycb.test;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class PwTest {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testEncoder() {
        System.out.println(bCryptPasswordEncoder.encode("aaaaaa"));
    }

    @Test
    void testSet() {
        Set<String> roleSet = new HashSet<>();
        roleSet.add("ADMIN");

        if (roleSet.contains("ADMIN")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}

