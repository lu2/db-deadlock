package net.haltuf.playground.dbdeadlock.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DbDeadLockingService implements AlphaBetaWriterService {

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private BetaService betaService;

    @Autowired
    private ThreadSleepService threadSleepService;


    @Override
    public void writeAlphaBeta() {
        alphaService.writeAlpha();
        threadSleepService.sleepForSomeTime();
        betaService.writeBeta();
    }

    @Override
    public void writeBetaAlpha() {
        betaService.writeBeta();
        threadSleepService.sleepForSomeTime();
        alphaService.writeAlpha();
    }

}
