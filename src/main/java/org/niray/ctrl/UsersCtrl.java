package org.niray.ctrl;

import org.niray.redis.RedisMapper;
import org.niray.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mac on 16/10/24.
 */
@Controller
public class UsersCtrl {

    @Autowired
    private RedisMapper redisMapper;

    private IUserService userService;

//    @Autowired
//    public UsersCtrl(IUserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping(value = "/mb", method = RequestMethod.POST)
    @ResponseBody
    public String helloKotlin(@RequestParam(value = "name", required = false, defaultValue = "name") String name) {
        redisMapper.save("hi", "hi" + name, 30 * 24 * 60 * 60);
//        List list = userService.selectList(new EntityWrapper<User>());
        return name;
    }


}
