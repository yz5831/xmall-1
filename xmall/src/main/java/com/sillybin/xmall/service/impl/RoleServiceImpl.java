package com.sillybin.xmall.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sillybin.xmall.dao.RoleDao;
import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.service.RoleService;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Resource(name="roleDao")
	private RoleDao roleDao;

	public List<Role> getRoleAllList() throws Exception {
		List<Role> roleList = roleDao.findAll(new Sort(Direction.ASC, "roleId"));
		return roleList;
	}

	public Role getRoleByRoleId(Long roleId) throws Exception {
		return roleDao.findRoleByRoleId(roleId);
	}
}
