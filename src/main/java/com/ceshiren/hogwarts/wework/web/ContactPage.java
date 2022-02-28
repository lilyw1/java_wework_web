package com.ceshiren.hogwarts.wework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

/**
 * @author wyl
 * @create 2022-02-20 15:24
 */
public class ContactPage extends WeworkPage{
    //构造器
    public ContactPage(){

    }
    //点击通讯录
    //重写下login,这样可以return ContactPage
    @Override
    public ContactPage login() throws IOException {
        super.login();
        return this;
    }

    public ContactPage open(){
        driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
        return this;
    }
    //添加用户的方法
    public ContactPage addMember(String acctid, String name, String mobile){
        //显示等待，添加成员按钮出现
        By addMemberButton = By.linkText("添加成员");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addMemberButton));
        //点击添加成员按钮
        driver.findElement(addMemberButton).click();
        driver.findElement(By.id("username")).sendKeys(name);//姓名
        driver.findElement(By.name("acctid")).sendKeys(acctid);//账号
        driver.findElement(By.name("biz_mail")).sendKeys(String.valueOf(System.currentTimeMillis()));//放一个当前的时间戳当邮箱避免重复
        driver.findElement(By.name("mobile")).sendKeys(mobile);//电话
        //输入上述信息后点击保存
        driver.findElement(By.linkText("保存")).click();
        driver.navigate().refresh();
        return this;
    }

    public ProfilePage searchMember(String acctid){
        //搜索按钮
        By memberSearchInput = By.id("memberSearchInput");
        driver.findElement(memberSearchInput).sendKeys(acctid);
        //搜索完成后，返回新的资料界面
        //多个PO之间联动
        return new ProfilePage(driver);
    }
    public void updateMember(String account, String name,String phone){
        //todo

    }
}
