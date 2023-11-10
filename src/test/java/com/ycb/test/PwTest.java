package com.ycb.test;

import com.ycb.mapper.FileMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

@SpringBootTest
public class PwTest {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    FileMapper fileMapper;

    @Test
    void testEncoder() {
        System.out.println(bCryptPasswordEncoder.encode("1111"));
    }

    @Test
    void downloadFile() {

    }
}

