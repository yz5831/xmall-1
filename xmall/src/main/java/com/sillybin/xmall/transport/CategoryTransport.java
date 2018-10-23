package com.sillybin.xmall.transport;

import com.sillybin.xmall.pojo.entity.Category;
import com.sillybin.xmall.pojo.vo.XmallPage;

/**
 * 传输层接口
 * @author Captwill
 * @version v1.0
 */
public interface CategoryTransport {
	public XmallPage<Category> getCategoryListByPage(XmallPage<Category> xmallPage, String categoryName) throws Exception;
}
