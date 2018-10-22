package com.sillybin.xmall.service;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Role;

public interface RoleService {
	public List<Role> getRoleAllList() throws Exception;
	public Role getRoleByRoleId(Long roleId) throws Exception;
}
