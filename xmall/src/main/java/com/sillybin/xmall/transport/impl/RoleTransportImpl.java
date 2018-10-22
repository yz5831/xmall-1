package com.sillybin.xmall.transport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.Role;
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
}
