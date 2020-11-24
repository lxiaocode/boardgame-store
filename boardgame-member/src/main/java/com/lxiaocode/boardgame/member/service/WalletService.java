package com.lxiaocode.boardgame.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lxiaocode.boardgame.member.domain.Wallet;
import com.lxiaocode.boardgame.member.domain.WalletMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * wallet
 * @author lixiaofeng
 * @date 2020/11/21 下午7:51
 * @blog http://www.lxiaocode.com/
 */
@Service
public class WalletService {

    private final WalletMapper walletMapper;

    public WalletService(WalletMapper walletMapper) {
        this.walletMapper = walletMapper;
    }

    /**
     * init a wallet
     * @param wallet
     * @return wallet id
     */
    public String saveWallet(Wallet wallet) {
        walletMapper.insert(wallet);
        return wallet.getId();
    }

    /**
     * get the number of wallets by member id
     * @param memberId member id
     * @return number of wallets
     */
    public int countWalletByMemberId(String memberId) {
        return walletMapper.selectCount(
                Wrappers.<Wallet>lambdaQuery().eq(Wallet::getMemberId, memberId));
    }

    /**
     * find the wallet by member id
     * @param memberId member id
     * @return
     */
    public Wallet findWalletByMemberId(String memberId) {
        return walletMapper.selectOne(
                Wrappers.<Wallet>lambdaQuery().eq(Wallet::getMemberId, memberId));
    }
}
