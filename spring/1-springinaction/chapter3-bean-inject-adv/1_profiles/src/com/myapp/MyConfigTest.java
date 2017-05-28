package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lyzzzz on 2016-11-12.
 */

public class MyConfigTest {
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = MyConfig.class)
    @ActiveProfiles("dev")
    public static class DevMyConfigTest {
        @Autowired
        private TestBean testBean;

        @Test
        public void test() {
            System.out.println(testBean.getStr());
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes =  MyConfig.class)
    @ActiveProfiles("prod")
    public static class ProdMyConfigTest {
        @Autowired
        private TestBean testBean;

        @Test
        public void test() {
            System.out.println(testBean.getStr());
        }
    }
}
