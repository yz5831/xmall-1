package com.sillybin.xmall.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sillybin.xmall.dao.RoleDao;
import com.sillybin.xmall.dao.StatusDao;
import com.sillybin.xmall.pojo.entity.Role;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.service.RoleService;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Resource(name="roleDao")
	private RoleDao roleDao;
	@Resource(name="statusDao")
	private StatusDao statusDao;

	public List<Role> getRoleAllList() throws Exception {
		List<Role> roleList = roleDao.findAll(new Sort(Direction.ASC, "roleId"));
		return roleList;
	}

	public Role getRoleByRoleId(Long roleId) throws Exception {
		return roleDao.findRoleByRoleId(roleId);
	}

	/**
	 ** 通过分页对象XmallPage获得角色信息列表
	 * @param xmallPage
	 * @param queryName
	 * @return XmallPage<Role>
	 * @throws Exception
	 */
	public XmallPage<Role> getRoleListByPage(XmallPage<Role> xmallPage, String queryName) throws Exception {
		// 设定分页对象Pageable
		Pageable pageable = PageRequest.of(xmallPage.getPageNum() - 1, xmallPage.getPageSize(), 
				Direction.ASC, "roleId");
		// 根据是否有模糊查询分别获得结果
		Page<Role> page = null;
		if (queryName != null) {
			// 根据查询获得page对象
			page = roleDao.findAll(new Specification<Role>() {
				private static final long serialVersionUID = 1L;

				public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.like(root.get("roleName"), "%" + queryName + "%");
					return query.where(predicate).getRestriction();
				}
			}, pageable);
		} else {
			// 不进行模糊查询
			page = roleDao.findAll(pageable);
		}
		
		// 设定结果集
		xmallPage.setList(page.getContent());
		xmallPage.setTotalCount(page.getTotalElements());
		xmallPage.setTotalPage((long) page.getTotalPages());
		
		return xmallPage;
	}

	/**
	 ** 根据角色主键和状态编码更改角色状态
	 * @param id
	 * @param code
	 * @return boolean
	 * @throws Exception
	 */
	public boolean changeStatus(Long id, String code) throws Exception {
		// 获得角色对象
		Role role = roleDao.getOne(id);
		// 根据状态编码获得状态对象
		Status status = statusDao.findStatusByStatusCode(code);
		role.setStatus(status);
		try {
			roleDao.save(role);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 ** 根据角色名称获得角色对象
	 * @param roleName
	 * @return Role
	 * @throws Exception
	 */
	public Role getRoleByRoleName(String roleName) throws Exception {
		return roleDao.findRoleByRoleName(roleName);
	}
	
	/**
	 ** 根据角色编码获得角色对象 
	 * @param roleCode
	 * @return Role
	 * @throws Exception
	 */
	public Role getRoleByRoleCode(String roleCode) throws Exception {
		return roleDao.findRoleByRoleCode(roleCode);
	}
}
