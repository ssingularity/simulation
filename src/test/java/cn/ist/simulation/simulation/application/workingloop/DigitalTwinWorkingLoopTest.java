package cn.ist.simulation.simulation.application.workingloop;

import cn.ist.simulation.simulation.application.port.behavior.TestIndividualDTBehavior;
import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.domain.DTInput;
import cn.ist.simulation.simulation.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 17:15
 */
class DigitalTwinWorkingLoopTest {
    DigitalTwinWorkingLoop digitalTwinWorkingLoop;

    TestIndividualDTBehavior testIndividualDTBehavior = new TestIndividualDTBehavior();

    @BeforeEach
    public void init() {
        digitalTwinWorkingLoop = new DigitalTwinWorkingLoop(1000L, Mockito.mock(FetchDigitalTwinPort.class), 0, testIndividualDTBehavior);
    }

    @Test
    public void testDigitalTwinWorkingLoop() {
        DTInput dtInput = new DTInput();
        dtInput.setId("1");
        digitalTwinWorkingLoop.handleInputFromNeighbor(dtInput);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.started).isEqualTo(false);
        Assertions.assertThat(testIndividualDTBehavior.finished).isEqualTo(false);

        Product product = new Product();
        product.setId("1");
        digitalTwinWorkingLoop.handleInputFromPt(product);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.started).isEqualTo(true);
        Assertions.assertThat(testIndividualDTBehavior.finished).isEqualTo(false);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.started).isEqualTo(true);
        Assertions.assertThat(testIndividualDTBehavior.finished).isEqualTo(true);
    }
}