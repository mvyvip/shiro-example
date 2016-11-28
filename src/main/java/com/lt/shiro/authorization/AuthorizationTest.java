package com.lt.shiro.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: litao
 * @Copyright: 成都国盛天丰网络科技有限公司
 * @Date: 2016/11/28 0028 10:28
 * @Version: V1.0
 */
public class AuthorizationTest {

    /**
     * 授权测试
     */
    @Test
    public void testAuthorization() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authorization.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登录状态：" + subject.isAuthenticated());

        // 授权测试
        System.out.println("是否有单个角色：" + subject.hasRole("role1"));
        System.out.println("是否有多个角色：" + subject.hasAllRoles(Arrays.asList("role1", "role2")));
        System.out.println("是否有单个资源：" + subject.isPermitted("user:create"));
        System.out.println("是否有多个资源：" + subject.isPermittedAll("user:create,user:delete"));

        // 对应的check方法 subject.checkXxx
    }

    /**
     * 自定义real 授权测试
     */
    @Test
    public void testAuthorizationCustomRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authorization-real.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登录状态：" + subject.isAuthenticated());

        // 授权测试
        System.out.println("是否有单个角色：" + subject.hasRole("role1"));
        System.out.println("是否有多个角色：" + subject.hasAllRoles(Arrays.asList("role1", "role2")));
        System.out.println("是否有单个资源：" + subject.isPermitted("user:create"));
        System.out.println("是否有多个资源：" + subject.isPermittedAll("user:create,user:delete"));

        // 对应的check方法 subject.checkXxx 验证失败将抛出异常: UnauthorizedException
//        subject.checkPermission("user:create");
    }



}
