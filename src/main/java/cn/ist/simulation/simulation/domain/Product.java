package cn.ist.simulation.simulation.domain;

import lombok.Data;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 13:27
 */
@Data
public class Product {
    private String id;

    private String sender;

    private ProductState state;

    private ProductType type;

    /**
     * sealing machine selection, 1 for SEALING_MACHINE1 and 2 for SEALING_MACHINE2
     */
    private short sm;

    private long timestamp;

    public Product() {
        timestamp = System.currentTimeMillis();
    }
}
