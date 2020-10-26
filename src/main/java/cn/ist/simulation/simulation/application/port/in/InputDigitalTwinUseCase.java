package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.product.Product;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 15:17
 */
public interface InputDigitalTwinUseCase {
    void handleNeighborInput(Integer index, DTInput dtInput);

    void handlePTInput(Integer index, Product input);

    void handlePTOutput(Integer index, Product output);
}
