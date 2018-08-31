package com.cmrh.journey.system.common.org.n3r.idworker;

import com.cmrh.journey.system.common.org.n3r.idworker.strategy.DefaultWorkerIdStrategy;

/**
 * @author pangbohuan
 */
public class Id {
    private static WorkerIdStrategy workerIdStrategy;
    private static IdWorker idWorker;

    static {
        configure(DefaultWorkerIdStrategy.WORKER_ID_STRATEGY);
    }

    public static synchronized void configure(WorkerIdStrategy custom) {
        if (workerIdStrategy == custom) {
            return;
        }

        if (workerIdStrategy != null) {
            workerIdStrategy.release();
        }
        workerIdStrategy = custom;
        workerIdStrategy.initialize();
        idWorker = new IdWorker(workerIdStrategy.availableWorkerId());
    }

    public static long next() {
        return idWorker.nextId();
    }

    public static long getWorkerId() {
        return idWorker.getWorkerId();
    }
}
