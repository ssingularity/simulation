package cn.ist.simulation.simulation.domain;

import lombok.Data;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 14:11
 */
@Data
public class Neighbor {
    final Integer id;

    public Neighbor(Integer id) {
        this.id = id;
    }
}
