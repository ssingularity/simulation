package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.PhysicalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 15:52
 */
public interface StorePhysicalTwinPort {
    PhysicalTwin storePhysicalTwin(PhysicalTwin physicalTwin);
}
