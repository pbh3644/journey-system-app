package com.pbh.journey.system.common.utils.util;

import com.pbh.journey.system.common.utils.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author pangbohuan
 * @description 当前用户
 * @date 2018-11-02 11:15
 **/
public class CurrentUserUtils extends JwtTokenUtils {
    /**
     * 根据登录账号保存用户信息到redis中
     *
     * @param userAccount  登录账号
     * @param jsonSysUser  SysUserDTO DTO的Json串
     * @param isRememberMe 是否记住密码
     * @return true
     */
    public static boolean saveLoginUser(String userAccount, String jsonSysUser, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return RedisUtils.set(CommonConstants.SYSTEM_CODE + userAccount, jsonSysUser, expiration);
    }

    /**
     * 查看登录账号是否已过期-从redis查
     *
     * @param userAccount
     * @return 是否
     */
    public static boolean isExpiration(String userAccount) {
        return RedisUtils.hasKey(CommonConstants.SYSTEM_CODE + userAccount);
    }

    /**
     * 根据token从redis删除缓存
     */
    public static void delToken(String token) {
        delUserAccount(getUserAccount(token));
    }

    /**
     * 根据登录账号-从redis删除缓存
     */
    public static void delUserAccount(String userAccount) {
        RedisUtils.del(CommonConstants.SYSTEM_CODE + userAccount);
    }

    /**
     * 获取当前的登录账号
     * 请求的request
     */
    public static String getUserAccount() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        return getUserAccount(token);
    }

    /**
     * 获取当前登录用户json串
     *
     * @return json串, 可以转换成
     */
    public static String getCurrentUserJson() {
        String userAccount = getUserAccount();
        return RedisUtils.get(CommonConstants.SYSTEM_CODE + userAccount).toString();
    }

    /**
     * 获取当前登录用户map
     *
     * @return Map
     */
    public static Map<String, String> getCurrentUserMap() {
        String userAccount = getUserAccount();
        return getCurrentUserMap(userAccount);
    }

    /**
     * 获取当前登录用户map
     *
     * @param userAccount
     * @return Map
     */
    public static Map<String, String> getCurrentUserMap(String userAccount) {
        if (isExpiration(userAccount)) {
            return (Map<String, String>) RedisUtils.get(CommonConstants.SYSTEM_CODE + userAccount);
        }
        return null;
    }


    /**
     * 获取当前登录用户ID
     *
     * @return id
     */
    public static Long getCurrentUserId() {
        String userAccount = getUserAccount();
        return getCurrentUserId(userAccount);
    }

    /**
     * 获取当前登录用户ID
     *
     * @param userAccount
     */
    public static Long getCurrentUserId(String userAccount) {
        Map<String, String> currentUser = getCurrentUserMap(userAccount);
        if (currentUser == null) {
            return null;
        }
        String id = currentUser.get("id");
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return Long.parseLong(id);
    }
}
