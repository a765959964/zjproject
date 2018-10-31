package com.example.demo.core.shiro;


import com.example.demo.model.SysUser;
import com.example.demo.service.SysMenuService;
import com.example.demo.service.SysUserRoleService;
import com.example.demo.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 登录认证实现
 * 自定义如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class CustomRealm extends AuthorizingRealm {


    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
     */
    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("md5");
        hashMatcher.setStoredCredentialsHexEncoded(true);
        hashMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(hashMatcher);
    }


    /**
     *   链接权限的实现
     *  定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method 参数不能为空.");
        }
        SysUser user = (SysUser) getAvailablePrincipal(principals);

//        UserInfo user = (UserInfo) getAvailablePrincipal(principals);
        System.out.println("获取角色信息："+user.getRoleIds());
        System.out.println("获取部门信息："+user.getMenuIds());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoleIds());
        info.setStringPermissions(user.getMenuIds());
        return info;
    }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("--用户名不能为空---");
        }
        SysUser sysUser  = sysUserService.selectBy("name",username);
        if(sysUser == null){
            throw new UnknownAccountException("没有找到此账户 [" + username + "]");
        }
        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        List<String> roleIds = sysUserRoleService.getRolesByUserId(sysUser.getId().toString());
        List<String> menuIds = sysMenuService.getMenusByUserId(sysUser.getId().toString());
        Set<String> roles = new HashSet(roleIds);
        Set<String> menus = new HashSet(menuIds);

        sysUser.setRoleIds(roles);
        sysUser.setMenuIds(menus);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,sysUser.getPwd(),getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));
        return info;
    }

}
