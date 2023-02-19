package net.haltuf.playground.dbdeadlock.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class DbNotDeadLockingServiceTest extends DbDeadLockingServiceTest {

    @Autowired
    private AlphaBetaWriterService dbNotDeadLockingService;

    @PostConstruct
    void setupOnce() {
        alphaBetaWriterService = dbNotDeadLockingService;
    }

    protected void checkThreadException() throws Exception {
        // not expecting deadlock here
        throwExceptionIfAny();
    }

}
