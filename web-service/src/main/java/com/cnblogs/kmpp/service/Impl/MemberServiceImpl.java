package com.cnblogs.kmpp.service.Impl;

import com.cnblogs.kmpp.domain.Member;
import com.cnblogs.kmpp.redis.MemberDao;
import com.cnblogs.kmpp.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    public void add(Member member) {
        memberDao.add(member);
    }

    public void delete(String id) {
        memberDao.delete(id);
    }

    public Member get(String id) {
        return memberDao.get(id);
    }
}
