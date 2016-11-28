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
        String password = "123";
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

}
