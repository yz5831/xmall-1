package com.sillybin.xmall.service;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.pojo.vo.XmallPage;

public interface RoleService {
	public List<Role> getRoleAllList() throws Exception;
	public Role getRoleByRoleId(Long roleId) throws Exception;
	/**
	 ** 通过分页对象XmallPage获得角色信息列表
	 * @param xmallPage
	 * @param queryName
	 * @return XmallPage<Role>
	 * @throws Exception
	 */
	public XmallPage<Role> getRoleListByPage(XmallPage<Role> xmallPage, String queryName) throws Exception;
	
	/**
	 ** 根据角色主键和状态编码更改角色状态
	 * @param id
	 * @param code
	 * @return boolean
	 * @throws Exception
	 */
	public boolean changeStatus(Long id, String code) throws Exception;
	
	/**
	 ** 根据角色名称获得角色对象
	 * @param roleName
	 * @return Role
	 * @throws Exception
	 */
	public Role getRoleByRoleName(String roleName) throws Exception;
	
	/**
	 ** 根据角色编码获得角色对象 
	 * @param roleCode
	 * @return Role
	 * @throws Exception
	 */
	public Role getRoleByRoleCode(String roleCode) throws Exception;
	
	/**
	 ** 保存角色信息
	 * @param role
	 * @param statusId
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrUpdateRole(Role role, Long statusId) throws Exception;
}
