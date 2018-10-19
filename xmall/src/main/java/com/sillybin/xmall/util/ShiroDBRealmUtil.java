package com.sillybin.xmall.util;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.transport.UserTransport;

public class ShiroDBRealmUtil extends AuthorizingRealm {
	@Resource(name="userTransport")
	private UserTransport userTransport;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String loginName = userToken.getUsername();
		char[] passwords = userToken.getPassword();
		if (loginName != null && !"".equals(loginName.trim()) && passwords != null && passwords.length > 0) {
			// 对于登录密码进行加密，并且重新设定会userToken中
			userToken.setPassword(EncryptionUtil.encrypt(new String(passwords)).toCharArray());
			// 使用loginName查找用户对象
			try {
				User user = userTransport.getUserByLoginName(loginName);
				if (user != null) {
					if (ConstantUtil.STATUS_ENABLE.equals(user.getStatus().getStatusCode())) {
						SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
								user.getLoginName(), user.getPassword(), getName());
						SecurityUtils.getSubject().getSession().setAttribute("user", user);
						return info;
					} else {
						throw new AuthenticationException("该用户禁止登陆，请联系管理员");
					}
				} else {
					throw new AuthenticationException("该用户未注册，请联系管理员");
				}
			} catch (Exception e) {
				throw new AuthenticationException("系统错误，请联系管理员");
			}
		} else {
			throw new AuthenticationException("请填写用户名和密码");
		}
	}
}
