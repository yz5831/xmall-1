package com.sillybin.xmall.transport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.service.StatusService;
import com.sillybin.xmall.transport.StatusTransport;
@Component("statusTransport")
public class StatusTransportImpl implements StatusTransport {
	@Resource(name="statusService")
	private StatusService statusService;

	public Status getStatusByStatusCode(String statusCode) throws Exception {
		return statusService.getStatusByStatusCode(statusCode);
	}

	public List<Status> getStatusListByParentCode(String statusCode) throws Exception {
		return statusService.getStatusListByParentCode(statusCode);
	}
}
