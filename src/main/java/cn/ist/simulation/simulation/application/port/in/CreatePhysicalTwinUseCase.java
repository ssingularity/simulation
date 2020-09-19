package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.domain.PhysicalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:34
 */
public interface CreatePhysicalTwinUseCase {
    void createPhysicalTwin(PhysicalTwin physicalTwin);
}
