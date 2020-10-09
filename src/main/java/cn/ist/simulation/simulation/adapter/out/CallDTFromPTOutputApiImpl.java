package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.out.CallDTFromPTOutputApi;
import cn.ist.simulation.simulation.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:31
 */
@Slf4j
@Component
public class CallDTFromPTOutputApiImpl implements CallDTFromPTOutputApi {
    @Override
    public void call(Integer dtIndex, Product output) {
        log.info("PT Call DT{} with Output Product {}", dtIndex, output);
    }
}
