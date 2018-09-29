package com.pbh.journey.system.common.result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author pangbohuan
 * @description: 定义一个统一的返回格式
 * @date 2018-07-25 17:40
 **/
@Data
public class JourneySystemAppResult implements Serializable {

    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应业务状态
     */
    private String code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    public static JourneySystemAppResult build(Integer status, String msg, Object data) {
        return new JourneySystemAppResult(status, msg, data);
    }

    public static JourneySystemAppResult ok(Object data) {
        return new JourneySystemAppResult(data);
    }

    public static JourneySystemAppResult ok() {
        return new JourneySystemAppResult(null);
    }

    public static JourneySystemAppResult errorMsg(String msg) {
        return new JourneySystemAppResult(ResponseStatusCode.BUSINESS_EXCEPTIONS, msg, null);
    }

    public static JourneySystemAppResult errorMap(String msg, Object data) {
        return new JourneySystemAppResult(ResponseStatusCode.BUSINESS_EXCEPTIONS, msg, data);
    }

    public static JourneySystemAppResult errorTokenMsg(String msg) {
        return new JourneySystemAppResult(ResponseStatusCode.TOKEN_EXCEPTION, msg, null);
    }

    public static JourneySystemAppResult errorException(String msg) {
        return new JourneySystemAppResult(ResponseStatusCode.SYSTEM_EXCEPTION, msg, null);
    }


    public JourneySystemAppResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.code = "N";
    }

    public JourneySystemAppResult(Object data) {
        this.status = ResponseStatusCode.SUCCEED;
        this.msg = "OK";
        this.data = data;
        this.code = "Y";
    }

    /**
     * @param jsonData
     * @param clazz
     * @return
     * @Description: 将json结果集转化为LeeJSONResult对象
     * 需要转换的对象是一个类
     */
    public static JourneySystemAppResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JourneySystemAppResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param json
     * @return
     * @Description: 没有object对象的转化
     */
    public static JourneySystemAppResult format(String json) {
        try {
            return MAPPER.readValue(json, JourneySystemAppResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonData
     * @param clazz
     * @return
     * @Description: Object是集合转化
     * 需要转换的对象是一个list
     */
    public static JourneySystemAppResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
