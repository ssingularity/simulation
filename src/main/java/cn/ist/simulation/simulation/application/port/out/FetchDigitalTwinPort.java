package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.DigitalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:20
 */
public interface FetchDigitalTwinPort {
    DigitalTwin fetchDigitalTwin(Integer index);

    Boolean existsByIndex(Integer index);
}
