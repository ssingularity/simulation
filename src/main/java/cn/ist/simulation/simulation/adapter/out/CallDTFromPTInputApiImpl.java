package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.out.CallDTFromPTInputApi;
import cn.ist.simulation.simulation.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:33
 */
@Slf4j
@Component
public class CallDTFromPTInputApiImpl implements CallDTFromPTInputApi {
    @Override
    public void call(Integer dtIndex, Product input) {
        log.info("PT Call DT{} with Input Product {}", dtIndex, input);
    }
}
