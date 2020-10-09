package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.DT.DTInput;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 16:28
 */
public interface CallDTFromNeighborApi {
    /**
     * 由DT的Neighbor调用，标识某个Product已经完成并且可以被目标DT处理
     * @param dtIndex 目标DT
     * @param dtInput DT的输入
     */
    void call(Integer dtIndex, DTInput dtInput);
}
