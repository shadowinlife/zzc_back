package com.se.back.common.holder;

import com.se.back.data.enums.UserType;
import lombok.Data;

/**
 * @author mgong
 */
@Data
public class UserInfo {
    private final String userIdentify;
    private final UserType userType;
    private final String cookie;
}
