package com.sillybin.xmall.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.sillybin.xmall.dao.UserDao;
import com.sillybin.xmall.pojo.entity.User;
import com.sillybin.xmall.pojo.vo.XmallPage;
import com.sillybin.xmall.service.UserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;

	public User getUserByLoginName(String loginName) throws Exception {
		return userDao.findUserByLoginName(loginName);
	}

	public XmallPage<User> getUserListByPage(XmallPage<User> xmallPage, String username) throws Exception {
		// 设定分页对象
		Pageable pageable = PageRequest.of(xmallPage.getPageNum() - 1, xmallPage.getPageSize(), 
				Direction.ASC, "createTime");
		Page<User> page = null;
		if (username != null) {
			page = userDao.findAll(new Specification<User>() {
				private static final long serialVersionUID = 1L;
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.like(root.get("username"), "%" + username + "%");
					return query.where(predicate).getRestriction();
				}
			}, pageable);
		} else {
			page = userDao.findAll(pageable);
		}
		// 封装数据
		xmallPage.setList(page.getContent());
		xmallPage.setTotalCount(page.getTotalElements());
		xmallPage.setTotalPage((long) page.getTotalPages());
		return xmallPage;
	}

	public User getUserByUserId(Long userId) throws Exception {
		return userDao.findUserByUserId(userId);
	}

	public boolean updateUser(User user) throws Exception {
		User updateUser = userDao.save(user);
		if (user.getStatus().getStatusId() != updateUser.getStatus().getStatusId()) {
			return true;
		}
		return false;
	}

	public User getLastUser() throws Exception {
		User user = userDao.findUserListDESC();
		return user;
	}

	public boolean saveUser(User user) throws Exception {
		User saveUser = userDao.save(user);
		if (saveUser.getUserId() != null) {
			return true;
		}
		return false;
	}
}
