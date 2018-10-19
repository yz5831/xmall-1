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

	public boolean updateUser(User user) throws Exception {
		return userService.updateUser(user);
	}
}
