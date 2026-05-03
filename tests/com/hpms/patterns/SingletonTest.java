package com.hpms.patterns;

import com.hpms.patterns.singleton.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingletonTest {

    @Test
    void returnsSingleInstance() {
        DatabaseConnection first = DatabaseConnection.getInstance();
        DatabaseConnection second = DatabaseConnection.getInstance();

        assertSame(first, second);
    }

    @Test
    void isThreadSafeUnderConcurrentAccess() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<Callable<DatabaseConnection>> tasks = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            tasks.add(DatabaseConnection::getInstance);
        }

        List<Future<DatabaseConnection>> futures = executor.invokeAll(tasks);
        Set<DatabaseConnection> uniqueInstances = new HashSet<>();
        for (Future<DatabaseConnection> future : futures) {
            uniqueInstances.add(future.get());
        }
        executor.shutdown();

        assertEquals(1, uniqueInstances.size());
        assertTrue(executor.isShutdown());
    }
}