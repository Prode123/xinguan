package com.example.humanresources.config;

import com.example.humanresources.dao.TbRoleDao;
import com.example.humanresources.dao.TbUserDao;
import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbRole;
import com.example.humanresources.entity.TbUser;
import com.example.humanresources.service.TbUserService;
import com.example.humanresources.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MyRealm extends AuthorizingRealm {
    @Resource
    private TbUserDao tbUserDao;

    @Resource
    private TbRoleDao tbRoleDao;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户名
        String username = (String)principalCollection.getPrimaryPrincipal();
        //根据用户名查角色和权限
        List<String> roles = null;
        //找到roleId
        List<Integer> roleIdByUsername = tbUserDao.getRoleIdByUsername(username);
        for (Integer integer : roleIdByUsername) {
            TbRole tbRole = tbRoleDao.selectById(integer.longValue());
            roles.add(tbRole.getRoleName());
        }
        //根据用户名查角色和权限
        List<String> permissions = null;
        List<TbMenu> menuByRoleId = tbRoleDao.findMenuByRoleId(roleIdByUsername);
        for (TbMenu tbMenu : menuByRoleId) {
            permissions.add(tbMenu.getPerms());
        }
        //返回当前用户的所有角色权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取当前用户的用户名
        String username = (String) authenticationToken.getPrincipal();
        //去数据库查询该用户
        TbUser user = tbUserDao.getUserByUsername(username);


        if (user == null) {
            return null;
        }

        //参数1:返回数据库中正确的用户名
        //参数2:返回数据库中正确密码
        //参数3:提供当前realm的名字 this.getName();
        return new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());
    }

}
