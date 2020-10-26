package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.application.port.behavior.TestIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/5 14:45
 */
class DigitalTwinWorkingLoopTest {
    DigitalTwinWorkingLoop digitalTwinWorkingLoop;

    TestIndividualDTBehavior testIndividualDTBehavior = new TestIndividualDTBehavior(0, 1000L);

    @BeforeEach
    void init() {
        digitalTwinWorkingLoop = new DigitalTwinWorkingLoop(1000L, testIndividualDTBehavior);
    }

    @Test
    public void testDigitalTwinWorkingLoop() {
        Product product = new Product();
        product.setId("1");

        DTInput dtInput = new DTInput();
        dtInput.setProduct(product);

        digitalTwinWorkingLoop.handleNeighborInput(dtInput);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
        digitalTwinWorkingLoop.handlePTInput(product);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(1);

        digitalTwinWorkingLoop.handlePTOutput(product);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
    }

    @Test
    public void testDigitalTwinWorkingLoopWithCausality() {
        Product firstProduct = new Product();
        firstProduct.setId("1");
        Product laterProduct = new Product();
        laterProduct.setId("2");

        DTInput firstDtInput = new DTInput();
        firstDtInput.setProduct(firstProduct);
        DTInput laterDtInput = new DTInput();
        laterDtInput.setProduct(laterProduct);

        digitalTwinWorkingLoop.handleNeighborInput(firstDtInput);
        digitalTwinWorkingLoop.handleNeighborInput(laterDtInput);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);

        digitalTwinWorkingLoop.handlePTInput(laterProduct);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);

        digitalTwinWorkingLoop.handlePTInput(firstProduct);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(2);

        digitalTwinWorkingLoop.handlePTOutput(firstProduct);
        digitalTwinWorkingLoop.handlePTOutput(laterProduct);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
    }

    @Test
    public void testDigitalTwinWorkingLoopWithOutputFromPT() {
        Product product = new Product();
        product.setId("1");

        DTInput dtInput = new DTInput();
        dtInput.setProduct(product);

        digitalTwinWorkingLoop.handleNeighborInput(dtInput);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
        digitalTwinWorkingLoop.handlePTInput(product);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(1);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(1);

        digitalTwinWorkingLoop.handlePTOutput(product);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
    }

}