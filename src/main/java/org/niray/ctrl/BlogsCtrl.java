package org.niray.ctrl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.niray.entity.Blog;
import org.niray.entity.User;
import org.niray.mongo.MongoDBService;
import org.niray.redis.RedisMapper;
import org.niray.service.IBlogService;
import org.niray.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by Mac on 16/10/24.
 */
@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class BlogsCtrl {

    private final static Logger logger = Logger.getLogger(BlogsCtrl.class);

    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private MongoDBService mongoDBService;

    private IBlogService blogService;

    @Autowired
    public BlogsCtrl(IBlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/blog/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String helloKotlin() {
        List list = blogService.selectList(new EntityWrapper<Blog>());
        return JSONArray.toJSONString(list);
    }

    @RequestMapping(value = "/blog/add", method = RequestMethod.POST)
    @ResponseBody
    public String addUserPost(@RequestParam(value = "title", required = false, defaultValue = "title") String title,
                              @RequestParam(value = "content", required = false, defaultValue = "content") String content,
                              @RequestParam(value = "user_id", required = false, defaultValue = "user_id") int user_id,
                              @RequestParam(value = "pubDate", required = false, defaultValue = "pubDate") String pubDate) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setUserId(user_id);
        blog.setContent(content);
        blog.setPubDate(pubDate);
//        return ResultUtil.getReturnMsg(false, 1, "对不起用户已经过期，请登录后重新操作");
        return blogService.insert(blog) ? "success" : "Failure";
    }

    @RequestMapping(value = "/blog/del", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String deleteUserPost(@RequestParam(value = "id", required = false, defaultValue = "0") int uid) {
        return blogService.deleteById(uid) ? "success" : "Failure";
    }


    @RequestMapping(value = "/blog/find", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String findUserBlog(@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        logger.debug(uid + "--------------");
        List<Blog> blogByUid = blogService.getBlogByUid(uid, page, pageSize);
        return JSONArray.toJSONString(blogByUid);
    }

    @RequestMapping(value = "/blog/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String updateUserPost(@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
                                 @RequestParam(value = "title", required = false, defaultValue = "title") String title,
                                 @RequestParam(value = "content", required = false, defaultValue = "content") String content) {
        Blog user = blogService.selectById(uid);
        user.setTitle(title);
        user.setContent(content);
        return blogService.updateById(user) ? "Success" : "Failure";
    }


    @RequestMapping(value = "/blog/search", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String searchBlogByKeyword(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        if (keyword == null || keyword.trim().length() == 0)
            return ResultUtil.getReturnMsg(false, 1, "请输入搜索关键字");
        List<Blog> blogByUid = blogService.searchBlogByKey(keyword, page, pageSize);
        return JSONArray.toJSONString(blogByUid);
    }

    @RequestMapping(value = "/mongo/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String mongoDBAddPost(@RequestParam(value = "nickname", required = false, defaultValue = "default") String name,
                                 @RequestParam(value = "password", required = false, defaultValue = "pwd") String pwd) {
        User user = new User();
        user.setNickname(name);
        user.setPassword(pwd);
        return JSONArray.toJSONString(mongoDBService.findAll());
    }

    @RequestMapping(value = "/redis/set", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String redisSet(@RequestParam(value = "key", required = false, defaultValue = "default") String key,
                           @RequestParam(value = "value", required = false, defaultValue = "default") String value) {
        redisMapper.save(key, value, 30 * 60 * 24 * 24);
        return ResultUtil.getReturnMsg(true, 2000, "success");
    }

    @RequestMapping(value = "/redis/get", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String redisGet(@RequestParam(value = "key", required = false, defaultValue = "default") String key) {
        return redisMapper.get(key);
    }

}
