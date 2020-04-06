package com.cnblogs.kmpp.service;

import com.cnblogs.kmpp.domain.Album;
import org.apache.http.ParseException;

import java.io.IOException;
import java.util.List;

public interface AlbumService {
    /**
     * 添加专辑
     *
     * @param album 专辑
     */
    void insert(Album album) throws ParseException, IOException;

    /**
     * 所有专辑列表
     */
    List<Album> list() throws Exception;
}
