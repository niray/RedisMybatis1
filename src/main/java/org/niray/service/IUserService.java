package org.niray.service;

import com.baomidou.framework.service.ISuperService;
import org.niray.entity.User;

/**
 * User 表数据服务层接口
 */
public interface IUserService extends ISuperService<User> {

    int findByNameLength(int length);

}