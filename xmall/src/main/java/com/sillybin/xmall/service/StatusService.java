package com.sillybin.xmall.service;

import com.sillybin.xmall.pojo.entity.Status;

public interface StatusService {
	public Status getStatusByStatusCode(String statusCode) throws Exception;
}
