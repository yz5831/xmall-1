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
import org.springframework.data.domain.Sort;
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
				Direction.ASC, "userId");
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
		return userDao.findById(userId).get();
	}

	public User getLastUser() throws Exception {
		List<User> userList = userDao.findAll(new Sort(Direction.DESC, "userId"));
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
	
	/**
	 * 保存/更新用户信息
	 * @param user 带保存/更新用户对象
	 * @return boolean true-保存或者更新用户成功，false-保存或者更新用户失败
	 * @throws Exception
	 */
	public boolean saveOrUpdateUser(User user) throws Exception {
		try {
			userDao.save(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
