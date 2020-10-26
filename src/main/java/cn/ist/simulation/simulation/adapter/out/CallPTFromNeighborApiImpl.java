package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.out.CallPTFromNeighborApi;
import cn.ist.simulation.simulation.domain.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:35
 */
@Slf4j
@Component
public class CallPTFromNeighborApiImpl implements CallPTFromNeighborApi {
    @Override
    public void call(Integer ptIndex, Product product) {
        log.info("Neighbor Call PT{} with Product {}", ptIndex, product);
    }
}
