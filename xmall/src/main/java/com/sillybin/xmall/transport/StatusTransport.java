package com.sillybin.xmall.transport;

import java.util.List;

import com.sillybin.xmall.pojo.entity.Status;

public interface StatusTransport {
	public Status getStatusByStatusCode(String statusCode) throws Exception;
	public List<Status> getStatusListByParentCode(String statusCode) throws Exception;
}
