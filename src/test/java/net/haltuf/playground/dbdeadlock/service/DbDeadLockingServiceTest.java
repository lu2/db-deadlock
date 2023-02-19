package net.haltuf.playground.dbdeadlock.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DbDeadLockingServiceTest {

    private List<Thread> threads;
    private List<Exception> exceptions;

    @Qualifier("dbDeadLockingService")
    @Autowired
    protected AlphaBetaWriterService alphaBetaWriterService;

    @BeforeEach
    void setUp() {
        threads = new ArrayList<>();
        exceptions = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void writeAlphaBeta() {
        alphaBetaWriterService.writeAlphaBeta();
    }

    @Test
    void writeBetaAlpha() {
        alphaBetaWriterService.writeBetaAlpha();
    }

    @Test
    void writeBothSameOrderAlphaBeta() throws Exception {
        threads.add(createAlphaBeta());
        threads.add(createAlphaBeta());
        threads.forEach(Thread::start);
        joinThreads();
        throwExceptionIfAny();
    }

    @Test
    void writeBothSameOrderBetaAlpha() throws Exception {
        threads.add(createBetaAlpha());
        threads.add(createBetaAlpha());
        threads.forEach(Thread::start);
        joinThreads();
        throwExceptionIfAny();
    }

    @Test
    void writeBothOppositeOrderAlphaBeta() throws Exception {
        threads.add(createAlphaBeta());
        threads.add(createBetaAlpha());
        threads.forEach(Thread::start);
        joinThreads();
        checkThreadException();
    }

    protected void checkThreadException() throws Exception {
        // expecting deadlock here
        assertThrows(JpaSystemException.class, this::throwExceptionIfAny);
    }

    protected void throwExceptionIfAny() throws Exception {
        for (Exception exception : exceptions) {
            throw exception;
        }
    }

    private Thread createAlphaBeta() {
        return new Thread(() -> {
            try {
                alphaBetaWriterService.writeAlphaBeta();
            } catch (Exception ex) {
                exceptions.add(ex);
            }
        });
    }

    private Thread createBetaAlpha() {
        return new Thread(() -> {
            try {
                alphaBetaWriterService.writeBetaAlpha();
            } catch (Exception ex) {
                exceptions.add(ex);
            }
        });
    }

    @SneakyThrows
    private void joinThreads() {
        for (Thread thread : threads) {
            thread.join();
        }
    }

}