package com.lt.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by lt on 2016/11/25 0025.
 */
public class AuthenticationTest {

    @Test
    public void testLoginAndLogout() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authentication.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        String username = "admin";
        String password = "123";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

        System.out.println("是否通过认证：" + subject.isAuthenticated());
        subject.logout();
        System.out.println("是否通过认证：" + subject.isAuthenticated());
    }

    /**
     *  测试realm
     */
    @Test
    public void testRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        String username = "admin";
        String password = "1232";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        System.out.println("是否通过认证：" + subject.isAuthenticated());

    }

    /**
     *  测试realm
     */
    @Test
    public void testRealmMD5() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        String username = "zhangsan";
        String password = "111111";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        System.out.println("是否通过认证：" + subject.isAuthenticated());

    }

    // 自定义realm实现散列值匹配
    @Test
    public void testCustomRealmMd5() {

        // 创建securityManager工厂，通过ini配置文件创建securityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro-realm-md5.ini");

        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();

        // 将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils里边创建一个subject
        Subject subject = SecurityUtils.getSubject();

        // 在认证提交前准备token（令牌）
        // 这里的账号和密码 将来是由用户输入进去
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
                "111111");

        try {
            // 执行认证提交
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();

        System.out.println("是否认证通过：" + isAuthenticated);

    }// 自定义realm实现散列值匹配
    @Test
    public void testCustomRealmMd5_() {

        // 创建securityManager工厂，通过ini配置文件创建securityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro-realm-md5.ini");

        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();

        // 将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils里边创建一个subject
        Subject subject = SecurityUtils.getSubject();

        // 在认证提交前准备token（令牌）
        // 这里的账号和密码 将来是由用户输入进去
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
                "111111");

        try {
            // 执行认证提交
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();

        System.out.println("是否认证通过>>：" + isAuthenticated);

    }
}
