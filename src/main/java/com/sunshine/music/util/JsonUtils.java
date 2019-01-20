package com.sunshine.music.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class JsonUtils {
    private static ObjectMapper OBJECT_MAPPER = JacksonMapperInstance.getInstance();

    static {
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static <T> String toJson(T o) {
        if (o != null && !(o instanceof String)) {
            try {
                return OBJECT_MAPPER.writeValueAsString(o);
            } catch (Exception e) {
                throw new RuntimeException("json parse fail");
            }
        }
        return (String) o;
    }

    public static JsonNode toJsonNode(String json) {
        JsonNode rst;
        try {
            rst = OBJECT_MAPPER.readTree(json);
            return rst;
        } catch (Exception var4) {
            log.error("json parse fail json: {}",json);
            throw new RuntimeException("json parse fail");
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        Object rst = null;

        try {
            rst = OBJECT_MAPPER.readValue(json, type);
            return (T) rst;
        } catch (Exception var4) {
            throw new RuntimeException("json parse fail");
        }
    }

    public static <T> T fromJson(String json, JavaType type) {
        Object rst = null;

        try {
            rst = OBJECT_MAPPER.readValue(json, type);
            return (T) rst;
        } catch (Exception var4) {
            throw new RuntimeException("json parse fail");
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        Object rst = null;

        try {
            rst = OBJECT_MAPPER.readValue(json, typeRef);
            return (T) rst;
        } catch (Exception var4) {
            throw new RuntimeException("json parse fail");
        }
    }
}
