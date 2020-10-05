package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:20
 */
public interface FetchPhysicalTwinPort {
    Boolean existsByIndex(Integer index);

    PhysicalTwin fetchPhysicalTwin(Integer index);
}
