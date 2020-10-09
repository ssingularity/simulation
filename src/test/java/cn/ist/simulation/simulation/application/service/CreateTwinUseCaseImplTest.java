package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.*;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 14:44
 */
class CreateTwinUseCaseImplTest {
    CreateDigitalTwinUseCase createDigitalTwinUseCase = Mockito.mock(CreateDigitalTwinUseCase.class);

    CreatePhysicalTwinUseCase createPhysicalTwinUseCase = Mockito.mock(CreatePhysicalTwinUseCase.class);

    AbstractIndividualDTBehavior individualDTBehavior = Mockito.mock(AbstractIndividualDTBehavior.class);

    AbstractIndividualPTBehavior individualPTBehavior = Mockito.mock(AbstractIndividualPTBehavior.class);

    CreateTwinUseCaseImpl createTwinUseCase;

    @BeforeEach
    public void init() {
        this.createTwinUseCase = new CreateTwinUseCaseImpl(createDigitalTwinUseCase, createPhysicalTwinUseCase);
    }

    @Test
    void createTwin() {
        this.createTwinUseCase.createTwin(new CreateTwinCommand(0, individualDTBehavior, individualPTBehavior));
        Mockito.verify(createDigitalTwinUseCase).createDigitalTwin(new DigitalTwin(0), individualDTBehavior);
        Mockito.verify(createPhysicalTwinUseCase).createPhysicalTwin(new PhysicalTwin(0), individualPTBehavior);
    }
}