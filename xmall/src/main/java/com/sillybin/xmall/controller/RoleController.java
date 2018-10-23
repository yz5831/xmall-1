package com.sillybin.xmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sillybin.xmall.controller.base.BaseController;
import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.transport.RoleTransport;
import com.sillybin.xmall.transport.StatusTransport;
import com.sillybin.xmall.util.ConstantUtil;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Resource(name="roleTransport")
	private RoleTransport roleTransport;
	@Resource(name="statusTransport")
	private StatusTransport statusTransport;
	
	/**
	 ** 获得角色列表页面
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getRoleIndex() throws Exception {
		return "role/role_index";
	}
	
	/**
	 ** 使用Ajax方式分页获得角色信息列表
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @param queryName 角色名称，模糊匹配
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleListByPage(Integer pageNum, Integer pageSize, 
			Integer draw, String queryName) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 将pageNum、pageSize和draw封装为XmallPage对象
		XmallPage<Role> xmallPage = new XmallPage<>(pageNum, pageSize, draw);
		// 判断用户是否进行了模糊查询
		if (queryName != null && !"".equals(queryName.trim())) {
			// 将查询参数设定到返回结果集中
			resultMap.put("queryName", queryName);
		} else {
			// 否则将空字符串设定到结果集中，并且将queryName变为null
			resultMap.put("queryName", "");
			queryName = null;
		}
		// 查询结果，获得XmallPage对象
		xmallPage = roleTransport.getRoleListByPage(xmallPage, queryName);
		resultMap.put("page", xmallPage);
		
		return resultMap;
	}
	
	/**
	 ** 根据角色主键和状态编码更改角色状态
	 * @param id
	 * @param code
	 * @return boolean
	 * @throws Exception
	 */
	@RequestMapping(value="/status", method=RequestMethod.PUT)
	@ResponseBody
	public boolean changeStatus(Long id, String code) throws Exception {
		// 调用业务层进行修改
		return roleTransport.changeStatus(id, code);
	}
	
	/**
	 ** 获得添加角色页面
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView getCreatePage() throws Exception {
		Map<String, Object> resultMap = new HashMap<String ,Object>();
		// 获得状态列表
		List<Status> statusList = statusTransport.getStatusListByParentCode(ConstantUtil.STATUS_ISABLE);
		resultMap.put("statusList", statusList);
		return new ModelAndView("role/role_create", resultMap);
	}
	
	/**
	 ** 判断角色名称是否已经被占用
	 * @param roleId
	 * @param roleName
	 * @return boolean
	 * @throws Exception
	 */
	@RequestMapping(value="/checkRoleName", method=RequestMethod.GET)
	@ResponseBody
	public boolean checkRoleName(Long roleId, String roleName) throws Exception {
		// 根据角色名称查找角色对象
		Role role = roleTransport.getRoleByRoleName(roleName);
		if (role == null || (roleId != null && role.getRoleName().equals(roleName))) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value="/checkRoleCode", method=RequestMethod.GET)
	@ResponseBody
	public boolean checkRoleCode(Long roleId, String roleCode) throws Exception {
		// 根据角色名称查找角色对象
		Role role = roleTransport.getRoleByRoleCode(roleCode);
		if (role == null || (roleId != null && role.getRoleCode().equals(roleCode))) {
			return true;
		} else {
			return false;
		}
	}
}
