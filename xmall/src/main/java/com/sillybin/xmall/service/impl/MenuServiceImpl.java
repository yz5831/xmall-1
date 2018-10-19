package com.sillybin.xmall.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sillybin.xmall.dao.MenuDao;
import com.sillybin.xmall.pojo.entity.Menu;
import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.service.MenuService;
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
	@Resource(name="menuDao")
	private MenuDao menuDao;

	public List<Menu> getMenuListForIndex(Role role) throws Exception {
		// 根据角色获得对应的父级菜单
		List<Menu> menuList = menuDao.findFirstMenuListByRole(role.getRoleId());
		if (menuList != null && menuList.size() > 0) {
			for (Menu menu : menuList) {
				List<Menu> secondMenuList = menuDao.findSecondMenuListByRole(role.getRoleId(), menu.getMenuId());
				menu.setMenuList(secondMenuList);
			}
		}
		return menuList;
	}
}
