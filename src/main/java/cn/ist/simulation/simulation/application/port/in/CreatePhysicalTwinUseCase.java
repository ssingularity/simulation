package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:34
 */
public interface CreatePhysicalTwinUseCase {
    /**
     * 创建对应的PT
     * @param physicalTwin 创建的PT
     * @param individualPTBehavior PT对应的个性化行为，通过Adapter层进行注入
     */
    void createPhysicalTwin(PhysicalTwin physicalTwin, AbstractIndividualPTBehavior individualPTBehavior);
}
