package cn.ist.simulation.simulation.domain.product;

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

    private ProductColor color;

    private ProductTag tag;

    private long timestamp;

    public Product() {
        timestamp = System.currentTimeMillis();
    }
}
