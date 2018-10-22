package com.sillybin.xmall.service;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Status;

public interface StatusService {
	public Status getStatusByStatusCode(String statusCode) throws Exception;
	public List<Status> getStatusListByParentCode(String statusCode) throws Exception;
}
