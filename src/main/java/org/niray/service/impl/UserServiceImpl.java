package org.niray.service.impl;

import org.springframework.stereotype.Service;

import org.niray.mapper.UserMapper;
import org.niray.entity.User;
import org.niray.service.IUserService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements IUserService {


}