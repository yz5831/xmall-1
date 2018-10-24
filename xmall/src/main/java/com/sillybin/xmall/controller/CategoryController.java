package com.sillybin.xmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sillybin.xmall.controller.base.BaseController;
import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.transport.CategoryTransport;
import com.sillybin.xmall.transport.StatusTransport;
import com.sillybin.xmall.util.ConstantUtil;

@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController extends BaseController {
	@Resource(name="categoryTransport")
	private CategoryTransport categoryTransport;
	@Resource(name="statusTransport")
	private StatusTransport statusTransport;
	
	/**
	 ** 获得商品列表页面
	 * @return String 转发地址
	 * @throws Exception
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndexPage() throws Exception {
		return "category/category_index";
	}
	
	/**
	 ** 分页获得商品信息列表
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示数量，默认为10
	 * @param draw 标记量
	 * @param query 查询参数
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListByPage(Integer pageNum, Integer pageSize, Integer draw, String query) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 封装查询对象XmallPage
		XmallPage<Category> xmallPage = new XmallPage<Category>(pageNum, pageSize, draw);
		// 判断用户是否进行了查询操作
		if (query != null && !"".equals(query.trim())) {
			// 进行了模糊查询
			resultMap.put("query", query);
		} else {
			// 不进行模糊查询，则设定query为null
			resultMap.put("query", "");
			query = null;
		}
		// 获得分页对象
		resultMap.put("page", categoryTransport.getListByPage(xmallPage, query));
		return resultMap;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView getSavePage(@PathVariable("id")Long id) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 判断是否有id
		if (id != 0) {
			// 此时为修改操作
			// 根据id获得对应的对象
			Category category = categoryTransport.getCategoryById(id);
			resultMap.put("category", category);
		}
		// 获得所有一级类别
		List<Category> categoryList = categoryTransport.getCategoryListByParentId(null);
		// 获得所有启用状态列表
		List<Status> statusList = statusTransport.getStatusListByParentCode(ConstantUtil.STATUS_ISABLE);
		resultMap.put("categoryList", categoryList);
		resultMap.put("statusList", statusList);
		// 进行转发
		return new ModelAndView("category/category_save", resultMap);
	}
	
	@RequestMapping(value="/text", method=RequestMethod.POST)
	@ResponseBody
	public boolean checkName(Long id, String text) throws Exception {
		Category category = categoryTransport.getText(text);
		if (category == null || (id == category.getCategoryId())) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public boolean saveOrUpdate(Category category, Long statusId, Long parentId) throws Exception {
		// 获得当前登录用户
		Long userId = ((User) session.getAttribute("user")).getUserId();
		return categoryTransport.saveOrUpdate(category, userId, statusId, parentId);
	}
	
	@RequestMapping(value="/status", method=RequestMethod.PUT)
	@ResponseBody
	public boolean changeStatus(Long id, String code) throws Exception {
		// 调用业务层进行修改
		return categoryTransport.changeStatus(id, code);
	}
}
