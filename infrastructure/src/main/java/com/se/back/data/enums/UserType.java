package com.se.back.data.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mgong
 */

public enum UserType {
    /** 合作方 */
    PARTNER("partner".toUpperCase()),
    /** vip user */
    VIP("vip".toUpperCase()),
    /** login user */
    SEED("seed".toUpperCase()),
    /** ano */
    ANONYMOUS("anonymous".toUpperCase());

    private static final Map<String, UserType> BY_VALUE = new HashMap<>();

    static {
        for (UserType e : values()) {
            BY_VALUE.put(e.getUserType(), e);
        }
    }

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public static UserType valueOfType(String label) {
        return BY_VALUE.getOrDefault(label.toUpperCase(), null);
    }

    public String getUserType() {
        return userType;
    }
}
