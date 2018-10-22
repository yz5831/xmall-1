package com.sillybin.xmall.transport;

import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;

public interface UserTransport {
	public User getUserByLoginName(String loginName) throws Exception;
	public XmallPage<User> getUserListByPage(XmallPage<User> xmallPage, String username) throws Exception;
	public User getUserByUserId(Long userId) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public User getLastUser() throws Exception;
	public boolean saveUser(User user) throws Exception;
}
