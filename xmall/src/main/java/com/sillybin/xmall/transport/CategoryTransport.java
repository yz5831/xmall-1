package com.sillybin.xmall.transport;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.vo.XmallPage;

/**
 * 传输层接口
 * @author Captwill
 * @version v1.0
 */
public interface CategoryTransport {
	/**
	 ** 分页查询对象列表
	 * @param xmallPage 分页对象
	 * @param query 查询参数
	 * @return XmallPage<Category>
	 * @throws Exception
	 */
	public XmallPage<Category> getListByPage(XmallPage<Category> xmallPage, String query) throws Exception;

	/**
	 ** 根据上级主键获得列表
	 * @param parentId
	 * @return List<Category>
	 */
	public List<Category> getCategoryListByParentId(Long parentId) throws Exception;
	
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
