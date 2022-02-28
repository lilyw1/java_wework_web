package com.ceshiren.hogwarts.wework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @description:个人资料页
 * @author wyl
 * @create 2022-02-20 17:22
 */
public class ProfilePage extends WeworkPage{
    //构造器，初始化对象
    public ProfilePage(ChromeDriver driver) {
        //复用driver的本质是复用相同的浏览器自动化流程，driver包含sessionid ，不能重新初始化
        this.driver = driver;
        wait = new WebDriverWait(driver,20,2);
        //直到加载出搜索的资料页面，且可通过CSS定位到姓名
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".member_display_cover_detail_name")));
    }
    //获取资料信息
    public User get(){
        //姓名
        String name = driver.findElement(
                By.cssSelector(".member_display_cover_detail_name")).getText();
        //手机号
        String mobile = driver.findElement(
                By.cssSelector(".member_display_item_Phone .member_display_item_right")).getText();
        User user = new User();
        user.username = name;
        user.mobile = mobile;
        return user;
    }
    //页面是否更新
    public void update(){

    }
    //是否删除掉
    //删除当前添加的账号
    public void deleteCurrentMember(){
        //会出现点击不生效
        //js执行延迟，控件的状态切换1.出现在dom中，2.可看见css,3.可被点击，4，js事件绑定
        do{
            driver.findElement(By.linkText("删除")).click();
            System.out.println("click");
        }while (driver.findElements(By.linkText("确认")).size() <= 0);

        driver.findElement(By.linkText("确认")).click();
    }
}
