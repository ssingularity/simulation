package cn.ist.simulation.simulation.domain.DT;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/22 13:34
 */
public class Instruction {
    private Map<String, Object> instructions = new HashMap<>();

    public Object get(String key) {
        return instructions.get(key);
    }

    public void put(String key, Object value) {
        instructions.put(key, value);
    }
}
