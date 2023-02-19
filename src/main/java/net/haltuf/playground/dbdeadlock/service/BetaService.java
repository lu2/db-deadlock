package net.haltuf.playground.dbdeadlock.service;

import lombok.extern.slf4j.Slf4j;
import net.haltuf.playground.dbdeadlock.entity.Beta;
import net.haltuf.playground.dbdeadlock.repository.BetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BetaService {

    @Autowired
    private BetaRepository betaRepository;


    public void lockBeta() {
        betaRepository.lockTable();
    }

    public void writeBeta() {
        log.info("Counting beta START");
        long betaCount = betaRepository.count();
        log.info("Counting beta FINISH");
        Beta beta = new Beta();
        log.info("Writing beta START");
        beta.setData(String.valueOf(betaCount));
        betaRepository.saveAndFlush(beta);
        log.info("Writing beta FINISH");
    }

}
