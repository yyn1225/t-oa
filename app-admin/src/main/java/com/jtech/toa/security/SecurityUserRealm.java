package com.jtech.toa.security;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.util.PasswordUtil;
import com.jtech.marble.util.text.EncodeUtil;
import com.jtech.toa.entity.system.Resource;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.service.system.IResourceService;
import com.jtech.toa.service.system.IRoleService;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class SecurityUserRealm extends AuthorizingRealm {


    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserRealm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IRoleService roleService;

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher(PasswordUtil.HASH_ALGORITHM);
        md5CredentialsMatcher.setHashIterations(PasswordUtil.HASH_INTERATIONS);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //从数据库中获取当前登录用户的详细信息
        Long userId = shiroUser.getId();
        // 角色获取
        List<Role> roles = roleService.findByUserId(userId.intValue());
        for (Role role : roles) {
            userRoles.add(role.getRole());
        }
        //为当前用户设置角色和权限
        List<Resource> resources = resourceService.findByLangAndUserId("zh", userId.intValue());
        for (Resource resource : resources) {
            if (resource.getCode() == null || Strings.isNullOrEmpty(resource.getCode())) {
                continue;
            }
            userPermissions.add(resource.getCode());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(userPermissions);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        Optional<User> userOpt = userService.findByUsername(token.getUsername());
        if (userOpt.isPresent()) {
            final User user = userOpt.get();
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            String credentials = user.getPassword();
            ShiroUser shiroUser = ShiroUser.builder().id(user.getId()).account(user.getLoginName())
                    .name(user.getName()).build();

            String saltSource = user.getSalt();

            byte[] salt = EncodeUtil.decodeHex(StringUtils.upperCase(saltSource));

            ByteSource credentialsSalt = ByteSource.Util.bytes(salt);

            return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, super.getName());
        }
        return null;
    }
}
