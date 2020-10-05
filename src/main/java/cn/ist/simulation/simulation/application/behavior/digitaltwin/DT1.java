package cn.ist.simulation.simulation.application.behavior.digitaltwin;

import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.DT.DTTask;
import lombok.RequiredArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/23 14:55
 */
@RequiredArgsConstructor
public class DT1 extends AbstractIndividualDTBehavior {
    private final FetchDigitalTwinPort fetchDigitalTwinPort;

    private final Integer index;

    @Override
    protected void preStartTask(DTTask dtTask) {

    }

    @Override
    public void doOutput(DTInput product) {

    }

    @Override
    protected long getTaskTime() {
        return 1000L;
    }
}
