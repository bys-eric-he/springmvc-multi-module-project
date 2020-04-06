package com.cnblogs.kmpp.api;

import com.cnblogs.kmpp.common.CompactDisc;
import com.cnblogs.kmpp.service.Performance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/api/v1/performance", description = "Performance信息操作接口")
@RequestMapping("/api/v1/performance")
public class PerformanceAPI {

    @Autowired
    private Performance performance;

    @Autowired
    private CompactDisc blankDisc;

    private static Logger log = Logger.getLogger(PerformanceAPI.class);

    @ApiOperation(value = "打印Performance信息", httpMethod = "POST")
    @RequestMapping(value = "/perform", method = RequestMethod.POST)
    public void perform() throws Exception {
        try {
            log.info("visit->http://localhost:8098/api/v1/performance/perform");
            performance.perform();
            performance.perform(100);
            blankDisc.play();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
