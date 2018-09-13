package com.pbh.journey.system.common.utils.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 端口生成工具类
 *
 * @author pangbohuan
 * @create 2018-08-23 10:04
 **/
public class ServerPortUtils {
    private final static Logger logger = LoggerFactory.getLogger(ServerPortUtils.class);

    /**
     * 获取唯一端口
     */
    public static void getSolePort(String[] args) {
        Boolean isServerPort = false;
        String serverPort = "";
        if (args != null) {
            for (String arg : args) {
                if (StringUtils.hasText(arg) && arg.startsWith("--server.port")) {
                    isServerPort = true;
                    serverPort = arg;
                    break;
                }
            }
        }
        // 没有指定端口，则随机生成一个可用的端口
        if (!isServerPort) {
            int port = getAvailablePort();
            logger.info("current server.port=" + port);
            System.setProperty("server.port", String.valueOf(port));
        } else {
            logger.info("current server.port=" + serverPort.split("=")[1]);
            System.setProperty("server.port", serverPort.split("=")[1]);
        }
    }

    /**
     * 获取可用端口，范围2000-65535
     *
     * @return
     */
    public static int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();
        int port = random.nextInt(max) % (max - min + 1) + min;
        boolean using = NetUtils.isLoclePortUsing(port);
        if (using) {
            return getAvailablePort();
        } else {
            return port;
        }
    }

}
