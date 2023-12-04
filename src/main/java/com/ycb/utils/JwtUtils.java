package com.ycb.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ycb.constant.RedisConstant;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * jwt工具类
 */
@Component
public class JwtUtils {
    @Value("${spring.security.jwt.secret}")
    private String secret;
    @Value("${spring.security.jwt.expireTime}")
    private int expireTime;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建jwt
     *
     * @param userId 用户id
     * @param user   用户信息
     * @return jwt
     */
    public String createJwt(int userId, UserDetails user) {
        return JWT.create()
                .withJWTId(String.valueOf(UUID.randomUUID()))
                .withClaim("userId", userId)
                .withClaim("username", user.getUsername())
                // 权限
                .withClaim("authorities", user.getAuthorities().toString())
                // 过期时间
                .withExpiresAt(this.getExpireTime())
                // 密钥
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 根据配置计算过期时间
     *
     * @return 过期时间
     */
    public Date getExpireTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expireTime);
        return calendar.getTime();
    }

    /**
     * 解析jwt
     *
     * @param header 请求头
     * @return jwt
     */
    public DecodedJWT parseJwt(String header) {
        String token = subHeader(header);
        if (token == null) {
            return null;
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            // 在黑名单
            if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(RedisConstant.LOGOUT_JWT_BLACK_LIST + jwt.getId()))) {
                return null;
            }
            // 已过期
            Date expiresAt = jwt.getExpiresAt();
            if (expiresAt.before(new Date())) {
                return null;
            }
            return jwt;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * jwt转换成用户信息
     *
     * @param jwt jwt
     * @return 用户信息
     */
    public UserDetails jwtToUser(DecodedJWT jwt) {
        // 创建User
        Map<String, Claim> claims = jwt.getClaims();
        return User.withUsername(String.valueOf(claims.get("username")))
                .password("???")
                .authorities(claims.get("authorities").asString())
                .build();
    }

    /**
     * jwt写入黑名单
     *
     * @param jwt jwt
     * @return 错误信息
     */
    public String jwtJoinBlackList(DecodedJWT jwt) {
        if (jwt == null) {
            return "系统异常，请稍后重试";
        }
        String key = RedisConstant.LOGOUT_JWT_BLACK_LIST + jwt.getId();
        // 已经在黑名单
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return "操作频繁，请稍后重试";
        }
        // 设置过期时间
        long timeOut = Math.max(new Date(jwt.getExpiresAt().getTime() - System.currentTimeMillis()).getTime(), 0);
        stringRedisTemplate.opsForValue().set(key, "", timeOut, TimeUnit.MILLISECONDS);
        return null;
    }


    /**
     * 获取请求头中的token
     *
     * @param header 请求头
     * @return token
     */
    private String subHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
    }
}
