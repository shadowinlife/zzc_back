package com.se.back.common.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.se.back.BaseConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author mgong
 */
@Component
public class BytesUtils {
    @Autowired
    BaseConfigurer baseConfigurer;

    public String hashString(String input) {
        HashFunction hashFunction = Hashing.md5();
        HashCode hash = hashFunction.hashString(input +  baseConfigurer.getSalt(), StandardCharsets.UTF_8);
        return hash.toString();
    }
}
