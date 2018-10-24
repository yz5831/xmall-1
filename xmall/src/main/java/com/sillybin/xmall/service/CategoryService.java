package com.sillybin.xmall.service;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.vo.XmallPage;

public interface CategoryService {
	/**
	 ** 分页查询列表
	 * @param xmallPage
	 * @param query
	 * @return XmallPage<Category>
	 * @throws Exception
	 */
	public XmallPage<Category> getListByPage(XmallPage<Category> xmallPage, String query) throws Exception;

	/**
	 ** 根据上级主键获得列表
	 * @param parentId
	 * @return List<Category>
	 */
	public List<Category> getCategoryListByParent(Long parentId) throws Exception;
	
	/**
	 ** 根据主键获得Category对象
	 * @param id
	 * @return Category
	 * @throws Exception
	 */
	public Category getCategoryById(Long id) throws Exception;

	public Category getText(String text) throws Exception;

	public boolean saveOrUpdate(Category category, Long userId, Long statusId, Long parentId) throws Exception;

	public boolean changeStatus(Long id, String code) throws Exception;

	
}
