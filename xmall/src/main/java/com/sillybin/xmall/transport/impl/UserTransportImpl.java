package com.sillybin.xmall.transport.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.service.UserService;
import com.sillybin.xmall.transport.UserTransport;

@Component("userTransport")
public class UserTransportImpl implements UserTransport {
	@Resource(name="userService")
	private UserService userService;

	public User getUserByLoginName(String loginName) throws Exception {
		return userService.getUserByLoginName(loginName);
	}

	public XmallPage<User> getUserListByPage(XmallPage<User> xmallPage, String username) throws Exception {
		return userService.getUserListByPage(xmallPage, username);
	}

	public User getUserByUserId(Long userId) throws Exception {
		return userService.getUserByUserId(userId);
	}

	public User getMaxUser() throws Exception {
		return userService.getLastUser();
	}

	/**
	 * 保存/更新用户信息
	 * @param user 带保存/更新用户对象
	 * @return boolean true-保存或者更新用户成功，false-保存或者更新用户失败
	 * @throws Exception
	 */
	public boolean saveOrUpdateUser(User user) throws Exception {
		return userService.saveOrUpdateUser(user);
	}
}
