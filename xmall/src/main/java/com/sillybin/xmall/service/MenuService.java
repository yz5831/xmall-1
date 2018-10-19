package com.sillybin.xmall.service;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Menu;
import com.sillybin.xmall.pojo.entity.Role;

public interface MenuService {
	public List<Menu> getMenuListForIndex(Role role) throws Exception;
}
