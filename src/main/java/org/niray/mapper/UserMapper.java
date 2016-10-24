package org.niray.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import org.niray.entity.User;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends AutoMapper<User> {

    int findByNameLength(int length);

}