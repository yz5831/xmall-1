package com.sillybin.xmall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sillybin.xmall.dao.base.BaseDao;
import com.sillybin.xmall.pojo.entity.Menu;

public interface MenuDao extends BaseDao<Menu, Long> {
	@Query("from Menu m join fetch m.roleList r where r.roleId=:roleId and m.parent is null order by m.sortOrder asc")
	public List<Menu> findFirstMenuListByRole(@Param("roleId")Long roleId) throws Exception;

	@Query("from Menu m join fetch m.roleList r where r.roleId=:roleId and m.parent.menuId=:parentId order by m.sortOrder asc")
	public List<Menu> findSecondMenuListByRole(@Param("roleId")Long roleId, @Param("parentId")Long parentId) throws Exception;
}
