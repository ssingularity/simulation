package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StoreDigitalTwinPort;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 14:16
 */
class DigitalTwinUseCaseImplTest {
    private FetchDigitalTwinPort fetchDigitalTwinPort = Mockito.mock(FetchDigitalTwinPort.class);

    private StoreDigitalTwinPort storeDigitalTwinPort = Mockito.mock(StoreDigitalTwinPort.class);

    AbstractIndividualDTBehavior individualDTBehavior = Mockito.mock(AbstractIndividualDTBehavior.class);

    private DigitalTwinUseCaseImpl digitalTwinUseCase;

    @BeforeEach
    void init() {
        this.digitalTwinUseCase = new DigitalTwinUseCaseImpl(fetchDigitalTwinPort, storeDigitalTwinPort);
    }

    @Test
    void createDigitalTwin() {
        Mockito.when(fetchDigitalTwinPort.existsByIndex(0)).thenReturn(false);
        this.digitalTwinUseCase.createDigitalTwin(new DigitalTwin(0), individualDTBehavior);
        Assertions.assertThat(this.digitalTwinUseCase.getWorkingLoopMap()).size().isEqualTo(1);
        Assertions.assertThat(this.digitalTwinUseCase.getWorkingLoopMap()).containsKeys(0);
    }

    @Test
    void createDigitalExistingTwin() {
        Mockito.when(fetchDigitalTwinPort.existsByIndex(0)).thenReturn(true);
        Assertions.assertThatThrownBy(() -> this.digitalTwinUseCase.createDigitalTwin(new DigitalTwin(0), individualDTBehavior)).isInstanceOf(RuntimeException.class);
    }
}