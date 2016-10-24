package org.niray.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import org.niray.entity.Blog;

import java.util.HashMap;
import java.util.List;

/**
 * Blog 表数据库控制层接口
 */
public interface BlogMapper extends AutoMapper<Blog> {

    List<Blog> getBlogByUid(HashMap params);

}