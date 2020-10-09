package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.CreateDigitalTwinUseCase;
import cn.ist.simulation.simulation.application.port.in.CreatePhysicalTwinUseCase;
import cn.ist.simulation.simulation.application.port.in.CreateTwinCommand;
import cn.ist.simulation.simulation.application.port.in.CreateTwinUseCase;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;
import lombok.RequiredArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:48
 */
@RequiredArgsConstructor
public class CreateTwinUseCaseImpl implements CreateTwinUseCase {
    final private CreateDigitalTwinUseCase createDigitalTwinUseCase;

    final private CreatePhysicalTwinUseCase createPhysicalTwinUseCase;

    @Override
    public Boolean createTwin(CreateTwinCommand createTwinCommand) {
        Integer index = createTwinCommand.getIndex();
        createDigitalTwinUseCase.createDigitalTwin(new DigitalTwin(index), createTwinCommand.getIndividualDTBehavior());
        createPhysicalTwinUseCase.createPhysicalTwin(new PhysicalTwin(index), createTwinCommand.getIndividualPTBehavior());
        return true;
    }

}
