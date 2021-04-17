package com.se.back.data.repo.redis.mapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author mgong
 */
@Getter
@Setter
@RedisHash(value="user_token")
public class CookieMapper {
    @Id private String cookie;
    private String userIdentity;
    private String userType;
}
