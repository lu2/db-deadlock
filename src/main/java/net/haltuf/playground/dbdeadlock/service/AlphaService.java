package net.haltuf.playground.dbdeadlock.service;

import lombok.extern.slf4j.Slf4j;
import net.haltuf.playground.dbdeadlock.entity.Alpha;
import net.haltuf.playground.dbdeadlock.repository.AlphaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlphaService {

    @Autowired
    private AlphaRepository alphaRepository;

    public void lockAlpha() {
        alphaRepository.lockTable();
    }

    public void writeAlpha() {
        log.info("Counting alpha START");
        long alphaCount = alphaRepository.count();
        log.info("Counting alpha FINISH");
        Alpha alpha = new Alpha();
        log.info("Writing alpha START");
        alpha.setData(String.valueOf(alphaCount));
        alphaRepository.saveAndFlush(alpha);
        log.info("Writing alpha FINISH");
    }

}
