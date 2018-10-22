package com.sillybin.xmall.transport;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Role;

public interface RoleTransport {
	public List<Role> getRoleAllList() throws Exception;
	public Role getRoleByRoleId(Long roleId) throws Exception;
}
