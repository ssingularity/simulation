package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.out.CallDTFromNeighborApi;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:35
 */
@Slf4j
@Component
public class CallDTFromNeighborApiImpl implements CallDTFromNeighborApi {
    @Override
    public void call(Integer dtIndex, DTInput dtInput) {
        log.info("Neighbor Call DT{} with Product {}", dtIndex, dtInput.getProduct());
    }
}
