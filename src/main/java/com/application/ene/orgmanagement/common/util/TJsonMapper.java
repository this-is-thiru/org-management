package com.application.ene.orgmanagement.common.util;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;


@Component
@RequiredArgsConstructor
public class TJsonMapper {

    private static JsonMapper JSON_MAPPER;

    @Autowired
    public TJsonMapper(JsonMapper jsonMapper) {
        TJsonMapper.JSON_MAPPER = jsonMapper;
    }

    public static <T> T copy(Object source, Class<T> targetClass) {
        return readValue(writeValueAsString(source), targetClass);
    }

    public static <T> List<T> readAsList(String content, Class<T> targetClass) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        JavaType listType = JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, targetClass);
        return JSON_MAPPER.readValue(content, listType);
    }

    public static <T> List<T> readAsList(Object object, Class<T> targetClass) {

        if (object == null) {
            return null;
        }

        String content = writeValueAsString(object);
        JavaType listType = JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, targetClass);
        return JSON_MAPPER.readValue(content, listType);
    }

    private static <T> T readValue(String content, Class<T> targetClass) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return JSON_MAPPER.readValue(content, targetClass);
    }

    public static String writeValueAsString(Object source) {
        return JSON_MAPPER.writeValueAsString(source);
    }
}
