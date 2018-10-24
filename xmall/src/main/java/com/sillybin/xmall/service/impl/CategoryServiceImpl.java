package com.sillybin.xmall.service.impl;

import java.util.Date;
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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sillybin.xmall.dao.CategoryDao;
import com.sillybin.xmall.dao.StatusDao;
import com.sillybin.xmall.dao.UserDao;
import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.service.CategoryService;
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	@Resource(name="statusDao")
	private StatusDao statusDao;
	@Resource(name="userDao")
	private UserDao userDao;

	/**
	 ** 分页查询列表
	 * @param xmallPage
	 * @param query
	 * @return XmallPage<Category>
	 * @throws Exception
	 */
	public XmallPage<Category> getListByPage(XmallPage<Category> xmallPage, String query) throws Exception {
		// 创建分页对象Pageable
		Pageable pageable = PageRequest.of(xmallPage.getPageNum() - 1, xmallPage.getPageSize(), 
				Direction.ASC, "sortOrder");
		// 创建Page分页对象
		Page<Category> page = null;
		if (query != null) {
			// 进行查询匹配
			page = categoryDao.findAll(new Specification<Category>() {
				private static final long serialVersionUID = 1L;

				public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.like(root.get("categoryName"), "%" + query + "%");
					return query.where(predicate).getRestriction();
				}
			}, pageable);
		} else {
			page = categoryDao.findAll(pageable);
		}
		
		// 封装XmallPage对象
		xmallPage.setList(page.getContent());
		xmallPage.setTotalCount(page.getTotalElements());
		xmallPage.setTotalPage((long) page.getTotalPages());
		return xmallPage;
	}

	/**
	 ** 根据上级主键获得列表
	 * @param parentId
	 * @return List<Category>
	 */
	public List<Category> getCategoryListByParent(Long parentId) throws Exception {
		List<Category> categoryList = categoryDao.findAll(new Specification<Category>() {
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = null;
				if (parentId != null) {
					predicate = cb.equal(root.get("parent").get("categoryId"), parentId);
				} else {
					predicate = cb.isNull(root.get("parent"));
				}
				return query.where(predicate).getRestriction();
			}
		});
		return categoryList;
	}
	
	/**
	 ** 根据主键获得Category对象
	 * @param id
	 * @return Category
	 * @throws Exception
	 */
	public Category getCategoryById(Long id) throws Exception {
		return categoryDao.getOne(id);
	}

	public Category getText(String text) throws Exception {
		return categoryDao.findText(text);
	}

	public boolean saveOrUpdate(Category category, Long userId, Long statusId, Long parentId) throws Exception {
		// 根据statusId获得对象
		Status status = statusDao.getOne(statusId);
		category.setStatus(status);
		// 根据userId获得User对象
		User user = userDao.getOne(userId);
		// 判断此时是否有categoryId
		if (category.getCategoryId() == null) {
			// 说明为添加
			category.setCreateUser(user);
			category.setCreateTime(new Date());
		} else {
			category.setUpdateUser(user);
			category.setUpdateTime(new Date());
		}
		// 判断parentId是否为null
		if (parentId != null) {
			// 获得父类
			Category parent = categoryDao.getOne(parentId);
			category.setParent(parent);
		}
		
		try {
			categoryDao.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean changeStatus(Long id, String code) throws Exception {
		// 根据categoryId获得Category对象
		Category category = categoryDao.getOne(id);
		// 根据statusCode获得Status对象
		Status status = statusDao.findStatusByStatusCode(code);
		
		category.setStatus(status);
		try {
			categoryDao.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
