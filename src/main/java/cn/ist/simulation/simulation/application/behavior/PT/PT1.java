package cn.ist.simulation.simulation.application.behavior.PT;

import cn.ist.simulation.simulation.application.port.out.CallDTFromPTInputApi;
import cn.ist.simulation.simulation.application.port.out.CallDTFromPTOutputApi;
import cn.ist.simulation.simulation.application.port.out.CallPTFromNeighborApi;
import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import cn.ist.simulation.simulation.domain.PT.PTTask;
import cn.ist.simulation.simulation.domain.Product;
import cn.ist.simulation.simulation.domain.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/5 15:21
 */
@Slf4j
@RequiredArgsConstructor
public class PT1 extends AbstractIndividualPTBehavior {
    private final Integer index;

    private final CallDTFromPTInputApi callDTFromPTInputApi;

    private final CallDTFromPTOutputApi callDTFromPTOutputApi;

    private final CallPTFromNeighborApi callPTFromNeighborApi;

    private Boolean primary = true;

    @Override
    protected void preStartTask(PTTask ptTask) {
        log.info("PT{} start Task {}", index, ptTask.getProduct().getId());
        callDTFromPTInputApi.call(index, ptTask.getProduct());
    }

    @Override
    public void doOutput(Product product) {
        product.setSender("PT" + index);
        product.setTimestamp(System.currentTimeMillis());
        if (product.getType() == ProductType.TYPE1 || (product.getType() == ProductType.TYPE2 && this.primary)) {
            callPTFromNeighborApi.call(3, product);
        }
        else {
            callPTFromNeighborApi.call(4, product);
        }
        callDTFromPTOutputApi.call(index, product);
    }

    @Override
    protected long getTaskTime() {
        return 1000L;
    }
}
