package com.lxiaocode.boardgame.member.biz;

import com.lxiaocode.boardgame.member.domain.Wallet;
import com.lxiaocode.boardgame.member.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lixiaofeng
 * @date 2020/11/21 下午8:58
 * @blog http://www.lxiaocode.com/
 */
@Service
public class WalletAction {

    private final WalletService walletService;

    public WalletAction(WalletService walletService) {
        this.walletService = walletService;
    }

    public void initWalletByMemberId(String memberId) {
        Wallet wallet = new Wallet();
        wallet.setMemberId(memberId);
        wallet.setMoney(0L);
        wallet.setFroze(0L);

        walletService.saveWallet(wallet);
    }
}
