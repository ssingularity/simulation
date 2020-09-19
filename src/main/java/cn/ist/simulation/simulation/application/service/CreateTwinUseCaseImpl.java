package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.*;
import cn.ist.simulation.simulation.domain.DigitalTwin;
import cn.ist.simulation.simulation.domain.PhysicalTwin;
import lombok.AllArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:48
 */
@AllArgsConstructor
public class CreateTwinUseCaseImpl implements CreateTwinUseCase {
    CreateDigitalTwinUseCase createDigitalTwinUseCase;

    CreatePhysicalTwinUseCase createPhysicalTwinUseCase;

    @Override
    public Boolean createTwin(CreateTwinCommand createTwinCommand) {
        Integer index = createTwinCommand.getIndex();
        String url = createTwinCommand.getUrl();
        createDigitalTwinUseCase.createDigitalTwin(new DigitalTwin(index, url));
        createPhysicalTwinUseCase.createPhysicalTwin(new PhysicalTwin(index, url));
        return true;
    }

}
