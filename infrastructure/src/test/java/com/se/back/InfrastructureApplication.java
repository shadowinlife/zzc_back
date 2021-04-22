package com.se.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 信长华
 * @date: 2021/4/22 20:14
 * @className: InfrastructureApplication
 * @description: TODO
 * @version: 1.0
 */


/**
 * 这个启动类是用来做测试的
 * 必须放在测试目录下，不然会和其他模块冲突
 * 一山不容二 @SpringBootApplication
 * https://stackoverflow.com/questions/37615914
 * 参考 https://github.com/fish-springboot/SpringBootModules
 */
@SpringBootApplication
public class InfrastructureApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApplication.class, args);
    }
}
