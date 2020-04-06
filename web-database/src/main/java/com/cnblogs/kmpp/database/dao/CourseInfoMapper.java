package com.cnblogs.kmpp.database.dao;

import com.cnblogs.kmpp.database.model.CourseInfo;

public interface CourseInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
}