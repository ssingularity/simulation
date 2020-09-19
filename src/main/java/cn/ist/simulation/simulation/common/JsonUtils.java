package cn.ist.simulation.simulation.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 1:00
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(source + " can't convert to string");
        }
    }
}
