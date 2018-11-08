package com.pbh.journey.system.common.utils.util;

import com.pbh.journey.system.common.utils.exception.BussinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author pangbohuan
 * @description 由于项目中使用了太多(等值比较非等值比较非空比较), 所以自定义各种值比较场景判断减少代码的重复量,并且符合条件抛出异常
 * @date 2018-11-08 14:14
 **/
public final class CompareSceneException {


    /**
     * 自定义检验两个数型是否相等-如果相等的情况下抛出异常
     *
     * @param base 值一
     * @param val  值二
     * @param err  异常信息
     */
    public static void customNumericalEquality(long base, long val, String err) {
        if (base == val) {
            throw new BussinessException(err);
        }
    }

    /**
     * 自定义检验两个数型值是否相等-如果相等的情况下抛出异常
     *
     * @param base 值一
     * @param val  值二
     * @param err  异常信息
     */
    public static void customNumericalNotEquality(long base, long val, String err) {
        if (base != val) {
            throw new BussinessException(err);
        }
    }

    /**
     * 如果一个字符值为空值-抛出异常
     *
     * @param val 值一
     * @param err 异常信息
     */
    public static void customStringIsNull(String val, String err) {
        if (StringUtils.isEmpty(val)) {
            throw new BussinessException(err);
        }
    }

    /**
     * 如果两个字符值相等-抛出异常
     *
     * @param base 值一
     * @param val  值二
     * @param err  异常信息
     */
    public static void customStringEquality(String base, String val, String err) {
        if (base.equals(val)) {
            throw new BussinessException(err);
        }
    }


    /**
     * 如果两个字符值不相等-抛出异常
     *
     * @param base 值一
     * @param val  值二
     * @param err  异常信息
     */
    public static void customStringNotEquality(String base, String val, String err) {
        if (!base.equals(val)) {
            throw new BussinessException(err);
        }
    }

    /**
     * 如果一个对象为空
     *
     * @param object 对象
     * @param err    异常信息
     */
    public static void customObjectIsNull(Object object, String err) {
        if (object == null) {
            throw new BussinessException(err);
        }
    }

    /**
     * 如果一个对象不为空
     *
     * @param object 对象
     * @param err    异常信息
     */
    public static void customObjectIsNotNull(Object object, String err) {
        if (object != null) {
            throw new BussinessException(err);
        }
    }


    /**
     * 校验手机号码的合法性
     *
     * @param mobile 手机号
     * @param err    异常信息
     */
    public static void mobileCheckout(String mobile, String err) {
        if (!RegexUtils.checkMobile(mobile)) {
            throw new BussinessException(err);
        }
    }

    /**
     * 校验身份证号码的合法性
     *
     * @param idCard 身份证号
     * @param err    异常信息
     */
    public static void idCardCheckout(String idCard, String err) {
        if (!RegexUtils.checkIdCard(idCard)) {
            throw new BussinessException(err);
        }
    }

    /**
     * 校验邮箱号码的合法性
     *
     * @param mailbox 邮箱号
     * @param err     异常信息
     */
    public static void mailboxCheckout(String mailbox, String err) {
        if (!RegexUtils.checkEmail(mailbox)) {
            throw new BussinessException(err);
        }
    }
}
