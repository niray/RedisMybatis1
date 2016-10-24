package org.niray.service;

import com.baomidou.framework.service.ISuperService;
import org.niray.entity.Blog;

import java.util.List;

/**
 * Blog 表数据服务层接口
 */
public interface IBlogService extends ISuperService<Blog> {

    List<Blog> getBlogByUid(int uid, int page, int pageSize);

}