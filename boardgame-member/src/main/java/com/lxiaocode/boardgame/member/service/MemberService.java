package com.lxiaocode.boardgame.member.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.domain.MemberMapper;
import com.lxiaocode.boardgame.member.domain.dto.RegisterDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:11
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /**
     * query the member by username.
     * @param username
     * @return
     */
    public Optional<Member> findMemberByUsername(String username) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        Member member = memberMapper.selectOne(wrapper);
        return Optional.ofNullable(member);
    }

    /**
     * save member information
     * @return
     */
    public String saveMember(Member member) {
        memberMapper.insert(member);
        return member.getId();
    }

    /**
     * check member uniqueness
     * @param dto
     * @return
     */
    public boolean checkUniqueMember(RegisterDTO dto) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username", dto.getUsername())
                .or().eq("email", dto.getEmail())
                .or().eq("telephone", dto.getTelephone());
        return memberMapper.selectCount(wrapper) == 0;
    }
}
