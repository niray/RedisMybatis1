package org.niray.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import org.niray.entity.User;
import org.niray.mapper.UserMapper;
import org.niray.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    public int findByNameLength(int length) {
        return userMapper.findByNameLength(length);
    }
}