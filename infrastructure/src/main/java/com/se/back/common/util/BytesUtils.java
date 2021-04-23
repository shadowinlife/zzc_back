package com.se.back.common.util;


import cn.hutool.crypto.SecureUtil;
import com.se.back.BaseConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author mgong
 */
@Component
public class BytesUtils {
    final BaseConfigurer baseConfigurer;

    public BytesUtils(BaseConfigurer baseConfigurer) {
        this.baseConfigurer = baseConfigurer;
    }

    public String hashString(String input) {
        return SecureUtil.md5(input + baseConfigurer.getSalt());
    }
}
