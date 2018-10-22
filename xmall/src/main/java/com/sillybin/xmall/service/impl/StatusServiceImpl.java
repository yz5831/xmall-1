package com.sillybin.xmall.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sillybin.xmall.dao.StatusDao;
import com.sillybin.xmall.pojo.entity.Status;
import com.sillybin.xmall.service.StatusService;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {
	@Resource(name="statusDao")
	private StatusDao statusDao;

	public Status getStatusByStatusCode(String statusCode) throws Exception {
		return statusDao.findStatusByStatusCode(statusCode);
	}

	public List<Status> getStatusListByParentCode(String statusCode) throws Exception {
		List<Status> statusList = statusDao.findStatusByParentCode(statusCode);
		return statusList;
	}
}
