package com.cnblogs.kmpp.database.dao.Impl;

import java.util.List;
import java.util.Map;

import com.cnblogs.kmpp.database.dao.BaseDao;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

/**
 * 数据库操作抽像类
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    public void addObject(String statementName, T obj)
            throws DataAccessException {
        getSqlSession().insert(statementName, obj);
    }

    public int deleteObject(String statementName, String objId)
            throws DataAccessException {
        return getSqlSession().delete(statementName, objId);
    }

    public int deleteForMap(String statementName, Map<String, Object> map) throws DataAccessException {
        return getSqlSession().delete(statementName, map);
    }

    public T findObject(String statementName, String objId)
            throws DataAccessException {
        return getSqlSession().selectOne(statementName, objId);
    }

    public int updateObject(String statementName, T obj)
            throws DataAccessException {
        return getSqlSession().update(statementName, obj);
    }

    public int updateObjectState(String statementName, Map<String, Object> map)
            throws DataAccessException {
        return getSqlSession().update(statementName, map);
    }

    public int getObjectCount(String statementName, String filter) {
        Long obj = getSqlSession().selectOne(statementName, filter);
        return obj.intValue();
    }

    public int getObjectCount(String statementName, Map<String, Object> map) {
        Long obj = getSqlSession().selectOne(statementName, map);
        return obj.intValue();
    }

    public T findObject(String statementName, Map<String, Object> map) {
        return getSqlSession().selectOne(statementName, map);
    }

    public List<T> listByPage(String statementName, Map<String, Object> map,
                              int skipResults, int pageSize) {
        return getSqlSession().selectList(statementName, map, new RowBounds(skipResults, pageSize));
    }

    public List<T> listByPage(String statementName, String filter, int skipResults, int pageSize) {
        return getSqlSession().selectList(statementName, filter, new RowBounds(skipResults, pageSize));
    }

    public List<T> list(String statementName) {
        return getSqlSession().selectList(statementName);
    }

    public List<T> list(String statementName, String filter) {
        return getSqlSession().selectList(statementName, filter);
    }

    public List<T> list(String statementName, Map<String, Object> map) {
        return getSqlSession().selectList(statementName, map);
    }
}