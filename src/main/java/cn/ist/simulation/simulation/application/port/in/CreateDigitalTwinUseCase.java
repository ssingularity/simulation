package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.domain.DigitalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 15:01
 */
public interface CreateDigitalTwinUseCase {
    void createDigitalTwin(DigitalTwin digitalTwin);
}
