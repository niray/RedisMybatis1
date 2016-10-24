package org.niray.service.impl;

import org.springframework.stereotype.Service;

import org.niray.mapper.BlogMapper;
import org.niray.entity.Blog;
import org.niray.service.IBlogService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Blog 表数据服务层接口实现类
 *
 */
@Service
public class BlogServiceImpl extends SuperServiceImpl<BlogMapper, Blog> implements IBlogService {


}