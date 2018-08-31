package com.cmrh.journey.system.common.org.n3r.idworker;

/**
 * @author pangbohuan
 */
public interface RandomCodeStrategy {
    /**
     * 初始化
     * */
    void init();

    /**
     * 前处理
     * @return int
     * */
    int prefix();

    /**
     * 后处理
     * @return int
     * */
    int next();

    /**
     * 中间处理
     * */
    void release();
}
