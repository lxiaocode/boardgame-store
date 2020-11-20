package com.lxiaocode.boardgame.auth.service;

import com.lxiaocode.boardgame.auth.domain.MemberDetails;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:34
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberService memberService;

    public MemberDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberService.findMemberByUsername(username).orElseGet(Member::new);

        MemberDetails memberDetails = new MemberDetails();
        BeanUtils.copyProperties(member, memberDetails);
        // TODO 加载权限
        return memberDetails;
    }

    public Optional<UserDetails> loadUserByUserId(String userId) {
        return memberService.findMemberByUserId(userId).map(member -> {
            MemberDetails memberDetails = new MemberDetails();
            BeanUtils.copyProperties(member, memberDetails);
            // TODO 加载权限
            return memberDetails;
        });
    }
}
