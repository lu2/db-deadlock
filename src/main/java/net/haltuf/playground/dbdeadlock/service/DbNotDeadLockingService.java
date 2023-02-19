package net.haltuf.playground.dbdeadlock.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class DbNotDeadLockingService extends DbDeadLockingService {

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private BetaService betaService;


    @Override
    public void writeAlphaBeta() {
        log.info("Lock alpha beta START");
        alphaService.lockAlpha();
        betaService.lockBeta();
        log.info("Lock alpha beta FINISH");
        super.writeAlphaBeta();
    }

    @Override
    public void writeBetaAlpha() {
        log.info("Lock alpha beta START");
        alphaService.lockAlpha();
        betaService.lockBeta();
        log.info("Lock alpha beta FINISH");
        super.writeBetaAlpha();
    }

}
