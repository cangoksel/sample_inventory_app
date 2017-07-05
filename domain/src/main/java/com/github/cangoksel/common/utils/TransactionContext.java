package com.github.cangoksel.common.utils;

import java.util.UUID;

/**
 * Created by herdemir on 09.09.2015.
 */
public enum TransactionContext {
    INSTANCE;

    private final ThreadLocal<Transaction> threadLocal = new InheritableThreadLocal<>();

    public void create(final Object anyObject) {
        if (threadLocal.get() == null) {
            threadLocal.set(new Transaction(UUID.randomUUID(), anyObject));
        }
    }

    public UUID getTransactionId() {
        return threadLocal.get() == null ? null : threadLocal.get().getId();
    }

    public void clear(final Object anyObject) {
        if (threadLocal.get() != null && anyObject.equals(threadLocal.get().getAnyObject())) {
            threadLocal.remove();
        }
    }

    public static class Transaction {
        private final UUID id;
        private final Object anyObject;

        public Transaction(UUID id, Object anyObject) {
            this.id = id;
            this.anyObject = anyObject;
        }

        public UUID getId() {
            return id;
        }

        public Object getAnyObject() {
            return anyObject;
        }
    }
}
