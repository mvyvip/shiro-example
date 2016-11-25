package com.lt.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: litao
 * @Copyright: 成都国盛天丰网络科技有限公司
 * @Date: 2016/11/25 0025 16:07
 * @Version: V1.0
 */
public class CustomRealm extends AuthorizingRealm {
    // 用于授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 用于认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();

        // 根据用户名去数据库查询，如果没有该用户返回null
        // 比如数据库只有admin
        if(!"admin".equals(username)) {
            return null; // 会抛出异常：UnknownAccountException
        }

        // 模拟数据库查出来的密码是 123
        // 密码认证失败抛出：IncorrectCredentialsException
        String passwrod = "123";

        // 认证成功返回认证信息
        return new SimpleAuthenticationInfo(username, passwrod, super.getName());
    }
}
