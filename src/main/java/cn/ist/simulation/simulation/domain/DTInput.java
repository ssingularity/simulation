package cn.ist.simulation.simulation.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 16:13
 */
@Data
public class DTInput {
    private Product product;

    private Map<Integer, Integer> vector = new HashMap<>();

    public String getId() {
        return product.getId();
    }
}
