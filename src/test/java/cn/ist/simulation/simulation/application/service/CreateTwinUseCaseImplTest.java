package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.*;
import cn.ist.simulation.simulation.domain.DigitalTwin;
import cn.ist.simulation.simulation.domain.PhysicalTwin;
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

    CreateTwinUseCaseImpl createTwinUseCase;

    @BeforeEach
    public void init() {
        this.createTwinUseCase = new CreateTwinUseCaseImpl(createDigitalTwinUseCase, createPhysicalTwinUseCase);
    }

    @Test
    void createTwin() {
        this.createTwinUseCase.createTwin(new CreateTwinCommand(0, ""));
        Mockito.verify(createDigitalTwinUseCase).createDigitalTwin(new DigitalTwin(0, ""));
        Mockito.verify(createPhysicalTwinUseCase).createPhysicalTwin(new PhysicalTwin(0, ""));
    }
}