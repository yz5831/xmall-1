package com.sillybin.xmall.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sillybin.xmall.dao.base.BaseDao;
import com.sillybin.xmall.pojo.entity.Role;

public interface RoleDao extends BaseDao<Role, Long> {
	@Query("from Role r where r.roleId=:roleId")
	public Role findRoleByRoleId(@Param("roleId")Long roleId) throws Exception;

	@Query("from Role r where r.roleName=:roleName")
	public Role findRoleByRoleName(@Param("roleName")String roleName) throws Exception;
	
	@Query("from Role r where r.roleCode=:roleCode")
	public Role findRoleByRoleCode(@Param("roleCode")String roleCode) throws Exception;
}
