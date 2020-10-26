package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTInput {
    private Product product;

    private Map<Integer, Integer> vector = new HashMap<>();

    public String getId() {
        return product.getId();
    }
}
