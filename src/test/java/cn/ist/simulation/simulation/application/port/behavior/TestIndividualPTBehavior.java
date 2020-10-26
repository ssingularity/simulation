package cn.ist.simulation.simulation.application.port.behavior;

import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import cn.ist.simulation.simulation.domain.PT.PTTask;
import cn.ist.simulation.simulation.domain.product.Product;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/5 14:44
 */
public class TestIndividualPTBehavior extends AbstractIndividualPTBehavior {
    public int workingProduct = 0;

    public TestIndividualPTBehavior(Integer index, Long taskTime) {
        super(index, taskTime);
    }

    @Override
    protected void preStartTask(PTTask ptTask) {
        this.workingProduct++;
    }

    @Override
    public void doOutput(Product product) {
        this.workingProduct--;
    }
}
