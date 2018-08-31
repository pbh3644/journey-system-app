package com.cmrh.journey.system.common.utils.util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * @author pangbohuan
 * @description 获取请求IP
 * @date 2018-08-15 10:04
 **/
public class IpUtils {

    private final static String UNKNOWN = "unknown";
    private final static String DOC = ",";

    /**
     * 获取请求的ip地址
     *
     * @param request http请求
     * @return ip地址
     */
    public static String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.contains(DOC)) {
            ip = ip.substring(ip.lastIndexOf(DOC) + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * ip地址是否可以ping通
     *
     * @param ipAddress ip地址
     * @param timeout   超时，单位毫秒
     * @return true | false
     */
    public static boolean ipCanPing(String ipAddress, int timeout) {

        if (StringUtil.isEmpty(ipAddress)) {
            return false;
        }

        boolean canPing = false;
        try {
            canPing = InetAddress.getByName(ipAddress).isReachable(timeout);
        } catch (IOException e) {
            return canPing;
        }
        return canPing;
    }

    /**
     * 链接是否可以被请求
     *
     * @param url     地址
     * @param timeout 超时，单位毫秒
     * @return true | false
     */
    public static boolean addrCanConnect(String url, int timeout) {

        if (StringUtil.isEmpty(url)) {
            return false;
        }

        boolean canConnect = false;
        try {
            URL urlStr = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlStr.openConnection();
            connection.setUseCaches(false);
            connection.setConnectTimeout(timeout);
            int state = connection.getResponseCode();
            if (state == HttpURLConnection.HTTP_OK) {
                canConnect = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return canConnect;
    }
}
