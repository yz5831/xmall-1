package com.sillybin.xmall.transport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.service.CategoryService;
import com.sillybin.xmall.transport.CategoryTransport;

@Component("categoryTransport")
public class CategoryTransportImpl implements CategoryTransport {
	@Resource(name="categoryService")
	private CategoryService categoryService;

	/**
	 ** 分页查询对象列表
	 * @param xmallPage 分页对象
	 * @param query 查询参数
	 * @return XmallPage<Category>
	 * @throws Exception
	 */
	public XmallPage<Category> getListByPage(XmallPage<Category> xmallPage, String query) throws Exception {
		return categoryService.getListByPage(xmallPage, query);
	}
	
	/**
	 ** 根据上级主键获得列表
	 * @param parentId
	 * @return List<Category>
	 */
	public List<Category> getCategoryListByParentId(Long parentId) throws Exception {
		return categoryService.getCategoryListByParent(parentId);
	}

	/**
	 ** 根据主键获得Category对象
	 * @param id
	 * @return Category
	 * @throws Exception
	 */
	public Category getCategoryById(Long id) throws Exception {
		return categoryService.getCategoryById(id);
	}

	public Category getText(String text) throws Exception {
		return categoryService.getText(text);
	}

	public boolean saveOrUpdate(Category category, Long userId, Long statusId, Long parentId) throws Exception {
		return categoryService.saveOrUpdate(category, userId, statusId, parentId);
	}

	public boolean changeStatus(Long id, String code) throws Exception {
		return categoryService.changeStatus(id, code);
	}
}
