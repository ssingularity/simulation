package cn.ist.simulation.simulation.domain.workingloop;

import cn.ist.simulation.simulation.application.port.behavior.TestIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.DT.DigitalTwinWorkingLoop;
import cn.ist.simulation.simulation.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 17:15
 */
class DigitalTwinWorkingLoopTest {
    DigitalTwinWorkingLoop digitalTwinWorkingLoop;

    TestIndividualDTBehavior testIndividualDTBehavior = new TestIndividualDTBehavior();

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

        digitalTwinWorkingLoop.handleInputFromNeighbor(dtInput);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
        digitalTwinWorkingLoop.handleInputFromPt(product);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(1);

        digitalTwinWorkingLoop.handleOutputFromPt(product);
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

        digitalTwinWorkingLoop.handleInputFromNeighbor(firstDtInput);
        digitalTwinWorkingLoop.handleInputFromNeighbor(laterDtInput);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);

        digitalTwinWorkingLoop.handleInputFromPt(laterProduct);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);

        digitalTwinWorkingLoop.handleInputFromPt(firstProduct);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(2);

        digitalTwinWorkingLoop.handleOutputFromPt(firstProduct);
        digitalTwinWorkingLoop.handleOutputFromPt(laterProduct);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
    }

    @Test
    public void testDigitalTwinWorkingLoopWithOutputFromPT() {
        Product product = new Product();
        product.setId("1");

        DTInput dtInput = new DTInput();
        dtInput.setProduct(product);

        digitalTwinWorkingLoop.handleInputFromNeighbor(dtInput);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
        digitalTwinWorkingLoop.handleInputFromPt(product);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(1);

        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(1);

        digitalTwinWorkingLoop.handleOutputFromPt(product);
        digitalTwinWorkingLoop.doTask();
        Assertions.assertThat(testIndividualDTBehavior.workingProduct).isEqualTo(0);
    }
}