package com.lxiaocode.boardgame.member.biz;

import com.lxiaocode.boardgame.member.service.WalletService;
import org.springframework.stereotype.Service;

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
        walletService.saveWallet(memberId);
    }
}
