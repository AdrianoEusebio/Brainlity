package com.example.brainlity.DAO;

public class SyncManager {
    private static SyncManager instance;
    private boolean syncNeeded = true;

    private SyncManager() {
        // Construtor privado para Singleton
    }

    public static SyncManager getInstance() {
        if (instance == null) {
            instance = new SyncManager();
        }
        return instance;
    }

    public boolean isSyncNeeded() {
        return syncNeeded;
    }

    public void setSyncNeeded(boolean syncNeeded) {
        this.syncNeeded = syncNeeded;
    }
}
