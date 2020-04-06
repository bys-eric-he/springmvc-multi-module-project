package com.cnblogs.kmpp.service.Impl;

import com.cnblogs.kmpp.database.dao.AlbumDao;
import com.cnblogs.kmpp.database.model.AlbumInfo;
import com.cnblogs.kmpp.domain.Album;
import com.cnblogs.kmpp.service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

    @Resource(name = "albumDao")
    private AlbumDao albumDao;

    /**
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
     * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
     * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
     * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
     * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
     * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
     */
    ObjectMapper mapper = new ObjectMapper();

    Logger log = Logger.getLogger(AlbumServiceImpl.class);

    public void insert(Album album) throws ParseException, IOException {
        AlbumInfo albumInfo = new AlbumInfo();
        albumInfo.setId(album.getId());
        albumInfo.setArea(album.getArea());
        albumInfo.setCompany(album.getCompany());
        albumInfo.setDescription(album.getDescription());
        albumInfo.setImage(album.getImage());
        albumInfo.setIsValid(album.getIsValid());
        albumInfo.setName(album.getName());
        albumInfo.setPlayCount(album.getPlayCount());
        albumInfo.setPublishDate(album.getPublishDate());
        albumInfo.setVersion(album.getVersion());
        //AlbumInfo类转JSON
        String json = mapper.writeValueAsString(albumInfo);
        //返序列化
        //albumInfo = mapper.readValue(json, AlbumInfo.class);
        log.info("save albumInfo ->" + json);

        try {
            albumDao.insert(albumInfo);
        } catch (Exception exception) {
            log.warn("save albumInfo exception->" + exception.getMessage());
        }
    }

    /**
     * 所有专辑列表
     */
    public List<Album> list() throws Exception {
        final List<Album> albums = new ArrayList<>();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("name", "Jay");
            List<AlbumInfo> albumInfos = albumDao.listByPage(paramMap, 0, 10);
            if (albumInfos == null || albumInfos.size() == 0) {
                return Collections.emptyList();
            }

            albumInfos.forEach(o -> {
                Album album = new Album();
                album.setId(o.getId());
                album.setArea(o.getArea());
                album.setCompany(o.getCompany());
                album.setDescription(o.getDescription());
                album.setImage(o.getImage());
                album.setIsValid(o.getIsValid());
                album.setName(o.getName());
                album.setPlayCount(o.getPlayCount());
                album.setPublishDate(o.getPublishDate());
                album.setVersion(o.getVersion());
                albums.add(album);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return albums;
    }
}
