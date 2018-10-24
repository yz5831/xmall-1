package com.sillybin.xmall.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sillybin.xmall.dao.base.BaseDao;
import com.sillybin.xmall.pojo.entity.Category;

public interface CategoryDao extends BaseDao<Category, Long> {
	@Query("from Category c where c.text=:text")
	public Category findText(@Param("text")String text) throws Exception;
}
