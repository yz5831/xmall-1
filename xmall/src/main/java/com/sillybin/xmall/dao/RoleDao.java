package com.sillybin.xmall.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sillybin.xmall.dao.base.BaseDao;
import com.sillybin.xmall.pojo.entity.Role;

public interface RoleDao extends BaseDao<Role, Long> {
	@Query("from Role r where r.roleId=:roleId")
	public Role findRoleByRoleId(@Param("roleId")Long roleId) throws Exception;
}
