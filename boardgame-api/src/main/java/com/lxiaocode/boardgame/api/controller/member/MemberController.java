package com.lxiaocode.boardgame.api.controller.member;

import com.lxiaocode.boardgame.auth.domain.MemberDetails;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.member.biz.MemberAction;
import com.lxiaocode.boardgame.member.domain.dto.RegisterDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lixiaofeng
 * @date 2020/11/19 下午6:27
 * @blog http://www.lxiaocode.com/
 */
@Valid
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberAction memberAction;

    public MemberController(MemberAction memberAction) {
        this.memberAction = memberAction;
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Valid RegisterDTO registerDTO) {
        return memberAction.register(registerDTO);
    }

    @GetMapping("/info")
    public Result getMemberInformation(Authentication authentication) {
        String userId = ((MemberDetails) authentication.getPrincipal()).getId();
        return memberAction.getMemberInformation(userId);
    }
}
