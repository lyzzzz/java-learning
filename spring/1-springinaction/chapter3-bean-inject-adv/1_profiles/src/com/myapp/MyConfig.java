package com.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by lyzzzz on 2016-11-12.
 */
@Configuration
public class MyConfig {
    @Bean
    @Profile("dev")
    public TestBean dev() {
        return new TestBean("devvvvv");
    }

    @Bean
    @Profile("prod")
    public TestBean prod() {
        return new TestBean("proddddd");
    }
}
