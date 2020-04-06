package com.cnblogs.kmpp.database.dao;

import com.cnblogs.kmpp.database.model.AlbumInfo;

import java.util.List;
import java.util.Map;

/**
 * 专辑数据库相关操作接口
 * @author JohnFNash
 */
@SuppressWarnings("rawtypes")
public interface AlbumDao {
    /**
     * 添加专辑
     * @param albumInfo 专辑
     */
    void insert(AlbumInfo albumInfo);

    /**
     * 按照编号查询专辑
     * @param id
     * @return
     */
    AlbumInfo find(String id);

    /**
     * 统计专辑数目
     * @return 专辑数目
     */
    long count(String filter);

    /**
     * 按照条件分页查询专辑列表
     * @param paramMap
     * @param skipResults
     * @param pageSize
     * @return
     */
    List<AlbumInfo> listByPage(Map<String,Object> paramMap, int skipResults, int pageSize);

    /**
     * 按照条件查询专辑列表
     * @param filter
     * @return
     */
    List<AlbumInfo> list(String filter);

    /**
     * 更新专辑信息
     * @param albumInfo
     */
    void update(AlbumInfo albumInfo);

    /**
     * 启用/停用专辑信息
     * @param id
     * @param state
     */
    void updateState(String id, String state);
}
