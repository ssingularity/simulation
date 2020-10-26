package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.in.InputDigitalTwinUseCase;
import cn.ist.simulation.simulation.application.port.out.CallDTFromPTOutputApi;
import cn.ist.simulation.simulation.domain.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:31
 */
@Slf4j
@Component
public class CallDTFromPTOutputApiImpl implements CallDTFromPTOutputApi {
    @Autowired
    InputDigitalTwinUseCase inputDigitalTwinUseCase;

    @Override
    public void call(Integer dtIndex, Product output) {
        log.info("PT Call DT{} with Output Product {}", dtIndex, output);
        inputDigitalTwinUseCase.handlePTOutput(dtIndex, output);
    }
}
