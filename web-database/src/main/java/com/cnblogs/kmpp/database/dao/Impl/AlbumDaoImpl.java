package com.cnblogs.kmpp.database.dao.Impl;

import com.cnblogs.kmpp.database.dao.AlbumDao;
import com.cnblogs.kmpp.database.model.AlbumInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专辑数据库操作层
 */
@SuppressWarnings("rawtypes")
public class AlbumDaoImpl extends BaseDaoImpl<AlbumInfo> implements AlbumDao {

    public void insert(AlbumInfo albumInfo) {
        addObject("Album.add", albumInfo);
    }

    public AlbumInfo find(String id) {
        return findObject("Album.find", id);
    }

    public long count(String filter) {
        return getObjectCount("Album.count", filter);
    }

    public List<AlbumInfo> listByPage(Map<String, Object> paramMap, int skipResults, int pageSize) {
        return listByPage("Album.list", paramMap, skipResults, pageSize);
    }

    public List<AlbumInfo> listByStatement(String filter) {
        return list("Album.listByFilter", filter);
    }

    public void update(AlbumInfo albumInfo) {
        updateObject("Album.update", albumInfo);
    }

    public void updateState(String id, String state) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("isValid", state);
        updateObjectState("Album.updateState", paramMap);
    }
}