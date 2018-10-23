package com.sillybin.xmall.transport.impl;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.transport.CategoryTransport;

@Component("categoryTransport")
public class CategoryTransportImpl implements CategoryTransport {

	public XmallPage<Category> getCategoryListByPage(XmallPage<Category> xmallPage, String categoryName)
			throws Exception {
		return null;
	}
}
