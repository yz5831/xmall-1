package com.sillybin.xmall.transport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.service.RoleService;
import com.sillybin.xmall.transport.RoleTransport;
@Component("roleTransport")
public class RoleTransportImpl implements RoleTransport {
	@Resource(name="roleService")
	private RoleService roleService;

	public List<Role> getRoleAllList() throws Exception {
		return roleService.getRoleAllList();
	}

	public Role getRoleByRoleId(Long roleId) throws Exception {
		return roleService.getRoleByRoleId(roleId);
	}

	/**
	 ** 通过分页对象XmallPage获得List对象
	 * @param xmallPage
	 * @param queryName
	 * @return XmallPage<Role>
	 * @throws Exception
	 */
	public XmallPage<Role> getRoleListByPage(XmallPage<Role> xmallPage, String queryName) throws Exception {
		return roleService.getRoleListByPage(xmallPage, queryName);
	}

	/**
	 ** 根据角色主键和状态编码更改角色状态
	 * @param id
	 * @param code
	 * @return boolean
	 * @throws Exception
	 */
	public boolean changeStatus(Long id, String code) throws Exception {
		return roleService.changeStatus(id, code);
	}

	/**
	 ** 根据角色名称获得角色对象
	 * @param roleName
	 * @return Role
	 * @throws Exception
	 */
	public Role getRoleByRoleName(String roleName) throws Exception {
		return roleService.getRoleByRoleName(roleName);
	}
	
	public Role getRoleByRoleCode(String roleCode) throws Exception {
		return roleService.getRoleByRoleCode(roleCode);
	}

	/**
	 ** 使用Ajax保存角色信息
	 * @param role
	 * @param statusId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveOrUpdateRole(Role role, Long statusId) throws Exception {
		return roleService.saveOrUpdateRole(role, statusId);
	}
}
