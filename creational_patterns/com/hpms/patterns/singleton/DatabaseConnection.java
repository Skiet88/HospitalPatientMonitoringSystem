package com.hpms.patterns.singleton;

import java.util.UUID;

public final class DatabaseConnection {
    private static volatile DatabaseConnection instance;

    private final UUID connectionId;
    private boolean connected;

    private DatabaseConnection() {
        this.connectionId = UUID.randomUUID();
        this.connected = false;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public synchronized void connect() {
        connected = true;
    }

    public synchronized void disconnect() {
        connected = false;
    }

    public synchronized boolean isConnected() {
        return connected;
    }

    public UUID getConnectionId() {
        return connectionId;
    }
}