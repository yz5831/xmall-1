package com.sillybin.xmall.transport;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Menu;
import com.sillybin.xmall.pojo.entity.Role;

public interface MenuTransport {
	public List<Menu> getMenuListForIndex(Role role) throws Exception;
}
