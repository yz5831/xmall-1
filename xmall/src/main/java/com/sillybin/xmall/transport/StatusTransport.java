package com.sillybin.xmall.transport;

import com.sillybin.xmall.pojo.entity.Status;

public interface StatusTransport {
	public Status getStatusByStatusCode(String statusCode) throws Exception;
}
