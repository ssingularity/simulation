package cn.ist.simulation.simulation.application.behavior.digitaltwin;

import cn.ist.simulation.simulation.application.port.out.CallDTFromNeighborApi;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.DT.DTTask;
import cn.ist.simulation.simulation.domain.Product;
import cn.ist.simulation.simulation.domain.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/23 14:55
 */
@Slf4j
@RequiredArgsConstructor
public class DT1 extends AbstractIndividualDTBehavior {
    private final Integer index;

    private final CallDTFromNeighborApi callDTFromNeighborApi;

    private final Map<Integer, Integer> V = new HashMap<>();

    private Boolean primary = true;

    @Override
    protected void preStartTask(DTTask dtTask) {
        log.info("DT{} Start Task {}", index, dtTask.getDtInput().getId());
    }

    @Override
    public void doOutput(Product product) {
        product.setSender("DT" + index);
        DTInput dtInput = new DTInput(product, V);
        //TODO 更新V
        if (product.getType() == ProductType.TYPE1 || (product.getType() == ProductType.TYPE2 && this.primary)) {
            callDTFromNeighborApi.call(3, dtInput);
        }
        else {
            callDTFromNeighborApi.call(4, dtInput);
        }
    }

    @Override
    protected long getTaskTime() {
        return 1000L;
    }
}
