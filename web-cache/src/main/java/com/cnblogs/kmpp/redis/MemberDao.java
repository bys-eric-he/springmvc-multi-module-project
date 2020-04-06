package com.cnblogs.kmpp.redis;

import com.cnblogs.kmpp.domain.Member;

import java.util.List;

public interface MemberDao {
    boolean add(Member member);
    boolean add(List<Member> list);
    void delete(String key);
    Member get(String keyId);
}
