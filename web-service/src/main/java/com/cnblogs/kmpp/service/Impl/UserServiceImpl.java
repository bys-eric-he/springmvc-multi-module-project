package com.cnblogs.kmpp.service.Impl;


import com.cnblogs.kmpp.database.dao.UserInfoMapper;
import com.cnblogs.kmpp.database.model.CourseInfo;
import com.cnblogs.kmpp.database.model.UserInfo;
import com.cnblogs.kmpp.domain.Course;
import com.cnblogs.kmpp.domain.User;
import com.cnblogs.kmpp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
     * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
     * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
     * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
     * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
     * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
     */
    ObjectMapper mapper = new ObjectMapper();

    Logger log = Logger.getLogger(UserServiceImpl.class);

    public User getUserById(int id) throws ParseException, IOException {
        User user = new User();
        log.info("getUserById->".concat(String.valueOf(id)));
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        user.setId(userInfo.getId());
        user.setUname(userInfo.getUname());
        user.setUnumber(userInfo.getUnumber());

        log.info("get user->" + mapper.writeValueAsString(user));
        return user;
    }

    public List<User> getUsers() throws ParseException, IOException {
        List<User> users = new ArrayList<User>();
        List<UserInfo> userInfos = userInfoMapper.selectAll();

        for (UserInfo userinfo : userInfos) {
            User user = new User();
            List<Course> courseInfos = new ArrayList<Course>();
            user.setId(userinfo.getId());
            user.setUname(userinfo.getUname());
            user.setUnumber(userinfo.getUnumber());
            for (CourseInfo courseInfo : userinfo.getCourseInfos()) {
                Course course = new Course();
                course.setId(courseInfo.getId());
                course.setCname(courseInfo.getCname());
                course.setCaddress(courseInfo.getCaddress());
                courseInfos.add(course);
            }
            user.setCourseInfos(courseInfos);
            users.add(user);
        }

        if (log.isInfoEnabled()) {
            log.info("users->" + mapper.writeValueAsString(users));
        }
        return users;
    }

    public int insert(User user) throws ParseException, IOException {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUname(user.getUname());
        userInfo.setUnumber(user.getUnumber());
        int result = userInfoMapper.insert(userInfo);

        //User类转JSON
        //输出结果：{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}
        String json = mapper.writeValueAsString(userInfo);
        //返序列化
        //user = mapper.readValue(json, User.class);
        log.info("save users ->" + json);
        return result;
    }
}