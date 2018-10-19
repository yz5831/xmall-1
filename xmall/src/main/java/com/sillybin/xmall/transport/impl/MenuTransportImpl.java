package com.sillybin.xmall.transport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.Menu;
import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.service.MenuService;
import com.sillybin.xmall.transport.MenuTransport;
@Component("menuTransport")
public class MenuTransportImpl implements MenuTransport {
	@Resource(name="menuService")
	private MenuService menuService;

	public List<Menu> getMenuListForIndex(Role role) throws Exception {
		return menuService.getMenuListForIndex(role);
	}
}
