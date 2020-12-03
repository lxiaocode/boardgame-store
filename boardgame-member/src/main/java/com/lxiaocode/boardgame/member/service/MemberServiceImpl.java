package com.lxiaocode.boardgame.member.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.common.service.IUserService;
import com.lxiaocode.boardgame.member.domain.Member;
import com.lxiaocode.boardgame.member.domain.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:11
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IUserService<Member> {
    @Override
    public void saveOne(Member user) {
        super.save(user);
    }

    @Override
    public Optional<Member> getByUserId(String userId) {
        return super.lambdaQuery()
                .eq(Member::getId, userId)
                .oneOpt();
    }

    @Override
    public Optional<Member> getByUsername(String username) {
        return super.lambdaQuery()
                .eq(Member::getUsername, username)
                .oneOpt();
    }

    @Override
    public boolean checkUnique(Member user) {
        Integer count = super.lambdaQuery()
                .eq(Member::getUsername, user.getUsername())
                .or()
                .eq(Member::getEmail, user.getEmail())
                .or()
                .eq(Member::getTelephone, user.getTelephone()).count();
        return count == 0;
    }
}
