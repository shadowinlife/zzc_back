package com.se.back.test;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;

/**
 * @author: 信长华
 * @date: 2021/4/23 11:40
 * @className: hutoolsTest
 * @description: TODO
 * @version: 1.0
 */
public class hutoolTest {
    @Test
    public void md5Test(){
        String s = SecureUtil.md5("中招测试数据1-生产数据");
        System.out.println("s = " + s);
    }
}
