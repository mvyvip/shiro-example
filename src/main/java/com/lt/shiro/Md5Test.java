package com.lt.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author: litao
 * @Copyright: 成都国盛天丰网络科技有限公司
 * @Date: 2016/11/25 0025 16:44
 * @Version: V1.0
 */
public class MD5Test {
    public static void main(String[] args) {
        String source = "123"; // 原始数据
        String salt = ""; // 加盐数据
        int hashIterations = 1; // 散列次数
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

        System.out.println("md5Hash = " + md5Hash);

        //第一个参数：散列算法
        SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
        System.out.println("simpleHash = " + simpleHash);
    }
}
