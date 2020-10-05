package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.*;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;
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
        createDigitalTwinUseCase.createDigitalTwin(new DigitalTwin(index, url), createTwinCommand.getIndividualDTBehavior());
        createPhysicalTwinUseCase.createPhysicalTwin(new PhysicalTwin(index, url), createTwinCommand.getIndividualPTBehavior());
        return true;
    }

}
