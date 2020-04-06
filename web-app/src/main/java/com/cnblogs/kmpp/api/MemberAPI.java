package com.cnblogs.kmpp.api;

import com.cnblogs.kmpp.domain.Member;
import com.cnblogs.kmpp.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/api/v1/member", description = "Member信息操作接口")
@RequestMapping("/api/v1/member")
public class MemberAPI {
    @Autowired
    private MemberService memberService;

    private static Logger log = Logger.getLogger(PerformanceAPI.class);

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存Member信息", httpMethod = "POST")
    public void saveMember() {
        log.info("visit->http://localhost:8098/api/v1/member/save");

        Member member = new Member();
        member.setId("00003");
        member.setNickname("zheng");
        memberService.add(member);

        member = memberService.get("00003");
        log.info("memberName->" + member.getNickname());

        member = memberService.get("00002");
        log.info("memberName->" + member.getNickname());

        memberService.delete("00001");
    }
}
