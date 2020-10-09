package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.CreateTopologyCommand;
import cn.ist.simulation.simulation.application.port.in.CreateTopologyUseCase;
import cn.ist.simulation.simulation.application.port.in.Node;
import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.FetchPhysicalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StoreDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StorePhysicalTwinPort;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 19:22
 */
class CreateTopologyUseCaseImplTest {
    FetchPhysicalTwinPort fetchPhysicalTwinPort = Mockito.mock(FetchPhysicalTwinPort.class);

    FetchDigitalTwinPort fetchDigitalTwinPort = Mockito.mock(FetchDigitalTwinPort.class);

    StorePhysicalTwinPort storePhysicalTwinPort = Mockito.mock(StorePhysicalTwinPort.class);

    StoreDigitalTwinPort storeDigitalTwinPort = Mockito.mock(StoreDigitalTwinPort.class);

    CreateTopologyUseCase createTopologyUseCase;

    PhysicalTwin physicalTwin;

    DigitalTwin digitalTwin;

    @BeforeEach
    void init() {
        this.createTopologyUseCase = new CreateTopologyUseCaseImpl(fetchPhysicalTwinPort, fetchDigitalTwinPort, storePhysicalTwinPort, storeDigitalTwinPort);
        physicalTwin = new PhysicalTwin(0);
        digitalTwin = new DigitalTwin(0);
        Mockito.when(fetchDigitalTwinPort.fetchDigitalTwin(0)).thenReturn(digitalTwin);
        Mockito.when(fetchPhysicalTwinPort.fetchPhysicalTwin(0)).thenReturn(physicalTwin);
        Mockito.when(fetchPhysicalTwinPort.existsByIndex(0)).thenReturn(true);
        Mockito.when(fetchDigitalTwinPort.existsByIndex(0)).thenReturn(true);
        Mockito.when(fetchPhysicalTwinPort.existsByIndex(1)).thenReturn(true);
        Mockito.when(fetchDigitalTwinPort.existsByIndex(1)).thenReturn(true);
    }

    @Test
    void testCommandThrowException() {
        Assertions.assertThatThrownBy(() -> new CreateTopologyCommand(null)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testPoint2SelfException() {
        CreateTopologyCommand createTopologyCommand = new CreateTopologyCommand(Arrays.asList(
                new Node(0, Arrays.asList(0), Arrays.asList(1))));
        Assertions.assertThatThrownBy(() -> this.createTopologyUseCase.createTopology(createTopologyCommand)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testPoint2NonExistTwinException() {
        CreateTopologyCommand createTopologyCommand = new CreateTopologyCommand(Arrays.asList(
                new Node(0, Arrays.asList(2), Arrays.asList(1))));
        Assertions.assertThatThrownBy(() -> this.createTopologyUseCase.createTopology(createTopologyCommand)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testCreateTopology() {
        CreateTopologyCommand createTopologyCommand = new CreateTopologyCommand(Arrays.asList(
                new Node(0, Arrays.asList(1), Arrays.asList(1))));
        this.createTopologyUseCase.createTopology(createTopologyCommand);
        Assertions.assertThat(physicalTwin.getInputNeighbor()).size().isEqualTo(1);
        Assertions.assertThat(physicalTwin.getOutputNeighbor()).size().isEqualTo(1);
        Assertions.assertThat(digitalTwin.getInputNeighbor()).size().isEqualTo(1);
        Assertions.assertThat(digitalTwin.getOutputNeighbor()).size().isEqualTo(1);
    }

}