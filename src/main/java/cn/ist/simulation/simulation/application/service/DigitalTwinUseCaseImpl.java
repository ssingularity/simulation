package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.CreateDigitalTwinUseCase;
import cn.ist.simulation.simulation.application.port.in.InputDigitalTwinUseCase;
import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StoreDigitalTwinPort;
import cn.ist.simulation.simulation.common.DefaultThreadFactory;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import cn.ist.simulation.simulation.domain.DT.DigitalTwinWorkingLoop;
import cn.ist.simulation.simulation.domain.product.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:46
 */
@Data
@RequiredArgsConstructor
public class DigitalTwinUseCaseImpl implements CreateDigitalTwinUseCase, InputDigitalTwinUseCase {
    final private FetchDigitalTwinPort fetchDigitalTwinPort;

    final private StoreDigitalTwinPort storeDigitalTwinPort;

    final private Map<Integer, DigitalTwinWorkingLoop> workingLoopMap = new ConcurrentHashMap<>();

    final private ExecutorService threadPool = Executors.newCachedThreadPool(new DefaultThreadFactory("DTWorkingLoop"));

    @Override
    public void createDigitalTwin(DigitalTwin digitalTwin, AbstractIndividualDTBehavior individualDTBehavior) {
        Integer index = digitalTwin.getIndex();
        if (fetchDigitalTwinPort.existsByIndex(index)) {
            throw new RuntimeException("Index为" + index + "的Digital Twin已存在");
        }
        storeDigitalTwinPort.storeDigitalTwin(digitalTwin);
        DigitalTwinWorkingLoop digitalTwinWorkingLoop = new DigitalTwinWorkingLoop(500, individualDTBehavior);
        workingLoopMap.put(index, digitalTwinWorkingLoop);
        threadPool.submit(digitalTwinWorkingLoop);
    }

    @Override
    public void handleNeighborInput(Integer index, DTInput dtInput) {
        Optional.ofNullable(workingLoopMap.get(index))
                .orElseThrow(() -> new RuntimeException("index 为" + index + "的WorkingLoop不存在"))
                .handleNeighborInput(dtInput);
    }

    @Override
    public void handlePTInput(Integer index, Product input) {
        Optional.ofNullable(workingLoopMap.get(index))
                .orElseThrow(() -> new RuntimeException("index 为" + index + "的WorkingLoop不存在"))
                .handlePTInput(input);
    }

    @Override
    public void handlePTOutput(Integer index, Product output) {
        Optional.ofNullable(workingLoopMap.get(index))
                .orElseThrow(() -> new RuntimeException("index 为" + index + "的WorkingLoop不存在"))
                .handlePTOutput(output);
    }

    public void destroy() {
        threadPool.shutdown();
    }
}
