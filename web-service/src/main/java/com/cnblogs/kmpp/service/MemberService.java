package com.cnblogs.kmpp.service;


import com.cnblogs.kmpp.domain.Member;

public interface MemberService {
    void add(Member member);
    void delete(String id);
    Member get(String id);
}
