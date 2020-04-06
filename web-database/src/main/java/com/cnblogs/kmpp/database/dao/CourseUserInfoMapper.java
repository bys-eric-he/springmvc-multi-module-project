package com.cnblogs.kmpp.database.dao;

import com.cnblogs.kmpp.database.model.CourseUserInfo;

public interface CourseUserInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseUserInfo record);

    int insertSelective(CourseUserInfo record);

    CourseUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseUserInfo record);

    int updateByPrimaryKey(CourseUserInfo record);
}