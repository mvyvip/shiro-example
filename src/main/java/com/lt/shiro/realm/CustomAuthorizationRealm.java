package com.lt.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: litao
 * @Copyright: 成都国盛天丰网络科技有限公司
 * @Date: 2016/11/28 0028$ {TIME}
 * @Version: V1.0
 */
public class CustomAuthorizationRealm extends AuthorizingRealm {

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();

        //根据身份信息获取权限信息
        //连接数据库...
        //模拟从数据库获取到数据
        Set<String> permissions = new HashSet<String>();
        permissions.add("user:create");//用户的创建
        permissions.add("items:add");//商品添加权限

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        //查到权限数据，返回授权信息(要包括 上边的permissions)
       return simpleAuthorizationInfo;
    }


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        // 模拟数据库查出来的密码123
        String password = "123";
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
