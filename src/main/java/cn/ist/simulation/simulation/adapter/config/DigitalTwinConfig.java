package cn.ist.simulation.simulation.adapter.config;

import cn.ist.simulation.simulation.application.behavior.DT.DT1;
import cn.ist.simulation.simulation.application.port.out.CallDTFromNeighborApi;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:17
 */
@Configuration
public class DigitalTwinConfig {
    @Autowired
    CallDTFromNeighborApi callDTFromNeighborApi;

    @Bean(name = "DT1")
    public AbstractIndividualDTBehavior dt1() {
        return new DT1(1, 1000L, callDTFromNeighborApi);
    }
}
