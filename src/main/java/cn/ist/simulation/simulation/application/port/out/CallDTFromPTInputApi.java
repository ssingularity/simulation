package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.Product;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 16:30
 */
public interface CallDTFromPTInputApi {
    /**
     * 由DT的PT调用，标识某个Product已经被PT接收并且可以开始被目标DT处理
     * @param dtIndex 目标DT
     * @param input 待处理的Prodcut
     */
    void call(Integer dtIndex, Product input);
}
