package cn.ist.simulation.simulation.application.port.out;

import cn.ist.simulation.simulation.domain.DT.DigitalTwin;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 15:53
 */
public interface StoreDigitalTwinPort {
    DigitalTwin storeDigitalTwin(DigitalTwin digitalTwin);
}
