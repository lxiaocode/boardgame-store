package com.lxiaocode.boardgame.member.biz;

import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.domain.dto.RegisterDTO;
import com.lxiaocode.boardgame.member.domain.vo.MemberInfoVO;
import com.lxiaocode.boardgame.member.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:18
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MemberAction {

    private final MemberService memberService;

    public MemberAction(MemberService memberService) {
        this.memberService = memberService;
    }

    public Result register(RegisterDTO dto) {
        if (! memberService.checkUniqueMember(dto)){
            return Result.fail(DefaultApiCode.MEMBER_EXISTS);
        }
        Member member = new Member();
        BeanUtils.copyProperties(dto, member);
        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        member.setAvatar("http://img.lxiaocode.com/123456789.png");
        memberService.saveMember(member);
        return Result.success("注册成功");
    }

    public Result getMemberInformation(String userId) {
        MemberInfoVO vo = new MemberInfoVO();
        Member member = memberService.findMemberByUserId(userId).get();
        BeanUtils.copyProperties(member, vo);

        return Result.success().addResult(vo);
    }
}
