package com.cnblogs.kmpp.api;

import com.cnblogs.kmpp.domain.Album;
import com.cnblogs.kmpp.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "/api/v1/album", description = "专辑信息操作接口")
@RequestMapping("/api/v1/album")
public class AlbumAPI {

    @Resource(name = "albumService")
    private AlbumService albumService;

    private static Logger log = Logger.getLogger(AlbumAPI.class);

    @ApiOperation(value = "插入专辑信息", httpMethod = "POST")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestBody Album album) throws ParseException, IOException {
        if (log.isInfoEnabled()) {
            log.info("visit->http://localhost:8098/api/v1/album/insert");
        }

        albumService.insert(album);
        return "---->save album successful!";
    }

    @ApiOperation(value = "获取所有专辑列表", httpMethod = "GET")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Album> list() throws Exception {
        if (log.isInfoEnabled()) {
            log.info("visit->http://localhost:8098/api/v1/album/list");
        }

        return albumService.list();
    }
}
