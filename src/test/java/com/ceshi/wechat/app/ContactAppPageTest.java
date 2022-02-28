package com.ceshi.wechat.app;

import com.ceshiren.hogwarts.wework.app.UserProfile;
import com.ceshiren.hogwarts.wework.app.WeWorkAppPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author wyl
 * @create 2022-02-27 14:52
 */
public class ContactAppPageTest {
    static WeWorkAppPage wework;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        //todo:数据清理，环境还原
        //保证数据的唯一性
        //UI自动化还原
        //接口自动化还原
        //数据化还原
       wework = new WeWorkAppPage();
    }
    @AfterAll
    static void afterAll(){
        //如果进程被非正常终止，afterall可能得不到正常执行
        //wework.close();
    }
    @Test
    void addMember() throws MalformedURLException {
        String name = "ckjava22_" + generateMobile();
        String mobile = generateMobile();

        UserProfile user1 = new UserProfile();
        user1.setMobile(mobile);
        user1.setName(name);

        UserProfile profile = wework.toContact()
                .addMember(user1)
                .searchMember(name)
                .getProfile();
        assertThat(profile.getName(),equalTo(user1.getName()));
        assertThat(profile.getMail(),containsString(user1.getName()+"@"));
    }
    String generateMobile(){
        return "156" + String.valueOf(System.currentTimeMillis()).substring(5);
    }

}
