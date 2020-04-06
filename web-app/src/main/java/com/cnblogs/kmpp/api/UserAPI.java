package com.cnblogs.kmpp.api;

import com.cnblogs.kmpp.domain.User;
import com.cnblogs.kmpp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "/api/v1/user", description = "User信息操作接口")
@RequestMapping("/api/v1/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    private static Logger log = Logger.getLogger(UserAPI.class);

    @ApiOperation(value = "显示指定用户信息", httpMethod = "GET")
    @RequestMapping(value = "/showInfo/{userId}", method = {RequestMethod.GET})
    public String showUserInfo(ModelMap modelMap, @PathVariable int userId) throws ParseException, IOException {
        User userInfo = userService.getUserById(userId);
        modelMap.addAttribute("userInfo", userInfo);
        return "index.jsp";
    }

    @RequestMapping(value = "/showInfos", method = RequestMethod.GET)
    @ApiOperation(value = "显示所有用户信息", httpMethod = "GET")
    @ResponseBody
    public List<User> showUserInfos() throws ParseException, IOException {
        if (log.isInfoEnabled()) {
            log.info("visit->http://localhost:8098/api/v1/user/showInfos");
        }
        return userService.getUsers();
    }
}
