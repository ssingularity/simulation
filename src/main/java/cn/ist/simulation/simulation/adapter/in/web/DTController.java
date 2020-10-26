package cn.ist.simulation.simulation.adapter.in.web;

import cn.ist.simulation.simulation.application.port.in.InputDigitalTwinUseCase;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:34
 */
@RestController
public class DTController {
    @Autowired
    InputDigitalTwinUseCase inputDigitalTwinUseCase;

    @PostMapping("/DT/{index}/neighborInput")
    public void handleNeighborInput(@PathVariable(name = "index") Integer index,
                                    @RequestBody DTInput dtInput) {
        inputDigitalTwinUseCase.handleNeighborInput(index, dtInput);
    }

    @PostMapping("/DT/{index}/ptInput")
    public void handlePTInput(@PathVariable(name = "index") Integer index,
                              @RequestBody Product product) {
        inputDigitalTwinUseCase.handlePTInput(index, product);
    }

    @PostMapping("/DT/{index}/ptOutput")
    public void handlePTOutput(@PathVariable(name = "index") Integer index,
                               @RequestBody Product product) {
        inputDigitalTwinUseCase.handlePTOutput(index, product);
    }
}
