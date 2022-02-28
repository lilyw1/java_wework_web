package com.ceshiren.hogwarts.wework.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:企业微信界面的登录
 * @author wyl
 * @create 2022-02-20 16:22
 */
public class WeworkPage {

    ChromeDriver driver;
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    WebDriverWait wait;

    public ContactPage login() throws IOException {
        driver = new ChromeDriver();
        //隐式等待，页面加载出来
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //显示等待
        wait = new WebDriverWait(driver,20,2);
        //wait = new WebDriverWait(driver, Duration.ofSeconds(20)),Duration.ofSeconds(2));
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<>() {};
        List<HashMap<String,Object>> cookies = mapper.readValue(new File("cookies.yaml"),typeReference);
        cookies.stream()
                //过滤出域名为work.weixin.qq.com的
                //过滤企业微信的cookie
                .filter(cookie ->cookie.get("domain").toString().contains("work.weixin.qq.com"))
                .forEach(cookie ->{
                    //写cookie到浏览器
                    driver.manage().addCookie(new Cookie(cookie.get("name").toString(),cookie.get("value").toString()));
                });
        //刷新的时候，浏览器会把新的cookie带到服务器，服务器返回登录后的页面
        driver.navigate().refresh();
        //cookie过期，直接抛出异常即可。只能通过手工重新登录，获取新cookie。
        return null;
    }

    public void close(){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.quit();
    }

}
