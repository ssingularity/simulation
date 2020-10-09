package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.Product;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 16:30
 */
public interface CallDTFromPTOutputApi {
    /**
     * 由DT的PT调用，标识某个Product已经被PT处理完成从而可以被目标DT标识为完成
     * @param dtIndex 目标DT
     * @param output 处理完成的Product
     */
    void call(Integer dtIndex, Product output);
}
