package com.sillybin.xmall.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sillybin.xmall.dao.base.BaseDao;
import com.sillybin.xmall.pojo.entity.Status;

public interface StatusDao extends BaseDao<Status, Long> {
	@Query("from Status s where s.statusCode=:statusCode")
	public Status findStatusByStatusCode(@Param("statusCode")String statusCode) throws Exception;
}
