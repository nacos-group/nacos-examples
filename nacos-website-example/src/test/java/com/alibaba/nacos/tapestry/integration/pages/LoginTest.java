package com.alibaba.nacos.tapestry.integration.pages;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class LoginTest extends SeleniumTestCase {

    @Test
    void loginSuccess() {
        // given
        open("/login");
        assertTrue(getTitle().startsWith("Login"));
        // when
        type("//input[@name='email']", "users@tapestry.apache.org");
        type("//input[@name='password']", "Tapestry5");
        submit("//form[@id='login']");
        // then
        waitForPageToLoad();
        assertTrue(getTitle().startsWith("Index"));
    }

    @Test
    void loginError() {
        // given
        open("/login");
        assertTrue(getTitle().startsWith("Login"));
        // when
        type("//input[@name='email']", "xxx1");
        type("//input[@name='password']", "xxx2");
        submit("//form[@id='login']");
        // then
        waitForPageToLoad();
        assertTrue(getTitle().startsWith("Login"));
    }
}

