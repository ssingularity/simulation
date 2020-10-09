package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.Product;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 16:33
 */
public interface CallPTFromNeighborApi {
    /**
     * 由PT调用，标识某个Product已经被PT处理完成从而通知其Neighbor PT
     * @param ptIndex 目标PT
     * @param product 处理完成的Product
     */
    void call(Integer ptIndex, Product product);
}
