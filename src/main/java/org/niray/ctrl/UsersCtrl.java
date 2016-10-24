package org.niray.ctrl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.niray.entity.User;
import org.niray.redis.RedisMapper;
import org.niray.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mac on 16/10/24.
 */
@Controller
public class UsersCtrl {

    @Autowired
    private RedisMapper redisMapper;

    private IUserService userService;

    @Autowired
    public UsersCtrl(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String helloKotlin() {
        List list = userService.selectList(new EntityWrapper<User>());
        return JSONArray.toJSONString(list);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public String addUserPost(@RequestParam(value = "nickname", required = false, defaultValue = "default") String name,
                              @RequestParam(value = "password", required = false, defaultValue = "pwd") String pwd) {
        User user = new User();
        user.setNickname(name);
        user.setPassword(pwd);
        userService.findByNameLength(4);
        return userService.insert(user) ? name + " " + pwd : "Failure";
    }

    @RequestMapping(value = "/user/del", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String deleteUserPost(@RequestParam(value = "uid", required = false, defaultValue = "0") int uid) {
        return userService.deleteById(uid) ? "success" : "Failure";
    }

    @RequestMapping(value = "/user/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String updateUserPost(@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
                                 @RequestParam(value = "nickname", required = false, defaultValue = "default") String name,
                                 @RequestParam(value = "password", required = false, defaultValue = "pwd") String pwd) {
        User user = userService.selectById(uid);
        user.setNickname(name);
        user.setPassword(pwd);
        return userService.updateById(user) ? "success" : "Failure";
    }


    @RequestMapping(value = "/user/find", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String updateUserPost(@RequestParam(value = "length", required = false, defaultValue = "0") int length) {
        return String.valueOf(userService.findByNameLength(length));
    }
//    if (redisMapper.get("user_list").equals("")) {
//        redisMapper.save("user_list", "hi" + name, 30 * 24 * 60 * 60);
//    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTest(@RequestParam(value = "desc") String desc,
                             @RequestParam(value = "file") MultipartFile file) {

        if (file.isEmpty()) {
            return "file empty";
        } else {
            try {
                File folderFile = new File("/Users/huway_iosdev2/Desktop/tmp/");
                if (!folderFile.exists()) folderFile.mkdir();
                File tmpFile = new File(folderFile, UUID.randomUUID().toString() + ".jpg");
                file.transferTo(tmpFile);
            } catch (IOException e) {
                return "server error:" + e.toString();
            }
        }

        return "succeed :" + desc;
    }


}
