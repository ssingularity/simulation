package cn.ist.simulation.simulation.application.port.behavior;

import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTTask;
import cn.ist.simulation.simulation.domain.Product;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 17:10
 */
@Slf4j
public class TestIndividualDTBehavior extends AbstractIndividualDTBehavior {
    public int workingProduct = 0;

    @Override
    protected void preStartTask(DTTask dtTask) {
        this.workingProduct++;
    }

    @Override
    protected long getTaskTime() {
        return 1000L;
    }

    @Override
    public void doOutput(Product product) {
        this.workingProduct--;
    }
}
