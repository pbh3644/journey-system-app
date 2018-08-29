package com.cmrh.journey.system.org.n3r.idworker;

/**
 * @author pangbohuan
 */
public interface WorkerIdStrategy {
    /**
     * 初始化前
     */
    void initialize();

    /**
     * 获取唯一ID
     *
     * @return long
     */
    long availableWorkerId();

    /**
     * 获取唯一ID
     * */
    void release();
}
