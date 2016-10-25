package org.niray.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import org.niray.entity.Blog;
import org.niray.mapper.BlogMapper;
import org.niray.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Blog 表数据服务层接口实现类
 */
@Service
public class BlogServiceImpl extends SuperServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    BlogMapper blogMapper;

    public List<Blog> getBlogByUid(int uid, int page, int pageSize) {
        HashMap params = new HashMap();
        params.put("uid", uid);
        params.put("page", page);
        params.put("pageSize", pageSize);
        return blogMapper.getBlogByUid(params);
    }

    public List<Blog> searchBlogByKey(String keyword, int page, int pageSize) {
        HashMap params = new HashMap();
        params.put("keyword", keyword);
        params.put("page", page);
        params.put("pageSize", pageSize);
        return blogMapper.searchBlogByKey(params);
    }

}