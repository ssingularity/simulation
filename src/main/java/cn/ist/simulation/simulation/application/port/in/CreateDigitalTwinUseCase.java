package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.domain.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DigitalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 15:01
 */
public interface CreateDigitalTwinUseCase {
    /**
     * 创建对应的DT
     * @param digitalTwin 创建的DT
     * @param individualDTBehavior DT对应的个性化行为，通过Adapter层进行注入
     */
    void createDigitalTwin(DigitalTwin digitalTwin, AbstractIndividualDTBehavior individualDTBehavior);
}
