package cn.hbh;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {
    // 返回一个唯一的Realm名字
    @Override
    public String getName() {
        return "myrealm1";
    }
    // 判断此Realm是否支持此Token
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }
    // 根据Token获取此认证信息
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 获取密码
        String password= new String ((char[]) authenticationToken.getCredentials());
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();// 用户名错误
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();// 密码错误
        }
        // 如果身份验证认证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}