package com.ycb.test;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PwTest {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testEncoder() {
        System.out.println(bCryptPasswordEncoder.encode("1111"));
    }

    @Test
    void testUuid() {
    }
}
