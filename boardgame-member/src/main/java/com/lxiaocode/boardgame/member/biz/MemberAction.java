package com.lxiaocode.boardgame.member.biz;

import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.domain.dto.RegisterDTO;
import com.lxiaocode.boardgame.member.domain.vo.MemberInfoVO;
import com.lxiaocode.boardgame.member.service.MemberServiceImpl;
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

    private final MemberServiceImpl memberServiceImpl;

    private final WalletAction walletAction;

    public MemberAction(MemberServiceImpl memberServiceImpl, WalletAction walletAction) {
        this.memberServiceImpl = memberServiceImpl;
        this.walletAction = walletAction;
    }

    /**
     * 用户会员注册
     * @param dto 会员注册参数
     * @return Result
     */
    public Result register(RegisterDTO dto) {
        Member member = new Member();
        BeanUtils.copyProperties(dto, member);

        if (! memberServiceImpl.checkUnique(member)){
            return Result.fail(DefaultApiCode.MEMBER_EXISTS);
        }else {
            member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
            member.setAvatar("http://img.lxiaocode.com/123456789.png");
            memberServiceImpl.saveOne(member);
        }

        // 初始化钱包
        walletAction.initWalletByMemberId(member.getId());
        return Result.success("注册成功");
    }

    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public JsonResult<MemberInfoVO> getMemberInformation(String memberId) {
        MemberInfoVO vo = new MemberInfoVO();
        memberServiceImpl.getByUserId(memberId).ifPresent(member -> {
            BeanUtils.copyProperties(member, vo);
        });

        return Result.success().addResult(vo);
    }
}
