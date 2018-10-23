package com.sillybin.xmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sillybin.xmall.controller.base.BaseController;
import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.transport.CategoryTransport;

@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController extends BaseController {
	@Resource(name="categoryTransport")
	private CategoryTransport categoryTransport;
	
	/**
	 * 转发获得商品类别地址
	 * @return String 商品类别列表转发地址
	 * @throws Exception
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getCategoryIndex() throws Exception {
		return "category/category_index";
	}
	
	/**
	 * 分页获得商品信息列表
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示数量
	 * @param draw
	 * @param categoryName 商品类别名称，模糊匹配
	 * @return Map<String, Object> page-分页信息，categoryName-商品类别名称
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCategoryListByPage(Integer pageNum, Integer pageSize, 
			Integer draw, String categoryName) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 根据pageNum、pageSize和draw封装XmallPage对象
		XmallPage<Category> xmallPage = new XmallPage<>(pageNum, pageSize, draw);
		// 判断是否进行了模糊查询
		if (categoryName != null && !"".equals(categoryName.trim())) {
			// 进行模糊匹配，该模糊匹配值必须重新带回到页面中
			resultMap.put("categoryName", categoryName);
		} else {
			// 如果不进行模糊匹配，那么categoryName回传的时候设置为""
			resultMap.put("categoryName", "");
			// 并且将categoryName设置为null
			categoryName = null;
		}
		// 进行分页查询
		xmallPage = categoryTransport.getCategoryListByPage(xmallPage, categoryName);
		resultMap.put("page", xmallPage);
		return resultMap;
	}
}
