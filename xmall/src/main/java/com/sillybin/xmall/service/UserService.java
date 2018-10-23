package com.sillybin.xmall.service;

import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;

public interface UserService {
	public User getUserByLoginName(String loginName) throws Exception;
	public XmallPage<User> getUserListByPage(XmallPage<User> xmallPage, String username) throws Exception;
	public User getUserByUserId(Long userId) throws Exception;
	public User getLastUser() throws Exception;
	
	/**
	 * 保存/更新用户信息
	 * @param user 带保存/更新用户对象
	 * @return boolean true-保存或者更新用户成功，false-保存或者更新用户失败
	 * @throws Exception
	 */
	public boolean saveOrUpdateUser(User user) throws Exception;
}
