package com.cnblogs.kmpp.service;

import com.cnblogs.kmpp.domain.User;
import org.apache.http.ParseException;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User getUserById(int id) throws ParseException, IOException;

    List<User> getUsers() throws ParseException, IOException;

    int insert(User user) throws ParseException, IOException;
}
