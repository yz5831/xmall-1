package com.sillybin.xmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sillybin.xmall.controller.base.BaseController;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.transport.StatusTransport;
import com.sillybin.xmall.transport.UserTransport;
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource(name="userTransport")
	private UserTransport userTransport;
	@Resource(name="statusTransport")
	private StatusTransport statusTransport;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getUserLoginForm() throws Exception {
		return "user/user_login_form";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView loginUserError() throws Exception {
		RedirectView view = new RedirectView(request.getContextPath() + "/user/logout");
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getUserIndex() throws Exception {
		return "user/user_index";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserListByPage(Integer pageNum, Integer pageSize, Integer draw, String username) 
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		XmallPage<User> xmallPage = new XmallPage<User>(pageNum, pageSize, draw);
		if (username != null && !"".equals(username.trim())) {
			resultMap.put("username", username);
			xmallPage = userTransport.getUserListByPage(xmallPage, username);
		} else {
			resultMap.put("username", "");
			xmallPage = userTransport.getUserListByPage(xmallPage, null);
		}
		resultMap.put("page", xmallPage);
		return resultMap;
	}
	
	@RequestMapping(value="/status", method=RequestMethod.PUT)
	@ResponseBody
	public boolean changeStatus(Long userId, String statusCode) throws Exception {
		// 根据userId获得User对象
		User user = userTransport.getUserByUserId(userId);
		// 根据statusCode获得Status对象
		Status status = statusTransport.getStatusByStatusCode(statusCode);
		
		user.setStatus(status);
		return userTransport.updateUser(user);
	}
}
