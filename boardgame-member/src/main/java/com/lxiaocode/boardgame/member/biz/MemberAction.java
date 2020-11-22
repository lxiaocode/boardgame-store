package com.lxiaocode.boardgame.member.biz;

import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.domain.dto.RegisterDTO;
import com.lxiaocode.boardgame.member.domain.vo.MemberInfoVO;
import com.lxiaocode.boardgame.member.service.MemberService;
import com.lxiaocode.boardgame.member.service.WalletService;
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

    private final WalletAction walletAction;

    public MemberAction(MemberService memberService, WalletAction walletAction) {
        this.memberService = memberService;
        this.walletAction = walletAction;
    }

    /**
     * 用户会员注册
     * @param dto
     * @return
     */
    public Result register(RegisterDTO dto) {
        if (! memberService.checkUniqueMember(dto)){
            return Result.fail(DefaultApiCode.MEMBER_EXISTS);
        }
        Member member = new Member();
        BeanUtils.copyProperties(dto, member);
        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        member.setAvatar("http://img.lxiaocode.com/123456789.png");
        memberService.saveMember(member);
        // init a wallet
        walletAction.initWalletByMemberId(member.getId());
        return Result.success("注册成功");
    }

    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public Result getMemberInformation(String memberId) {
        MemberInfoVO vo = new MemberInfoVO();
        Member member = memberService.findMemberByMemberId(memberId).get();
        BeanUtils.copyProperties(member, vo);

        return Result.success().addResult(vo);
    }
}
