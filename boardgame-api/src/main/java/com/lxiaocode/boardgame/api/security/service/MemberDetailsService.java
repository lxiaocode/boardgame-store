package com.lxiaocode.boardgame.api.security.service;

import com.lxiaocode.boardgame.auth.domain.SecurityUserDetails;
import com.lxiaocode.boardgame.auth.service.SecurityUserDetailsService;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.service.MemberServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:34
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MemberDetailsService implements SecurityUserDetailsService {

    private final MemberServiceImpl memberServiceImpl;

    public MemberDetailsService(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberServiceImpl.getByUsername(username).orElseGet(Member::new);

        SecurityUserDetails memberDetails = new SecurityUserDetails();
        BeanUtils.copyProperties(member, memberDetails);
        // TODO 加载权限
        return memberDetails;
    }

    @Override
    public Optional<UserDetails> loadUserByUserId(String memberId) {
        return memberServiceImpl.getByUserId(memberId).map(member -> {
            SecurityUserDetails memberDetails = new SecurityUserDetails();
            BeanUtils.copyProperties(member, memberDetails);
            // TODO 加载权限
            return memberDetails;
        });
    }
}
