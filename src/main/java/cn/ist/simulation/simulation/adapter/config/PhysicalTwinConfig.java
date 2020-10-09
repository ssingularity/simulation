package cn.ist.simulation.simulation.adapter.config;

import cn.ist.simulation.simulation.application.behavior.PT.PT1;
import cn.ist.simulation.simulation.application.port.out.CallDTFromPTInputApi;
import cn.ist.simulation.simulation.application.port.out.CallDTFromPTOutputApi;
import cn.ist.simulation.simulation.application.port.out.CallPTFromNeighborApi;
import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:18
 */
@Configuration
public class PhysicalTwinConfig {
    @Autowired
    CallDTFromPTInputApi callDTFromPTInputApi;

    @Autowired
    CallDTFromPTOutputApi callDTFromPTOutputApi;

    @Autowired
    CallPTFromNeighborApi callPTFromNeighborApi;

    @Bean(name = "PT1")
    public AbstractIndividualPTBehavior pt1() {
        return new PT1(1, 1000L, callDTFromPTInputApi, callDTFromPTOutputApi, callPTFromNeighborApi);
    }
}
