package com.sillybin.xmall.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sillybin.xmall.controller.base.BaseController;
import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.transport.RoleTransport;
import com.sillybin.xmall.transport.StatusTransport;
import com.sillybin.xmall.transport.UserTransport;
import com.sillybin.xmall.util.ConstantUtil;
import com.sillybin.xmall.util.UserUtil;
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource(name="userTransport")
	private UserTransport userTransport;
	@Resource(name="statusTransport")
	private StatusTransport statusTransport;
	@Resource(name="roleTransport")
	private RoleTransport roleTransport;
	
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
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView getUserAddForm() throws Exception {
		// 按照用户主键降序排列，获得最后一个用户
		User lastUser = userTransport.getLastUser();
		String userNo = "";
		if (lastUser == null) {
			userNo = UserUtil.createUserNo(1l);
		} else {
			userNo = UserUtil.createUserNo(lastUser.getUserId() + 1);
		}
		// 获得所有的可用角色列表
		List<Role> roleList = roleTransport.getRoleAllList();
		// 封装结果，进行转发
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userNo", userNo);
		resultMap.put("roleList", roleList);
		
		return new ModelAndView("user/user_create", resultMap);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public boolean saveUser(User user, Long roleId) throws Exception {
		// 根据角色主键获得角色信息
		Role role = roleTransport.getRoleByRoleId(roleId);
		user.setRole(role);
		// 设定用户默认为启用状态
		user.setStatus(statusTransport.getStatusByStatusCode(ConstantUtil.STATUS_ENABLE));
		// 判断是否填写的身份证号码
		if (user.getIdCard() != null && !"".equals(user.getIdCard().trim())) {
			// 根据身份证号码切割生日
			user.setBirthday(UserUtil.parseBirthday(user.getIdCard()));
		}
		// 设定创建人和创建时间
		user.setCreateUser((User) session.getAttribute("user"));
		user.setCreateTime(new Date());
		// 保存用户信息
		return userTransport.saveUser(user);
	}
}
