package cn.ist.simulation.simulation.application.port.behavior;

import cn.ist.simulation.simulation.domain.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DTInput;
import cn.ist.simulation.simulation.domain.DigitalTwin;
import cn.ist.simulation.simulation.domain.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 17:10
 */
@Slf4j
public class TestIndividualDTBehavior extends AbstractIndividualDTBehavior {
    public boolean started = false;

    public boolean finished = false;

    @Override
    protected void startTask(Task task) {
        task.setRemainingTime(1000L);
        this.started = true;
    }

    @Override
    public void doOutput(DigitalTwin dt, DTInput product) {
        this.finished = true;
    }
}
