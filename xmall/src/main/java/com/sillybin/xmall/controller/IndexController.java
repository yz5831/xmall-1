package com.sillybin.xmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sillybin.xmall.controller.base.BaseController;
import com.sillybin.xmall.pojo.entity.Menu;
import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.transport.MenuTransport;

@Controller("indexController")
public class IndexController extends BaseController {
	@Resource(name="menuTransport")
	private MenuTransport menuTransport;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getIndex() throws Exception {
		// 获得当前登录用户
		User user = (User) session.getAttribute("user");
		// 根据用户角色获得对应的菜单集合
		List<Menu> menuList = menuTransport.getMenuListForIndex(user.getRole());
		return new ModelAndView("index", "menuList", menuList);
	}
}
