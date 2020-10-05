package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.application.port.behavior.TestIndividualPTBehavior;
import cn.ist.simulation.simulation.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/5 14:45
 */
class PhysicalTwinWorkingLoopTest {
    PhysicalTwinWorkingLoop physicalTwinWorkingLoop;

    TestIndividualPTBehavior testIndividualPTBehavior = new TestIndividualPTBehavior();

    @BeforeEach
    public void init() {
        physicalTwinWorkingLoop = new PhysicalTwinWorkingLoop(500L, testIndividualPTBehavior);
    }

    @Test
    void testPhysicalTwinWorkingLoop() {
        Product product = new Product();
        product.setId("1");

        physicalTwinWorkingLoop.handleInput(product);
        Assertions.assertThat(testIndividualPTBehavior.workingProduct).isEqualTo(0);

        physicalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualPTBehavior.workingProduct).isEqualTo(1);

        physicalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualPTBehavior.workingProduct).isEqualTo(0);
    }
}