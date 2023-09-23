package com.alibaba.nacos.tapestry.integration.pages;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class NavigationTest extends SeleniumTestCase {
    @Test
    void navigationIndex() {
        navigation("Index");
    }
    @Test
    void navigationAbout() {
        navigation("About");
    }
    @Test
    void navigationLogin() {
        navigation("Login");
    }
    @Test
    void navigationError404() {
        navigation("Error404");
    }
    private void navigation(String page) {
        // given
        open(String.format("/%s", page.toLowerCase().trim()));
        waitForPageToLoad();
        // then
        assertTrue(getTitle().startsWith(page));
    }
}
