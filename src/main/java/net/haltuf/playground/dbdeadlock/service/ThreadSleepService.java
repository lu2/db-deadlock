package net.haltuf.playground.dbdeadlock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThreadSleepService {

    public void sleepForSomeTime() {
        sleep(1000);
    }

    public void sleep(long millis) {
        try {
            log.info("Sleep START");
            Thread.sleep(millis);
            log.info("Sleep FINISH");
        } catch (InterruptedException e) {
            log.warn("Sleep INTERRUPT");
        }
    }

}
