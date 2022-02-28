package com.ceshi.wework.web.exercise;

import com.ceshiren.hogwarts.wework.web.ContactPage;
import com.ceshiren.hogwarts.wework.web.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * description:添加用户，并断言;(删除用户，关闭浏览器）
 * @author wyl
 * @create 2022-02-20 15:31
 */
class ContactWebPageTest {
    private final ContactPage contact = new ContactPage();

    @Test
    //链式调用
    void addMemberLink() throws IOException {

        //链式调用,获取资料页面的用户信息
        User user =contact.login()
                .open()
                .addMember("202202201532","202202201532","18069866718")
                .searchMember("202202201532")
                .get();

        //对添加的用户信息进行断言
        assertEquals(user.getUsername(),"202202201532");
        assertEquals(user.getMobile(),"18069866718");
        assertThat(user.getMobile(),containsString("1806"));
        //profile.deleteCurrentMember();
        //contact.close();
    }

    @Test
    void deleteMember() {
    }

    @Test
    void getMember() {
    }

    @Test
    void updateMember() {
    }
}