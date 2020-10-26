package cn.ist.simulation.simulation.application.port.behavior;

import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTTask;
import cn.ist.simulation.simulation.domain.DT.Instruction;
import cn.ist.simulation.simulation.domain.product.Product;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 17:10
 */
@Slf4j
public class TestIndividualDTBehavior extends AbstractIndividualDTBehavior {
    public int workingProduct = 0;

    public TestIndividualDTBehavior(Integer index, Long taskTime) {
        super(index, taskTime);
    }

    @Override
    protected void preStartTask(DTTask dtTask) {
        this.workingProduct++;
    }

    @Override
    protected void handleInstruction(Instruction instruction) {

    }

    @Override
    public void doOutput(Product product) {
        this.workingProduct--;
    }
}
